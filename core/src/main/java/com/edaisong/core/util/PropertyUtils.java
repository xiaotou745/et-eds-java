package com.edaisong.core.util;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderSupport;

/*
 * 读取属性文件
 * 
 * 2015年7月27
 * */
public class PropertyUtils {
	private static final Properties prop = new Properties();
	static {
		String env = getEnvSet();
		InputStream inputStream = ConfigHelper.class.getClassLoader()
				.getResourceAsStream("/" + env);
		try {
			prop.load(inputStream);
			inputStream.close();
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
		}
	}

	/**
	 * 从api项目中的spring-context.xml中读取当前环境的配置文件路径
	 * @author hailongzhao
	 * @Date 20150826
	 * @return
	 */
	private static String getEnvSet() {
		try {
			PropertiesLoaderSupport property = (PropertiesLoaderSupport) SpringBeanHelper
					.getCustomBeanByType(PropertyPlaceholderConfigurer.class);
			Field field = PropertiesLoaderSupport.class
					.getDeclaredField("locations");
			field.setAccessible(true);
			Resource[] locations = (Resource[]) field.get(property);
			ClassPathResource location = (ClassPathResource) locations[0];
			return location.getPath();
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
			return "conf/custom/dev-conf.properties";
		}
	}

	public static String getProperty(String key, String defaultValue) {
		return prop.getProperty(key, defaultValue);
	}

	public static String getProperty(String key) {
		return prop.getProperty(key);
	}
}
