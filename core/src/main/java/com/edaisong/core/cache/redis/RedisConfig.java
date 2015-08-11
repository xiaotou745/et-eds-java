package com.edaisong.core.cache.redis;

import com.edaisong.core.util.ParseHelper;
import com.edaisong.core.util.PropertyUtils;

public class RedisConfig {
	
	public static String getRedisHost(){
		return PropertyUtils.getProperty("redis.host","192.168.0.14");
	}
	
	public static int getRedisPort(){
		String port = PropertyUtils.getProperty("redis.port");
		return ParseHelper.ToInt(port, 6379);
	}
	
	public static long getMaxWaitMillis(){
		String maxWaitMillis = PropertyUtils.getProperty("redis.max-wait-millis");
		return ParseHelper.ToLong(maxWaitMillis, 1000);
	}
	
	public static int getMaxTotal(){
		String maxTotal = PropertyUtils.getProperty("redis.max-total");
		return ParseHelper.ToInt(maxTotal, 100);
	}
	
	public static int getMaxIdle(){
		String maxIdle = PropertyUtils.getProperty("redis.max-idle");
		return ParseHelper.ToInt(maxIdle, 100);
	}
	
	public static int getTimeout(){
		String timeout = PropertyUtils.getProperty("redis.timeout");
		return ParseHelper.ToInt(timeout, 2000);
	}
	
	public static int getRetryCount(){
		String retryCount = PropertyUtils.getProperty("redis.retry-count");
		return ParseHelper.ToInt(retryCount, 3);
	}
}
