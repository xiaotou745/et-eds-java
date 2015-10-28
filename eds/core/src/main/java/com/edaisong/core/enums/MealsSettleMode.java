package com.edaisong.core.enums;

public enum MealsSettleMode {
	/**
	 * 线下结算
	 */
	LineOff(0, "线下结算"),
	/**
	 * 线上结算
	 */
	LineOn(1, "线上结算");
	private int value = 0;
	private String desc;
	private MealsSettleMode(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static MealsSettleMode getEnum(int index) {
		for (MealsSettleMode c : MealsSettleMode.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
