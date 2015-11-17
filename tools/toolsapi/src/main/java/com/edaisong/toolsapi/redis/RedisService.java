package com.edaisong.toolsapi.redis;
 
import java.util.Set;
import java.util.concurrent.TimeUnit; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.data.redis.core.RedisTemplate; 
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service; 

import com.edaisong.toolscore.util.PropertyUtils;
@Service
public class RedisService {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

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
		return "java_" + PropertyUtils.getProperty("GlobalVersion") + "_"
				+ orginKey;
	}
}
