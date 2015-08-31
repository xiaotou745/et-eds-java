package com.edaisong.core.enums;

public enum ClienterBalanceRecordRecordType {

	/**
	 * ClienterBalanceRecordRecordType
	 */
	OrderCommission(1, "订单佣金"),
	/**
	 * 取消订单
	 */
	CancelOrder(2, "取消订单"),
	/**
	 * 提现申请
	 */
	WithdrawApply(3, "提现申请"),
	/**
	 * 提现拒绝
	 */
	WithdrawRefuse(4, "提现拒绝"),
	/**
	 * 打款失败
	 */
	PayFailure(5, "打款失败"),
	/**
	 * 系统奖励
	 */
	SystemReward(6, "系统奖励"),
	/**
	 * 系统赔偿
	 */
	SystemCompensation(7, "系统赔偿"),
	/**
	 * 余额调整
	 */
	BalanceAdjustment(8, "余额调整"),
	/**
	 * 手续费
	 */
	ProcedureFee(9, "手续费"),
	/**
	 * 现金提现
	 */
	CashWithdraw(10, "现金提现"),
	/**
	 * 异常订单
	 */
	Abnormal(11, "异常订单");
	private int value = 0;
	private String desc;

	private ClienterBalanceRecordRecordType(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}

	public int value() {
		return this.value;
	}

	public String desc() {
		return this.desc;
	}

	public static ClienterBalanceRecordRecordType getEnum(int index) {
		for (ClienterBalanceRecordRecordType c : ClienterBalanceRecordRecordType
				.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
