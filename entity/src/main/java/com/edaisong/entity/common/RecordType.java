package com.edaisong.entity.common;
/**
 * 商家/骑士  流水 交易类型
 * @author CaoHeYang
 * @date 20150909
 */
public class RecordType {
	private int code;
	private String desc;
	private int type;

	/**
	 * 交易类型码
	 * 
	 * @return
	 */
	public int getCode() {
		return code;
	}

	/**
	 * 交易类型码
	 * 
	 * @param code
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * 类型描述
	 * 
	 * @return
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * 类型描述
	 * 
	 * @param desc
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * 0 出账入账 1出账 2入账
	 * 
	 * @return
	 */
	public int getType() {
		return type;
	}

	/**
	 * 0 出账入账 1出账 2入账
	 * 
	 * @param type
	 */
	public void setType(int type) {
		this.type = type;
	}
}
