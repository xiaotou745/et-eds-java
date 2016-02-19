package com.edaisong.toolscore.enums;

public enum AppSource {
	/**
	 * 易代送
	 */
	EdaiSong(1, "易代送"),
	/**
	 * 人人推
	 */
	RenRenTui(2, "人人推");
	private int value = 0;
	private String desc;
	private AppSource(int value, String desc) { 
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
