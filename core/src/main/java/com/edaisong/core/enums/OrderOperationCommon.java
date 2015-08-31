package com.edaisong.core.enums;

/**
 * 订单操作日志 审核描述状态
 * 
 * @author CaoHeYang
 * @date 20150831
 */
public enum OrderOperationCommon {
	/**
	 * 审核通过
	 */
	AuditStatusOk(1, "审核通过"),
	/**
	 * 审核拒绝
	 */
	AuditStatusRefuse(1, "审核拒绝");
	private int value = 0;
	private String desc;

	private OrderOperationCommon(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}

	public int value() {
		return this.value;
	}

	public String desc() {
		return this.desc;
	}

	public static OrderOperationCommon getEnum(int index) {
		for (OrderOperationCommon c : OrderOperationCommon.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
