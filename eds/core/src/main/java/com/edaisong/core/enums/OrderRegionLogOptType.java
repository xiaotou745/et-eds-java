package com.edaisong.core.enums;

public enum OrderRegionLogOptType {
	/**
	 * 新增
	 */
	add(1, "新增"),
	/**
	 * 修改
	 */
	update(2, "修改"),
	/**
	 * 删除
	 */
	delete(3, "删除");
	private int value = 0;
	private String desc;
	private OrderRegionLogOptType(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static OrderRegionLogOptType getEnum(int index) {
		for (OrderRegionLogOptType c : OrderRegionLogOptType.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
