package com.edaisong.toolsapi.common;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.edaisong.toolscore.util.JsonUtil;
import com.edaisong.toolsentity.domain.ConnectionInfo;

import redis.clients.jedis.JedisPoolConfig;

public class RedisTools {
	
	public RedisTemplate getRedisTemplate(String con){
		try {
			ConnectionInfo conInfo = JsonUtil.str2obj(con, ConnectionInfo.class);
			JedisPoolConfig config=new JedisPoolConfig();
			config.setMaxTotal(100);
			config.setMaxIdle(100);
			config.setMaxWaitMillis(1000);
			JedisConnectionFactory factory=new JedisConnectionFactory();
			factory.setPoolConfig(config);
			factory.setUsePool(true);
			factory.setHostName(conInfo.getHost());
			factory.setPort(conInfo.getPort());
			RedisTemplate template=new RedisTemplate();
			template.setConnectionFactory(factory);
			StringRedisSerializer serializer=new StringRedisSerializer();
			template.setKeySerializer(serializer);
			template.setHashKeySerializer(serializer);
			return template;
		} catch (Exception e) {
			throw new RuntimeException("创建RedisTemplate连接工厂时出错:" + e.getMessage());
		}
		
	}
}
