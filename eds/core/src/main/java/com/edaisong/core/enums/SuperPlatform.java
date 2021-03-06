package com.edaisong.core.enums;

public enum SuperPlatform {
	/**
	 * 商家
	 */
	Business(0, "商家"),
	/**
	 * 骑士
	 */
	Clienter(1, "骑士"),
	/**
	 * 服务平台
	 */
	ServicePlatform(2, "服务平台"),
	/**
	 * 管理后台
	 */
	ManagementBackGround(3, "管理后台"),
	/**
	 * 第三方对接平台
	 */
	ThirdParty(4, "第三方对接平台");
	private int value = 0;
	private String desc;
	private SuperPlatform(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static SuperPlatform getEnum(int index) {
		for (SuperPlatform c : SuperPlatform.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
