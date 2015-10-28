package com.edaisong.toolscore.enums.returnenums;
/**
 * api-http 返回值公用 enum
 * @author CaoHeYang
 * @date 2015-0909
 */
public enum HttpReturnEnums {
	/**
	 * 成功
	 */
	Success(1,"成功"),
	/**
	 * 系统错误
	 */
	SystemError(-1,"系统错误"),
	/**
	 * 参数错误
	 */
	ParaError(-2,"参数错误");
	private int value = 0;
	private String desc;
	private HttpReturnEnums(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static HttpReturnEnums getEnum(int index) {
		for (HttpReturnEnums c : HttpReturnEnums.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
