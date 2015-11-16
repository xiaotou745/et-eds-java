package com.edaisong.api.dao.inter;

import java.util.Date;
import java.util.List;

import com.edaisong.entity.Order;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.BusTaskList;
import com.edaisong.entity.domain.BusiPubOrderTimeStatisticsModel;
import com.edaisong.entity.domain.BusinessOrderSummaryModel;
import com.edaisong.entity.domain.DaySatisticsB;
import com.edaisong.entity.domain.DaySatisticsC;
import com.edaisong.entity.domain.ExportOrder;
import com.edaisong.entity.domain.InStoreTask;
import com.edaisong.entity.domain.OrderDetailBusiness;
import com.edaisong.entity.domain.OrderListModel;
import com.edaisong.entity.domain.OrderMapDetail;
import com.edaisong.entity.domain.QueryOrder;
import com.edaisong.entity.domain.RegionOrderDetail;
import com.edaisong.entity.domain.RegionOrderTotal;
import com.edaisong.entity.domain.ServiceClienter;
import com.edaisong.entity.req.InStoreTaskReq;
import com.edaisong.entity.req.OrderDetailBusinessReq;
import com.edaisong.entity.req.OrderOtherSearch;
import com.edaisong.entity.req.OrderReq;
import com.edaisong.entity.req.OrderStatisticsBReq;
import com.edaisong.entity.req.OrderStatisticsCReq;
import com.edaisong.entity.req.PagedBusTaskListReq;
import com.edaisong.entity.req.PagedCustomerSearchReq;
import com.edaisong.entity.req.PagedOrderSearchReq;
import com.edaisong.entity.req.QueryOrderReq;
import com.edaisong.entity.resp.OrderStatisticsBResp;
import com.edaisong.entity.resp.QueryOrderBResp;
import com.edaisong.entity.resp.OrderStatisticsCResp;
import com.edaisong.entity.resp.QueryOrderCResp;

public interface IOrderDao {

	int insert(Order record);
	
	/**
	 * 查询订单是否存在
	 * 
	 * @author 胡灵波
	 * @Date 2015年11月9日 14:55:29
	 * @param 商户Id,时间戳
	 *            查询条件实体
	 * @return
	 */
	Order selectIsExistByBusinessId(OrderReq req) ;
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
	  * 根据订单号/订单id查订单信息
	  * @author CaoHeYang
	  * @param ordernNo 订单号
	  * @param orderId  订单id
	  * @Date 20150827
	  * @return
	  */
	 OrderListModel getOrderByNoId(String ordernNo,int orderId);
	 
	 /**
	  * 根据订单号/订单id查订单信息(写库)
	  * 此处使用了nolock
	  * @author 胡灵波
	  * @param ordernNo 订单号
	  * @param orderId  订单id
	  * @Date 2015年9月1日 14:23:15
	  * @return
	  */
	 OrderListModel getOrderWriteByNoId(String ordernNo, int orderId);
	 
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
	OrderMapDetail getOrderMapDetail(int orderId);

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
	
	/**
	 * update 任意列 根据id 
	 * @author CaoHeYang
	 * @param order
	 * @return
	 */
   int updateByPrimaryKeySelective(Order order);
   
   /**
    * 更新订单真实佣金
    * @author CaoHeYang
    * @param orderOtherSearch
    * @date 20150831
    * @return
    */
    int updateOrderRealCommission(OrderOtherSearch orderOtherSearch);
    
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
	 * B端任务统计接口 add by caoheyang 20150910
	 * @author CaoHeYang
	 * @return
	 */
	List<ServiceClienter> getOrderStatisticsServiceClienterB(OrderStatisticsBReq orderStatisticsBReq);
	
	/**
	 * B端任务统计接口  天数据列表  add by caoheyang 20150910
	 * @author CaoHeYang
	 * @return
	 */
	List<DaySatisticsB> getOrderStatisticsDaySatistics(OrderStatisticsBReq orderStatisticsBReq);
	/**
	 * B端任务统计接口   add by caoheyang 20150910
	 * @author CaoHeYang
	 * @return
	 */
	OrderStatisticsBResp  getOrderStatistics(OrderStatisticsBReq orderStatisticsBReq);
	/**
	 * C端任务统计接口
	 * @author WangXuDan
	 * @date 20150910
	 * @param data
	 */
	OrderStatisticsCResp  getOrderStatisticsC(OrderStatisticsCReq orderStatisticsCReq);
	/**
	 * C端任务统计接口  天数据列表
	 * @author WangXuDan
	 * @date 20150910
	 * @param data
	 */
	List<DaySatisticsC> getOrderStatisticsDaySatisticsC(OrderStatisticsCReq orderStatisticsCReq);
	
	/**
	 * B 端首页 订单列表
	 * @author CaoHeYang
	 * @date 20150910
	 * @param data 
	 * @return
	 */
	List<QueryOrder> queryOrder(QueryOrderReq query) ;
	
	/**
	 * c端查询待取货订单（会计算距离）
	 * @author CaoHeYang
	 * @date 20150910
	 * @param data 
	 * @return
	 */
	List<QueryOrder> queryDeliveryOrderC(QueryOrderReq query) ;
	
	
	/**
	 * 骑士端我的任务
	 * @author CaoHeYang
	 * @date 20150914
	 * @param data 
	 * @return
	 */
	QueryOrderCResp queryOrderC(QueryOrderReq query) ;
	/**
	 * 商家端我的任务
	 * @author CaoHeYang
	 * @date 20150914
	 * @param data 
	 * @return
	 */
	QueryOrderBResp queryOrderB(QueryOrderReq query) ;
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
	 * 获取指定区域下今日未完成的订单数量
	 * @date 20151030
	 * @author hailongzhao
	 * @param regionId
	 * @return
	 */
	Long queryIngOrderByRegionId(Long regionId) ;

	OrderStatisticsCResp getOrderGrabStatisticsC(
			OrderStatisticsCReq orderStatisticsCReq);

	List<DaySatisticsC> getOrderGrabStatisticsDaySatisticsC(
			OrderStatisticsCReq orderStatisticsCReq);
	
}