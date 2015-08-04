package com.edaisong.core.util;

import java.io.InputStream;
import java.util.Properties;

import com.edaisong.core.common.ConfigHelper;

/*
 * 读取属性文件
 * 
 * 2015年7月27
 * */
public class PropertyUtils {
	private static final Properties prop = new Properties();

	static {
		InputStream inputStream = ConfigHelper.class.getClassLoader().getResourceAsStream("/conf/custom/conf.properties");
		try {
			prop.load(inputStream);
			inputStream.close();  
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
		}
	}
	
	public static String getProperty(String key, String defaultValue){
		return prop.getProperty(key, defaultValue);
	}

	public static String getProperty(String key){
		return prop.getProperty(key);
	}
}
