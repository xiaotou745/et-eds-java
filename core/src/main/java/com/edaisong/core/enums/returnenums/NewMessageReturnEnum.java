package com.edaisong.core.enums.returnenums;

/**
 * app 登陆后获取顶端未读公告 返回值
 * @author CaoHeYang
 * @date 20150909
 */
public enum NewMessageReturnEnum {
	/**
	 * 无未读最新消息
	 */
	NoNews(2, "无未读最新消息");
	private int value = 0;
	private String desc;
	private NewMessageReturnEnum(int value, String desc) { // 必须是private的，否则编译错误
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
