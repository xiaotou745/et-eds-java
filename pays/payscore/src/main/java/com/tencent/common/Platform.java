package com.tencent.common;


public enum Platform {
	EDSBApp(1,"易代送商家端APP支付"),
	EDSBQr(2,"易代送商家端扫码支付"),
	EDSSSBApp(3,"易代送闪送商家端APP支付"),
	EDSSSBQr(4,"易代送闪送商家端扫码支付"),
	EDSCApp(5,"易代送骑士端APP支付"),
	EDSCQr(6,"易代送骑士端扫码支付"),
	EDSQQSCApp(7,"轻骑士APP支付"),
	EDSQQSCQr(8,"轻骑士扫码支付");
	private int value = 0;
	private String desc;
	private Platform(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static Platform getEnum(int index) {
		for (Platform c : Platform.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
