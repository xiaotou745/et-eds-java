package com.edaisong.entity.resp;

import java.util.List;

import com.edaisong.entity.OrderChild;
import com.edaisong.entity.OrderOther;
import com.edaisong.entity.domain.OrderDetailBusiness;

/**
 * 商家后台 订单详情页面完整数据 
 * @author CaoHeYang
 * @Date 20150804
 */
public class OrderDetailBusinessResp {
	private List<OrderChild> orderChilds;
	private OrderDetailBusiness orderModel;

	/**
	 * 子订单集合
	 * @return
	 */
	public List<OrderChild> getOrderChilds() {
		return orderChilds;
	}

	/**
	 * 子订单
	 * @param orderOthers
	 */
	public void setOrderChilds(List<OrderChild> orderChilds) {
		this.orderChilds = orderChilds;
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
