package com.edaisong.core.enums.returnenums;
/**
 * 骑士端获取店内任务 返回值
 * @author CaoHeYang
 * @date 20151102
 */
public enum InStoreTaskReturnEnum {
	/**
	 * 骑士id有误
	 */
	ClienterIdError(2,"骑士id有误"),
	/**
	 * 位置信息有误
	 */
	LocationError(3,"位置信息有误");
	private int value = 0;
	private String desc;
	private InStoreTaskReturnEnum(int value, String desc) { // 必须是private的，否则编译错误
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
