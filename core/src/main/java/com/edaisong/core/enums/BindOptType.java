package com.edaisong.core.enums;

/**
 * 绑定类型
 * @author  pengyi 
 * @date 2015年9月9日 下午3:51:01
 * @version 1.0
 * @parameter
 * @since
 */
public enum BindOptType {
	/**
	 * 绑定
	 */
    Bind(1,"绑定"),
    /**
	 * 解除绑定
	 */
    RemoveBind(2,"解除绑定");
	private int value = 0;
	private String desc;
	private BindOptType(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static BindOptType getEnum(int index) {
		for (BindOptType c : BindOptType.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
