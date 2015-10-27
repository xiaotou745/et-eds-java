package com.edaisong.toolscore.enums.returnenums;

public enum AccountBillReturnEnum {

	Success(1, "获取成功"),
	ParError(2, "参数有误");
	private int value = 0;
	private String desc;
	private AccountBillReturnEnum(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

}

