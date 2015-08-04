package com.edaisong.api.dal.dao.inter;

import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.OrderDetailBusiness;
import com.edaisong.entity.domain.OrderListModel;
import com.edaisong.entity.domain.OrderMapDetail;
import com.edaisong.entity.req.OrderDetailBusinessReq;
import com.edaisong.entity.req.PagedOrderSearchReq;

public interface IOrderDao {

	/**
	 * 后台订单列表页面 
	 * @author CaoHeYang
	 * @Date 20150728
	 * @param search 查询条件实体
	 * @return
	 */
	PagedResponse<OrderListModel> getOrders(PagedOrderSearchReq search);
	
	 /**
	  * 根据orderID获取订单地图数据
	  * @param orderId
	  * @author CaoHeYang
	  * @Date 20150730
	  * @return
	  */
	 OrderMapDetail  getOrderMapDetail(long orderId);
	 
	 /**
	  * 商家后台 订单详情页面基础数据 
	  * @param para 查询条件
	  * @author CaoHeYang 
	  * @Date 20150804
	  * @return
	  */
	 OrderDetailBusiness  getOrderDetailBusiness(OrderDetailBusinessReq para);
	
}