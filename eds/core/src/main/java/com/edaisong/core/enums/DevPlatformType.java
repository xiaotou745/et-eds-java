package com.edaisong.core.enums;
/**
 * 所属平台
 * @author ofmyi_000
 *
 */
public enum DevPlatformType  {
	/**
	 * 易代送管理后台
	 */
	EdsAdmin(1, "管理后台"),
	/**
	 * 易代送
	 */
	Eds(2, "E代送"),
	/**
	 * 易代送智能调度
	 */
	EdsSs(3, "智能调度");

	
	private int value = 0;
	private String desc;
	private DevPlatformType(int value, String desc) { 
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static DevPlatformType getEnum(int index) {
		for (DevPlatformType c : DevPlatformType.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}

