package com.edaisong.core.enums.returnenums;
/**
 * 新增第三方集团appkey配置
 * @author CaoHeYang
 * @date 20160119
 */
public enum GroupAddConfigReturnEnum {
	GroupIdError(-1, "集团ID不能为空！"),
	AppKeyError(-2, "集团AppKey不能为空"),
	AppVersionError(-3, "集团AppVersion不能为空"),
	Error(-4, "服务器错误！");
	private int value = 0;
	private String desc;
	private GroupAddConfigReturnEnum(int value, String desc) { // 必须是private的，否则编译错误
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
