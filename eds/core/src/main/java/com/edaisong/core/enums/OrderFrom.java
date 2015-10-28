package com.edaisong.core.enums;

/**
 * 订单来源，默认0表示E代送B端订单，1聚网客,2万达，3全时，4美团
 * @author zhaohailong
 *
 */
public enum OrderFrom {
	/**
	 * E代送B端
	 */
	EDaiSong(0, "B端"),
	/**
	 * 聚网客
	 */
	JuWangKe(1, "聚网客"),
	/**
	 * 万达
	 */
	WanDa(2, "万达"),
	/**
	 * 全时
	 */
	QuanShi(3, "全时"),
	/**
	 * 美团
	 */
	MeiTuan(4, "美团"),
	/**
	 * 回家吃饭
	 */
	HomeForDinner(5, "回家吃饭"),
	/**
	 * 首旅
	 */
	Tourism(6, "首旅"),
	/**
	 * 商家版后台
	 */
	BusinessWeb(99, "商户web版"),
	;
	private int value = 0;
	private String desc;
	private OrderFrom(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static OrderFrom getEnum(int index) {
		for (OrderFrom c : OrderFrom.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
