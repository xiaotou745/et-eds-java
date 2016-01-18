package com.edaisong.core.enums.returnenums;

/**
 * 新增第三方集团
 * @author CaoHeYang
 * @date 20160118
 */
public enum GroupAddReturnEnum {

	GroupNameError(-1, "集团名称不能为空！"),
	GroupExists(-2, "该第三方公司已经存在！"),
	ServiceError(-3, "服务器异常！");
	private int value = 0;
	private String desc;

	private GroupAddReturnEnum(int value, String desc) { // 必须是private的，否则编译错误
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
