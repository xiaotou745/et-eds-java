package com.edaisong.core.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PropertiesLoaderSupport;

/**
 * 读取配置文件的帮助类
 * @author hailongzhao
 * @date 20150827
 */
public class PropertyUtils {
	private static final Properties prop = new Properties();
	static {
		InputStream inputStream = null;
		try {
			List<String> res = getEnvSet();
			//从当前类目录中读配置文件
			if (res.get(0) == "0") {
				inputStream = PropertyUtils.class.getClassLoader()
						.getResourceAsStream(res.get(1));
			} else {//从绝对路径中读取配置文件
				inputStream = new FileInputStream(res.get(1));
			}

			prop.load(inputStream);
			inputStream.close();
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	/**
	 * 从当前项目应用的context.xml文件中读取当前环境的配置文件路径
	 * 
	 * @author hailongzhao
	 * @Date 20150826
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	private static List<String> getEnvSet() throws NoSuchFieldException,
			SecurityException, IllegalArgumentException,
			IllegalAccessException, IOException {
		List<String> res = new ArrayList<String>();

		PropertiesLoaderSupport property = (PropertiesLoaderSupport) SpringBeanHelper
				.getCustomBeanByType(PropertyPlaceholderConfigurer.class);
		Field field = PropertiesLoaderSupport.class
				.getDeclaredField("locations");
		field.setAccessible(true);
		Resource[] locations = (Resource[]) field.get(property);
		if (locations[0] instanceof ClassPathResource) {
			ClassPathResource location = (ClassPathResource) locations[0];
			res.add("0");
			res.add("/" + location.getPath());
		} else if (locations[0] instanceof UrlResource) {
			UrlResource location = (UrlResource) locations[0];
			res.add("1");
			res.add(location.getURL().getPath());
		}
		return res;
	}

	public static String getProperty(String key, String defaultValue) {
		return prop.getProperty(key, defaultValue);
	}

	public static String getProperty(String key) {
		return prop.getProperty(key);
	}
}
