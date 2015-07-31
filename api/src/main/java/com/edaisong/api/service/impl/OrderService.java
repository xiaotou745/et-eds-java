package com.edaisong.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dal.dao.inter.IOrderDao;
import com.edaisong.api.service.inter.IOrderService;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.OrderListModel;
import com.edaisong.entity.domain.OrderMapDetail;
import com.edaisong.entity.req.PagedOrderSearchReq;

@Service
public class OrderService implements IOrderService {

	@Autowired 
	private IOrderDao orderDao;

	/**
	 * 后台订单列表页面 
	 * @author CaoHeYang
	 * @Date 20150728
	 * @param search 查询条件实体
	 * @return
	 */
	@Override
	public PagedResponse<OrderListModel> getOrders(PagedOrderSearchReq search) {
		return orderDao.getOrders(search);
	}

	
	
	/**
	 * 后台订单列表页面 
	 * @author CaoHeYang
	 * @Date 20150728
	 * @param orderid 订单id
	 * @return
	 */
	@Override
	public OrderMapDetail getOrderMapDetail(long orderid) {
		return orderDao.getOrderMapDetail(orderid);
	}

}
