package com.edaisong.core.enums;

/**
 * 订单审核状态枚举
 * @author CaoHeYang
 * @date 20150831
 */
public enum OrderAuditStatus {
    /**
     * 待审
     */
    NotAudit (0,"待审核"),
    /**
     * 审核通过
     */
    Through (1,"审核通过"),
	/**
	 * 审核拒绝
	 */
    Refuse (2,"审核拒绝");
	private int value = 0;
	private String desc;

	private OrderAuditStatus(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}

	public int value() {
		return this.value;
	}

	public String desc() {
		return this.desc;
	}

	public static OrderAuditStatus getEnum(int index) {
		for (OrderAuditStatus c : OrderAuditStatus
				.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
