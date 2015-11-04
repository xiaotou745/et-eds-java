package com.edaisong.core.enums.returnenums;

/**
 * 获取门店发单模式：0 普通模式（默认），1 快单模式   默认0  返回值枚举
 * @author CaoHeYang
 * @date 20151104
 */
public enum GetPushOrderTypeReturnEnum {
	/**
	 * 商户不存在
	 */
	BusinessIdError(2, "商户不存在");
	private int value = 0;
	private String desc;

	private GetPushOrderTypeReturnEnum(int value, String desc) { // 必须是private的，否则编译错误
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
