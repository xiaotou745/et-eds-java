package com.edaisong.toolscore.enums;

public enum CancelOrderBusinessReturnEnum {
	/**
	 * 订单号不能为空
	 */
	OrderEmpty(-1,"订单号不能为空"),
	
	/**
	 * 取消订单失败,订单已被抢或订单不存在
	 */
	CancelOrderError(-2,"取消订单失败,订单已被抢或订单不存在");
	private int value = 0;
	private String desc;

	private CancelOrderBusinessReturnEnum(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}

	public int value() {
		return this.value;
	}

	public String desc() {
		return this.desc;
	}

	public static CancelOrderBusinessReturnEnum getEnum(int index) {
		for (CancelOrderBusinessReturnEnum c : CancelOrderBusinessReturnEnum.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
