package com.edaisong.entity.resp;

import java.util.List;

import com.edaisong.entity.OrderOther;
import com.edaisong.entity.domain.OrderDetailBusiness;

/**
 * 商家后台 订单详情页面完整数据 
 * @author CaoHeYang
 * @Date 20150804
 */
public class OrderDetailBusinessResp {
	private List<OrderOther> orderOthers;
	private OrderDetailBusiness orderModel;

	/**
	 * 子订单集合
	 * @return
	 */
	public List<OrderOther> getOrderOthers() {
		return orderOthers;
	}

	/**
	 * 子订单
	 * @param orderOthers
	 */
	public void setOrderOthers(List<OrderOther> orderOthers) {
		this.orderOthers = orderOthers;
	}

	/**
	 * 订单 基本详情
	 * @return
	 */
	public OrderDetailBusiness getOrderModel() {
		return orderModel;
	}

	/**
	 * 订单基本详情
	 * @param orderModel
	 */
	public void setOrderModel(OrderDetailBusiness orderModel) {
		this.orderModel = orderModel;
	}

}
