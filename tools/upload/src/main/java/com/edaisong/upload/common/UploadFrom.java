package com.edaisong.upload.common;

public enum UploadFrom {
	/**
	 * 商户
	 */
	Business(1, "商户"),
	/**
	 * 骑士
	 */
	Clienter(2, "骑士"),
	/**
	 * 订单
	 */
	Order(3, "订单"),
	/**
	 * 其他
	 */
	Other(4, "其他");

	private int value = 0;
	private String desc;
	private UploadFrom(int value, String desc) { 
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static UploadFrom getEnum(int index) {
		for (UploadFrom c : UploadFrom.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
