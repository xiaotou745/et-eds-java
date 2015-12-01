package com.edaisong.toolsapi.common;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.edaisong.toolsentity.domain.ConnectionInfo;

import redis.clients.jedis.JedisPoolConfig;

/**
 * 根据配置初始化redis客户端实例
 * @author hailongzhao
 * @date 20151130
 *
 */
public class RedisUtil {
	private RedisTemplate<String, Object> redisTemplate;
	public RedisUtil(ConnectionInfo conInfo){
		redisTemplate=getRedisTemplate(conInfo);
	}
	/**
	 * 根据连接串对象初始化RedisTemplate
	 * @param conInfo
	 * @author hailongzhao
	 * @date 20151130
	 * @return
	 */
	private RedisTemplate<String, Object> getRedisTemplate(ConnectionInfo conInfo){
		JedisPoolConfig config=new JedisPoolConfig();
		config.setMaxTotal(100);
		config.setMaxIdle(100);
		config.setMaxWaitMillis(1000);
		JedisConnectionFactory factory=new JedisConnectionFactory();
		factory.setPoolConfig(config);
		factory.setUsePool(true);
		factory.setHostName(conInfo.getHost());
		factory.setPort(conInfo.getPort());
		factory.afterPropertiesSet();
		RedisTemplate<String, Object> template=new RedisTemplate<String, Object>();
		template.setConnectionFactory(factory);
		StringRedisSerializer serializer=new StringRedisSerializer();
		template.setKeySerializer(serializer);
		template.setHashKeySerializer(serializer);
		template.afterPropertiesSet();
		return template;
	}
	private ValueOperations<String, Object> getOperation() {
		return redisTemplate.opsForValue();
	}

	/**
	 * redis存储，默认过期时间为1小时
	 * 
	 * @param key
	 *            key
	 * @param value
	 *            value
	 */
	public void set(String key, Object value) {
		set(key, value, 1, TimeUnit.HOURS);
	}

	/**
	 * redis存储方法
	 * 
	 * @param key
	 *            key
	 * @param value
	 *            value
	 * @param timeout
	 *            过期时间，单位默认为秒，如果需要更改，些调用重载方法{@link set(String key, Object value,
	 *            long timeout, TimeUnit timeUnit)}
	 */
	public void set(String key, Object value, long timeout) {
		set(key, value, timeout, TimeUnit.SECONDS);
	}

	/**
	 * redis存储
	 * 
	 * @param key
	 *            redis key
	 * @param value
	 *            redis value
	 * @param timeout
	 *            过期时间
	 * @param timeUnit
	 *            过期时间单位
	 */
	public void set(String key, Object value, long timeout, TimeUnit timeUnit) {
		ValueOperations<String, Object> operation = getOperation();
		operation.set(suffxKey(key), value, timeout, timeUnit);
	}

	/**
	 * 获取Redis值， isSuffxKey=false时不加redis版本及前缀
	 * */
	public <T> T get(String key, Class<T> type,Boolean isSuffxKey) {
		ValueOperations<String, Object> operation = getOperation();
		String finalKey=isSuffxKey?suffxKey(key):key;
		Object object = operation.get(finalKey);

		// TODO: 这里没有判断object的类型是否是T，之后再加；
		return (T) object;
	}

	/**
	 * 获取Redis值， isSuffxKey=true+版本及前缀
	 * */
	public <T> T get(String key, Class<T> type) {
		return get(key,type,true);
	}
	
	public void remove(String keyPattern) {
		Set<String> removeKeys = redisTemplate.keys(suffxKey(keyPattern));
		redisTemplate.delete(removeKeys);
	}

	/**
	 * 获取所有相似key
	 * 
	 * @param keyPattern
	 *            模糊键
	 * @author haichao
	 * @date 2015年9月29日 13:10:05
	 * */
	public Set<String> keys(String keyPattern){
		return redisTemplate.keys("*"+keyPattern+"*");
	}

	/**
	 * 给key添加后缀或前缀
	 * 
	 * @author hailongzhao
	 * @date 20150902
	 * @param orginKey
	 * @return
	 */
	private String suffxKey(String orginKey) {
		return orginKey;
//		return "java_" + PropertyUtils.getProperty("GlobalVersion") + "_"
//				+ orginKey;
	}
}
