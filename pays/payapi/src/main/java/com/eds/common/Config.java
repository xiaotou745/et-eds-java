package com.eds.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Config {
	public String getConfig(String key) {
		InputStream is = null;
		Properties dbproperties = new Properties();
		try {
			is = Config.class.getClassLoader().getResourceAsStream(
					"conf/core/current-conf.properties");
			dbproperties.load(is);

			return dbproperties.getProperty(key).toString();

		} catch (Exception e) {
			System.err.println("不能读取属性文件. "
					+ "请确保current-conf.properties在CLASSPATH指定的路径中");
		}
		return "";
		// return prop.getProperty(key);
	}
}
