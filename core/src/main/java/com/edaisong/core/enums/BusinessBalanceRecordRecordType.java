package com.edaisong.core.enums;

/**
 * 商户余额流水 交易类型(1：发布订单 2：取消订单 3：提款申请 4：提款拒绝 5：打款失败 6：系统奖励 7：系统赔偿 8：订单菜品费 9：充值
 * 10余额清零 11 手续费)
 * 
 * @author CaoHeYang
 *
 */
public enum BusinessBalanceRecordRecordType {

	/**
	 * 发布订单
	 */
	PublishOrder(1, "发布订单",1),
	/**
	 * 取消订单
	 */
	CancelOrder(2, " 取消订单",2),
	/**
	 * 提款申请
	 */
	WithdrawApply(3, "提款申请",1),

	/**
	 * 提款拒绝
	 */
	WithdrawRefuse(4, "提款拒绝",2),
	/**
	 * 打款失败
	 */
	PayFailure(5, "打款失败",2),
	/**
	 * 系统奖励
	 */
	//SystemReward(6, "系统奖励"),
	/**
	 * 系统赔偿
	 */
	//SystemCompensation(7, "系统赔偿"),
	/**
	 * 订单菜品费
	 */
	OrderMeals(8, "订单菜品费",2),
	/**
	 * 充值
	 */
	Recharge(9, "充值",2),
	/**
	 * 系统金额归零
	 */
	//SysClearMoney(10, "系统金额归零"),
	/**
	 * 手续费
	 */
	ProcedureFee(11, "手续费",1),
	/**
	 * 充值赠送
	 */
	RechargeGift(12, "充值赠送",2);

	private int value = 0;
	private String desc;
	private int  type;

	/**
	 * 
	 * @param value 值
	 * @param desc 文本
	 * @param type 类型 0 出账入账 1出账 2入账
	 */
	private BusinessBalanceRecordRecordType(int value, String desc,int type) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}

	/**
	 * 值
	 * @return
	 */
	public int value() {
		return this.value;
	}

	/**
	 * 文本
	 * @return
	 */
	public String desc() {
		return this.desc;
	}
	/**
	 * 0 出账入账 1出账 2入账
	 * @return
	 */
	public int type() {
		return this.type();
	}
	public static BusinessBalanceRecordRecordType getEnum(int index) {
		for (BusinessBalanceRecordRecordType c : BusinessBalanceRecordRecordType
				.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
