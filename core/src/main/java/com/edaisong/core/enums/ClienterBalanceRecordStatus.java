package com.edaisong.core.enums;

/**
 * 骑士余额流水状态(1、交易成功 2、交易中）
 * 
 * @author CaoHeYang
 *
 */
public enum ClienterBalanceRecordStatus {

	/**
	 * 交易成功
	 */
	Success(1, "交易成功"),
	/**
	 * 交易中
	 */
	Tradeing(2, "交易中");
	private int value = 0;
	private String desc;

	private ClienterBalanceRecordStatus(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}

	public int value() {
		return this.value;
	}

	public String desc() {
		return this.desc;
	}

	public static ClienterBalanceRecordStatus getEnum(int index) {
		for (ClienterBalanceRecordStatus c : ClienterBalanceRecordStatus.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
