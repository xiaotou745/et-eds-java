package com.edaisong.core.enums;

public enum GroupEnum {
	 
	JuWangKe(1,"聚网客"),
	
	WanDaJiTuan(2,"万达集团"),
	
	QuanShi(3,"全时"),
	
	MeiTuan(4,"美团"),
	
	HuiJiaChiFan(5,"回家吃饭"),
	
	ShouLu(6,"首旅"),
	
	KouBeiWaiMai(7,"口碑外卖"),
	
	ShanSongMoShi(8,"闪送模式");
	private int value = 0;
	private String desc;

	private GroupEnum(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}

	public int value() {
		return this.value;
	}

	public String desc() {
		return this.desc;
	}

	public static GroupEnum getEnum(int index) {
		for (GroupEnum c : GroupEnum.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
