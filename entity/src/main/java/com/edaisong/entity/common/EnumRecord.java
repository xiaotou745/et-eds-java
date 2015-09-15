package com.edaisong.entity.common;
/**
 * 
 * @author 胡灵波
 * @date 2015年9月10日 21:00:50
 */
public class EnumRecord {
	private int code;
	private String desc;

	/**
	 * 枚举key
	 * 
	 * @return
	 */
	public int getCode() {
		return code;
	}

	/**
	 * 枚举key
	 * 
	 * @param code
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * 枚举描述
	 * 
	 * @return
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * 枚举描述
	 * 
	 * @param desc
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}


}
