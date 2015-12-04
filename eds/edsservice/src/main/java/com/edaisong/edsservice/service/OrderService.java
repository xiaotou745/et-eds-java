package com.edaisong.edsservice.service;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Service;
import com.edaisong.api.service.impl.TestDouService;
import com.edaisong.edsservice.Main;

@Service
public class OrderService implements Job {
	
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		TestDouService service = Main.contentApp.getBean(TestDouService.class);
		service.insert("1");
	}

}
