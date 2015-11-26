package com.edaisong.core.enums;

/**
 * 支付宝批次状态
 * @author CaoHeYang
 * @date 20151020
 */
public enum OptLogEnum {
	/**
	 * 普通任务配送费配置日志
	 */
	TaskDistributionConfig(1,"打款中");
	private int value = 0;
	private String desc;
	private OptLogEnum(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static OptLogEnum getEnum(int index) {
		for (OptLogEnum c : OptLogEnum.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
