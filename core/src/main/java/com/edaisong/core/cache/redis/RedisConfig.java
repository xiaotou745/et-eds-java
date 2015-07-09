package com.edaisong.core.cache.redis;

import java.io.InputStream;
import java.util.Properties;

import com.edaisong.core.common.ConfigHelper;
import com.edaisong.core.common.ParseHelper;

public class RedisConfig {
	private static final Properties prop = new Properties();

	static {
		InputStream inputStream = ConfigHelper.class.getClassLoader().getResourceAsStream("redis.properties");
		try {
			prop.load(inputStream);
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
		}
	}
	
	public static String getRedisHost(){
		return prop.getProperty("redis.host","192.168.0.14");
	}
	
	public static int getRedisPort(){
		String port = prop.getProperty("redis.port");
		return ParseHelper.ToInt(port, 6379);
	}
	
	public static long getMaxWaitMillis(){
		String maxWaitMillis = prop.getProperty("redis.max-wait-millis");
		return ParseHelper.ToLong(maxWaitMillis, 1000);
	}
	
	public static int getMaxTotal(){
		String maxTotal = prop.getProperty("redis.max-total");
		return ParseHelper.ToInt(maxTotal, 100);
	}
	
	public static int getMaxIdle(){
		String maxIdle = prop.getProperty("redis.max-idle");
		return ParseHelper.ToInt(maxIdle, 100);
	}
	
	public static int getTimeout(){
		String timeout = prop.getProperty("redis.timeout");
		return ParseHelper.ToInt(timeout, 2000);
	}
	
	public static int getRetryCount(){
		String retryCount = prop.getProperty("redis.retry-count");
		return ParseHelper.ToInt(retryCount, 3);
	}
}
