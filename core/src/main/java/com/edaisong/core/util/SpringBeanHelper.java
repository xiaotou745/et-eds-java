package com.edaisong.core.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class SpringBeanHelper {
	private static ApplicationContext ctx_bean;
	static {
		try {
		    ctx_bean = ContextLoader.getCurrentWebApplicationContext();
//			ctx_bean = new ClassPathXmlApplicationContext(
//					"conf/core/spring-context.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Object getCustomBean(String beanName) {
	
		return ctx_bean.getBean(beanName);
	}
	public static <T> T getCustomBeanByType(Class<T> type) {
		return ctx_bean.getBean(type);
	}
}
