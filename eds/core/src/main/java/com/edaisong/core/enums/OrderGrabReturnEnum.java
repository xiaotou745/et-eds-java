package com.edaisong.core.enums;

public enum OrderGrabReturnEnum {
	/**
	 * 
	 */
	Success(1,""),
	/**
	 * 验证成功
	 */
	
	VerificationSuccess(2,""),
	/**
	 * 收货人手机号不能为空
	 */
	RecevicePhoneIsNULL(-1, "收货人手机号不能为空"), 
	/**
	 * 收货人地址不能为空
	 */
	ReceviceAddressIsNULL(-2, "收货人地址不能为空"),
	/**
	 *订单数量不符合规则
	 */
	OrderCountError(-3, "订单数量不符合规则"),
	/**
	 * 抱歉，子订单金额不可低于5元
	 */
	AmountLessThanTen(-4, "抱歉，子订单金额不可低于5元"), 
	/**
	 * 抱歉，子订单金额不可高于1000元
	 */
	AmountMoreThanFiveThousand(-5, "抱歉，子订单金额不可高于1000元"),
	/**
	 * 订单金额与子订单总金额不一致
	 */
	AmountIsNotEqual(-6, "订单金额与子订单总金额不一致"),
	/**
	 * 获取商户信息失败
	 */
	BusinessEmpty(-7, "获取商户信息失败"),
	/**
	 * 您的余额不足，请及时充值!
	 */
	BusiBalancePriceLack(-8, "您的余额不足，请及时充值!"),
	/**
	 * 您已被取消发单资格
	 */
	HadCancelQualification(-9, "您已被取消发单资格"),
	
	/**
	 * 订单已经存在
	 */
	OrderHasExist(-10, "订单已经存在"),
	/**
	 * 您的余额和集团的余额都不足，请及时充值!
	 */
	GroupBalancePriceLack(-11, "您的余额和集团的余额都不足，请及时充值!"),
	
	StrategyErr(-12, "策略错误"),
	
	/**
	 * 获取骑士信息失败
	 */
	ClienterEmpty(-13, "获取骑士信息失败"),
	/**
	 *获取抢单信息失败
	 */
	OrderGrabEmpty(-14, "获取抢单信息失败"),
	
	/**
	 *获取抢单子表信息失败
	 */
	OrderGrabChildEmpty(-15, "获取抢单子表信息失败");

	private int value = 0;
	private String desc;

	private OrderGrabReturnEnum(int value, String desc) { // 必须是private的，否则编译错误
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
