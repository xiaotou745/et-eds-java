package com.edaisong.quartzservice;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.impl.JobDetailImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.edaisong.toolscore.util.HttpUtil;


public class ChildJob implements Job {
	private final  static Logger log = LoggerFactory.getLogger("com.edaisong.quartzservice.ChildJob");
	/**
	 * 封装quartz服务的接口
	 * @author hailongzhao
	 * @date 20151211
	 */
	@Override
	public void execute(JobExecutionContext context) {
		String reqUrl = ((JobDetailImpl)context.getJobDetail()).getName();
		try {
			String result = HttpUtil.sendPost(reqUrl, "","application/json; charset=utf-8");
			log.info(reqUrl+"执行的结果:"+result);
		} catch (Exception e) {
			Helper.sendMail("执行job:"+reqUrl+"时出错:",e);
		}
	}
	
}
