package com.edaisong.core.enums;

import org.junit.internal.runners.statements.Fail;

public enum RegisterBEnum {
	/**
	 * 手机号码无效
	 */
	PhoneError(1001, "手机号码无效"),
	/**
	 * 该账号已经存在
	 */
	PhoneExists(1003,"该账号已经存在"), 
	/**
	 * 验证码错误
	 */
	CodeError(1005,"验证码错误"),
	/**
	 * 验证码不能为空
	 */
	VerCodeNull(1006, "验证码不能为空"),
	PassWordNull(1007,"密码不能为空"),
	/**
	 * 验证码超时
	 */
	CodeTimeOut(1009, "验证码超时"),
	Fail(1010,"注册失败"),
	CountError(-10,"您当前注册的次数大于10，请5分钟后重试");
	private int value = 0;
	private String desc;

	private RegisterBEnum(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}

	public int value() {
		return this.value;
	}

	public String desc() {
		return this.desc;
	}

	public static RegisterBEnum getEnum(int index) {
		for (RegisterBEnum c : RegisterBEnum.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
