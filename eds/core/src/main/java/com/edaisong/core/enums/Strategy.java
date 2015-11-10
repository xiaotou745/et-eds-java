package com.edaisong.core.enums;

/**
 * 商户余额流水状态(1、交易成功 2、交易中）
 * 
 * @author CaoHeYang
 *
 */
public enum Strategy {

	/**
	 * 普通补贴
	 */
	Strategy0(0, "普通补贴"),
	/**
	 * "时间段补贴
	 */
	Strategy1(1, "时间段补贴"),
	/**
	 * 保本补贴
	 */
	Strategy2(2, "保本补贴 "),
	/**
	 * 满金额补贴
	 */
	Strategy3(3, "满金额补贴"),
	/**
	 * 基本佣金+平台补贴
	 */
	Strategy4(4, "基本佣金+平台补贴");
	private int value = 0;
	private String desc;

	private Strategy(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}

	public int value() {
		return this.value;
	}

	public String desc() {
		return this.desc;
	}

	public static Strategy getEnum(int index) {
		for (Strategy c : Strategy.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
