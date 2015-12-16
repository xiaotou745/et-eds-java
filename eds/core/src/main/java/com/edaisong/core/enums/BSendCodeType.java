package com.edaisong.core.enums;

/**
 * B端获取验证码  类型参数
 * @author CaoHeYang
 * @date 20151110
 */
public enum BSendCodeType {
	/**
	 *注册
	 */
	Register(1,"注册"),
    /**
	 *修改密码
	 */
	ModifyPwd(2,"修改密码"),
    /**
	 *忘记密码
	 */
	ForgetPwd(3,"忘记密码"),
	/**
	 *修改绑定手机号验证当前手机号
	 */
    ModifyPhone(4,"修改绑定手机号验证当前手机号"),
    /**
	 * 修改绑定手机号验证新手机号
	 */
    ModifyPhoneNewPhone(5,"修改绑定手机号验证新手机号"),
    
	NoLoginPhone(6,"未登录发单");
	
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
