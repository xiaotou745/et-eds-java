package com.edaisong.toolscore.enums;

/**
 * 骑士余额流水 交易类型    * 0 出账入账 1出账 2入账
 * @author CaoHeYang
 *
 */
public enum ClienterBalanceRecordRecordType {

	/**
	 * 订单佣金
	 */
	OrderCommission(1, "订单佣金",2),
	/**
	 * 取消订单
	 */
	CancelOrder(2, "取消订单",1),
	/**
	 * 提现申请
	 */
	WithdrawApply(3, "提现申请",1),
	/**
	 * 提现拒绝
	 */
	WithdrawRefuse(4, "提现拒绝",2),
	/**
	 * 打款失败
	 */
	PayFailure(5, "打款失败",2),
	/**
	 * 系统奖励
	 */
	SystemReward(6, "系统奖励",2),
	/**
	 * 系统赔偿
	 */
	//SystemCompensation(7, "系统赔偿",2),
	/**
	 * 余额调整
	 */
	BalanceAdjustment(8, "余额调整",0),
	/**
	 * 手续费
	 */
	ProcedureFee(9, "手续费",1),
	/**
	 * 现金提现
	 */
	CashWithdraw(10, "现金提现",1),
	/**
	 * 异常订单
	 */
	Abnormal(11, "异常订单",1),
	/*
	 * 骑士冻结余额
	 */
	ForzenBalance(12,"冻结金额",1),
	
	UnfreezeBalance(13,"解冻金额",1);
	private int value = 0;
	private String desc;
	private int  type;
	/**
	 * 
	 * @param value 值
	 * @param desc 文本
	 * @param type 类型 0 出账入账 1出账 2入账
	 */
	private ClienterBalanceRecordRecordType(int value, String desc,int type) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
		this.type=type;
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
		return this.type;
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
