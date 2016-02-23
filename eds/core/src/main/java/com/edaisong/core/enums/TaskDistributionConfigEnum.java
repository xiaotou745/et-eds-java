package com.edaisong.core.enums;

public enum TaskDistributionConfigEnum {

	/**
	 * 成功
	 */
	Success(1, "成功！"),
	
	/**
	 * 距离重复
	 */
	KMErr(-1,"距离重复"),
	/**
	 * 重量重复
	 */
	KGErr(-2,"重量重复"),
	/**
	 * 商户不存在
	 */
	BusinessIsnull(-3,"商户不存在");

	private int value = 0;
	private String desc;
	private TaskDistributionConfigEnum(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static TaskDistributionConfigEnum getEnum(int index) {
		for (TaskDistributionConfigEnum c : TaskDistributionConfigEnum.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
