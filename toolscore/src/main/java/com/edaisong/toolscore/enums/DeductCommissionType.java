package com.edaisong.toolscore.enums;

/**
 * 扣除补贴类型
 * @author CaoHeYang
 * @date 20150831
 */
public enum DeductCommissionType {
	/**
	 * 自动扣除
	 */
	Auto(1,"自动扣除"),
	/**
	 * 人工扣除
	 */
	People(2,"人工扣除");
	private int value = 0;
	private String desc;

	private DeductCommissionType(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}

	public int value() {
		return this.value;
	}

	public String desc() {
		return this.desc;
	}

	public static DeductCommissionType getEnum(int index) {
		for (DeductCommissionType c : DeductCommissionType
				.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
