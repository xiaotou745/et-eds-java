package com.edaisong.core.enums;

/**
 * 支付状态(0待支付 ,1 已支付,2支付中)
 * @author CaoHeYang
 *
 */
public enum PayStatus {
	/**
	 * 待支付
	 */
	WaitPay(0,"待支付"),
	/**
	 * 已支付
	 */
	HadPay(1,"已支付"),
	/**
	 * 支付中
	 */
	WaitingPay(2,"支付中");
	private int value = 0;
	private String desc;
	private PayStatus(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static PayStatus getEnum(int index) {
		for (PayStatus c : PayStatus.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
