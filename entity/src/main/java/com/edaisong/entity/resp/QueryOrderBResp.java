package com.edaisong.entity.resp;

import java.util.List;

import com.edaisong.entity.domain.QueryOrder;

/**
 * B 端首页 订单列表
 * @author CaoHeYang
 * @date 20150910
 */
public class QueryOrderBResp {

    private  List<QueryOrder> orders;

	public List<QueryOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<QueryOrder> orders) {
		this.orders = orders;
	}
}
