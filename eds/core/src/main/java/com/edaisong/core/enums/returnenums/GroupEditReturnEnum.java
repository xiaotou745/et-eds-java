package com.edaisong.core.enums.returnenums;

/**
 * 修改第三方平台
 * @author CaoHeYang
 * @date 20160119
 */
public enum GroupEditReturnEnum {
	GroupNameError(-1, "集团名称不能为空！"),
	ServiceError(-3, "服务器异常！");
	private int value = 0;
	private String desc;

	private GroupEditReturnEnum(int value, String desc) { // 必须是private的，否则编译错误
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
