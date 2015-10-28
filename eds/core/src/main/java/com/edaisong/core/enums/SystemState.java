package com.edaisong.core.enums;

public enum SystemState  {

	/**
	 * 成功
	 */
	Success(1, "成功"),
	/**
	 * 系统错误
	 */
	SystemError(-1, "系统错误"),
	/**
	 * 录入信息有误，请确认后重试
	 */
	ParaError(2, "录入信息有误，请确认后重试"),
	/**
	 * 版本号不能为空
	 */
	VersionError(3, "版本号不能为空");
	
	private int value = 0;
	private String desc;
	private SystemState(int value, String desc) { 
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static SystemState getEnum(int index) {
		for (SystemState c : SystemState.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}

