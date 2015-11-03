package com.edaisong.core.enums.returnenums;
/**
 * 骑士端获取店内任务 返回值
 * @version 20151103
 * @author CaoHeYang
 * @date 20151103
 */
public enum GetMyServiceClientersReturnEnum {
	/**
	 * 商户id有误
	 */
	BusinessIdError(2,"商户id有误"),
	/**
	 *服务骑士状态录入有误
	 */
	AuditStatusError(3,"服务骑士状态录入有误");
	private int value = 0;
	private String desc;
	private GetMyServiceClientersReturnEnum(int value, String desc) { // 必须是private的，否则编译错误
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
