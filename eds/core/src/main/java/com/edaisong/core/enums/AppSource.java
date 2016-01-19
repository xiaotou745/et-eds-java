package com.edaisong.core.enums;

public enum AppSource {
	/**
	 * 易代送商户版
	 */
	EdaiSong(0,"易代送商户版"),
    /**
	 * 智能调度版
	 */
	ZhiNengDiaoDu(1,"智能调度版"),
	/**
	 * 闪送版
	 */
	ShanSong(2,"闪送版");
	private int value = 0;
	private String desc;
	private AppSource(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static AppSource getEnum(int index) {
		for (AppSource c : AppSource.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
