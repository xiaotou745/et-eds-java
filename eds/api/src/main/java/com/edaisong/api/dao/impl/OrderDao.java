package com.edaisong.api.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IOrderDao;
import com.edaisong.entity.Account;
import com.edaisong.entity.Order;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.BusTaskList;
import com.edaisong.entity.domain.BusiPubOrderTimeStatisticsModel;
import com.edaisong.entity.domain.BusinessOrderSummaryModel;
import com.edaisong.entity.domain.DaySatisticsB;
import com.edaisong.entity.domain.DaySatisticsC;
import com.edaisong.entity.domain.ExportOrder;
import com.edaisong.entity.domain.ExportShanSongOrder;
import com.edaisong.entity.domain.OrderDetailBusiness;
import com.edaisong.entity.domain.OrderListModel;
import com.edaisong.entity.domain.OrderMapDetail;
import com.edaisong.entity.domain.QueryOrder;
import com.edaisong.entity.domain.RegionOrderDetail;
import com.edaisong.entity.domain.RegionOrderTotal;
import com.edaisong.entity.domain.ServiceClienter;
import com.edaisong.entity.domain.ShanSongOrderListModel;
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

@Repository
public class OrderDao extends DaoBase implements IOrderDao {	
	
	@Override
	public int insert(Order record)
	{
		return getMasterSqlSessionUtil().insert(
				"com.edaisong.api.dao.inter.IOrderDao.insertSelective", record);
	}
	
	@Override
	public Order selectIsExistByBusinessId(OrderReq req)
	{
		Order result = getMasterSqlSessionUtil().selectOne(
				"com.edaisong.api.dao.inter.IOrderDao.selectIsExistByBusinessId",
				req);
		return result;
	}
	
	/**
	 * 后台订单列表页面
	 * 
	 * @author CaoHeYang
	 * @Date 20150728
	 * @param search
	 *            查询条件实体
	 * @return
	 */
	@Override
	public PagedResponse<OrderListModel> getOrders(PagedOrderSearchReq search) {

		PagedResponse<OrderListModel> result = new PagedResponse<OrderListModel>();
		result = getReadOnlySqlSessionUtil().selectPageList(
				"com.edaisong.api.dao.inter.IOrderDao.GetOrders", search);
		return result;
	}

	/**
	 * 根据orderID获取订单地图数据
	 * 
	 * @param orderId
	 * @author CaoHeYang
	 * @Date 20150730
	 * @return
	 */
	public OrderMapDetail getOrderMapDetail(int orderId) {
		OrderMapDetail result = getReadOnlySqlSessionUtil().selectOne(
				"com.edaisong.api.dao.inter.IOrderDao.getOrderMapDetail",
				orderId);
		return result;
	}

	/**
	 * 商家后台 订单详情页面基础数据
	 * 
	 * @param para
	 *            查询条件
	 * @author CaoHeYang
	 * @Date 20150804
	 * @return
	 */
	public OrderDetailBusiness getOrderDetailBusiness(
			OrderDetailBusinessReq para) {
		OrderDetailBusiness result = getReadOnlySqlSessionUtil()
				.selectOne(
						"com.edaisong.api.dao.inter.IOrderDao.getOrderDetailBusiness",
						para);
		return result;
	}

	/**
	 * 以自身的字段作为查询条件 查询order 的基础数据 可扩展
	 * 
	 * @param order
	 *            条件实体
	 * @author CaoHeYang
	 * @Date 20150804
	 * @return
	 */
	@Override
	public Order getOneByCriteria(Order order) {
		Order result = getReadOnlySqlSessionUtil()
				.selectOne(
						"com.edaisong.api.dao.inter.IOrderDao.getOneByCriteria",
						order);
		return result;
	}


	/**
	 * 商户取消订单功能
	 * 
	 * @author CaoHeYang
	 * @Date 20150804
	 * @param order
	 * @return
	 */
	@Override
	public int cancelOrderBusiness(Order order) {
		int res = getMasterSqlSessionUtil()
				.update(
						"com.edaisong.api.dao.inter.IOrderDao.cancelOrderBusiness",
						order);
		return res;
	}

