package com.edaisong.entity.resp;

import java.util.List;

import com.edaisong.entity.domain.QueryOrder;

/**
 * B 端首页 订单列表
 * 
 * @author CaoHeYang
 * @date 20150910
 */
public class QueryOrderBResp {

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
	private int waitPayCount; //待支付数量
	private int hadCompleteCount; //已完成数量
	private int ssCancelOrder; //闪送模式待支付取消订单
	
/**
 *  待接单数量
 * @return
 */
	public int getNewCount() {
		return newCount;
	}
/**
 * 待接单数量
 * @param newCount
 */
	public void setNewCount(int newCount) {
		this.newCount = newCount;
	}
/**
 * 待取货数量
 * @return
 */
	public int getDeliveryCount() {
		return deliveryCount;
	}
/**
 * 待取货数量
 * @param deliveryCount
 */
	public void setDeliveryCount(int deliveryCount) {
		this.deliveryCount = deliveryCount;
	}
/**
 * 配送中数量
 * @return
 */
	public int getTakingCount() {
		return takingCount;
	}
/**
 * 配送中
 * @param takingCount
 */
	public void setTakingCount(int takingCount) {
		this.takingCount = takingCount;
	}

public int getWaitPayCount() {
	return waitPayCount;
}

public void setWaitPayCount(int waitPayCount) {
	this.waitPayCount = waitPayCount;
}

public int getHadCompleteCount() {
	return hadCompleteCount;
}

public void setHadCompleteCount(int hadCompleteCount) {
	this.hadCompleteCount = hadCompleteCount;
}

public int getSsCancelOrder() {
	return ssCancelOrder;
}

public void setSsCancelOrder(int ssCancelOrder) {
	this.ssCancelOrder = ssCancelOrder;
}
}
