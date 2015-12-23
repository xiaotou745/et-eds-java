package com.edaisong.mqservice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.management.ManagementFactory;

//import javax.sound.midi.Receiver;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.edaisong.api.activemq.ActiveMqService;
import com.edaisong.core.util.JsonUtil;
import com.edaisong.core.util.SystemUtils;
import com.edaisong.entity.domain.ActionLog;

public class Main {

	private static ApplicationContext contentApp = new ClassPathXmlApplicationContext(
			"applicationContext.xml");

	public static void main(String[] args) throws Exception {
		/* 加载定时任务 */
		writePID();// 生成PID
//		writeLog();
	
	}

	/**
	 * 该方法做日志发布测试时用的
	 * 
	 * @date 2015年12月14日 14:49:21
	 * @author haichao
	 * */
	private static void writeLog() {
		ActiveMqService service = contentApp.getBean(ActiveMqService.class);
		ActionLog logEngity = new ActionLog();
		logEngity.setUserID(-1);
		logEngity.setUserName("");
		logEngity.setRequestType(0);
		logEngity.setClientIp("8.8.8.8");
		logEngity.setSourceSys("taobaoopenapi");
		logEngity.setRequestUrl("baidu.com");
		logEngity.setParam("param");
		logEngity.setDecryptMsg("msg");
		logEngity.setContentType("contentType");
		logEngity.setHeader("header.toString()");
		logEngity.setRequestMethod("httpRequestMethod");
		logEngity.setMethodName("methodName.toString()");
		logEngity.setResultJson("resultJson");
		logEngity.setAppServer("appServerIP");
		logEngity.setException("exceptionMsg");
		logEngity.setStackTrace("stackTrace");
		logEngity.setExecuteTime((long) 3412312);
		logEngity.setRequestTime("2015-12-14");
		logEngity.setRequestEndTime("2015-12-15");
		String jsonMsg = JsonUtil.obj2string(logEngity);
		service.asynSendMessage("test",jsonMsg);
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
