package com.edaisong.toolscore.enums;

/**
 * 控制其他系统时，可能用到的服务器类型
 * @author hailongzhao
 * @date 20151119
 *
 */
public enum ServerType  {
	/**
	 * SqlServer
	 */
	SqlServer(0, "SqlServer"),
//	/**
//	 * MySql
//	 */
//	MySql(1, "MySql"),
	/**
	 * Redis
	 */
	Redis(2, "Redis"),
//	/**
//	 * MemoryCache
//	 */
//	MemoryCache(3, "MemoryCache"),
//	/**
//	 * RabbitMq
//	 */
//	RabbitMq(4, "RabbitMq"),
//	/**
//	 * ActiveMq
//	 */
//	ActiveMq(5, "ActiveMq"),
	/**
	 * MongoDb
	 */
	MongoDb(6, "MongoDb");

	private int value = 0;
	private String desc;
	private ServerType(int value, String desc) { 
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static ServerType getEnum(int index) {
		for (ServerType c : ServerType.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}

