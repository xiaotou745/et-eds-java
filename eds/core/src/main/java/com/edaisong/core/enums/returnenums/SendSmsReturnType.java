package com.edaisong.core.enums.returnenums;

public enum SendSmsReturnType {
	/**
	 * 
	 */
	Success(200,"Success"),
	/**
	 * 手机号码无效
	 */
	PhoneError(1001, "手机号码无效"),
	/**
	 * 发送失败
	 */
	Fail(1002,"发送失败");
	
	private int value = 0;
	private String desc;
	private SendSmsReturnType(int value, String desc) { 
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static SendSmsReturnType getEnum(int index) {
		for (SendSmsReturnType c : SendSmsReturnType.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}