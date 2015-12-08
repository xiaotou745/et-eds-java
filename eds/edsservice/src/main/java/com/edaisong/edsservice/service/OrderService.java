package com.edaisong.edsservice.service;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.edaisong.api.service.impl.TestDouService;

@Service
public class OrderService implements Job {
	public static ApplicationContext contentApp = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		TestDouService service = contentApp.getBean(TestDouService.class);
		service.insert("1");
	}

}
