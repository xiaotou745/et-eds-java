package com.edaisong.entity.domain;

/**
 * Signalr 推送消息
 * 
 * @author CaoHeYang
 * @date 20160104
 * @param <T>
 */
public class SignalrPushMessage<T> {
	private int type;
	private T model;

	public SignalrPushMessage() {
	}

	public SignalrPushMessage(int type, T model) {
		this.type = type;
		this.model = model;
	}

	/**
	 * 推送消息类型 1 里程计算推送订单
	 * 
	 * @return
	 */
	public int getType() {
		return type;
	}

	/**
	 * 推送消息类型 1 里程计算推送订单
	 * 
	 * @param type
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * 消息主体
	 * 
	 * @return
	 */
	public T getModel() {
		return model;
	}

	/**
	 * 消息主体
	 * 
	 * @param model
	 */
	public void setModel(T model) {
		this.model = model;
	}

}
