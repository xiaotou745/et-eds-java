package com.edaisong.api.common;

import java.util.Date;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;


import com.edaisong.core.util.JsonUtil;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.core.util.SpringBeanHelper;
import com.edaisong.core.util.StringUtils;
import com.edaisong.core.util.SystemUtils;
import com.edaisong.entity.domain.ServiceLog;

public class ChildJob implements Job {

	/**
	 * 封装quartz服务的接口
	 * @author hailongzhao
	 * @date 20151211
	 */
	@Override
	public void execute(JobExecutionContext context) {
		String exceptionMsg = "";
		String stackTrace = "";
		Date requestTime=new Date();
		
		String jobName = context.getJobDetail().getName();
		try {
			Object obj = SpringBeanHelper.getCustomBean(jobName);
			if (obj != null && obj instanceof IJobDo) {
				((IJobDo) obj).run();
			}else {
				 exceptionMsg = "获取bean失败";
				 stackTrace = "没有获取到jobName对应的bean或这个bean没有实现IJobDo";
			}

		} catch (Exception e) {
			System.out.println("执行job:"+jobName+"时出错:"+e.getMessage());
			exceptionMsg = e.getMessage();
			stackTrace = StringUtils.getStackTrace(e);
		}
		writelog(jobName,requestTime,exceptionMsg,stackTrace);
	}
	private void writelog(String jobName,Date requestTime,String exceptionMsg,String stackTrace){
		try {
			Date endDate=new Date();
			List<String> ipinfoList = SystemUtils.getLocalIpInfo();
			String appServerIP = JsonUtil.obj2string(ipinfoList);
			
			ServiceLog logEngity = new ServiceLog();
			logEngity.setServiceBean(jobName);
			logEngity.setSourceSys("admin");
			logEngity.setAppServer(appServerIP);
			logEngity.setException(exceptionMsg);
			logEngity.setStackTrace(stackTrace);
			logEngity.setExecuteTime(endDate.getTime() - requestTime.getTime());
			logEngity.setRequestTime(ParseHelper.ToDateString(requestTime, ""));
			logEngity.setRequestEndTime(ParseHelper.ToDateString(endDate, ""));
			LogServiceBLL obj = (LogServiceBLL)SpringBeanHelper.getCustomBean("logServiceBLL");
			obj.ServiceActionLog(logEngity);
		} catch (Exception e) {
			// TODO: handle exception
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
