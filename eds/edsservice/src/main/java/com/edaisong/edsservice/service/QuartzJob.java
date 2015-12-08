package com.edaisong.edsservice.service;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.edaisong.api.service.impl.QuartzService;
import com.edaisong.api.service.impl.TestDouService;
import com.edaisong.api.service.inter.IQuartzService;
import com.edaisong.core.util.QuartzManager;
import com.edaisong.entity.QuartzServiceModel;

public class QuartzJob implements Job {
	public static ApplicationContext contentApp = new ClassPathXmlApplicationContext(
			"applicationContext.xml");

	@Autowired
	IQuartzService quartzService;

	/**
	 * 任务调度测试方法 窦海超 2015年12月3日 13:07:54
	 * */
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {

		QuartzService service = contentApp.getBean(QuartzService.class);
		List<QuartzServiceModel> list = service.query();
		for (QuartzServiceModel item : list) {
			mainJob(item);
		}
	}

	/**
	 * @author haichao
	 * @date 2015年12月8日 15:00:16 子线程
	 * */
	private void mainJob(QuartzServiceModel item) {
		try {
			int qzState = QuartzManager.checkJob(item.getName());// 获取调度状态
			int dbStatus = item.getIsStart();// 0关闭，1开启
			String jobName = item.getName();
			if (dbStatus == 0 && qzState == 0) {
				QuartzManager.removeJob(jobName);// 判断quartz和数据库里的是否相同，不同要把调度里的关闭掉
				return;
			}
			if (dbStatus == 1 && qzState == -1) {
				URL url = new URL("file:" + item.getFilePath());
				@SuppressWarnings("resource")
				URLClassLoader myClassLoader = new URLClassLoader(
						new URL[] { url });
				Class<?> clazz = myClassLoader.loadClass(item.getPackages());
				QuartzManager.addJob(item.getName(), clazz, item.getExecTime());// 如果库里是开启，本地没开启就新增服务
				return;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
