package com.edaisong.api.redis;


import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.edaisong.core.util.PropertyUtils;

@Service
public class NetRedisService {

	 @Autowired
	private RedisTemplate<String, Object> netRedisTemplate;

	private ValueOperations<String, Object> getOperation() {
		return netRedisTemplate.opsForValue();
	}

	/**
	 * redis存储，默认过期时间为1小时
	 * 
	 * @param key key
	 * @param value value
	 */
	public void set(String key, Object value) {
		set(key, value, 1, TimeUnit.HOURS);
	}

	/**
	 * redis存储方法
	 * 
	 * @param key key
	 * @param value value
	 * @param timeout 过期时间，单位默认为秒，如果需要更改，些调用重载方法{@link set(String key, Object
	 *            value, long timeout, TimeUnit timeUnit)}
	 */
	public void set(String key, Object value, long timeout) {
		set(key, value, timeout, TimeUnit.SECONDS);
	}

	/**
	 * redis存储
	 * 
	 * @param key redis key
	 * @param value redis value
	 * @param timeout 过期时间
	 * @param timeUnit 过期时间单位
	 */
	public void set(String key, Object value, long timeout, TimeUnit timeUnit) {
		ValueOperations<String, Object> operation = getOperation();
		operation.set(suffxKey(key), value, timeout, timeUnit);
	}
	public <T> T get(String key, Class<T> type) {
		ValueOperations<String, Object> operation = getOperation();
		Object object = operation.get(suffxKey(key));

		// TODO: 这里没有判断object的类型是否是T，之后再加；
		return (T) object;
	}


	
	public void remove(String keyPattern){
		Set<String> removeKeys = netRedisTemplate.keys(suffxKey(keyPattern));
		netRedisTemplate.delete(removeKeys);
	}
	/**
	 * 给key添加后缀或前缀
	 * @author hailongzhao
	 * @date 20150902
	 * @param orginKey
	 * @return
	 */
	private String suffxKey(String orginKey){
		return orginKey+"_"+PropertyUtils.getProperty("GlobalVersion");
	}
//	/**
//	 * 事务批量存储方法，把一个k-v的map一起存储到redis中，过期时间使用默认的1小时
//	 * 
//	 * @param values key-value map数据
//	 */
//	public void txSet(Map<? extends String, ? extends Object> values) {
//		txSet(values, 1, TimeUnit.HOURS);
//	}

//	/**
//	 * 事务存储方法，把一个k-v的键值对map一起存储到redis中
//	 * 
//	 * @param values key-value map
//	 * @param timeout 过期时间
//	 * @param timeUnit 过期时间单位
//	 */
//	public void txSet(Map<? extends String, ? extends Object> values, long timeout, TimeUnit timeUnit) {
//		SessionCallback<String> callback = new SessionCallback<String>() {
//			public <K, V> String execute(RedisOperations<K, V> operation) throws DataAccessException {
//				operation.multi();
//
//				for (Entry<? extends String, ? extends Object> entry : values.entrySet()) {
//					BoundValueOperations<K, V> boundValueOps = operation.boundValueOps((K) entry.getKey());
//					boundValueOps.set((V) entry.getValue(), timeout, timeUnit);
//				}
//
//				operation.exec();
//				return null;
//			}
//		};
//		redisTemplate.execute(callback);
//	}

//	public void mutilSet(Map<? extends String, ? extends Object> values) {
//		ValueOperations<String, Object> operation = getOperation();
//
//		operation.multiSet(values);
//	}
//	public <T> List<T> mutilGet(String key, Class<T> type) {
//	ValueOperations<String, Object> operation = getOperation();
//
//	Set<String> keys = redisTemplate.keys(suffxKey(key));
//
//	List<Object> values = operation.multiGet(keys);
//
//	List<T> collect = values.stream().map(o -> (T) o).collect(Collectors.toList());
//	return collect;
//}
	
}
