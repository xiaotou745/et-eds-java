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
	Fail(1002,"发送失败"),
	/**
	 * 该账号已经存在
	 */
	PhoneExists(1003,"该账号已经存在"),
	/**
	 * 该账号不存在
	 */
	PhoneNotExists(1004,"该账号不存在"),
	/**
	 * 验证码错误
	 */
	CodeError(1005,"验证码错误"),
	/**
	 * 验证码不能为空
	 */
	VerCodeNull(1006, "验证码不能为空");
	
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
