package com.edaisong.core.enums;

/**
 *  Signalr  推送消息类型  
 * @author CaoHeYang
 * date 20160104
 */
public enum SignalrPushMessageType {
	/**
	 * 里程计算推送订单 
	 */
	ShanSongPushOrder(1,"里程计算推送订单");
	private int value = 0;
	private String desc;
	private SignalrPushMessageType(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static SignalrPushMessageType getEnum(int index) {
		for (SignalrPushMessageType c : SignalrPushMessageType.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
