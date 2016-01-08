package com.edaisong.upload.common;
import java.io.InputStream;
import java.util.Properties;

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
				inputStream = PropertyUtils.class.getClassLoader()
						.getResourceAsStream("conf.properties");

			prop.load(inputStream);
			inputStream.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex.getMessage());
		}
	}

	public static String getProperty(String key, String defaultValue) {
		return prop.getProperty(key, defaultValue);
	}

	public static String getProperty(String key) {
		return prop.getProperty(key);
	}
}
