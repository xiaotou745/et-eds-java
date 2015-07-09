package com.edaisong.core.cache.redis;

import java.util.HashMap;
import java.util.Map;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 在不同的线程中使用相同的Jedis实例会发生奇怪的错误。但是创建太多的实现也不好因为这意味着会建立很多sokcet连接，
 * 也会导致奇怪的错误发生。单一Jedis实例不是线程安全的。为了避免这些问题，可以使用JedisPool,
 * JedisPool是一个线程安全的网络连接池。可以用JedisPool创建一些可靠Jedis实例，可以从池中拿到Jedis的实例。
 * 这种方式可以解决那些问题并且会实现高效的性能
 */
public class JedisUtils {
	private JedisUtils() {
	}

	private static Map<String, JedisPool> maps = new HashMap<String, JedisPool>();

	/**
	 * 获取连接池.
	 * 
	 * @return 连接池实例
	 */
	private static JedisPool getPool(String host, int port) {
		String key = host + ":" + port;
		JedisPool pool = null;
		if (!maps.containsKey(key)) {
			JedisPoolConfig config = new JedisPoolConfig();
			// 设置空间连接
			config.setMaxIdle(RedisConfig.getMaxIdle());
			// 设置最大连接数
			config.setMaxTotal(RedisConfig.getMaxTotal());
			// 设置最大阻塞时间，记住是毫秒数milliseconds
			config.setMaxWaitMillis(RedisConfig.getMaxWaitMillis());
			config.setTestOnBorrow(true);
			config.setTestOnReturn(true);
			try {
				/**
				 * 如果你遇到 java.net.SocketTimeoutException: Read timed out
				 * exception的异常信息 请尝试在构造JedisPool的时候设置自己的超时值.
				 * JedisPool默认的超时时间是2秒(单位毫秒)
				 */
				pool = new JedisPool(config, host, port, RedisConfig.getTimeout());
				maps.put(key, pool);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			pool = maps.get(key);
		}
		return pool;
	}

	/**
	 * 类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例 没有绑定关系， 而且只有被调用到时才会装载， 从而实现了延迟加载。
	 */
	private static class RedisUtilsHolder {
		/**
		 * 静态初始化器，由JVM来保证线程安全
		 */
		private static JedisUtils instance = new JedisUtils();
	}

	/**
	 * 当getInstance方法第一次被调用的时候，它第一次读取
	 * RedisUtilHolder.instance，导致RedisUtilHolder类得到初始化；而这个类在装载并被初始化的时候，会初始化它的静
	 * 态域，从而创建RedisUtil的实例，由于是静态的域，因此只会在虚拟机装载类的时候初始化一次，并由虚拟机来保证它的线程安全性。
	 * 这个模式的优势在于，getInstance方法并没有被同步，并且只是执行一个域的访问，因此延迟初始化并没有增加任何访问成本。
	 */
	public static JedisUtils getInstance() {
		return RedisUtilsHolder.instance;
	}

	/**
	 * 获取Redis实例.
	 * 
	 * @return Redis工具类实例
	 */
	public Jedis getJedis(String host, int port) {
		Jedis jedis = null;
		int count = 0;
		do {
			try {
				jedis = getPool(host, port).getResource();
				// log.info("get redis master1!");
			} catch (Exception e) {
				// 销毁对象
				getPool(host, port).returnBrokenResource(jedis);
			}
			count++;
		} while (jedis == null && count < RedisConfig.getRetryCount());
		return jedis;
	}

	/**
	 * 释放redis实例到连接池.
	 * 
	 * @param jedis redis实例
	 * @param host 主机地址
	 * @param port 端口
	 */
	public void closeJedis(Jedis jedis, String host, int port) {
		if (jedis != null) {
			getPool(host, port).returnResource(jedis);
		}
	}
}
