package com.edaisong.mqservice;

import com.edaisong.core.util.MapUtils;

//import com.edaisong.core.util.SystemUtils;

public class TestMain {
	public static void main(String[] args) throws Exception {
		double d = MapUtils.GetShortDistance(39.922113, 116.51701, 39.929213,
				116.50822);
		// double d= Distance(39.922113, 116.51701,39.929213, 116.50822);
		System.out.println(d);
		//动态添加quartz服务
//		QuartzManager.addJob(job_name,
//				com.edaisong.mqservice.impl.QuartzJob.class,
//				"0/10 * * * * ?");
	}
}
