package com.edaisong.core.enums;

public enum ClienterAllowWithdrawRecordType {
	/**
	 * ClienterBalanceRecordRecordType
	 */
	OrderCommission(1, "订单佣金"),

	/**
	 * 提现申请
	 */
	WithdrawApply(3, "提现申请"),

	/**
	 *  余额调整
	 */
	BalanceAdjustment(8, "余额调整"),
	/**
	 * 手续费
	 */
	ProcedureFee(9, "手续费");
	private int value = 0;
	private String desc;

	private ClienterAllowWithdrawRecordType(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}

	public int value() {
		return this.value;
	}

	public String desc() {
		return this.desc;
	}

	public static ClienterAllowWithdrawRecordType getEnum(int index) {
		for (ClienterAllowWithdrawRecordType c : ClienterAllowWithdrawRecordType
				.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
