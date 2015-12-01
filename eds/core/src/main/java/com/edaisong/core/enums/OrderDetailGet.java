package com.edaisong.core.enums;

/**
 * 支付宝批次状态
 * @author CaoHeYang
 * @date 20151020
 */
public enum OrderDetailGet {
	/**
	 * 成功
	 */
	Success(1,""),
	/**
	 * 订单Id不能为空
	 */
	OrderIdIsNULL(-1,"订单Id不能为空");
	private int value = 0;
	private String desc;
	private OrderDetailGet(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static OrderDetailGet getEnum(int index) {
		for (OrderDetailGet c : OrderDetailGet.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
