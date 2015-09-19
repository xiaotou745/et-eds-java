package com.edaisong.core.enums;

public enum UserType  {
	/**
	 * 商家
	 */
	Business(1, "门店"),
	/**
	 * 骑士
	 */
	Clienter(2, "骑士");

	private int value = 0;
	private String desc;
	private UserType(int value, String desc) { 
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static UserType getEnum(int index) {
		for (UserType c : UserType.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}

