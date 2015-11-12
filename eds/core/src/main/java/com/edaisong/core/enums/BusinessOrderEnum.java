package com.edaisong.core.enums;

public enum BusinessOrderEnum {
	 
	BusinessIdEmpty(-1,"商户Id不能为空"),	 
	GrabOrderIdEmpty(-2,"任务Id不能为空"),
	BusinessNotExist(-3,"商户不存在"),
	ClienterIdNotExist(-4,"骑士Id不能为空");
	
	private int value = 0;
	private String desc;

	private BusinessOrderEnum(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}

	public int value() {
		return this.value;
	}

	public String desc() {
		return this.desc;
	}

	public static BusinessOrderEnum getEnum(int index) {
		for (BusinessOrderEnum c : BusinessOrderEnum.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
