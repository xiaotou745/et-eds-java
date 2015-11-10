package com.edaisong.core.enums;

public enum SendSmsType {
	Success(200,"Success"),
	PhoneError(1001, "手机号码无效"),
	Fail(1002,"发送失败"),
	PhoneExists(1003,"该账号已经存在"),
	PhoneNotExists(1004,"该账号不存在");
	
	private int value = 0;
	private String desc;
	private SendSmsType(int value, String desc) { 
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static SendSmsType getEnum(int index) {
		for (SendSmsType c : SendSmsType.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
