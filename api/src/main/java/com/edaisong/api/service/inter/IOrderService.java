package com.edaisong.api.service.inter;
import java.util.Date;
import java.util.List;

import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.BusiPubOrderTimeStatisticsModel;
import com.edaisong.entity.domain.BusinessOrderSummaryModel;
import com.edaisong.entity.domain.OrderListModel;
import com.edaisong.entity.domain.OrderMapDetail;
import com.edaisong.entity.req.CancelOrderBusinessReq;
import com.edaisong.entity.req.OrderDetailBusinessReq;
import com.edaisong.entity.req.OrderReq;
import com.edaisong.entity.req.PagedOrderSearchReq;
import com.edaisong.entity.resp.CancelOrderBusinessResp;
import com.edaisong.entity.resp.OrderDetailBusinessResp;
import com.edaisong.entity.resp.OrderResp;

public interface IOrderService {
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
	  * 商家后台 订单详情页面完整数据 
	  * @param para 查询条件
	  * @author CaoHeYang 
	  * @Date 20150804
	  * @return
	  */
	OrderDetailBusinessResp  getOrderDetailBusiness(OrderDetailBusinessReq para);
	 
	/**
	 * 商户取消订单功能 
	 * @param req 参数
	 * @author CaoHeYang
	 * @Date 20150804
	 * @return
	 */
	CancelOrderBusinessResp cancelOrderBusiness(CancelOrderBusinessReq req);
	
	/**
	 * 商户发布订单
	 * @param req 参数
	 * @author 胡灵波
	 * @Date 2015年8月6日 09:51:47
	 * @return
	 */
	OrderResp AddOrder(OrderReq  req);
	
	/**
	 * 获得商家订单概览信息
	 * @param 商家Id
	 * @author pengyi
	 * @date 20150818
	 * @return
	 */
	BusinessOrderSummaryModel getBusinessOrderSummary(int businessId);
	
	/**
	 * 获得商家发布订单时间统计
	 * @param 商家Id
	 * @param 开始时间
	 * @param 结束时间
	 * @author pengyi
	 * @date 20150819
	 * @return
	 */
	List<BusiPubOrderTimeStatisticsModel> getBusiPubOrderTimeStatistics(int businessId,Date startTime,Date endTime);
}
