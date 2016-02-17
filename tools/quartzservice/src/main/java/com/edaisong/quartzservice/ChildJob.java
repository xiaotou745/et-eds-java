package com.edaisong.quartzservice;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

import com.edaisong.toolscore.util.HttpUtil;


public class ChildJob implements Job {

	/**
	 * 封装quartz服务的接口
	 * @author hailongzhao
	 * @date 20151211
	 */
	@Override
	public void execute(JobExecutionContext context) {
		String reqUrl = context.getJobDetail().getName();
		try {
			String result = HttpUtil.sendPost(reqUrl, "");
			System.out.println(result);
		} catch (Exception e) {
			Helper.sendMail("执行job:"+reqUrl+"时出错:",e);
		}
	}
	
}
