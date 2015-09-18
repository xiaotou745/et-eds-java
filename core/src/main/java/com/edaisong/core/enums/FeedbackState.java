package com.edaisong.core.enums;

public enum FeedbackState  {

	/**
	 * 成功
	 */
	Success(1, "您的反馈我们已经收到，感谢您的参与！"),
	/**
	 * 系统错误
	 */
	SystemError(-1, "系统错误");

	
	private int value = 0;
	private String desc;
	private FeedbackState(int value, String desc) { 
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static FeedbackState getEnum(int index) {
		for (FeedbackState c : FeedbackState.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}

