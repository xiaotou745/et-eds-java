package com.edaisong.core.enums;

/**
 * 订单来源，默认0表示E代送B端订单，1聚网客,2万达，3全时，4美团
 * @author zhaohailong
 *
 */
public enum OrderPlatform {
	/**
	 * E代送商户版
	 */
	EDaiSong(1, "E代送商户版"),	
	/**
	 * E代送智能调度
	 */
	FastOrder(2, "E代送智能调度"),
	/**
	 * E代送
	 */
	FlashOrder(3, "E代送");
	private int value = 0;
	private String desc;
	private OrderPlatform(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static OrderPlatform getEnum(int index) {
		for (OrderPlatform c : OrderPlatform.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
