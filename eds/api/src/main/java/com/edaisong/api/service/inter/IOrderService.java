package com.edaisong.api.service.inter;

import java.util.Date;
import java.util.List;

import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.common.ResponseBase;
import com.edaisong.entity.domain.BusTaskList;
import com.edaisong.entity.domain.BusiPubOrderTimeStatisticsModel;
import com.edaisong.entity.domain.BusinessOrderSummaryModel;
import com.edaisong.entity.domain.ExportOrder; 
import com.edaisong.entity.domain.ExportShanSongOrder;
import com.edaisong.entity.domain.InStoreTask;
import com.edaisong.entity.domain.OrderListModel;
import com.edaisong.entity.domain.OrderMapDetail;
import com.edaisong.entity.domain.QueryOrder;
import com.edaisong.entity.domain.ShanSongOrderListModel;
import com.edaisong.entity.req.InStoreTaskReq;
import com.edaisong.entity.domain.RegionOrderDetail;
import com.edaisong.entity.domain.RegionOrderTotal;
import com.edaisong.entity.req.GetPushClienterIdsReq;
import com.edaisong.entity.req.OptOrder;
import com.edaisong.entity.req.CancelOrderBusinessReq;
import com.edaisong.entity.req.OrderBlancePayReq;
import com.edaisong.entity.req.OrderDetailBusinessReq; 
import com.edaisong.entity.req.OrderDetailReq;
import com.edaisong.entity.req.OrderDraftReq;
import com.edaisong.entity.req.OrderDraftReturnReq;
import com.edaisong.entity.req.OrderReq;
import com.edaisong.entity.req.OrderStatisticsBReq;
import com.edaisong.entity.req.OrderStatisticsCReq;
import com.edaisong.entity.req.PagedBusTaskListReq;
import com.edaisong.entity.req.PagedCustomerSearchReq;
import com.edaisong.entity.req.PagedOrderSearchReq;
import com.edaisong.entity.req.QueryOrderReq;
import com.edaisong.entity.req.QueryShanSongOrderReq;
import com.edaisong.entity.resp.BusinessBalanceInfoResp;
import com.edaisong.entity.resp.CancelOrderBusinessResp;
import com.edaisong.entity.resp.OrderBlancePayResp;
import com.edaisong.entity.resp.OrderDetailBusinessResp;
import com.edaisong.entity.resp.OrderDetailResp;
import com.edaisong.entity.resp.OrderResp;
import com.edaisong.entity.resp.OrderStatisticsBResp;
import com.edaisong.entity.resp.QueryOrderBResp;
import com.edaisong.entity.resp.OrderStatisticsCResp;
import com.edaisong.entity.resp.QueryOrderCResp;

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
	 * 发布订单 api调用
	 * 
	 * @param req
	 *            参数
	 * @author 胡灵波
	 * @Date 2015年8月6日 09:51:47
	 * @return
	 */
	HttpResultModel<OrderResp> PushOrder(OrderReq req);
	
	
	// region 闪送模式
	/**
	 * 发布订单 api调用 闪送模式 
	 * 
	 * @param req
	 *            参数
	 * @author 胡灵波
	 * @Date 2015年8月6日 09:51:47
	 * @return
	 */
	HttpResultModel<OrderResp> FlashPushOrder(OrderDraftReq req);
	
	/**
	 * 余额支付 闪送模式 
	 * 
	 * @param req
	 *            参数
	 * @author 胡灵波
	 * @Date 2015年12月14日 11:24:01
	 * @return
	 */
	HttpResultModel<OrderBlancePayResp> OrderBalancePay(OrderBlancePayReq req);
	
	/**
	 * 获取订单详情  闪送模式 api
	 * 
	 * @param req
	 *            参数
	 * @author hulingbo
	 * @Date 2015年11月27日 11:50:01
	 * @return
	 */
	HttpResultModel<OrderDetailResp> GetOrderDetails(OrderDetailReq req);
	// endregion 

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
	HttpResultModel<OrderStatisticsBResp> getOrderStatisticsB(OrderStatisticsBReq orderStatisticsBReq);
    /**
     * C端任务统计接口
     * @author WangXuDan
     * @date 20150910
     * @param orderStatisticsCReq
     */
    OrderStatisticsCResp getOrderStatisticsC(OrderStatisticsCReq orderStatisticsCReq);

	/**
	 * B 端首页 订单列表
	 * @author CaoHeYang
	 * @date 20150910
	 * @param data 
	 * @return
	 */
    HttpResultModel< QueryOrderBResp> queryOrderB(QueryOrderReq query) ;
    
    /**
	 * C 端我的任务
	 * 
	 * @author CaoHeYang
	 * @date 20150911
	 * @param para
	 */
    HttpResultModel<QueryOrderCResp> queryOrderC(QueryOrderReq query );
    
    /**
     * B端已完成任务列表或者配送员配送列表
     * @author CaoHeYang
     * @date 20150910
     * @param query
     * @type  0 B端 1 C端
     * @return
     */
    HttpResultModel<List<QueryOrder>> getCompliteOrder(QueryOrderReq query,int type);
    
    /**
	 * 门店审核列表
	 * 
	 * @author 茹化肖
	 * @Date 2015年9月17日14:58:18
	 * @param search
	 *            查询参数：可能为订单号，骑士手机号，骑士姓名
	 * @return
	 */
	PagedResponse<BusTaskList> busTaskList(PagedBusTaskListReq req);
	/**
	 * 获取商家的今日订单的区域统计数据
	 * @date 20151030
	 * @author hailongzhao
	 * @param businessId
	 * @return
	 */
	List<RegionOrderTotal> queryTodayOrderTotal(Long businessId) ;
	/**
	 * 获取商家的今日订单的区域统计详细数据
	 * @date 20151030
	 * @author hailongzhao
	 * @param businessId
	 * @return
	 */
	List<RegionOrderDetail> queryTodayOrderDetail(Long businessId) ;	
	/**
	 *  骑士端获取店内任务
	 * @version 3.0  
	 * @author CaoHeYang
	 * @date 20151030
	 * @param para
	 * @return
	 */
	List<InStoreTask>  getInStoreTask(InStoreTaskReq para);
	/**
	 * 获取指定区域下今日未完成的订单数量
	 * @date 20151030
	 * @author hailongzhao
	 * @param regionId
	 * @return
	 */
	Long queryIngOrderByRegionId(Long regionId) ;
	/**
	 * C端任务统计接口 
	 * @author wangchao
	 * @param data
	 */
	OrderStatisticsCResp getOrderGrabStatisticsC(
			OrderStatisticsCReq orderStatisticsCReq);
	/*
	 * B端任务统计
	 * wangchao
	 */
	HttpResultModel<OrderStatisticsBResp> getOrderGrabStatisticsB(
			OrderStatisticsBReq para);

	HttpResultModel<List<QueryOrder>> getCompliteOrderGrab(QueryOrderReq query,int type);
	
	
	/**
	 * 后台E单订单列表页面
	 * 
	 * @author CaoHeYang
	 * @Date 20150728
	 * @param search
	 *            查询条件实体
	 * @return
	 */
	PagedResponse<ShanSongOrderListModel> getShanSongOrders(PagedOrderSearchReq search);
	/**
	 * 导出订单
	 * 
	 * @author CaoHeYang
	 * @Date 20151125
	 * @param search
	 *            查询条件实体
	 * @return
	 */

	 List<ExportShanSongOrder> exportShanSongOrder(PagedOrderSearchReq search); 
	 
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
	   ShanSongOrderListModel getShanSongOrderByNo(String ordernNo);
		/**
		 * 闪送管理后台取消订单
		 * 
		 * @author CaoHeYang
		 * @param auditOkOrde
		 * @date 20151126
		 * @return
		 */
		ResponseBase shanSongCancelOrder(OptOrder cancelOrder);
		
		/**
		 * B 端首页 闪送订单列表
		 * @author CaoHeYang
		 * @date 20150910
		 * @param data 
		 * @return
		 */
	    HttpResultModel<QueryOrderBResp> shanSongQueryOrderB(QueryShanSongOrderReq query) ;
	    
	    /**
	     * 里程计算 推单   新订单
	     * @author CaoHeYang
	     * @date 20160104
	     * @param req
	     * @param orderId
	     * @return
	     */
	    Boolean shanSongPushOrder(GetPushClienterIdsReq req,int orderId);
	    /**
	     * 里程计算 推单   订单已被处理
	     * @author CaoHeYang
	     * @date 20160104
	     * @param orderId
	     * @return
	     */
	    Boolean shanSongPushOrder(int orderId);
}

