package com.edaisong.core.enums;

public enum OrderIsPay {
	/**
	 * 未付
	 */
	WaitPay(0,"顾客未付款"),
	/**
	 * 已付
	 */
	HadPay(1,"顾客已付款");

	private int value = 0;
	private String desc;
	private OrderIsPay(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static OrderIsPay getEnum(int index) {
		for (OrderIsPay c : OrderIsPay.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
