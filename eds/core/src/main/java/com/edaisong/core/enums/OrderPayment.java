package com.edaisong.core.enums;


public enum OrderPayment {
	/**
	 * 余额 
	 */
	Balance(0, "余额"),
	/**
	 * 余额
	 */
	Zhifubao(1, "余额"),
	/**
	 * 微信
	 */
	Weixin(2, "微信 ");

	private int value = 0;
	private String desc;
	private OrderPayment(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static OrderPayment getEnum(int index) {
		for (OrderPayment c : OrderPayment.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
