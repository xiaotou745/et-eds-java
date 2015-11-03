package com.edaisong.core.enums.returnenums;

/**
 * 商戶端 我的骑士 申请中 同意/拒绝功能
 * 
 * @author CaoHeYang
 * @date 20151103
 */
public enum OptBindClienterReturnEnum {
	/**
	 * 绑定关系不存在
	 */
	RelationIdError(2, "绑定关系不存在"),
	/**
	 * 商户不存在
	 */
	BusinessIdError(3, "商户不存在"),
	/**
	 * 操作状态有误
	 */
	AuditStatusError(4, "操作状态有误"),
	/**
	 * 该申请无效或已被处理
	 */
	StatusError(5, "该申请无效或已被处理");
	private int value = 0;
	private String desc;

	private OptBindClienterReturnEnum(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}

	public int value() {
		return this.value;
	}

	public String desc() {
		return this.desc;
	}

}
