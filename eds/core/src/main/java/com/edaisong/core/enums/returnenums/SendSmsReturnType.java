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
	 * 验证码获取类型错误
	 */
	MessageTypeError(1003,"验证码获取类型错误"),
		/**
	 * 验证码不存在
	 */
	CodeNotExists(1004,"验证码不存在"),
	/**
 * 正在发送
 */
	Sending(1005,"正在发送"),;
	
	
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
