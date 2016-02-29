package com.edaisong.core.enums;

public enum AreaLevel {
	/**
	 * 国家
	 */
	Country(1, "国家"),
	/**
	 * 省份
	 */
	Province(2, "省份"),
	/**
	 * 城市
	 */
	City(3, "城市"),
	/**
	 * 区县
	 */
	District(4, "区县");

	private int value = 0;
	private String desc;
	private AreaLevel(int value, String desc) { 
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static AreaLevel getEnum(int index) {
		for (AreaLevel c : AreaLevel.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
