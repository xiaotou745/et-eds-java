package com.edaisong.edsservice.service;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.service.inter.ITestService;
import com.edaisong.core.util.SpringBeanHelper;

@Service
public class OrderService implements Job {
	@Autowired
	ITestService testService;

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		ITestService testService = (ITestService) SpringBeanHelper
				.getCustomBeanByType(ITestService.class);

		// TestService service=new OrderService();
		testService.insert("1");// new
								// SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new
								// Date()) +
	}

}
