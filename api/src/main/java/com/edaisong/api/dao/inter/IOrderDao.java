package com.edaisong.api.dao.inter;

import java.util.Date;
import java.util.List;

import com.edaisong.entity.Order;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.BusiPubOrderTimeStatisticsModel;
import com.edaisong.entity.domain.BusinessOrderSummaryModel;
import com.edaisong.entity.domain.OrderDetailBusiness;
import com.edaisong.entity.domain.OrderListModel;
import com.edaisong.entity.domain.OrderMapDetail;
import com.edaisong.entity.req.OrderDetailBusinessReq;
import com.edaisong.entity.req.PagedCustomerSearchReq;
import com.edaisong.entity.req.PagedOrderSearchReq;

public interface IOrderDao {

	int insert(Order record);
	/**
	 * 后台订单列表页面
	 * 
	 * @author CaoHeYang
	 * @Date 20150728
	 * @param search
	 *            查询条件实体
	 * @return
	 */
	PagedResponse<OrderListModel> getOrders(PagedOrderSearchReq search);

	/**
	 * 商家中心订单列表页面右上角自定义查询 
	 * @author zhaohailong
	 * @Date 20150821
	 * @param search 查询参数：可能为订单号，骑士手机号，骑士姓名
	 * @return
	 */
	 PagedResponse<OrderListModel> customerGetOrders(PagedCustomerSearchReq req);
	/**
	 * 根据orderID获取订单地图数据
	 * 
	 * @param orderId
	 * @author CaoHeYang
	 * @Date 20150730
	 * @return
	 */
	OrderMapDetail getOrderMapDetail(long orderId);

	/**
	 * 商家后台 订单详情页面基础数据
	 * 
	 * @param para
	 *            查询条件
	 * @author CaoHeYang
	 * @Date 20150804
	 * @return
	 */
	OrderDetailBusiness getOrderDetailBusiness(OrderDetailBusinessReq para);

	/**
	 * 以自身的字段作为查询条件 查询order 的基础数据 可扩展
	 * 
	 * @param order
	 *            条件实体
	 * @author CaoHeYang
	 * @Date 20150804
	 * @return
	 */
	Order getOneByCriteria(Order order);

	/**
	 * 商户取消订单功能
	 * 
	 * @author CaoHeYang
	 * @Date 20150804
	 * @param order
	 * @return
	 */
	int cancelOrderBusiness(Order order);
	
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