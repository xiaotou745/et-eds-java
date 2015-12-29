package com.edaisong.mqservice.impl;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.edaisong.api.activemq.ActiveMqService;

public class MqJob implements Job {

	public static ApplicationContext contentApp = new ClassPathXmlApplicationContext(
			"conf/core/dev-context.xml");

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		ActiveMqService service = contentApp.getBean(ActiveMqService.class);

//		service.asynSendMessage("test","123");
		// 这里实现服务监听1分钟一次，如果mq停止了就发邮件，然后重启mq
		System.out.println("输出值");
	}

}