	@Override
	public BusinessOrderSummaryModel getBusinessOrderSummary(int businessId) {
		return getReadOnlySqlSessionUtil().selectOne(
				"com.edaisong.api.dao.inter.IOrderDao.getBusinessOrderSummary", 
				businessId);
	}

	@Override
	public List<BusiPubOrderTimeStatisticsModel> getBusiPubOrderTimeStatistics(int businessId,Date startTime,Date endTime) {
		Map<String, Object> paramMap = new HashedMap();
		paramMap.put("businessId", businessId);
		paramMap.put("startTime", startTime);
		paramMap.put("endTime", endTime);
		return getReadOnlySqlSessionUtil().selectList(
				"com.edaisong.api.dao.inter.IOrderDao.getBusiPubOrderTimeStatistics", 
				paramMap);
	}

	@Override
	public PagedResponse<OrderListModel> customerGetOrders(PagedCustomerSearchReq req) {
		return getReadOnlySqlSessionUtil().selectPageList("com.edaisong.api.dao.inter.IOrderDao.customerGetOrders", req);
	}

	 /**
	  *  根据订单号/订单id查订单信息
	  * @author CaoHeYang
	  * @param ordernNo 订单号
	  * @param orderId  订单id
	  * @Date 20150827
	  * @return
	  */
	@Override
	public OrderListModel getOrderByNoId(String ordernNo, int orderId) {
		Map<String, Object> paramMap = new HashedMap();
		paramMap.put("orderNo", ordernNo);  //订单号
		paramMap.put("orderId", orderId);  //订单id
		return getReadOnlySqlSessionUtil().selectOne("com.edaisong.api.dao.inter.IOrderDao.getOrderByNoId", paramMap);
	}
	
	 /**
	  *  根据订单号/订单id查订单信息(写库),
	  *  此处查询使用了 nolock
	  * @author 胡灵波
	  * @param ordernNo 订单号
	  * @param orderId  订单id
	  * @Date 2015年9月1日 14:20:22
	  * @return
	  */
	@Override
	public OrderListModel getOrderWriteByNoId(String ordernNo, int orderId) {
		Map<String, Object> paramMap = new HashedMap();
		paramMap.put("orderNo", ordernNo);  //订单号
		paramMap.put("orderId", orderId);  //订单id
		return getMasterSqlSessionUtil().selectOne("com.edaisong.api.dao.inter.IOrderDao.getOrderByNoId", paramMap);
	}
	/**
	 * update 任意列 根据id 
	 * @author CaoHeYang
	 * @param order
	 * @return
	 */
  public int updateByPrimaryKeySelective(Order order){
	  return getMasterSqlSessionUtil().update("com.edaisong.api.dao.inter.IOrderDao.updateByPrimaryKeySelective", order);
  }
  /**
   * 更新订单真实佣金
   * @author CaoHeYang
   * @param orderOtherSearch
   * @date 20150831
   * @return
   */
	@Override
	public int updateOrderRealCommission(OrderOtherSearch orderOtherSearch) {
		return getMasterSqlSessionUtil().update("com.edaisong.api.dao.inter.IOrderDao.updateOrderRealCommission", orderOtherSearch);
	}

	/**
	 * 导出订单
	 * 
	 * @author CaoHeYang
	 * @Date 20150728
	 * @param search
	 *            查询条件实体
	 * @return
	 */
	@Override
	public List<ExportOrder> exportOrder(PagedOrderSearchReq search) {
		return getReadOnlySqlSessionUtil().selectList(
				"com.edaisong.api.dao.inter.IOrderDao.exportOrder", 
				search);
	}

	/**
	 * B端任务统计接口 统计服务骑士信息 add by caoheyang 20150910
	 * @author CaoHeYang
	 * @return
	 */
	@Override
	public List<ServiceClienter> getOrderStatisticsServiceClienterB(OrderStatisticsBReq orderStatisticsBReq) {
		return getReadOnlySqlSessionUtil().selectList(
				"com.edaisong.api.dao.inter.IOrderDao.getOrderStatisticsServiceClienterB", 
				orderStatisticsBReq);
	}


	/**
	 * B端任务统计接口  天数据列表  add by caoheyang 20150910
	 * @author CaoHeYang
	 * @return
	 */
	@Override
	public List<DaySatisticsB> getOrderStatisticsDaySatistics(OrderStatisticsBReq orderStatisticsBReq) {
			return getReadOnlySqlSessionUtil().selectList(
					"com.edaisong.api.dao.inter.IOrderDao.getOrderStatisticsDaySatistics", 
					orderStatisticsBReq);
	}

