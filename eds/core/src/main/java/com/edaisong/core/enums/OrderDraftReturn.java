package com.edaisong.core.enums;

/**
 * 闪送发单返回值
 * @author 胡灵波
 * 2015年11月25日 15:39:28
 *
 */
public enum OrderDraftReturn {
	/**
	 * 
	 */
	Success(1,""),
	/**
	 * 验证成功
	 */
	
	VerificationSuccess(2,""),
	/**
	 * 发货人不能为空
	 */
	PubNameIsNULL(-1, "发货人不能为空"), 
	/**
	 * 发货电话不能为空
	 */
	PubPhoneNoIsNULL(-2, "发货电话不能为空"),
	/**
	 * 发货地址不能为空
	 */
	PubAddressIsNULL(-3, "发货地址不能为空"),
	/**
	 *取货状态不能为空
	 */
	TakeTypeIsNULL(-4, "取货状态不能为空"),
	/**
	 * 收货人不能为空
	 */
	ReceviceNameIsNULL(-5, "收货人不能为空"), 		
	
	/**
	 * 收货人电话不能为空
	 */
	RecevicePhoneNoIsNull(-6, "收货人电话不能为空"),
	/**
	 * 收货地址不能为空
	 */
	ReceviceAddressIsNull(-7, "收货地址不能为空"),
	/**
	 * 物品名称不能为空
	 */
	ProductNameIsNull(-8, "物品名称不能为空"),
	/**
	 * 金额不能为空
	 */
	AmountIsNull(-9, "金额不能为空"),
	
	/**
	 * 重量不能为空
	 */
	WeightIsNull(-10, "重量不能为空"),
	
	/**
	 * 距离不能为空
	 */
	KMIsNull(-11, "距离不能为空");	
	

	private int value = 0;
	private String desc;

	private OrderDraftReturn(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}

	public int value() {
		return this.value;
	}

	public String desc() {
		return this.desc;
	}

	public static OrderGrabReturnEnum getEnum(int index) {
		for (OrderGrabReturnEnum c : OrderGrabReturnEnum.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}