package com.edaisong.core.enums;

/**
 * 发单模式：0 普通模式（默认），1 快单模式   默认0
 * 
 * @author 胡灵波
 *
 */
public enum BusinessPushOrderType {

		/**
		 * 普通模式
		 */
	Ordinary(0, "普通"),
		/**
		 * 快单模式
		 */
	Quick(1, "快单模式");
	private int value = 0;
	private String desc;

	private BusinessPushOrderType(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}

	public int value() {
		return this.value;
	}

	public String desc() {
		return this.desc;
	}

	public static BusinessPushOrderType getEnum(int index) {
		for (BusinessPushOrderType c : BusinessPushOrderType.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
