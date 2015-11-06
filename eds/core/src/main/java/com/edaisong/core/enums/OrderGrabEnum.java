package com.edaisong.core.enums;

public enum OrderGrabEnum {
	 
    ClienterIdEmpty(-1,"骑士Id不能为空"),
    
    LatitudeEmpty(-2,"纬度为空"),
     
    LongitudeEmpty (-3,"经度为空"), 
    
    GrabOrderIdEmpty(-4,"任务Id不能为空");
    
	private int value = 0;
	private String desc;
	private OrderGrabEnum(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static OrderGrabEnum getEnum(int index) {
		for (OrderGrabEnum c : OrderGrabEnum.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
