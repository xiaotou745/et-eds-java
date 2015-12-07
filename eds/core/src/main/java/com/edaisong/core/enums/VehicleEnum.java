package com.edaisong.core.enums;

public enum VehicleEnum { 
	
	ZiXingChe(1,"自行车/电动车"),
	
	MoTuoChe(2,"摩托车"),
	
	GongGongJiaoTong(3,"公共交通"),
	
	QiChe(4,"汽车");
	private int value = 0;
	private String desc;

	private VehicleEnum(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}

	public int value() {
		return this.value;
	}

	public String desc() {
		return this.desc;
	}

	public static VehicleEnum getEnum(int index) {
		for (VehicleEnum c : VehicleEnum.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
	public static VehicleEnum getEnum(String text) {
		for (VehicleEnum c : VehicleEnum.values()) {
			if (c.desc().equals(text)) {
				return c;
			}
		}
		return null;
	}
}
