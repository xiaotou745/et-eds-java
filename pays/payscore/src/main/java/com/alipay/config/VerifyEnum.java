package com.alipay.config;

public enum VerifyEnum {
	RSA(0, "RSA加密"), 
	KEY(1, "KEY加密");

	private int value = 0;
	private String desc;

	private VerifyEnum(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}

	public int value() {
		return this.value;
	}

	public String desc() {
		return this.desc;
	}

	public static VerifyEnum getEnum(int index) {
		for (VerifyEnum c : VerifyEnum.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
