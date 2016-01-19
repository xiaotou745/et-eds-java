package com.edaisong.core.enums.returnenums;

/**
 * 更新集团状态 
 * @author CaoHeYang
 * @date 20160118
 */
public enum GroupUpdateStatusReturnEnum {

	GroupIdError(-1, "第三方平台id不合法"),
	Error(-2, "更新失败");
	private int value = 0;
	private String desc;

	private GroupUpdateStatusReturnEnum(int value, String desc) { // 必须是private的，否则编译错误
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
