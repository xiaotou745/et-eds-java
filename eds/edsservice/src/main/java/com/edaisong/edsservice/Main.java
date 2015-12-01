package com.edaisong.edsservice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.management.ManagementFactory;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.edaisong.core.util.QuartzManager;
import com.edaisong.edsservice.service.MainJob;

/**
 * quartz服务 2015年11月5日 11:03:43 窦海超
 * **/

public class Main {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		/* 加载定时任务 */
		writePID();// 生成PID
		SimpleDateFormat DateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date d = new Date();
		String returnstr = DateFormat.format(d);

		MainJob job = new MainJob();
		String job_name = "11";
		try {
			System.out.println(returnstr + "【系统启动】");
			QuartzManager.addJob(job_name, job, "0/2 * * * * ?"); // 每2秒钟执行一次

			// Thread.sleep(10000);
			// System.out.println("【修改时间】");
			// QuartzManager.modifyJobTime(job_name,"0/10 * * * * ?");
			// Thread.sleep(20000);
			// System.out.println("【移除定时】");
			// QuartzManager.removeJob(job_name);
			// Thread.sleep(10000);
			//
			// System.out.println("/n【添加定时任务】");
			// QuartzManager.addJob(job_name,job,"0/5 * * * * ?");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// new ClassPathXmlApplicationContext("applicationContext.xml");
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
