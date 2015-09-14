package com.edaisong.entity.resp;

import java.util.List;

import com.edaisong.entity.domain.QueryOrder;

/**
 * B 端首页 订单列表
 * 
 * @author CaoHeYang
 * @date 20150910
 */
public class QueryOrderCResp {

	private List<QueryOrder> orders;

	public List<QueryOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<QueryOrder> orders) {
		this.orders = orders;
	}

	private int newCount;
	private int deliveryCount;
	private int takingCount;

	/**
	 * 待取货数量
	 * 
	 * @return
	 */
	public int getDeliveryCount() {
		return deliveryCount;
	}

	/**
	 * 待取货数量
	 * 
	 * @param deliveryCount
	 */
	public void setDeliveryCount(int deliveryCount) {
		this.deliveryCount = deliveryCount;
	}

	/**
	 * 配送中数量
	 * 
	 * @return
	 */
	public int getTakingCount() {
		return takingCount;
	}

	/**
	 * 配送中
	 * 
	 * @param takingCount
	 */
	public void setTakingCount(int takingCount) {
		this.takingCount = takingCount;
	}
}
