package com.edaisong.edsservice.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Service;

public class QuartzJob implements Job {

	/**
	 * 任务调度测试方法 窦海超 2015年12月3日 13:07:54
	 * */
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		 System.out.println(new
		 SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+
		 "★★★★★★★★★★★");
	
	}

}
