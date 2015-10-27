package com.edaisong.toolscore.enums;

/**
 * 平台属性：0：商家端;1：配送端;2：服务平台;3：管理后台 4 第三方对接平台
 * 
 * @author CaoHeYang
 * @date 20150827
 */
public enum OrderPlatform {
	/**
	 * 商家端
	 */
	Business(0, "商家端"),
	/**
	 * 配送端
	 */
	Clienter(1, "配送端"),
	/**
	 * 服务平台
	 */
	Service(2, "服务平台"),
	/**
	 * 管理后台
	 */
	Web(3, "管理后台"),
	/**
	 * 第三方对接平台
	 */
	OtherThird(4, "已支付");
	private int value = 0;
	private String desc;

	private OrderPlatform(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}

	public int value() {
		return this.value;
	}

	public String desc() {
		return this.desc;
	}

	public static OrderPlatform getEnum(int index) {
		for (OrderPlatform c : OrderPlatform.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
