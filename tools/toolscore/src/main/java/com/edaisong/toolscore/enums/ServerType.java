package com.edaisong.toolscore.enums;

/**
 * 控制其他系统时，可能用到的服务器类型
 * @author hailongzhao
 * @date 20151119
 *
 */
public enum ServerType  {
	/**
	 * SqlServer数据库
	 */
	SqlServer(0, "SqlServer数据库"),
//	/**
//	 * MySql数据库
//	 */
//	MySql(1, "MySql数据库"),
	/**
	 * Redis缓存
	 */
	Redis(2, "Redis缓存"),
//	/**
//	 * MemoryCache缓存
//	 */
//	MemoryCache(3, "MemoryCache缓存"),
//	/**
//	 * RabbitMq消息队列
//	 */
//	RabbitMq(4, "RabbitMq消息队列"),
	/**
	 * ActiveMq消息队列
	 */
	ActiveMq(5, "ActiveMq消息队列"),
	/**
	 * MongoDb数据库
	 */
	MongoDb(6, "MongoDb数据库");

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

