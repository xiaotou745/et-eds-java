package com.edaisong.core.enums;
/**
 * 骑士等级
 * @author hailongzhao
 * @Date 20151119
 */
public enum ClienterGradeType {
	/**
	 * 众包
	 */
	Crowdsourcing(1,"众包"),
    /**
	 * 全职
	 */
    FullTimeClienter(2,"全职"),
    /**
	 * 测试
	 */
    TestClienter (3,"测试");

	private int value = 0;
	private String desc;
	private ClienterGradeType(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static ClienterGradeType getEnum(int index) {
		for (ClienterGradeType c : ClienterGradeType.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
