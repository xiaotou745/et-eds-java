package com.edaisong.core.enums;

/**
 * 支付方式(1 用户支付 2 骑士代付)
 * @author CaoHeYang
 *
 */
public enum PayStyle {
	/**
	 * 用户支付
	 */
	BuyerPay(1,"用户支付"),
	/**
	 * 骑士支付
	 */
	ClienterPay(2,"骑士支付");
	
	private int value = 0;
	private String desc;
	private PayStyle(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static PayStyle getEnum(int index) {
		for (PayStyle c : PayStyle.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
