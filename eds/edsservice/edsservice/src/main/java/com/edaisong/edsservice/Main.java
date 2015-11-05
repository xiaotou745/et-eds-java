package com.edaisong.edsservice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.management.ManagementFactory;

import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * quartz服务
 * 2015年11月5日 11:03:43
 * 窦海超 
 * **/

public class Main {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		/* 加载定时任务 */
		writePID();//生成PID
		new ClassPathXmlApplicationContext("applicationContext.xml");
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
