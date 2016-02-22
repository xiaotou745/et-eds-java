package com.edaisong.core.enums;

/**
 * 集团审核状态
 * @author CaoHeYang
 * @date 20160219
 */
public enum GroupAuditStatu {
	/**
	 * 0待审核
	 */
	Wait(0,"待审核"),
    /**
	 *  1审核通过
	 */
	Pass(1,"审核通过"),
	/**
	  2 审核拒绝
	 */
	Refuse(2,"审核拒绝");
	private int value = 0;
	private String desc;
	private GroupAuditStatu(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static GroupAuditStatu getEnum(int index) {
		for (GroupAuditStatu c : GroupAuditStatu.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
