package com.edaisong.core.enums;

import org.junit.internal.runners.statements.Fail;

public enum ClienterBindBusinessEnum {
	/**
	 * 订单号不能为空
	 */
	Success(1,"已申请"),
	
	Fail(0,"申请失败"),
	
	HadBind(2,"已经申请绑定"),
	
	ClienterNameEmpty(3,"骑士名称和Id不能为空"),
	
	BusinessNameEmpty(4,"商户名称和Id不能为空");
	
	
	private int value = 0;
	private String desc;

	private ClienterBindBusinessEnum(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}

	public int value() {
		return this.value;
	}

	public String desc() {
		return this.desc;
	}

	public static ClienterBindBusinessEnum getEnum(int index) {
		for (ClienterBindBusinessEnum c : ClienterBindBusinessEnum.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
