package com.edaisong.entity.common;

import com.edaisong.core.enums.OrderFrom;

/**
 * api-http 返回值公用 enum
 * @author CaoHeYang
 * @date 2015-0909
 */
public enum HttpReturnRnums {
	/**
	 * 成功
	 */
	Success(1,"成功"),
	/**
	 * 参数错误
	 */
	ParaError(-2,"参数错误");
	private int value = 0;
	private String desc;
	private HttpReturnRnums(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static HttpReturnRnums getEnum(int index) {
		for (HttpReturnRnums c : HttpReturnRnums.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
