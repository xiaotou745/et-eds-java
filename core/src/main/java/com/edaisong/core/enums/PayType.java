package com.edaisong.core.enums;

/**
 * 支付类型(1 支付宝 2 微信 3 网银)
 * @author CaoHeYang
 *
 */
public enum PayType {
	/**
	 * 支付宝
	 */
    ZhiFuBao(1,"支付宝"),
    /**
	 * 微信
	 */
    WeiXin(2,"微信"),
    /**
	 * 网银
	 */
    WangYin (3,"网银"),
    /**
	 * 财付通
	 */
    CaiFuTong (4,"财付通"),
    /**
	 * 百度钱包
	 */
    BaiDuQinBao (5,"百度钱包"),
    /**
   	 * 现金支付
   	 */
    Cash(6,"现金支付");
    
	private int value = 0;
	private String desc;
	private PayType(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static PayType getEnum(int index) {
		for (PayType c : PayType.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
