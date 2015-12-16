package com.edaisong.edsservice.service;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.print.attribute.standard.JobName;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.edaisong.api.common.QuartzManager;
import com.edaisong.core.util.JsonUtil;
import com.edaisong.edsservice.Main;
import com.edaisong.entity.QuartzServiceModel;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.PagedQuartzServiceReq;


public class QuartzJob implements Job {
	private static String oldConfigs="";
	/**
	 * 任务调度测试方法 窦海超 2015年12月3日 13:07:54
	 * */
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		PagedQuartzServiceReq req=new PagedQuartzServiceReq();
		PagedResponse<QuartzServiceModel> list = Main.service.pagedQuery(req);
		String newConfigs=JsonUtil.obj2string(list);
		if (oldConfigs.equals("")) {
			oldConfigs=newConfigs;
		}else {
			if (oldConfigs.equals(newConfigs)) {
				return;
			}
		}
		for (QuartzServiceModel item : list.getResultList()) {
			mainJob(item);
		}
	}

	/**
	 * @author haichao
	 * @date 2015年12月8日 15:00:16 子线程
	 * */
	private void mainJob(QuartzServiceModel item) {
		try {
			String jobName = item.getBeanName();
			
			int qzState = QuartzManager.checkJob(jobName);// 获取调度状态
			int dbStatus = item.getIsStart();// 0关闭，1开启

			if (dbStatus == 0 && qzState == 0) {
				QuartzManager.removeJob(jobName);// 判断quartz和数据库里的是否相同，不同要把调度里的关闭掉
				return;
			}
			
			if (dbStatus == 1 && qzState == -1) {
				QuartzManager.addJob(jobName, ChildJob.class, item.getExecTime());// 如果库里是开启，本地没开启就新增服务
				return;
			}
			if (dbStatus == 1 && qzState != -1) {
				//如果库里是开启的，且本地已经添加过，则修改执行频率（方法内部会判断是否发生了变更）
				QuartzManager.modifyJobTime(jobName, item.getExecTime());
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
