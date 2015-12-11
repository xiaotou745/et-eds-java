package com.edaisong.api.quartzservice;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.edaisong.api.service.impl.TestDouService;

public class TestJob implements Job {
	public static ApplicationContext contentApp = new ClassPathXmlApplicationContext(
			"conf/core/dev-context.xml");
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		TestDouService service = contentApp.getBean(TestDouService.class);
		service.insert("1");
	}

}
