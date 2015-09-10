package com.edaisong.api.service.inter;

import java.util.Date;
import java.util.List;

import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.common.ResponseBase;
import com.edaisong.entity.domain.BusiPubOrderTimeStatisticsModel;
import com.edaisong.entity.domain.BusinessOrderSummaryModel;
import com.edaisong.entity.domain.ExportOrder;
import com.edaisong.entity.domain.OrderListModel;
import com.edaisong.entity.domain.OrderMapDetail;
import com.edaisong.entity.req.OptOrder;
import com.edaisong.entity.req.CancelOrderBusinessReq;
import com.edaisong.entity.req.OptOrder;
import com.edaisong.entity.req.OrderDetailBusinessReq;
import com.edaisong.entity.req.OrderReq;
import com.edaisong.entity.req.OrderStatisticsBReq;
import com.edaisong.entity.req.PagedCustomerSearchReq;
import com.edaisong.entity.req.PagedOrderSearchReq;
import com.edaisong.entity.resp.BusinessBalanceInfoResp;
import com.edaisong.entity.resp.CancelOrderBusinessResp;
import com.edaisong.entity.resp.OrderDetailBusinessResp;
import com.edaisong.entity.resp.OrderResp;
import com.edaisong.entity.resp.OrderStatisticsBResp;

public interface IOrderService {
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
	 * 导出订单
	 * 
	 * @author CaoHeYang
	 * @Date 20150728
	 * @param search
	 *            查询条件实体
	 * @return
	 */
	List<ExportOrder> exportOrder(PagedOrderSearchReq search);

	/**
	 * 根据订单号/订单id查订单信息
	 * 
	 * @author CaoHeYang
	 * @param ordernNo
	 *            订单号
	 * @param orderId
	 *            订单id
	 * @Date 20150827
	 * @return
	 */
	OrderListModel getOrderByNoId(String ordernNo, int orderId);

	/**
	 * 商家中心订单列表页面右上角自定义查询
	 * 
	 * @author zhaohailong
	 * @Date 20150821
	 * @param search
	 *            查询参数：可能为订单号，骑士手机号，骑士姓名
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
	OrderMapDetail getOrderMapDetail(int orderId);

	/**
	 * 商家后台 订单详情页面完整数据
	 * 
	 * @param para
	 *            查询条件
	 * @author CaoHeYang
	 * @Date 20150804
	 * @return
	 */
	OrderDetailBusinessResp getOrderDetailBusiness(OrderDetailBusinessReq para);

	/**
	 * 商户取消订单功能
	 * 
	 * @param req
	 *            参数
	 * @author CaoHeYang
	 * @Date 20150804
	 * @return
	 */
	CancelOrderBusinessResp cancelOrderBusiness(CancelOrderBusinessReq req);

	/**
	 * 商户发布订单
	 * 
	 * @param req
	 *            参数
	 * @author 胡灵波
	 * @Date 2015年8月6日 09:51:47
	 * @return
	 */
	OrderResp AddOrder(OrderReq req);

	/**
	 * 商户发单，点击按纽钱查询商户余额信息，以及该订单的结算信息
	 * 
	 * @author CaoHeYang
	 * @param req
	 * @Date 20150824
	 * @return
	 */
	BusinessBalanceInfoResp getBalanceInfo(OrderReq req);

	/**
	 * 获得商家订单概览信息
	 * 
	 * @param 商家Id
	 * @author pengyi
	 * @date 20150818
	 * @return
	 */
	BusinessOrderSummaryModel getBusinessOrderSummary(int businessId);

	/**
	 * 获得商家发布订单时间统计
	 * 
	 * @param 商家Id
	 * @param 开始时间
	 * @param 结束时间
	 * @author pengyi
	 * @date 20150819
	 * @return
	 */
	List<BusiPubOrderTimeStatisticsModel> getBusiPubOrderTimeStatistics(
			int businessId, Date startTime, Date endTime);

	/**
	 * 管理后台取消订单
	 * 
	 * @author CaoHeYang
	 * @param auditOkOrde
	 * @date 20150831r
	 * @return
	 */
	ResponseBase cancelOrder(OptOrder cancelOrder);

	/**
	 * 订单审核通过
	 * 
	 * @author CaoHeYang
	 * @param auditOkOrder
	 * @date 20150831
	 * @return
	 */
	ResponseBase auditOk(OptOrder auditOkOrder);
	/**
	 * 订单审核拒绝
	 * @param auditRefuseOrder
	 * @author CaoHeYang
	 * @date 20150831
	 * @return
	 */
	ResponseBase auditRefuse(OptOrder auditRefuseOrder);
	
	/**
	 * B端任务统计接口
	 * @author CaoHeYang
	 * @date 20150910
	 * @param data 
	 * @return
	 */
    OrderStatisticsBResp getOrderStatisticsB(OrderStatisticsBReq orderStatisticsBReq);

}
