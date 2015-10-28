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
			//web站点启动后，ctx_bean才会有值。如果是api调用，则会返回null
			if (ctx_bean == null) {
				ctx_bean = new ClassPathXmlApplicationContext("conf/core/dev-context.xml");
				if (ctx_bean == null) {
					throw new RuntimeException("没有获取到springcontext");
				}
			}
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
