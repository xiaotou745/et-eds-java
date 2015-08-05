package com.edaisong.api.business;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SqlSessionFactoryPool {
	private static ApplicationContext ctx_bean;
	static {
		try {
			ctx_bean = new ClassPathXmlApplicationContext(
					"conf/core/spring-context.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * 以spring方式根据factoryName获取SqlSessionFactory
	 */
	public static SqlSessionFactory getSpring_SessionFactory(String factoryName) {
		return (SqlSessionFactory) ctx_bean.getBean(factoryName);
	}
	public static Object getCustomBean(String beanName) {
	
		return ctx_bean.getBean(beanName);
	}
	public static <T> T getCustomBeanByType(Class<T> type) {
		return ctx_bean.getBean(type);
	}
}
