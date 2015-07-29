package com.edaisong.api.service.inter;

import java.util.List;

import com.edaisong.entity.common.ResponsePageList;
import com.edaisong.entity.domain.OrderListModel;
import com.edaisong.entity.req.OrderSearchWebReq;

public interface IOrderService {
	/**
	 * 后台订单列表页面 
	 * @author CaoHeYang
	 * @Date 20150728
	 * @param search 查询条件实体
	 * @return
	 */
	 ResponsePageList<OrderListModel> getOrders(OrderSearchWebReq search);
}
