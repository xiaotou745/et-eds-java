package com.edaisong.core.enums;

/**
 *
 * @author  pengyi 
 * @date 2015年9月10日 下午4:17:48
 * @version 1.0
 * @parameter
 * @since
 */
public enum CardBindC {

	Success(1,"成功"),
	Exists(2,"该骑士已绑定过金融账号"),
	AccountNotSame(3,"两次录入的金融账号不一致"),
	NameError(4,"帐户名不能为空"),
	NoClienter(5,"骑士不存在"),
	NoCreateBy(6,"创建人名称不存在"),
	SystemError(0,"系统错误"),
	ParamError(-2,"未传参或者参数错误,参数错误时返回所有的错误信息文本");
	
	private int value = 0;
	private String desc;
	private CardBindC(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static CardBindC getEnum(int index) {
		for (CardBindC c : CardBindC.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
