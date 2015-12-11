package com.edaisong.core.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.edaisong.core.util.SpringBeanHelper;

public class ChildJob implements Job {

	/**
	 * 封装quartz服务的接口
	 * @author hailongzhao
	 * @date 20151211
	 */
	@Override
	public void execute(JobExecutionContext context) {
		String jobName = context.getJobDetail().getName();
		try {
			Object obj = SpringBeanHelper.getCustomBean(jobName);
			if (obj != null && obj instanceof IJobDo) {
				((IJobDo) obj).run();
			}

		} catch (Exception e) {
			System.out.println("执行job:"+jobName+"时出错:"+e.getMessage());
			//e.printStackTrace();
		}
	}
//	private static ApplicationContext contentApp = null;
//	public static  Object getExecuteObj(String beanName) {
//		synchronized (ChildJob.class) {
//			if (contentApp == null) {
//				synchronized (ChildJob.class) {
//					contentApp = new ClassPathXmlApplicationContext(
//							"conf/core/dev-context.xml");
//				}
//			}
//		}
//		return contentApp.getBean(beanName);
//	}
}
