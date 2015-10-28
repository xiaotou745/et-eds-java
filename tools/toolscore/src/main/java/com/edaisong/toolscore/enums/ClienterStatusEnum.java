package com.edaisong.toolscore.enums;

/**
 * 骑士审核状态
 * @author CaoHeYang
 * @date 20150911
 */
public enum ClienterStatusEnum {
	/**
	 * 审核拒绝
	 */
    WaitAudit(0,"审核拒绝"),
    /**
	 * 审核通过
	 */
    AuditPass(1,"审核通过"),
    /**
	 * 2未审核
	 */
    AuditRefuse (2,"未审核"),
    /**
	 * 审核中
	 */
    InAudit (3,"审核中");

	private int value = 0;
	private String desc;
	private ClienterStatusEnum(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static ClienterStatusEnum getEnum(int index) {
		for (ClienterStatusEnum c : ClienterStatusEnum.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
