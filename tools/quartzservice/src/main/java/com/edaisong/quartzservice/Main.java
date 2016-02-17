package com.edaisong.quartzservice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.edaisong.toolsapi.common.QuartzManager;
import com.edaisong.toolscore.util.HttpUtil;
import com.edaisong.toolscore.util.JsonUtil;
import com.edaisong.toolsentity.QuartzServiceModel;
import com.edaisong.toolsentity.domain.QuartzHttpModel;

public class Main {
	private static List<QuartzServiceModel> oldListData = null;
	private static String oldVersionCode = "";
	public static void main(String[] args) throws Exception {
		/* 加载定时任务 */
		writePID();// 生成PID
		// writeLog();
		init();
	}

	private static void init() {
		Thread dThread = new Thread(new Runnable() {
			@Override
			public void run() {
				String configPath = Helper.getProperty("quartz.config.url");
				String appSource = Helper.getProperty("appSource");
				String configRate = Helper.getProperty("configRate");
				long sleepMillis = Integer.parseInt(configRate) * 1000;
				while (true) {
					try {
						refreshDo(configPath, appSource);
						Thread.sleep(sleepMillis);
					} catch (Exception e) {
						Helper.sendMail("每隔"+(sleepMillis/1000)+"秒更新quartz服务时异常:",e);
					}
				}
			}
		});
		dThread.setDaemon(false);
		dThread.start();
	}


	private static void refreshDo(String configPath, String appSource) {

		try {
			// 发送post请求，获取最新的服务配置信息
			String param="appSource="+appSource+"&versionCode="+oldVersionCode;
			String result = HttpUtil.sendPost(configPath, param);
			if (result == null || result.isEmpty()) {
				return;
			}
			QuartzHttpModel httpModel = JsonUtil.str2obj(result,QuartzHttpModel.class);
			if (httpModel==null||httpModel.getListData()==null) {
				return;
			}
			oldVersionCode=httpModel.getVersionCode();
			List<QuartzServiceModel> newListData=httpModel.getListData();
			// 如果是第一次启动，就新增服务
			if (oldListData == null) {
				for (QuartzServiceModel quartzServiceModel : newListData) {
					QuartzManager.addJob(quartzServiceModel.getReqUrl(),ChildJob.class, quartzServiceModel.getExecTime());
				}
			} else {
				List<Integer> oldIds = oldListData.stream().map(t -> t.getId()).collect(Collectors.toList());
				List<Integer> newIds = newListData.stream().map(t -> t.getId()).collect(Collectors.toList());
				List<Integer> addNewIds = new ArrayList<Integer>(newIds);
				addNewIds.removeAll(oldIds);// addNewIds中剩余的是新增的服务
				for (Integer newId : addNewIds) {
					QuartzServiceModel newModel = newListData.stream().filter(m -> m.getId() == newId).findFirst().get();
					QuartzManager.addJob(newModel.getReqUrl(), ChildJob.class,newModel.getExecTime());
				}
				List<Integer> removeOldIds = new ArrayList<Integer>(oldIds);
				removeOldIds.removeAll(newIds);// removeOldIds中剩余的是需要停止的服务
				for (Integer oldId : removeOldIds) {
					QuartzServiceModel oldModel = oldListData.stream().filter(m -> m.getId() == oldId).findFirst().get();
					QuartzManager.removeJob(oldModel.getReqUrl());
				}
				List<Integer> updateOldIds = new ArrayList<Integer>(oldIds);
				updateOldIds.retainAll(newIds);// updateOldIds中剩余的是交集，即可能需要重新设置的服务
				for (Integer oldId : updateOldIds) {
					QuartzServiceModel oldModel = oldListData.stream().filter(m -> m.getId() == oldId).findFirst().get();
					QuartzServiceModel newModel = newListData.stream().filter(m -> m.getId() == oldId).findFirst().get();
					// 请求地址或执行频率变更了，则需要重置服务
					if (!oldModel.getReqUrl().equals(newModel.getReqUrl())
							|| !oldModel.getExecTime().equals(newModel.getExecTime())) {
						QuartzManager.removeJob(oldModel.getReqUrl());
						QuartzManager.addJob(newModel.getReqUrl(),ChildJob.class, newModel.getExecTime());
					}

				}
			}
			oldListData = newListData;
		} catch (Exception e) {
			Helper.sendMail("根据"+configPath+"获取配置更新quartz服务时异常:",e);
		}

	}

	private static void writePID() throws IOException {
		File f = new File("device.pid");
		OutputStreamWriter writer = new OutputStreamWriter(
				new FileOutputStream(f));
		String processName = ManagementFactory.getRuntimeMXBean().getName();
		String pid = processName.substring(0, processName.indexOf("@"));
		writer.write(String.valueOf(pid));
		writer.flush();
		writer.close();
	}

}
