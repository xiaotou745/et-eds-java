package com.edaisong.toolscore.enums.returnenums;

/**
 * app端查询订单返回状态
 * @author CaoHeYang
 * @date 20150911
 */
public enum QueryOrderReturnEnum {
	/**
	 * 账号状态出错
	 */
	ErrStatus(-500,"商铺尚未审核通过");
	private int value = 0;
	private String desc;
	private QueryOrderReturnEnum(int value, String desc) { // 必须是private的，否则编译错误
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
