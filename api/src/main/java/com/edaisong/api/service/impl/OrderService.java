package com.edaisong.api.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dal.dao.inter.IOrderChildDao;
import com.edaisong.api.dal.dao.inter.IOrderDao;
import com.edaisong.api.dal.dao.inter.IOrderOtherDao;
import com.edaisong.api.service.inter.IOrderService;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.OrderListModel;
import com.edaisong.entity.domain.OrderMapDetail;
import com.edaisong.entity.req.OrderDetailBusinessReq;
import com.edaisong.entity.req.PagedOrderSearchReq;
import com.edaisong.entity.resp.OrderDetailBusinessResp;

@Service
public class OrderService implements IOrderService {

	@Autowired 
	private IOrderDao orderDao;
	@Autowired
	private IOrderOtherDao orderOtherDao;

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


	 /**
	  * 商家后台 订单详情页面完整数据 
	  * @param para 查询条件
	  * @author CaoHeYang 
	  * @Date 20150804
	  * @return
	  */
	@Override
	public OrderDetailBusinessResp getOrderDetailBusiness(
			OrderDetailBusinessReq para) {
		OrderDetailBusinessResp modelsBusinessResp=new OrderDetailBusinessResp();
		modelsBusinessResp.setOrderModel(orderDao.getOrderDetailBusiness(para));
		//modelsBusinessResp.setOrderOthers(orderOtherDao.getOrderChildByOrderInfo(para.getOrderNo(), para.getBusinessId()));
		return modelsBusinessResp;
	}

}
