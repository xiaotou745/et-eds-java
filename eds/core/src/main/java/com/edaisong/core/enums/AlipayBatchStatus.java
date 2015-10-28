package com.edaisong.core.enums;

/**
 * 支付宝批次状态
 * @author CaoHeYang
 * @date 20151020
 */
public enum AlipayBatchStatus {
	/**
	 * 打款中
	 */
	PlayGame(0,"打款中"),
    /**
	 * 打款完成
	 */
    Success(1,"打款完成");
	private int value = 0;
	private String desc;
	private AlipayBatchStatus(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static AlipayBatchStatus getEnum(int index) {
		for (AlipayBatchStatus c : AlipayBatchStatus.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