	/**
	 * B端任务统计接口   add by caoheyang 20150910
	 * @author CaoHeYang
	 * @return
	 */
	@Override
	public OrderStatisticsBResp getOrderStatistics(OrderStatisticsBReq orderStatisticsBReq) {
			return getReadOnlySqlSessionUtil().selectOne(
					"com.edaisong.api.dao.inter.IOrderDao.getOrderStatistics", 
					orderStatisticsBReq);
	}

	/**
	 * B 端首页 订单列表
	 * @author CaoHeYang
	 * @date 20150910
	 * @param data 
	 * @return
	 */
	@Override
	public List<QueryOrder> queryOrder(QueryOrderReq query) {
		PagedResponse<QueryOrder> result=  getReadOnlySqlSessionUtil().selectPageList(
				"com.edaisong.api.dao.inter.IOrderDao.queryOrder", 
				query);
		return result.getResultList();
	}
	/**
	 * C端任务统计接口
	 * @author WangXuDan
	 * @date 20150910
	 * @param orderStatisticsCReq
	 */
	@Override
	public OrderStatisticsCResp getOrderStatisticsC(
			OrderStatisticsCReq orderStatisticsCReq) {
		return getReadOnlySqlSessionUtil().selectOne(
				"com.edaisong.api.dao.inter.IOrderDao.getOrderStatisticsC", 
				orderStatisticsCReq);
	}
	/**
	 * C端任务统计接口  天数据列表
	 * @author WangXuDan
	 * @date 20150910
	 * @param orderStatisticsCReq
	 */
	@Override
	public List<DaySatisticsC> getOrderStatisticsDaySatisticsC(
			OrderStatisticsCReq orderStatisticsCReq) {
		return getReadOnlySqlSessionUtil().selectList(
				"com.edaisong.api.dao.inter.IOrderDao.getOrderStatisticsDaySatisticsC", 
				orderStatisticsCReq);
	}
	/**
	 * 骑士端我的任务
	 * @author CaoHeYang
	 * @date 20150914
	 * @param data 
	 * @return
	 */
	@Override
   public	QueryOrderCResp queryOrderC(QueryOrderReq query) {
	   return getReadOnlySqlSessionUtil().selectOne(
				"com.edaisong.api.dao.inter.IOrderDao.queryOrderC", 
				query);
   }
	/**
	 * 商家端我的任务
	 * @author CaoHeYang
	 * @date 20150914
	 * @param data 
	 * @return
	 */
	@Override
	public QueryOrderBResp queryOrderB(QueryOrderReq query) {
		   return getReadOnlySqlSessionUtil().selectOne(
					"com.edaisong.api.dao.inter.IOrderDao.queryOrderB", 
					query);
	}
	/**
	 * c端查询待取货订单（会计算距离）
	 * @author CaoHeYang
	 * @date 20150910
	 * @param data 
	 * @return
	 */
	@Override
	 public List<QueryOrder> queryDeliveryOrderC(QueryOrderReq query) {
		PagedResponse<QueryOrder> result=  getReadOnlySqlSessionUtil().selectPageList(
				"com.edaisong.api.dao.inter.IOrderDao.queryDeliveryOrderC", 
				query);
		return result.getResultList();
	 }
	
	 /**
		 * 门店审核列表
		 * 
		 * @author 茹化肖
		 * @Date 2015年9月17日14:58:18
		 * @param search
		 * @return
		 */
	@Override
	public PagedResponse<BusTaskList> busTaskList(PagedBusTaskListReq req){
		return  getReadOnlySqlSessionUtil().selectPageList(
				"com.edaisong.api.dao.inter.IOrderDao.busTaskList", req);
	}

	@Override
	public List<RegionOrderDetail> queryTodayOrderDetailing(Long businessId) {
		return  getReadOnlySqlSessionUtil().selectList(
				"com.edaisong.api.dao.inter.IOrderDao.queryTodayOrderDetailing", businessId);
	}
	@Override
	public List<RegionOrderDetail> queryTodayOrderDetailWait(Long businessId) {
		return  getReadOnlySqlSessionUtil().selectList(
				"com.edaisong.api.dao.inter.IOrderDao.queryTodayOrderDetailWait", businessId);
	}

