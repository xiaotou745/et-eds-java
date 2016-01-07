package com.alipay.config;

public enum Platform {

	EDS(0,"易代送"),
   
    RRT(1,"人人推");
	private int value = 0;
	private String desc;
	private Platform(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static Platform getEnum(int index) {
		for (Platform c : Platform.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
