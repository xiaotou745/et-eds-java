package com.edaisong.core.enums.returnenums;
/**
 * 商家解绑 返回值
 * @author CaoHeYang
 * @date 20151103
 */
public enum RemoveRelationReturnEnum {
	/**
	 * 门店不存在
	 */
	BusinessIdError(2, "门店不存在"),
	/**
	 * 商户不存在
	 */
	ClienterIdError(3, "骑士不存在"),
	/**
	 * 解除绑定原因必须在5-100字符之间
	 */
	RemarkError(4, "解除绑定原因必须在5-100字符之间");
	private int value = 0;
	private String desc;

	private RemoveRelationReturnEnum(int value, String desc) { // 必须是private的，否则编译错误
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
