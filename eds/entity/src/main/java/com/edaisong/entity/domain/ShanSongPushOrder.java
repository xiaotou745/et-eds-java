package com.edaisong.entity.domain;

/**
 * 里程计算推单消息结构
 * @author CaoHeYang
 * @date 20160104
 */
public class ShanSongPushOrder {
	private String clienterIds;
	private int orderId;
	private int orderType;

	/**
	 * 显示推单消息的骑士ids   ;id;id;格式
	 * @return
	 */
	public String getClienterIds() {
		return clienterIds;
	}

	/**
	 * 显示推单消息的骑士ids   ;id;id;格式
	 * @param clienterId
	 */
	public void setClienterIds(String clienterIds) {
		this.clienterIds = clienterIds;
	}

	/**
	 * 订单id
	 * @return
	 */
	public int getOrderId() {
		return orderId;
	}

	/**
	 * 订单id
	 * @param orderId
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	/**
	 * 订单状态   1  新订单  2订单已被处理
	 * @return
	 */
	public int getOrderType() {
		return orderType;
	}

	/**
	 * 订单状态   1  新订单  2订单已被处理
	 * @param orderType
	 */
	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}

}