	@Override
	public List<RegionOrderTotal> queryTodayOrderTotal(Long businessId) {
		return  getReadOnlySqlSessionUtil().selectList(
				"com.edaisong.api.dao.inter.IOrderDao.queryTodayOrderTotal", businessId);
	}

	@Override
	public Long queryIngOrderByRegionId(Long regionId) {
		return  getReadOnlySqlSessionUtil().selectOne(
				"com.edaisong.api.dao.inter.IOrderDao.queryIngOrderByRegionId", regionId);
	}

	@Override
	public OrderStatisticsCResp getOrderGrabStatisticsC(
			OrderStatisticsCReq orderStatisticsCReq) {
		return getReadOnlySqlSessionUtil().selectOne(
				"com.edaisong.api.dao.inter.IOrderDao.getOrderGrabStatisticsC", 
				orderStatisticsCReq);
	}

	@Override
	public List<DaySatisticsC> getOrderGrabStatisticsDaySatisticsC(
			OrderStatisticsCReq orderStatisticsCReq) {
		return getReadOnlySqlSessionUtil().selectList(
				"com.edaisong.api.dao.inter.IOrderDao.getOrderGrabStatisticsDaySatisticsC", 
				orderStatisticsCReq);
	}

	@Override
	public OrderStatisticsBResp getOrderGrabStatisticsB(
			OrderStatisticsBReq orderStatisticsBReq) {
		return getReadOnlySqlSessionUtil().selectOne(
				"com.edaisong.api.dao.inter.IOrderDao.getOrderGrabStatisticsB", 
				orderStatisticsBReq);
	}

	@Override
	public List<ServiceClienter> getOrderGrabStatisticsServiceClienterB(
			OrderStatisticsBReq orderStatisticsBReq) {
		return getReadOnlySqlSessionUtil().selectList(
				"com.edaisong.api.dao.inter.IOrderDao.getOrderGrabStatisticsServiceClienterB", 
				orderStatisticsBReq);
	}

	@Override
	public List<DaySatisticsB> getOrderGrabStatisticsDaySatisticsB(
			OrderStatisticsBReq orderStatisticsBReq) {
		return getReadOnlySqlSessionUtil().selectList(
				"com.edaisong.api.dao.inter.IOrderDao.getOrderGrabStatisticsDaySatisticsB", 
				orderStatisticsBReq);
	}

	@Override
	public List<QueryOrder> queryOrderGrab(QueryOrderReq query) {
		PagedResponse<QueryOrder> result=  getReadOnlySqlSessionUtil().selectPageList(
				"com.edaisong.api.dao.inter.IOrderDao.queryOrderGrabB", 
				query);
		return result.getResultList();
	}
	
	/**
	 *  后台E单订单列表页面
	 * 
	 * @author CaoHeYang
	 * @Date 20151125
	 * @param search
	 *            查询条件实体
	 * @return
	 */
	@Override
	public PagedResponse<ShanSongOrderListModel> getShanSongOrders(PagedOrderSearchReq search) {
		PagedResponse<ShanSongOrderListModel> result = new PagedResponse<ShanSongOrderListModel>();
		result = getReadOnlySqlSessionUtil().selectPageList(
				"com.edaisong.api.dao.inter.IOrderDao.getShanSongOrders", search);
		return result;
	}
	
	/**
	 * 导出订单
	 * 
	 * @author CaoHeYang
	 * @Date 20151125
	 * @param search
	 *            查询条件实体
	 * @return
	 */
	@Override
	public List<ExportShanSongOrder> exportShanSongOrder(PagedOrderSearchReq search) {
		return getReadOnlySqlSessionUtil().selectList(
				"com.edaisong.api.dao.inter.IOrderDao.exportShanSongOrder", 
				search);
	}
	
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
	@Override
	public  ShanSongOrderListModel getShanSongOrderByNo(String ordernNo){
		return getReadOnlySqlSessionUtil().selectOne("com.edaisong.api.dao.inter.IOrderDao.getShanSongOrderByNo"
				, ordernNo);
	}
}
