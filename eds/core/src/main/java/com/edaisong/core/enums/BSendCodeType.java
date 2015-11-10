package com.edaisong.core.enums;

/**
 * B端获取验证码  类型参数
 * @author CaoHeYang
 * @date 20151110
 */
public enum BSendCodeType {
	/**
	 *修改绑定手机号验证当前手机号
	 */
    ModifyPhone(1,"修改绑定手机号验证当前手机号"),
    /**
	 * 修改绑定手机号验证新手机号
	 */
    ModifyPhoneNewPhone(2,"修改绑定手机号验证新手机号");
	private int value = 0;
	private String desc;
	private BSendCodeType(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static BSendCodeType getEnum(int index) {
		for (BSendCodeType c : BSendCodeType.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
