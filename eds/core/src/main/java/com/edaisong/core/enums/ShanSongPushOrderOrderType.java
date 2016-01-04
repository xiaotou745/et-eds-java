package com.edaisong.core.enums;

/**
 * 里程计算推单  订单消息类型
 * @author CaoHeYang
 * @date 20150104
 */
public enum ShanSongPushOrderOrderType {
	/**
	 * 新订单 
	 */
	New(1,"新订单 "),
	/**
	 * 订单已被处理
	 */
	Other(1,"订单已被处理");
	private int value = 0;
	private String desc;
	private ShanSongPushOrderOrderType(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static ShanSongPushOrderOrderType getEnum(int index) {
		for (ShanSongPushOrderOrderType c : ShanSongPushOrderOrderType.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
