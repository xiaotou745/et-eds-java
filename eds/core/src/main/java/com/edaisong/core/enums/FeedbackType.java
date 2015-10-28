package com.edaisong.core.enums;

public enum FeedbackType  {

	/**
	 * 功能意见
	 */
	Function(1, "功能意见"),
	/**
	 * 页面意见
	 */
	Page(2, "页面意见"),
	/**
	 * 您的新需求
	 */
	Demand(3, "您的新需求"),
	/**
	 * 操作意见
	 */
	Operation(4, "操作意见"),
	/**
	 * 其它
	 */
	Other(5, "其它");
	
	
	private int value = 0;
	private String desc;
	private FeedbackType(int value, String desc) { 
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static FeedbackType getEnum(int index) {
		for (FeedbackType c : FeedbackType.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}

