package com.edaisong.core.enums;

public enum OrderTipEnum {

	/**
	 * 成功
	 */
	Success(1, "成功！"),
	
	/**
	 * 小费重复
	 */
	TipErr(-1,"小费重复");

	private int value = 0;
	private String desc;
	private OrderTipEnum(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static OrderTipEnum getEnum(int index) {
		for (OrderTipEnum c : OrderTipEnum.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
