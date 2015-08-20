package com.edaisong.core.enums;

/**
 * 订单状态  0:订单新增 1：订单已完成 2：订单已接单 3：订单已取消  4:取货中  30 待接入订单
 * @author CaoHeYang
 *
 */
public enum OrderStatus {
	/**
	 * 订单新增 
	 */
	New(0, "待接单"),
	/**
	 * 订单已完成 
	 */
	Complite(1, "已完成"),
	/**
	 * 订单已接单
	 */
	Delivery(2, "已接单 "),
	/**
	 * 订单已取消
	 */
	Cancel(3, "已取消"),
	/**
	 * 取货中
	 */
	Taking(4, "取货中"),
	/*
	 * 待接入
	 */
	Wait(30, "待接入")
	;
	private int value = 0;
	private String desc;
	private OrderStatus(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static OrderStatus getEnum(int index) {
		for (OrderStatus c : OrderStatus.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
