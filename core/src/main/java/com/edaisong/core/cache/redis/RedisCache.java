package com.edaisong.core.cache.redis;

import org.springframework.util.StringUtils;

import com.edaisong.core.util.SerializeUtils;

import redis.clients.jedis.Jedis;

public class RedisCache {

	private static Jedis getJedis() {
		return JedisUtils.getInstance().getJedis(RedisConfig.getRedisHost(), RedisConfig.getRedisPort());
	}

	public static boolean set(String nameSpace, String key, Object value) {
		return set(nameSpace, key, value, 0);
	}

	public static boolean set(String nameSpace, String key, Object value, int seconds) {
		if (StringUtils.isEmpty(key)) {
			return false;
		}
		if (value == null) {
			return false;
		}
		byte[] serialize = SerializeUtils.serialize(value);
		String keyname = nameSpace + ":" + key;
		if (seconds > 0) {
			getJedis().setex(keyname.getBytes(), seconds, serialize);
		} else {
			getJedis().set(keyname.getBytes(), serialize);
		}

		return true;
	}
}
