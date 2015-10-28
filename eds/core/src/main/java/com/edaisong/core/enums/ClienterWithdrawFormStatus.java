package com.edaisong.core.enums;

/**
 * 骑士提现单状态枚举
 * 
 * @author CaoHeYang
 * @date 20151021
 */
public enum ClienterWithdrawFormStatus {
	/**
	 * 待审核
	 */
	WaitAllow(1, "待审核"),
	/**
	 * 审核通过
	 */
	Allow(2, "审核通过"),
	/**
	 * 打款完成
	 */
	Success(3, "打款完成"),
	/**
	 * 打款异常
	 */
	Except(4, "打款异常"),
	/**
	 * 审核拒绝
	 */
	TurnDown(-1, "审核拒绝"),
	/**
	 * 打款失败
	 */
	Error(-2, "打款失败"),
	/**
	 * 打款中
	 */
	Paying(20, "打款中");
	private int value = 0;
	private String desc;

	private ClienterWithdrawFormStatus(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}

	public int value() {
		return this.value;
	}

	public String desc() {
		return this.desc;
	}

	public static ClienterWithdrawFormStatus getEnum(int index) {
		for (ClienterWithdrawFormStatus c : ClienterWithdrawFormStatus.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
