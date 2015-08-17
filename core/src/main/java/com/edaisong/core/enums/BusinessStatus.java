package com.edaisong.core.enums;

public enum BusinessStatus {
	/**
	 * 待审核
	 */
    WaitAudit(0,"待审核"),
    /**
	 * 审核通过
	 */
    AuditPass(1,"审核通过"),
    /**
	 * 待审核且未添加地址
	 */
    WaitAuditAndNoAdress (2,"待审核且未添加地址"),
    /**
	 * 审核中
	 */
    InAudit (3,"审核中"),
    /**
	 * 审核拒绝
	 */
    AuditRefuse (4,"审核拒绝");
	private int value = 0;
	private String desc;
	private BusinessStatus(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static BusinessStatus getEnum(int index) {
		for (BusinessStatus c : BusinessStatus.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
