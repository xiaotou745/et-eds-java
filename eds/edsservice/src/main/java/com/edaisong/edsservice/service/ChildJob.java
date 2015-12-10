package com.edaisong.edsservice.service;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.edaisong.core.inter.IJobDo;

public class ChildJob implements Job {

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		String jobName = context.getJobDetail().getName();
		String[] detail = jobName.split("#");
		int index = detail[2].lastIndexOf(".");
		String beanName = detail[2].substring(index + 1);
		if (!Character.isLowerCase(beanName.charAt(0))) {
			beanName = Character.toLowerCase(beanName.charAt(0))
					+ beanName.substring(1);
		}
		try {
			IJobDo obj = (IJobDo) getBean(detail[1], beanName);
			obj.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Map<String, ApplicationContext> contextMap = new HashMap<String, ApplicationContext>();
	private static Method add = null;

	private Object getBean(String filePath, String beanName) throws Exception {
		synchronized (ChildJob.class) {
			if (!contextMap.containsKey(filePath)) {
				synchronized (ChildJob.class) {
					if (add == null) {
						add = URLClassLoader.class.getDeclaredMethod("addURL",new Class[] { URL.class });
						add.setAccessible(true);
					}

					@SuppressWarnings("resource")
					URLClassLoader sysClassLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
				
//					URL[] systemClassPath=new URL[sysClassLoader.getURLs().length];
//					for (int i = 0; i < sysClassLoader.getURLs().length; i++) {
//						 if (sysClassLoader.getURLs()[i].getPath().indexOf("/eds/api")<0) {
//							 systemClassPath[i]=sysClassLoader.getURLs()[i];
//						 }else if(sysClassLoader.getURLs()[i].getPath().indexOf("/eds/api")>0&&
//								 sysClassLoader.getURLs()[i].getPath().indexOf("taobao")>0){
//							 systemClassPath[i]=new URL("file:/"+filePath);
//						}
//					}
//					for (URL url : systemClassPath) {
//						System.out.println(url);
//					}
					
					 //sysClassLoader=URLClassLoader.newInstance(systemClassPath);
//					 URLClassLoader sysClassLoader2
//					 =(URLClassLoader)ClassLoader.getSystemClassLoader();
					

					URL url = new URL("file:/" + filePath);
					add.invoke(sysClassLoader, new Object[] { url });


					ApplicationContext contentApp = new ClassPathXmlApplicationContext("applicationContext.xml");
					
					contextMap.put(filePath, contentApp);
				}
			}
		}
		return contextMap.get(filePath).getBean(beanName);
	}

}
