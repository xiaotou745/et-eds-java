package com.edaisong.core.enums.returnenums;
/**
 *  商家端修改绑定手机号
 * @author CaoHeYang
 * @date 20151110
 */
public enum BusinessModiyPhoneReturnEnum {
	/**
	 * 
	 */
	Success(200,"Success"),
	/**
	 * 手机号码无效
	 */
	PhoneError(1001, "手机号码无效"),
	/**
	 * 该账号已经存在
	 */
	PhoneExists(1003,"该账号已经存在"),
	/**
	 * 该账号不存在
	 */
	PhoneNotExists(1004,"该账号不存在"),
	/**
	 * 验证码错误
	 */
	CodeError(1005,"验证码错误"),
	/**
	 * 验证码不能为空
	 */
	VerCodeNull(1006, "验证码不能为空"),
	/**
	 * 商户id错误
	 */
	BusinessId(1007, "商户id错误"),
	/**
	 * 修改门店手机账号失败
	 */
	ModifyError(1008, "修改门店手机账号失败"),
	/**
	 * 验证码超时
	 */
	CodeTimeOut(1009, "验证码超时");
	
	private int value = 0;
	private String desc;
	private BusinessModiyPhoneReturnEnum(int value, String desc) { 
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static BusinessModiyPhoneReturnEnum getEnum(int index) {
		for (BusinessModiyPhoneReturnEnum c : BusinessModiyPhoneReturnEnum.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
