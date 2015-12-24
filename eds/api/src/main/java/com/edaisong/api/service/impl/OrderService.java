package com.edaisong.api.service.impl;

import java.lang.Double;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edaisong.api.common.CommissionFactory;
import com.edaisong.api.common.OrderPriceBaseProvider;
import com.edaisong.api.common.OrderSettleMoneyHelper;
import com.edaisong.api.common.TransactionalRuntimeException;
import com.edaisong.api.dao.inter.IBusinessBalanceRecordDao;
import com.edaisong.api.dao.inter.IBusinessDao;
import com.edaisong.api.dao.inter.IClienterBalanceRecordDao;
import com.edaisong.api.dao.inter.IClienterDao;
import com.edaisong.api.dao.inter.IClienterLocationDao;
import com.edaisong.api.dao.inter.IGroupBusinessDao;
import com.edaisong.api.dao.inter.IOrderChildDao;
import com.edaisong.api.dao.inter.IOrderDao;
import com.edaisong.api.dao.inter.IOrderDetailDao;
import com.edaisong.api.dao.inter.IOrderGrabDao;
import com.edaisong.api.dao.inter.IOrderOtherDao;
import com.edaisong.api.dao.inter.IOrderRegionDao;
import com.edaisong.api.dao.inter.IOrderSubsidiesLogDao;
import com.edaisong.api.dao.inter.IOrderTipCostDao;
import com.edaisong.api.redis.NetRedisService;
import com.edaisong.api.redis.RedisService;
import com.edaisong.api.service.inter.IBusinessService;
import com.edaisong.api.service.inter.IClienterService;
import com.edaisong.api.service.inter.IOrderService;
import com.edaisong.core.consts.RedissCacheKey;
import com.edaisong.core.enums.BusinessBalanceRecordRecordType;
import com.edaisong.core.enums.BusinessBalanceRecordStatus;
import com.edaisong.core.enums.BusinessPushOrderType;
import com.edaisong.core.enums.BusinessStatusEnum;
import com.edaisong.core.enums.CancelOrderBusinessReturnEnum;
import com.edaisong.core.enums.ClienterAllowWithdrawRecordStatus;
import com.edaisong.core.enums.ClienterAllowWithdrawRecordType;
import com.edaisong.core.enums.ClienterBalanceRecordRecordType;
import com.edaisong.core.enums.ClienterBalanceRecordStatus;
import com.edaisong.core.enums.ClienterStatusEnum;
import com.edaisong.core.enums.DeductCommissionType;
import com.edaisong.core.enums.MealsSettleMode;
import com.edaisong.core.enums.OrderAuditStatus;
import com.edaisong.core.enums.FlashPushOrderEnum;
import com.edaisong.core.enums.OrderDetailGet;
import com.edaisong.core.enums.OrderFrom;
import com.edaisong.core.enums.OrderOperationCommon;
import com.edaisong.core.enums.OrderPlatform;
import com.edaisong.core.enums.OrderStatus;
import com.edaisong.core.enums.PublishOrderReturnEnum;
import com.edaisong.core.enums.ShanSongOrderStatus;
import com.edaisong.core.enums.Strategy;
import com.edaisong.core.enums.SuperPlatform;
import com.edaisong.core.enums.TaskStatus;
import com.edaisong.core.enums.returnenums.QueryOrderReturnEnum;
import com.edaisong.core.security.MD5Util;
import com.edaisong.core.util.MapUtils;
import com.edaisong.core.util.OrderNoHelper;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.core.util.PropertyUtils;
import com.edaisong.entity.Business;
import com.edaisong.entity.BusinessBalanceRecord;
import com.edaisong.entity.Clienter;
import com.edaisong.entity.ClienterBalanceRecord;
import com.edaisong.entity.GroupBusiness;
import com.edaisong.entity.Order;
import com.edaisong.entity.OrderChild;
import com.edaisong.entity.OrderDetail;
import com.edaisong.entity.OrderOther;
import com.edaisong.entity.OrderRegion;
import com.edaisong.entity.OrderSubsidiesLog;
import com.edaisong.entity.OrderTipCost;
import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.common.Location;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.common.ResponseBase;
import com.edaisong.entity.domain.BusiPubOrderTimeStatisticsModel;
import com.edaisong.entity.common.ResponseCode;
import com.edaisong.entity.domain.BusTaskList;
import com.edaisong.entity.domain.BusinessModel;
import com.edaisong.entity.domain.BusinessOrderSummaryModel;
import com.edaisong.entity.domain.BusinessStatus;
import com.edaisong.entity.domain.DaySatisticsB;
import com.edaisong.entity.domain.DaySatisticsC;
import com.edaisong.entity.domain.ExportOrder;
import com.edaisong.entity.domain.ExportShanSongOrder;
import com.edaisong.entity.domain.InStoreOrderRegionInfo;
import com.edaisong.entity.domain.InStoreTask;
import com.edaisong.entity.domain.OrderCommission; 
import com.edaisong.entity.domain.OrderListModel;
import com.edaisong.entity.domain.OrderMapDetail;
import com.edaisong.entity.domain.QueryOrder;
import com.edaisong.entity.domain.RegionOrderDetail;
import com.edaisong.entity.domain.RegionOrderTotal;
import com.edaisong.entity.domain.ServiceClienter;
import com.edaisong.entity.domain.ShanSongOrderListModel; 
import com.edaisong.entity.req.InStoreTaskReq;
import com.edaisong.entity.req.OptOrder;
import com.edaisong.entity.req.BusinessMoney;
import com.edaisong.entity.req.CancelOrderBusinessReq;
import com.edaisong.entity.req.ClienterMoney;
import com.edaisong.entity.req.OrderBlancePayReq;
import com.edaisong.entity.req.OrderDetailBusinessReq;
import com.edaisong.entity.req.OrderDetailReq;
import com.edaisong.entity.req.OrderDraftReq;
import com.edaisong.entity.req.OrderOtherSearch;
import com.edaisong.entity.req.OrderRegionReq;
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
@Service
public class OrderService implements IOrderService {

	@Autowired
	private IOrderDao orderDao;
	@Autowired
	private IOrderOtherDao orderOtherDao;
	@Autowired
	private IOrderChildDao orderChildDao;
	@Autowired
	private IBusinessDao businessDao;
	@Autowired
	private IBusinessBalanceRecordDao businessBalanceRecordDao;
	@Autowired
	private IOrderSubsidiesLogDao orderSubsidiesLogDao;
	@Autowired
	private IBusinessService businessService;
	@Autowired
	private IClienterService clienterService;
	@Autowired
	private RedisService redisService;
	@Autowired
	private IOrderDetailDao orderDetailDao;
	@Autowired
	private IClienterBalanceRecordDao clienterBalanceRecordDao;
	@Autowired
	IClienterLocationDao clienterLocationDao;
	@Autowired
	private IClienterDao clienterDao;
	@Autowired
	private IGroupBusinessDao groupBusinessDao;

	@Autowired
	private IOrderRegionDao orderRegionDao;

	@Autowired
	private IOrderGrabDao iOrderGrabDao;
	
	@Autowired
	private NetRedisService netRedisService;
	
	@Autowired
	private IOrderTipCostDao orderTipCostDao;
	@Autowired
	private GlobalConfigService globalConfigService;
 
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
		return orderDao.getOrders(search);
	}

	/**
	 * 后台订单列表页面 
	 * @author CaoHeYang
	 * @Date 20150728
	 * @param orderid
	 * 订单id
	 * @return
	 */
	@Override
	public OrderMapDetail getOrderMapDetail(int orderid) {
		OrderMapDetail orderMapDetail = orderDao.getOrderMapDetail(orderid);
		if (orderMapDetail != null) {
			Date startTime = new Date();
			Date endTime = new Date();
			if (orderMapDetail.getPubDate() == null
					|| orderMapDetail.getPubDate().indexOf("1900-01-01") >= 0) {
				orderMapDetail.setPubDate("暂无");
			} else {
				startTime = ParseHelper.ToDate(orderMapDetail.getPubDate());
			}
			if (orderMapDetail.getGrabTime() == null
					|| orderMapDetail.getGrabTime().indexOf("1900-01-01") >= 0) {
				orderMapDetail.setGrabTime("暂无");
			} else {
				endTime = ParseHelper.ToDate(orderMapDetail.getGrabTime());
			}
			if (orderMapDetail.getTakeTime() == null
					|| orderMapDetail.getTakeTime().indexOf("1900-01-01") >= 0) {
				orderMapDetail.setTakeTime("暂无");
			} else {
				endTime = ParseHelper.ToDate(orderMapDetail.getTakeTime());
			}
			if (orderMapDetail.getActualDoneDate() == null
					|| orderMapDetail.getActualDoneDate().indexOf("1900-01-01") >= 0) {
				orderMapDetail.setActualDoneDate("暂无");
			} else {
				endTime = ParseHelper
						.ToDate(orderMapDetail.getActualDoneDate());
			}
			if (orderMapDetail.getGrabLatitude() == 0
					|| orderMapDetail.getGrabLongitude() == 0) {
				orderMapDetail.setGrabLongitude(orderMapDetail
						.getPubLongitude());
				orderMapDetail.setGrabLatitude(orderMapDetail.getPubLatitude());
			}
			if (orderMapDetail.getTakeLatitude() == 0
					|| orderMapDetail.getTakeLongitude() == 0) {
				orderMapDetail.setTakeLongitude(orderMapDetail
						.getPubLongitude());
				orderMapDetail.setTakeLatitude(orderMapDetail.getPubLatitude());
			}
			if (orderMapDetail.getCompleteLatitude() == 0
					|| orderMapDetail.getCompleteLongitude() == 0) {
				orderMapDetail.setCompleteLongitude(orderMapDetail
						.getPubLongitude());
				orderMapDetail.setCompleteLatitude(orderMapDetail
						.getPubLatitude());
			}
			// 开始时间小于结束时间才获取实时坐标
			if (startTime.compareTo(endTime) < 0
					&& orderMapDetail.getClienterId() > 0) {
				orderMapDetail.setLocations(clienterLocationDao
						.getLocationsByTime(startTime, endTime,
								orderMapDetail.getClienterId()));
			}
			if (orderMapDetail.getLocations() == null) {
				orderMapDetail.setLocations(new ArrayList<Location>());
			}
		}
		return orderMapDetail;

	}

	/**
	 * 商家后台 订单详情页面完整数据
	 * 
	 * @param para
	 *            查询条件
	 * @author CaoHeYang
	 * @Date 20150804
	 * @return
	 */
	@Override
	public OrderDetailBusinessResp getOrderDetailBusiness(
			OrderDetailBusinessReq para) {
		OrderDetailBusinessResp modelsBusinessResp = new OrderDetailBusinessResp();
		modelsBusinessResp.setOrderModel(orderDao.getOrderDetailBusiness(para));
		modelsBusinessResp.setOrderChilds(orderChildDao
				.getOrderChildByOrderInfo(para.getOrderNo(),
						para.getBusinessId()));
		return modelsBusinessResp;
	}

	/**
	 * 商户取消订单功能
	 * 
	 * @param req
	 *            参数
	 * @author CaoHeYang
	 * @Date 20150804
	 * @return
	 */
	@Override
	public CancelOrderBusinessResp cancelOrderBusiness(
			CancelOrderBusinessReq req) {
		CancelOrderBusinessResp resp = new CancelOrderBusinessResp();
		if (req.getOrderId() <= 0 || req.getOrderNo().isEmpty()
				|| req.getOrderNo() == null || req.getBusinessId() <= 0) {
			resp.setResponseCode(CancelOrderBusinessReturnEnum.OrderEmpty
					.value());
			resp.setMessage(CancelOrderBusinessReturnEnum.OrderEmpty.desc());
			return resp;
		}
		Order orderSearch = new Order();// 查询取消的订单的基础数据
		orderSearch.setId(req.getOrderId());
		orderSearch.setOrderno(req.getOrderNo());
		orderSearch.setBusinessid(req.getBusinessId());
		orderSearch.setStatus((byte) OrderStatus.New.value()); // 查询状态属于待接单的
		Order orderRe = orderDao.getOneByCriteria(orderSearch); // 查询取消的订单的基础数据
		if (orderRe == null) {
			resp.setResponseCode(CancelOrderBusinessReturnEnum.CancelOrderError
					.value());
			resp.setMessage(CancelOrderBusinessReturnEnum.CancelOrderError
					.desc());
			return resp;
		}
		cancelOrderBusinessTrans(req, orderRe); // 事务代码
		return resp;
	}

	/**
	 * 商户取消订单功能事务单元
	 * 
	 * @author CaoHeYang
	 * @param req
	 *            请求参数
	 * @param orderRe
	 *            当前要取消的订单的基础数据
	 * @Date 20150806
	 */
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public void cancelOrderBusinessTrans(CancelOrderBusinessReq req,
			Order orderRe) {
		// 更新订单为 取消状态 参数
		Order updateModel = new Order();
		updateModel.setId(req.getOrderId());
		updateModel.setOrderno(req.getOrderNo());
		updateModel.setBusinessid(req.getBusinessId());
		updateModel.setStatus((byte) OrderStatus.New.value()); // 查询状态属于待接单的
		updateModel.setOthercancelreason("商家取消订单");
		updateModel.setAmount(orderRe.getSettlemoney()); // 取消订单涉及到的金额数目此处取到的当前订单的
															// 结算费 即商家应付
		if (orderDao.cancelOrderBusiness(updateModel) > 0) { /* 取消订单 针对订单表的逻辑 */
			BusinessBalanceRecord businessBalanceRecord = new BusinessBalanceRecord();
			businessBalanceRecord
					.setStatus((short) BusinessBalanceRecordStatus.Success
							.value()); // 流水状态(1、交易成功 // 2、交易中）
			businessBalanceRecord
					.setRecordtype((short) BusinessBalanceRecordRecordType.CancelOrder
							.value()); // 取消订单
			businessBalanceRecord.setOperator("商家:" + req.getBusinessId()); // 商家id
			businessBalanceRecord.setWithwardid((long) req.getOrderId()); // 订单id
			businessBalanceRecord.setRelationno(req.getOrderNo()); // 关联单号
			businessBalanceRecord.setRemark("商户取消订单返回配送费"); // 注释
			businessBalanceRecord.setBusinessid(req.getBusinessId());// 商户Id
			if (orderRe.getGroupbusinessid() > 0) {
				businessBalanceRecord.setAmount(0d);
				businessBalanceRecord.setGroupamount(orderRe.getSettlemoney());
				businessBalanceRecord.setGroupid(orderRe.getGroupbusinessid());
			} else {
				businessBalanceRecord.setAmount(orderRe.getSettlemoney());
				businessBalanceRecord.setGroupamount(0d);
				businessBalanceRecord.setGroupid(0);
			}
			businessService.updateForWithdrawC(1, businessBalanceRecord);
			OrderOther orderOther = new OrderOther();
			orderOther.setOrderid(req.getOrderId());
			orderOther.setCancelTime(new Date());
			orderOtherDao.updateByPrimaryKeySelective(orderOther);
		} else {
			throw new TransactionalRuntimeException("更新订单状态为取消状态时失败");
		}
	}

	/**
	 * 商户发布订单功能(后台)
	 * 
	 * @param req
	 * @return
	 * @author zhaohailong
	 * @Date 2015年8月6日 09:56:25
	 */
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public OrderResp AddOrder(OrderReq req) {
		OrderResp resp = new OrderResp();
		BusinessModel businessModel = businessDao.getBusiness((long) req
				.getBusinessid());
		// 校验是否可以正常发单
		PublishOrderReturnEnum returnEnum = verificationAddOrder(req,
				businessModel);
		if (returnEnum != PublishOrderReturnEnum.VerificationSuccess) {
			resp.setResponseCode(returnEnum.value());
			resp.setMessage(returnEnum.desc());
			return resp;
		}
		if (checkHasExist(req.getBusinessid())) {
			resp.setResponseCode(PublishOrderReturnEnum.OrderHasExist.value());
			resp.setMessage(PublishOrderReturnEnum.OrderHasExist.desc());
			return resp;
		}
		// 订单主表
		Order order = fillOrder(req, businessModel);
		orderDao.insert(order);
		// 记录发单日志
		OrderSubsidiesLog record = new OrderSubsidiesLog();
		record.setOrderid(order.getId());
		record.setOrderstatus(OrderStatus.New.value());
		record.setOptid(req.getBusinessid());
		record.setPrice(0d);
		record.setOptname(businessModel.getName());
		record.setRemark(TaskStatus.PublishOrder.desc());
		record.setPlatform(SuperPlatform.Business.value());
		orderSubsidiesLogDao.insert(record);

		// 扣除商家结算费
		BusinessBalanceRecord balanceRecord = new BusinessBalanceRecord();
		balanceRecord.setBusinessid(req.getBusinessid());
		if (order.getGroupbusinessid()!=null&&order.getGroupbusinessid() > 0) {
			balanceRecord.setAmount(0d);
			balanceRecord.setGroupamount(order.getSettlemoney());
			balanceRecord.setGroupid(order.getGroupbusinessid());
			balanceRecord.setBalance(businessModel.getBalanceprice());
		} else {
			balanceRecord.setAmount(order.getSettlemoney());
			balanceRecord.setGroupamount(0);
			balanceRecord.setGroupid(0);
		}

		balanceRecord.setStatus((short) BusinessBalanceRecordStatus.Success
				.value());
		balanceRecord
				.setRecordtype((short) BusinessBalanceRecordRecordType.PublishOrder
						.value());
		balanceRecord.setOperator(businessModel.getName());
		balanceRecord.setWithwardid((long) order.getId());
		balanceRecord.setRelationno(order.getOrderno());
		balanceRecord.setRemark("配送费支出金额");
		businessService.updateForWithdrawC(0, balanceRecord);

		// 记录补贴日志
		if (order.getAdjustment() > 0) {
			OrderSubsidiesLog adjustRecord = new OrderSubsidiesLog();
			adjustRecord.setOrderid(order.getId());
			adjustRecord.setPrice(order.getAdjustment());
			adjustRecord.setOrderstatus(OrderStatus.New.value());
			adjustRecord.setOptid(req.getBusinessid());
			adjustRecord.setOptname(TaskStatus.PublishOrder.desc());
			adjustRecord.setRemark("补贴加钱,订单金额:" + order.getAmount()
					+ "-佣金补贴策略id:" + order.getCommissionformulamode());
			adjustRecord.setPlatform(SuperPlatform.Business.value());
			orderSubsidiesLogDao.insert(adjustRecord);
		}

		// 写入订单Other表
		OrderOther orderOther = new OrderOther();
		orderOther.setOrderid(order.getId());
		orderOther.setNeeduploadcount(req.getOrdercount());
		orderOther.setHaduploadcount(0);
		orderOther.setPublongitude(businessModel.getLongitude());
		orderOther.setPublatitude(businessModel.getLatitude());
		orderOther.setOnekeypuborder(businessModel.getOnekeypuborder());
		orderOther.setIsorderchecked(businessModel.getIsOrderChecked());
		orderOther.setIsAllowCashPay(businessModel.getIsAllowCashPay());
		orderOtherDao.insert(orderOther);

		// 写入OrderChild
		if (req.getListOrderChild() != null
				&& req.getListOrderChild().size() > 0) {
			fillOrderChild(req, businessModel, order);
			orderChildDao.insertList(req.getListOrderChild());
		}
		resetOrderHasExist(req.getBusinessid());
		return resp;
	}


	
    // endregion 

	/**
	 * 判断该商家是否在30s内已经发过订单
	 * 
	 * @param businessID
	 * @author 赵海龙
	 * @return
	 */
	private boolean checkHasExist(int businessID) {
		String timespanKey = RedissCacheKey.Order_TimeSpan + businessID;
		Object object = redisService.get(timespanKey, Object.class);
		if (object == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 重置商家是否在30s内已经发过订单的标记
	 * 
	 * @param businessID
	 */
	private void resetOrderHasExist(int businessID) {
		String timespanKey = RedissCacheKey.Order_TimeSpan + businessID;
		Object object = redisService.get(timespanKey, Object.class);
		if (object != null) {
			redisService.remove(timespanKey);
		}
		redisService.set(timespanKey, "", 1);
	}

	/**
	 * 商户发单，点击按纽钱查询商户余额信息，以及该订单的结算信息
	 * 
	 * @author CaoHeYang
	 * @param req
	 * @Date 20150824
	 * @return
	 */
	public BusinessBalanceInfoResp getBalanceInfo(OrderReq req) {
		BusinessBalanceInfoResp resp = new BusinessBalanceInfoResp();
		req.setOrderfrom(OrderFrom.BusinessWeb.value()); // 订单来源 商家版后台
		BusinessModel businessModel = businessDao.getBusiness((long) req
				.getBusinessid());
		if (businessModel == null) {
			resp.setResponseCode(PublishOrderReturnEnum.BusinessEmpty.value());
			resp.setMessage(PublishOrderReturnEnum.BusinessEmpty.desc());
			return resp;
		}
		Double settleMoney = OrderSettleMoneyHelper.GetSettleMoney(
				req.getAmount(), businessModel.getBusinesscommission(),
				businessModel.getCommissionfixvalue(), req.getOrdercount(),
				businessModel.getDistribsubsidy(), req.getOrderfrom());
		resp.setBalanceprice(businessModel.getBalanceprice());
		resp.setSettleMoney(settleMoney);

		return resp;
	}

	@Override
	public BusinessOrderSummaryModel getBusinessOrderSummary(int businessId) {
		return orderDao.getBusinessOrderSummary(businessId);
	}

	@Override
	public List<BusiPubOrderTimeStatisticsModel> getBusiPubOrderTimeStatistics(
			int businessId, Date startTime, Date endTime) {
		return orderDao.getBusiPubOrderTimeStatistics(businessId, startTime,
				endTime);
	}

	@Override
	public PagedResponse<OrderListModel> customerGetOrders(
			PagedCustomerSearchReq req) {
		return orderDao.customerGetOrders(req);
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
	public OrderListModel getOrderByNoId(String orderNo, int orderId) {
		if (orderNo == null || orderNo.isEmpty() || orderId <= 0) {
			return null;
		}
		OrderListModel orderListModel = orderDao.getOrderByNoId(orderNo,
				orderId);
		if (orderListModel != null) {
			List<OrderDetail> orderDetails = orderDetailDao
					.getOrderDetailIByNoId(orderNo, orderId);
			orderListModel.setOrderDetailList(orderDetails);
			List<OrderChild> orderChilds = orderChildDao
					.getOrderChildByOrderInfo(orderNo, 0);
			orderListModel.setOrderChildList(orderChilds);
		}
		return orderListModel;
	}

	/**
	 * 管理后台取消订单
	 * 
	 * @author CaoHeYang
	 * @param auditOkOrde
	 * @date 20150831r
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public ResponseBase cancelOrder(OptOrder cancelOrder) {
		ResponseBase responseBase = new ResponseBase();
		OrderListModel orderModel = orderDao.getOrderByNoId(
				cancelOrder.getOrderNo(), cancelOrder.getOrderId());
		if (orderModel == null) {
			responseBase.setResponseCode(ResponseCode.PARAMETER_NULL_ERROR);
			responseBase.setMessage("未查询到订单信息！");
			return responseBase;
		}
		orderModel.setOptUserName(cancelOrder.getOptUserName()); // 操作人
		orderModel.setRemark(cancelOrder.getOptLog()); // 操作日志
		if (orderModel.getStatus() == OrderStatus.Cancel.value()) {
			responseBase.setResponseCode(ResponseCode.PARAMETER_NULL_ERROR);
			responseBase.setMessage("订单已为取消状态，不能再次取消操作！");
			return responseBase;
		}
		if (orderModel.getIsJoinWithdraw() == 1) {
			responseBase.setResponseCode(ResponseCode.PARAMETER_NULL_ERROR);
			responseBase.setMessage("订单已分账，不能取消订单！");
			return responseBase;
		}
		Integer orderTaskPayStatus = orderChildDao
				.getOrderTaskPayStatus(cancelOrder.getOrderId());
		// 线上结算 餐费未线上支付模式并且餐费有支付
		if (orderModel.getMealsSettleMode() == MealsSettleMode.LineOn.value()
				&& orderTaskPayStatus > 0 && !orderModel.getIsPay()) {
			responseBase.setResponseCode(ResponseCode.PARAMETER_NULL_ERROR);
			responseBase.setMessage("餐费有支付，不能取消订单！");
			return responseBase;
		}
		// 取消订单
		Order tempCanelOrder = new Order();
		tempCanelOrder.setId(cancelOrder.getOrderId());
		tempCanelOrder.setStatus((byte) OrderStatus.Cancel.value());
		orderDao.updateByPrimaryKeySelective(tempCanelOrder);
		// 记录取消订单日志
		OrderSubsidiesLog record = new OrderSubsidiesLog();
		record.setOrderid(cancelOrder.getOrderId());
		record.setOrderstatus(OrderStatus.Cancel.value());
		record.setOptid(cancelOrder.getOptUserId());
		record.setPrice(orderModel.getOrderCommission()); // 佣金
		record.setOptname(cancelOrder.getOptUserName());
		record.setPlatform(SuperPlatform.ManagementBackGround.value());
		record.setRemark(cancelOrder.getOptUserName() + "通过后台管理系统取消订单,用户操作描述：【"
				+ cancelOrder.getOptLog() + "】");
		orderSubsidiesLogDao.insert(record);
		// 更新取消订单时间
		OrderOther orderOther = new OrderOther();
		orderOther.setOrderid(cancelOrder.getOrderId());
		orderOther.setCancelTime(new Date());
		orderOtherDao.updateByPrimaryKeySelective(orderOther);
		// 已完成订单 子订单全部已支付
		if (orderModel.getStatus() == OrderStatus.Complite.value()
				&& orderTaskPayStatus == 2
				&& orderModel.getHadUploadCount() == orderModel
						.getNeedUploadCount()) // 已完成订单
		{
			// 更新骑士余额 插流水
			ClienterMoney clienterMoney = new ClienterMoney();
			clienterMoney.setClienterId(orderModel.getClienterId());
			clienterMoney.setAmount(-orderModel.getOrderCommission());
			clienterMoney
					.setStatus(ClienterBalanceRecordStatus.Success.value());
			clienterMoney
					.setRecordType(ClienterBalanceRecordRecordType.CancelOrder
							.value());
			clienterMoney.setOperator(cancelOrder.getOptUserName());
			clienterMoney.setWithwardId((long) orderModel.getId()); // 订单id
			clienterMoney.setRelationNo(orderModel.getOrderNo()); // 关联单号
			clienterMoney.setRemark(orderModel.getRemark());
			clienterService.updateCAccountBalance(clienterMoney);
		}
		// 更新商家余额可提现金额 插流水
		BusinessMoney businessMoney = new BusinessMoney();
		businessMoney.setAmount(orderModel.getSettleMoney());// 金额
		businessMoney.setBusinessId(orderModel.getBusinessId());// 商户Id
		businessMoney.setStatus((short) BusinessBalanceRecordStatus.Success
				.value());
		businessMoney
				.setRecordType((short) BusinessBalanceRecordRecordType.CancelOrder
						.value()); // 取消订单
		businessMoney.setOperator(cancelOrder.getOptUserName());
		businessMoney.setWithwardId((long) orderModel.getId()); // 订单id
		businessMoney.setRelationNo(orderModel.getOrderNo()); // 关联单号
		businessMoney.setRemark(orderModel.getRemark()); // 注释
		businessService.updateBBalanceAndWithdraw(businessMoney);
		responseBase.setMessage("订单取消成功");
		return responseBase;
	}

	/**
	 * 订单审核通过
	 * 
	 * @author CaoHeYang
	 * @param auditOkOrder
	 * @date 20150831
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public ResponseBase auditOk(OptOrder auditOkOrder) {
		ResponseBase responseBase = new ResponseBase();
		OrderListModel orderModel = orderDao.getOrderByNoId(
				auditOkOrder.getOrderNo(), auditOkOrder.getOrderId());
		if (orderModel == null) {
			responseBase.setResponseCode(ResponseCode.PARAMETER_NULL_ERROR);
			responseBase.setMessage("未查询到订单信息！");
			return responseBase;
		}
		if (orderModel.getIsJoinWithdraw() == 1) {
			responseBase.setResponseCode(ResponseCode.PARAMETER_NULL_ERROR);
			responseBase.setMessage("订单已分账，不能审核通过！");
			return responseBase;
		}
		// 更新骑士余额 插流水
		ClienterMoney clienterMoney = new ClienterMoney();
		clienterMoney.setClienterId(orderModel.getClienterId());
		clienterMoney.setAmount(orderModel.getOrderCommission() == null ? 0
				: orderModel.getOrderCommission());
		clienterMoney.setStatus(ClienterBalanceRecordStatus.Success.value());
		clienterMoney
				.setRecordType(ClienterBalanceRecordRecordType.OrderCommission
						.value());
		clienterMoney.setOperator(auditOkOrder.getOptUserName());
		clienterMoney.setWithwardId((long) orderModel.getId()); // 订单id
		clienterMoney.setRelationNo(orderModel.getOrderNo()); // 关联单号
		clienterMoney.setRemark("管理后台审核通过加可提现");
		clienterService.updateCAllowWithdrawPrice(clienterMoney);
		// 更新已提现状态
		orderOtherDao.updateJoinWithdraw(auditOkOrder.getOrderId());
		// 更新审核状态
		orderOtherDao.updateAuditStatus(auditOkOrder.getOrderId(),
				OrderAuditStatus.Through.value());
		// 写入订单日志
		OrderSubsidiesLog orderSubsidiesLog = new OrderSubsidiesLog();
		orderSubsidiesLog.setOrderid(auditOkOrder.getOrderId());
		orderSubsidiesLog.setPrice(orderModel.getOrderCommission() == null ? 0
				: orderModel.getOrderCommission());
		orderSubsidiesLog.setOptname(auditOkOrder.getOptUserName());
		orderSubsidiesLog.setRemark("审核通过，增加"
				+ (orderModel.getOrderCommission() == null ? 0 : orderModel
						.getOrderCommission()) + "元可提现金额");
		orderSubsidiesLog.setOptid(auditOkOrder.getOptUserId());
		orderSubsidiesLog.setOrderstatus(OrderOperationCommon.AuditStatusOk
				.value());
		orderSubsidiesLog.setPlatform(SuperPlatform.ManagementBackGround
				.value());
		orderSubsidiesLogDao.insert(orderSubsidiesLog);
		return responseBase;
	}

	/**
	 * 订单审核拒绝
	 * 
	 * @param auditRefuseOrder
	 * @author CaoHeYang
	 * @date 20150831
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public ResponseBase auditRefuse(OptOrder auditRefuseOrder) {
		OrderListModel orderModel = orderDao.getOrderWriteByNoId(
				auditRefuseOrder.getOrderNo(), auditRefuseOrder.getOrderId());
		ResponseBase responseBase = auditRefuseCheck(auditRefuseOrder,
				orderModel);
		if (responseBase.getResponseCode() != ResponseCode.SUCESS) {
			return responseBase;
		}
		// 如果要扣除的金额大于0， 写流水
		if (orderModel.getOrderCommission() > orderModel.getSettleMoney()) {
			ClienterBalanceRecord currenModel = clienterBalanceRecordDao
					.getByOrderId(orderModel.getId());
			if (currenModel == null) {
				double diffOrderCommission = orderModel.getSettleMoney()
						- orderModel.getOrderCommission();
				double disOrderCommission = -diffOrderCommission;
				// 更新骑士余额
				ClienterMoney clienterMoney = auditRefuseGetClienterMoney(
						orderModel, auditRefuseOrder);
				clienterMoney.setRemark(auditRefuseOrder.getOptLog());
				clienterMoney.setAmount(diffOrderCommission);
				clienterMoney.setStatus(ClienterBalanceRecordStatus.Success
						.value());
				clienterMoney
						.setRecordType(ClienterBalanceRecordRecordType.Abnormal
								.value());
				clienterService.updateCAccountBalance(clienterMoney);

				// 更新订单真实佣金 更新无效订单(状态，原因)
				OrderOtherSearch orderOtherSearch = auditRefuseGetOrderOtherSearch(
						orderModel, auditRefuseOrder);
				orderOtherSearch.setRealOrderCommission(disOrderCommission);
				// 写入订单日志
				OrderSubsidiesLog orderSubsidiesLog = auditRefuseGetOrderSubsidiesLog(
						orderModel, auditRefuseOrder);
				orderSubsidiesLog.setPrice(diffOrderCommission);
				orderSubsidiesLog.setRemark("扣除" + disOrderCommission
						+ "元无效订单金额");
				auditRefusePartial(orderOtherSearch, orderSubsidiesLog);
			}
		}
		// 更新订单真实佣金
		double realOrderCommission = orderModel.getOrderCommission() == null ? 0
				: orderModel.getOrderCommission();
		realOrderCommission = realOrderCommission > orderModel.getSettleMoney() ? orderModel
				.getSettleMoney() : realOrderCommission;
		// 更新骑士可提现余额
		ClienterMoney clienterMoney = auditRefuseGetClienterMoney(orderModel,
				auditRefuseOrder);
		clienterMoney.setRemark(auditRefuseOrder.getOptLog());
		clienterMoney.setAmount(realOrderCommission);
		clienterMoney.setStatus(ClienterAllowWithdrawRecordStatus.Success
				.value());
		clienterMoney
				.setRecordType(ClienterAllowWithdrawRecordType.OrderCommission
						.value());
		clienterMoney.setRemark("管理后台审核拒绝加可提现");
		clienterService.updateCAllowWithdrawPrice(clienterMoney);

		// 订单other操作
		OrderOtherSearch orderOtherSearch = auditRefuseGetOrderOtherSearch(
				orderModel, auditRefuseOrder);
		orderOtherSearch.setRealOrderCommission(realOrderCommission);
		// 写入订单日志
		OrderSubsidiesLog orderSubsidiesLog = auditRefuseGetOrderSubsidiesLog(
				orderModel, auditRefuseOrder);
		orderSubsidiesLog.setPrice(realOrderCommission);
		orderSubsidiesLog.setRemark("增加" + realOrderCommission + "元可提现金额");
		auditRefusePartial(orderOtherSearch, orderSubsidiesLog);
		// 更新已提现状态
		orderOtherDao.updateJoinWithdraw(auditRefuseOrder.getOrderId());
		// 更新审核状态
		orderOtherDao.updateAuditStatus(auditRefuseOrder.getOrderId(),
				OrderAuditStatus.Refuse.value());
		return responseBase;
	}

	/**
	 * 取消订单 1.更新订单真实佣金 2.更新无效订单(状态，原因) 3.插入订单操作日志
	 * 
	 * @author CaoHeYang
	 * @param orderOtherSearch
	 * @param orderSubsidiesLog
	 * @date 20150901
	 */
	private void auditRefusePartial(OrderOtherSearch orderOtherSearch,
			OrderSubsidiesLog orderSubsidiesLog) {
		// 更新订单真实佣金
		orderDao.updateOrderRealCommission(orderOtherSearch);
		// 更新无效订单(状态，原因)
		orderOtherDao.updateOrderIsReal(orderOtherSearch);
		// 插入订单操作日志
		orderSubsidiesLogDao.insert(orderSubsidiesLog);
	}

	/**
	 * 订单审核拒绝 数据服务器端验证
	 * 
	 * @author CaoHeYang
	 * @param auditRefuseOrder
	 * @param orderModel
	 * @date 20150901
	 * @return
	 */
	private ResponseBase auditRefuseCheck(OptOrder auditRefuseOrder,
			OrderListModel orderModel) {
		ResponseBase responseBase = new ResponseBase();
		if (auditRefuseOrder.getOptLog() == null
				|| auditRefuseOrder.getOptLog().isEmpty()) {
			responseBase.setResponseCode(ResponseCode.PARAMETER_NULL_ERROR);
			responseBase.setMessage("请填写扣除网站补贴原因");
			return responseBase;
		}
		if (orderModel == null) {
			responseBase.setResponseCode(ResponseCode.PARAMETER_NULL_ERROR);
			responseBase.setMessage("未查询到订单信息！");
			return responseBase;
		}
		// 订单已分账 不能审核拒绝
		if (orderModel.getIsJoinWithdraw() == 1) {
			responseBase.setResponseCode(ResponseCode.PARAMETER_NULL_ERROR);
			responseBase.setMessage("订单已分账，不能审核拒绝！");
			return responseBase;
		}
		return responseBase;
	}

	/**
	 * 订单审核拒绝 获取更新骑士余额或者可提现余额的基础数据实体
	 * 
	 * @author CaoHeYang
	 * @param orderModel
	 * @param auditRefuseOrder
	 * @date 20150901
	 * @return
	 */
	private ClienterMoney auditRefuseGetClienterMoney(
			OrderListModel orderModel, OptOrder auditRefuseOrder) {
		ClienterMoney clienterMoney = new ClienterMoney();
		clienterMoney.setClienterId(orderModel.getClienterId());
		clienterMoney.setOperator(auditRefuseOrder.getOptUserName());
		clienterMoney.setWithwardId(orderModel.getId());
		clienterMoney.setRelationNo(orderModel.getOrderNo());
		return clienterMoney;
	}

	/**
	 * 订单审核拒绝 获取订单日志的基础数据实体
	 * 
	 * @author CaoHeYang
	 * @param orderModel
	 * @param auditRefuseOrder
	 * @date 20150901
	 * @return
	 */
	private OrderSubsidiesLog auditRefuseGetOrderSubsidiesLog(
			OrderListModel orderModel, OptOrder auditRefuseOrder) {
		OrderSubsidiesLog orderSubsidiesLog = new OrderSubsidiesLog();
		orderSubsidiesLog.setOrderid(orderModel.getId());
		orderSubsidiesLog.setOrderstatus(OrderOperationCommon.AuditStatusRefuse
				.value());
		orderSubsidiesLog.setPlatform(SuperPlatform.ManagementBackGround
				.value());
		orderSubsidiesLog.setOptname(auditRefuseOrder.getOptUserName());
		orderSubsidiesLog.setOptid(auditRefuseOrder.getOptUserId());
		return orderSubsidiesLog;
	}

	/**
	 * 订单审核拒绝 获取更新订单other的基础数据
	 * 
	 * @author CaoHeYang
	 * @param orderModel
	 * @param auditRefuseOrder
	 * @date 20150901
	 * @return
	 */
	private OrderOtherSearch auditRefuseGetOrderOtherSearch(
			OrderListModel orderModel, OptOrder auditRefuseOrder) {
		OrderOtherSearch orderOtherSearch = new OrderOtherSearch();
		orderOtherSearch.setOrderId(orderModel.getId());
		orderOtherSearch
				.setDeductCommissionReason(auditRefuseOrder.getOptLog());
		orderOtherSearch.setDeductCommissionType(DeductCommissionType.People
				.value());
		return orderOtherSearch;
	}

	/**
	 * 订单导出数据
	 * 
	 * @author CaoHeYang
	 * @date 20150906
	 */
	public List<ExportOrder> exportOrder(PagedOrderSearchReq search) {
		return orderDao.exportOrder(search);
	}

	/**
	 * B端任务统计接口
	 * 
	 * @author CaoHeYang
	 * @date 20150910
	 * @param data
	 * @return
	 */
	@Override
	public HttpResultModel<OrderStatisticsBResp> getOrderStatisticsB(
			OrderStatisticsBReq orderStatisticsBReq) {
		HttpResultModel<OrderStatisticsBResp> resultModel = new HttpResultModel<OrderStatisticsBResp>();

		// 注释掉对用户状态的判断
/*		if (businessDao.getUserStatus(orderStatisticsBReq.getBusinessId())
				.getStatus() != BusinessStatusEnum.AuditPass.value()) {
			resultModel.setStatus(QueryOrderReturnEnum.ErrStatus.value());
			resultModel.setMessage(QueryOrderReturnEnum.ErrStatus.desc());
			return resultModel;
		}*/

		OrderStatisticsBResp orderStatisticsResp = orderDao
				.getOrderStatistics(orderStatisticsBReq);// 当月数据总览统计
		List<ServiceClienter> serviceClienters = orderDao
				.getOrderStatisticsServiceClienterB(orderStatisticsBReq); // 获取每天发单骑士信息
		List<DaySatisticsB> daySatisticsBs = orderDao
				.getOrderStatisticsDaySatistics(orderStatisticsBReq); // B端任务统计接口
																		// 天数据列表
		serviceClienters.forEach(action -> action
				.setClienterPhoto(
					 	ParseHelper.ToString(action.getClienterPhoto(), "")==""?"": 
						PropertyUtils.getProperty("ImageClienterServicePath")+ action.getClienterPhoto()));
		for (DaySatisticsB daySatisticsB : daySatisticsBs) {
			List<ServiceClienter> temp = serviceClienters
					.stream()
					.filter(t -> t.getPubDate().equals(
							daySatisticsB.getMonthDate()))
					.collect(Collectors.toList());
			
			daySatisticsB.setServiceClienters(temp);
		}
		orderStatisticsResp.setDatas(daySatisticsBs);
		resultModel.setResult(orderStatisticsResp);
		return resultModel;
	}

	/**
	 * C端任务统计接口
	 * 
	 * @author WangXuDan
	 * @date 20150910
	 * @param orderStatisticsCReq
	 */
	@Override
	public OrderStatisticsCResp getOrderStatisticsC(
			OrderStatisticsCReq orderStatisticsCReq) {
		OrderStatisticsCResp orderStatisticsResp = orderDao
				.getOrderStatisticsC(orderStatisticsCReq);
		List<DaySatisticsC> daySatisticsCs = orderDao
				.getOrderStatisticsDaySatisticsC(orderStatisticsCReq);
		orderStatisticsResp.setDatas(daySatisticsCs);
		return orderStatisticsResp;
	}

	/**
	 * B 端首页 订单列表
	 * 
	 * @author CaoHeYang
	 * @date 20150910
	 * @param data
	 * @return
	 */
	@Override
	public HttpResultModel<QueryOrderBResp> queryOrderB(QueryOrderReq query) {
		HttpResultModel<QueryOrderBResp> resultModel = new HttpResultModel<QueryOrderBResp>();
		// 验证商家状态
		if (businessDao.getUserStatus(query.getBusinessId()).getStatus() != BusinessStatusEnum.AuditPass
				.value()) {
			resultModel.setStatus(QueryOrderReturnEnum.ErrStatus.value());
			resultModel.setMessage(QueryOrderReturnEnum.ErrStatus.desc());
			return resultModel;
		}
		QueryOrderBResp queryOrderBResp = orderDao.queryOrderB(query);
		queryOrderBResp.setOrders(orderDao.queryOrder(query));
		resultModel.setResult(queryOrderBResp);
		return resultModel;
	}

	/**
	 * C 端我的任务
	 * 
	 * @author CaoHeYang
	 * @date 20150911
	 * @param para
	 */
	@Override
	public HttpResultModel<QueryOrderCResp> queryOrderC(QueryOrderReq query) {
		HttpResultModel<QueryOrderCResp> resultModel = new HttpResultModel<QueryOrderCResp>();
		// if (clienterService.getUserStatus(query.getClienterId()).getStatus()
		// != ClienterStatusEnum.AuditPass.value()) {
		// resultModel.setStatus(QueryOrderReturnEnum.ErrStatus.value()).setMessage(QueryOrderReturnEnum.ErrStatus.desc());
		// return resultModel;
		// }//TODO 茹化肖注释 参见BUG7705 【我的任务】资格取消后，进入我的任务，订单提示账号状态出错
		QueryOrderCResp m = orderDao.queryOrderC(query);
		if (query.getLongitude() != null
				&& query.getLongitude() != 0 // 需要计算骑士距离门店距离
				&& query.getLatitude() != null && query.getLatitude() != 0
				&& query.getStatus() == OrderStatus.Delivery.value()) {
			List<QueryOrder> orders = orderDao.queryDeliveryOrderC(query);
			orders.forEach(action -> action.setDistance(action
					.getDistance_OrderBy() < 1000 ? action
					.getDistance_OrderBy() + "m" : new BigDecimal(action
					.getDistance_OrderBy() * 0.001).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue()
					+ "km")); 
			m.setOrders(orders);
		} else { // 不需要计算骑士距离门店距离
			m.setOrders(orderDao.queryOrder(query));
		} 
		resultModel.setResult(m);
		return resultModel;
	}

	/**
	 * B端已完成任务列表或者配送员配送列表 或者C 端已完成任务
	 * 
	 * @author CaoHeYang
	 * @date 20150910
	 * @param query
	 * @param type
	 *            =0 B端 1 C端
	 * @return
	 */
	@Override
	public HttpResultModel<List<QueryOrder>> getCompliteOrder(
			QueryOrderReq query, int type) {
		query.setStatus(OrderStatus.Complite.value());
		HttpResultModel<List<QueryOrder>> res = new HttpResultModel<List<QueryOrder>>();

		// C端判断C端逻辑
		if (type == 1 && clienterService.getUserStatus(query.getClienterId()).getStatus() != ClienterStatusEnum.AuditPass.value())
		{
			res.setStatus(QueryOrderReturnEnum.ErrStatus.value());
			res.setMessage(QueryOrderReturnEnum.ErrStatus.desc());
			return res;
		}
		query.setorderBy(1);
		res.setResult(orderDao.queryOrder(query));
		return res;
	}

	/**
	 * 门店任务审核列表 茹化肖 2015年9月17日15:07:56
	 */
	@Override
	public PagedResponse<BusTaskList> busTaskList(PagedBusTaskListReq req) {

		if (req.getStartDate().equals("开始日期")) {
			req.setStartDate("");
		}
		if (req.getStartDate() != null && !req.getStartDate().equals("")) {
			req.setStartDate(req.getStartDate() + " 00:00:00");
		}
		if (req.getEndDate().equals("结束日期")) {
			req.setEndDate("");
		}
		if (req.getEndDate() != null && !req.getEndDate().equals("")) {
			req.setEndDate(req.getEndDate() + " 23:59:59");
		}
		if (req.getSelectValue().equals("门店名称/注册电话")) {
			req.setSelectValue("");
		}
		if (req.getCityName().equals("-1") || req.getCityName().equals("全部城市")) {
			req.setCityName("");
		}
		return this.orderDao.busTaskList(req);
	}

	/**
	 * 骑士端获取店内任务
	 * 
	 * @version 3.0
	 * @author CaoHeYang
	 * @date 20151030
	 * @param para
	 * @return
	 */
	public List<InStoreTask> getInStoreTask(InStoreTaskReq para) {
		List<InStoreTask> list = businessDao.getInStoreTaskStroes(para); // 获取当前骑士的所有含有未接单订单的
																			// 雇主信息
		List<InStoreOrderRegionInfo> regionInfos = orderRegionDao
				.getInStoreOrderRegions(para); // 获取当前骑士的所有含有未接单订单的 雇主信息
		List<InStoreOrderRegionInfo> temp = regionInfos.stream().filter(predicate -> predicate.getParentId() == 0)
				.collect(Collectors.toList()); // 筛选出所有的一级区域
		 // 为所有的一级区域中含有子区域的设置二级区域
		for(InStoreOrderRegionInfo inStoreOrderRegionInfo : temp)
		{
			if (inStoreOrderRegionInfo.getParentId()==0) {
				inStoreOrderRegionInfo.setChilds(regionInfos.stream().filter(pre -> pre.getParentId() == inStoreOrderRegionInfo
						   .getId()).collect(Collectors.toList()));
				//有数量 
				if(inStoreOrderRegionInfo.getWaitingCount()==0){
					int tempCount=0;
					for (InStoreOrderRegionInfo inStoreOrderRegionInfo2 : inStoreOrderRegionInfo.getChilds()) {
//						System.out.println(inStoreOrderRegionInfo2.getWaitingCount());
						tempCount=inStoreOrderRegionInfo2.getWaitingCount()+tempCount;
					}
					inStoreOrderRegionInfo.setWaitingCount(tempCount);
				}
				
			}
		}
		List<InStoreTask> returnList=new ArrayList<InStoreTask>(); 

		for (InStoreTask action : list) {// 将所有的区域归类到对应的商家下
			action.setList(temp
					.stream()
					.filter(predicate -> predicate.getBusinessId() == action
							.getBusinessId()).collect(Collectors.toList()));
			Double tempDis = ParseHelper.ToDouble(
					action.getDistanceToBusiness(), 0);
			action.setDistanceToBusiness(tempDis < 1000 ? tempDis + "m"
					: ParseHelper.digitsNum(tempDis * 0.001, 2) + "km");
	        if (action.getList()!=null&&action.getList().size()==9) {
			      returnList.add(action);
			}
		}
		return returnList;
	}
	/**
	 * 设置RegionOrderDetail的区域名称
	 * 移除无效的区域信息
	 * @author hailongzhao
	 * @date 20151123
	 * @param regionNames
	 * @param ListData
	 */
	private void setRegionName(Map<Long, String> regionNames,List<RegionOrderDetail> ListData){
		for (int i = 0; i < ListData.size(); i++) {
			if (regionNames.containsKey(ListData.get(i).getOrderRegionOneId())) {
				ListData.get(i).setOneName(regionNames.get(ListData.get(i).getOrderRegionOneId()));
				if (ListData.get(i).getOrderRegionTwoId()>0) {
					if (regionNames.containsKey(ListData.get(i).getOrderRegionTwoId())) {
						ListData.get(i).setTwoName(regionNames.get(ListData.get(i).getOrderRegionTwoId()));
					}else {
						ListData.remove(i);
					}
				}
			}else {
				ListData.remove(i);
			}
		}
	}
	/**
	 * 获取今日订单数量详情
	 * @author hailongzhao
	 * @date 20151123
	 */
	@Override
	public List<RegionOrderDetail> queryTodayOrderDetail(Long businessId) {
		List<RegionOrderDetail> result=new ArrayList<>();
		OrderRegionReq orderRegionReq=new OrderRegionReq();
		orderRegionReq.setBusinessId(Integer.parseInt(businessId.toString()));
		orderRegionReq.setStatus(1);
		List<OrderRegion> regions=orderRegionDao.getOrderRegion(orderRegionReq);
		Map<Long, String> regionNames=new HashMap<Long, String>();
		regions.stream().forEach(t->regionNames.put(Long.parseLong(t.getId().toString()), t.getName()));
		if (regionNames.size()==0) {
			return result;
		}

		List<RegionOrderDetail> ingData=orderDao.queryTodayOrderDetailing(businessId);
		List<RegionOrderDetail> waitData=orderDao.queryTodayOrderDetailWait(businessId);
		setRegionName(regionNames,ingData);
		setRegionName(regionNames,waitData);
		if (ingData.size()==0&&waitData.size()==0) {
			return result;
		}
		
		//没有二级的一级区域
		List<RegionOrderDetail> ingDataNoChild=ingData.stream().filter(t->t.getOrderRegionOneId()>0&&
				t.getOrderRegionTwoId().equals(0l)).collect(Collectors.toList());
		List<RegionOrderDetail> waitDataNoChild=waitData.stream().filter(t->t.getOrderRegionOneId()>0&&
				t.getOrderRegionTwoId().equals(0l)).collect(Collectors.toList());
		for (RegionOrderDetail regionOrderDetail : waitDataNoChild) {
			regionOrderDetail.setLevelType(0);
		}
		for (RegionOrderDetail regionOrderDetail : ingDataNoChild) {
			regionOrderDetail.setLevelType(0);
		}
		result.addAll(ingDataNoChild);
		result.addAll(waitDataNoChild);
		//二级区域
		List<RegionOrderDetail> ingDataTwo=ingData.stream().filter(t->t.getOrderRegionOneId()>0&&
				t.getOrderRegionTwoId()>0).collect(Collectors.toList());
		List<RegionOrderDetail> waitDataTwo=waitData.stream().filter(t->t.getOrderRegionOneId()>0&&
				t.getOrderRegionTwoId()>0).collect(Collectors.toList());
		for (RegionOrderDetail regionOrderDetail : waitDataTwo) {
			regionOrderDetail.setLevelType(1);
		}
		for (RegionOrderDetail regionOrderDetail : ingDataTwo) {
			regionOrderDetail.setLevelType(1);
		}
		result.addAll(ingDataTwo);
		result.addAll(waitDataTwo);
		
		//有二级的一级区域，需要把二级的数量汇总到一级区域中
		List<RegionOrderDetail> hasChildList=new ArrayList<>();
		hasChildList.addAll(ingDataTwo);
		hasChildList.addAll(waitDataTwo);
		List<String> hasChildOne=hasChildList.stream().map(t->t.getOrderRegionOneId()+"#"+
				t.getStatus()).collect(Collectors.toList());
		String[] tea=null;
		for (String string : hasChildOne) {
			tea=string.split("#");
			Long oneId=Long.parseLong(tea[0]);
			Integer status=Integer.parseInt(tea[1]);
			long sum=hasChildList.stream().filter(t->t.getOrderRegionOneId().equals(oneId)&&
					t.getStatus().equals(status)).mapToLong(m->m.getNum()).sum();
			RegionOrderDetail temp=new RegionOrderDetail();
			temp.setNum(sum);
			temp.setLevelType(2);
			temp.setOrderRegionOneId(Long.parseLong(tea[0]));
			temp.setOneName(regionNames.get(Long.parseLong(tea[0])));
			temp.setOrderRegionTwoId(0l);
			temp.setTwoName("");
			temp.setStatus(Integer.parseInt(tea[1]));
			result.add(temp);
		}
		

		return result;
		//return orderDao.queryTodayOrderDetail(businessId);
	}

	@Override
	public List<RegionOrderTotal> queryTodayOrderTotal(Long businessId) {
		return orderDao.queryTodayOrderTotal(businessId);
	}

	@Override
	public Long queryIngOrderByRegionId(Long regionId) {
		return orderDao.queryIngOrderByRegionId(regionId);
	}

	/*
	 * 用户自定义方法
	 */
	/**
	 * 商户发单数据验证
	 * 
	 * @author CaoHeYang
	 * @param req
	 * @param businessModel
	 * @Date 20150818
	 * @return
	 */
	private PublishOrderReturnEnum verificationAddOrder(OrderReq req,
			BusinessModel businessModel) {
		if (businessModel == null) {
			return PublishOrderReturnEnum.BusinessEmpty;
		}
		boolean isOneKeyPubOrder = false;
		if (businessModel != null && businessModel.getOnekeypuborder() == 1) {
			isOneKeyPubOrder = true;
		}
		if (businessModel.getStatus() != BusinessStatusEnum.AuditPass.value())// 验证该商户有无发布订单资格
																				// 审核通过下不允许发单
		{
			return PublishOrderReturnEnum.HadCancelQualification;
		}
		// 非一键发单模式下
		if (!isOneKeyPubOrder) {
			if (req.getRecevicephoneno() == null
					|| req.getRecevicephoneno().isEmpty())// 手机号
			{
				return PublishOrderReturnEnum.RecevicePhoneIsNULL;
			}
			if (req.getReceviceaddress() == null
					|| req.getReceviceaddress().isEmpty()) {
				return PublishOrderReturnEnum.ReceviceAddressIsNULL;
			}
		}
		if (req.getListOrderChild().size() > 15
				|| req.getListOrderChild().size() <= 0
				|| req.getOrdercount() != req.getListOrderChild().size()) {
			return PublishOrderReturnEnum.OrderCountError;
		}
		Double amount = 0d;
		for (int i = 0; i < req.getListOrderChild().size(); i++)// 子订单价格
		{
			if (req.getListOrderChild().get(i).getGoodprice() < 5) // 金额小于5不合法
			{
				return PublishOrderReturnEnum.AmountLessThanTen;
			}
			if (req.getListOrderChild().get(i).getGoodprice() > 1000) // 金额大于1000不合法
			{
				return PublishOrderReturnEnum.AmountMoreThanFiveThousand;
			}
			amount = amount + req.getListOrderChild().get(i).getGoodprice();
		}
		if (req.getAmount().compareTo(amount) != 0) {
			return PublishOrderReturnEnum.AmountIsNotEqual; // 金额有误
		}

		Double settleMoney = OrderSettleMoneyHelper.GetSettleMoney(
				req.getAmount(), businessModel.getBusinesscommission(),
				businessModel.getCommissionfixvalue(), req.getOrdercount(),
				businessModel.getDistribsubsidy(), req.getOrderfrom());

		if (businessModel.getBalanceprice() < settleMoney) {
			if (businessModel.getGroupBusinessID() > 0) {
				GroupBusiness groupBusiness = groupBusinessDao
						.select(businessModel.getGroupBusinessID());
				if (groupBusiness.getAmount() < settleMoney
						&& groupBusiness.getIsAllowOverdraft() == 0) {
					return PublishOrderReturnEnum.GroupBalancePriceLack;
				}
			} else if (businessModel.getIsallowoverdraft() == 0) {
				// 商家不允许透支
				return PublishOrderReturnEnum.BusiBalancePriceLack;
			}
		}

		return PublishOrderReturnEnum.VerificationSuccess;
	}

	/**
	 * api发单数据验证
	 * 
	 * @author 胡灵波
	 * @param req
	 * @param businessModel
	 * @Date 2015年11月9日 15:32:43
	 * @return
	 */
	private PublishOrderReturnEnum verificationPushOrder(OrderReq req,
			BusinessModel businessModel) {
		if (businessModel == null) {
			return PublishOrderReturnEnum.BusinessEmpty;
		}
		if (businessModel.getStatus() != BusinessStatusEnum.AuditPass.value()) {
			return PublishOrderReturnEnum.HadCancelQualification;
		}
		if (businessModel.getStrategyId() != Strategy.Strategy4.value()) {
			return PublishOrderReturnEnum.StrategyErr;
		}
		if (businessModel.getPushOrderType() != BusinessPushOrderType.Quick
				.value()) {
			return PublishOrderReturnEnum.PushOrderTypeErr;
		}

		Double settleMoney = OrderSettleMoneyHelper.GetSettleMoney(
				req.getAmount(), businessModel.getBusinesscommission(),
				businessModel.getCommissionfixvalue(), req.getOrdercount(),
				businessModel.getDistribsubsidy(), req.getOrderfrom());

		if (businessModel.getBalanceprice() < settleMoney) {
			if (businessModel.getGroupBusinessID() > 0) {
				GroupBusiness groupBusiness = groupBusinessDao
						.select(businessModel.getGroupBusinessID());
				if (groupBusiness.getAmount() < settleMoney
						&& groupBusiness.getIsAllowOverdraft() == 0) {
					return PublishOrderReturnEnum.GroupBalancePriceLack;
				}
			} else if (businessModel.getIsallowoverdraft() == 0) {
				// 商家不允许透支
				return PublishOrderReturnEnum.BusiBalancePriceLack;
			}
		}

		return PublishOrderReturnEnum.VerificationSuccess;
	}
	
	/**
	 * 发布订单根据请求参数，商家信息装配订单信息(后台，api)
	 * 
	 * @author ZhaoHaiLong
	 * @param req
	 * @param businessModel
	 *            商家信息
	 * @return
	 */
	private Order fillOrder(OrderReq req, BusinessModel businessModel) {
		Order order = new Order();
		order.setOrderno(OrderNoHelper.generateOrderCode(req.getBusinessid()));
		order.setRecevicename(req.getRecevicename());
		order.setRecevicephoneno(req.getRecevicephoneno());
		if (businessModel.getOnekeypuborder() != null
				&& businessModel.getOnekeypuborder() > 0
				&& (req.getReceviceaddress() == null || req
						.getReceviceaddress().isEmpty())) {
			order.setReceviceaddress(null);
		} else {
			order.setReceviceaddress(req.getReceviceaddress());
		}

		order.setIspay(req.getIspay());
		order.setAmount(req.getAmount());
		order.setRemark(req.getRemark());
		order.setOrderfrom(req.getOrderfrom());
		order.setStatus((byte) OrderStatus.New.value());
		order.setOrdercount(req.getOrdercount());
		order.setPubdate(new Date());
		order.setBusinessid(req.getBusinessid());
		order.setPickupaddress(businessModel.getAddress());
		order.setRecevicelongitude(0d);
		order.setRecevicelatitude(0d);
		order.setTimespan(req.getTimeSpan());
		order.setRecevicecity(businessModel.getCity());

		order.setCommissionformulamode(businessModel.getStrategyId());
		order.setBusinesscommission(businessModel.getBusinesscommission());
		order.setBusinessgroupid(businessModel.getBusinessgroupid());
		order.setCommissiontype(businessModel.getCommissiontype());
		order.setCommissionfixvalue(businessModel.getCommissionfixvalue());
		order.setMealssettlemode(businessModel.getMealssettlemode()); // 餐费结算方式（0：线下结算
																		// 1：线上结算）
		order.setDistribsubsidy(businessModel.getDistribsubsidy());

		OrderCommission orderCommission = new OrderCommission();
		orderCommission.setAmount(req.getAmount());
		orderCommission.setBusinessCommission(businessModel
				.getBusinesscommission());
		orderCommission.setBusinessGroupId(businessModel.getBusinessgroupid());
		orderCommission.setCommissionFixValue(businessModel
				.getCommissionfixvalue());
		orderCommission.setCommissionType(businessModel.getCommissiontype());
		orderCommission.setDistribSubsidy(businessModel.getDistribsubsidy());
		orderCommission.setOrderCount(req.getOrdercount());
		orderCommission.setStrategyId(businessModel.getStrategyId());

		OrderPriceBaseProvider orderPriceService = CommissionFactory
				.GetCommission(businessModel.getStrategyId());
		order.setOrdercommission(orderPriceService
				.getCurrenOrderCommission(orderCommission));
		order.setWebsitesubsidy(orderPriceService
				.getOrderWebSubsidy(orderCommission));
		order.setCommissionrate(orderPriceService
				.getCommissionRate(orderCommission));
		order.setAdjustment(orderPriceService.getAdjustment(orderCommission));
		order.setBasecommission(orderPriceService
				.getBaseCommission(orderCommission));

		Double settleMoney = OrderSettleMoneyHelper.GetSettleMoney(
				req.getAmount(), businessModel.getBusinesscommission(),
				businessModel.getCommissionfixvalue(), req.getOrdercount(),
				businessModel.getDistribsubsidy(), req.getOrderfrom());
		order.setSettlemoney(settleMoney);

		// 如果当前商家的余额不够支付订单了，则消费集团的金额
		if (businessModel.getGroupBusinessID() > 0
				&& businessModel.getBalanceprice() < settleMoney) {
			order.setGroupbusinessid(businessModel.getGroupBusinessID());
		}

		order.setBusinessreceivable(Double.valueOf(0));// 退还商家金额
		if (!req.getIspay()
				&& order.getMealssettlemode() == MealsSettleMode.LineOn.value()) {
			Double money = req.getAmount()
					+ (businessModel.getDistribsubsidy() * req.getOrdercount());

			order.setBusinessreceivable(money);
		}
		order.setPlatform(req.getPlatform());// 新平台

		return order;
	}

	
	/**
	 * 商家发单 插入子订单(后台)
	 * 
	 * @param req
	 * @param businessModel
	 * @param order
	 */
	private void fillOrderChild(OrderReq req, BusinessModel businessModel,
			Order order) {
		if (req.getListOrderChild() != null
				&& req.getListOrderChild().size() > 0) {
			OrderChild child = null;
			short payStatus = 0;
			if (req.getIspay()
					|| (!req.getIspay() && businessModel.getMealssettlemode() == MealsSettleMode.LineOff
							.value())) {
				payStatus = 1;
			}
			for (int i = 0; i < req.getListOrderChild().size(); i++) {
				child = req.getListOrderChild().get(i);
				child.setChildid(i + 1);
				child.setCreateby(businessModel.getName());
				child.setUpdateby(businessModel.getName());
				child.setDeliveryprice(order.getDistribsubsidy());
				child.setOrderid(order.getId());
				child.setTotalprice(child.getGoodprice()
						+ child.getDeliveryprice());
				child.setPaystatus(payStatus);
				child.setOriginalorderno("");
				child.setWxcodeurl("");
				child.setPayprice(0d);
				child.setHasuploadticket(false);
				child.setThirdpaystatus((short) 0);
				
				//以下属性是智能调度用到的属性
				child.setBusinessid(req.getBusinessid());
				child.setStatus((short)0);
				child.setOrderRegionOneId(0);
				child.setOrderRegionTwoId(0);
				child.setOrderCommission(0d);
				child.setSettleMoney(0d);
				child.setCommissionRate(0d);
				child.setBaseCommission(0d);
				child.setWebsiteSubsidy(0d);
				child.setAdjustment(0d);
				child.setPlatform(1);
			}
		}
	}

	/**
	 * 商家发单 插入子订单(api)
	 * 
	 * @param req
	 * @param
	 * @param order
	 */
	private List<OrderChild> fillOrderChildApi(OrderReq req,
			BusinessModel businessModel, Order order) {
		// 写入订单明细表
		double goodPrice = order.getAmount() / order.getOrdercount();// 单价
		List<OrderChild> listOrderChild = new ArrayList<OrderChild>();
		List<OrderRegionReq> listOrderRegion = req.getListOrderRegion();
		int num = 0;
		for (int i = 0; i < listOrderRegion.size(); i++) {

			for (int j = 0; j < ((OrderRegionReq) listOrderRegion.get(i))
					.getOrderCount(); j++) {
				OrderChild child = new OrderChild();
				num = num + 1;
				child.setChildid(num);
				child.setBusinessid(req.getBusinessid());
				child.setCreateby(businessModel.getName());
				child.setUpdateby(businessModel.getName());
				child.setDeliveryprice(order.getDistribsubsidy());
				child.setOrderid(order.getId());
				child.setTotalprice(goodPrice + order.getDistribsubsidy());
				child.setGoodprice(goodPrice);

				short payStatus = 0;
				if (req.getIspay()
						|| (!req.getIspay() && businessModel
								.getMealssettlemode() == MealsSettleMode.LineOff
								.value())) {
					payStatus = 1;
				}
				child.setPaystatus(payStatus);
				child.setOriginalorderno("");
				child.setWxcodeurl("");
				child.setPayprice(0d);
				child.setHasuploadticket(false);
				child.setThirdpaystatus((short) 0);

				child.setStatus((short) OrderStatus.New.value());
				child.setOrderRegionOneId(listOrderRegion.get(i)
						.getOrderRegionOneId());
				child.setOrderRegionTwoId(listOrderRegion.get(i)
						.getOrderRegionTwoId());
				child.setPlatform(2);

				OrderCommission orderCommission = new OrderCommission();
				orderCommission.setAmount(goodPrice);
				orderCommission.setBusinessCommission(businessModel
						.getBusinesscommission());
				orderCommission.setBusinessGroupId(businessModel
						.getBusinessgroupid());
				orderCommission.setCommissionFixValue(businessModel
						.getCommissionfixvalue());
				orderCommission.setCommissionType(businessModel
						.getCommissiontype());
				orderCommission.setDistribSubsidy(businessModel
						.getDistribsubsidy());
				orderCommission.setOrderCount(1);
				orderCommission.setStrategyId(businessModel.getStrategyId());

				OrderPriceBaseProvider orderPriceService = CommissionFactory
						.GetCommission(businessModel.getStrategyId());
				child.setOrderCommission(orderPriceService
						.getCurrenOrderCommission(orderCommission));
				child.setWebsiteSubsidy(orderPriceService
						.getOrderWebSubsidy(orderCommission));
				child.setCommissionRate(orderPriceService
						.getCommissionRate(orderCommission));
				child.setAdjustment(orderPriceService
						.getAdjustment(orderCommission));
				child.setBaseCommission(orderPriceService
						.getBaseCommission(orderCommission));

				Double settleMoney = OrderSettleMoneyHelper.GetSettleMoney(
						goodPrice, businessModel.getBusinesscommission(),
						businessModel.getCommissionfixvalue(), 1,
						businessModel.getDistribsubsidy(), req.getOrderfrom());
				child.setSettleMoney(settleMoney);
				listOrderChild.add(child);
			}
		}

		return listOrderChild;
	}

	/**
	 * api发布订单组织OrderOther对象
	 * 
	 * @author 胡灵波
	 * @param req
	 * @param
	 * @return
	 */
	private OrderOther fillOrderOther(OrderReq req, Order order,
			BusinessModel businessModel) {
		OrderOther orderOther = new OrderOther();
		orderOther.setOrderid(order.getId());
		orderOther.setNeeduploadcount(req.getOrdercount());
		orderOther.setHaduploadcount(0);
		orderOther.setPublongitude(businessModel.getLongitude());
		orderOther.setPublatitude(businessModel.getLatitude());
		orderOther.setOnekeypuborder(businessModel.getOnekeypuborder());
		orderOther.setIsorderchecked(businessModel.getIsOrderChecked());
		orderOther.setIsAllowCashPay(businessModel.getIsAllowCashPay());

		return orderOther;
	}
    

	/**
	 * json转化为列表 "listOrderRegionStr":
	 * "[{\"orderCount\":5,\"orderRegionTwoId\":4,\"orderRegionOneId\":1},{\"orderCount\":5,\"orderRegionTwoId\":5,\"orderRegionOneId\":2}]"
	 * 
	 * @author 胡灵波
	 * @param req
	 * @Date 2015年11月9日 13:53:24
	 * @return
	 */
	private List<OrderRegionReq> converAnswerFormString(String answer) {
		if (answer == null || answer.equals(""))
			return new ArrayList();

		List<OrderRegionReq> list = new ArrayList<OrderRegionReq>();
		JSONArray jsonArray;
		try {
			jsonArray = new JSONArray(answer);
			for (int i = 0; i < jsonArray.length(); i++) {
				OrderRegionReq model = new OrderRegionReq();
				model.setOrderRegionOneId(jsonArray.getJSONObject(i).getInt(
						"orderRegionOneId"));
				model.setOrderRegionTwoId(jsonArray.getJSONObject(i).getInt(
						"orderRegionTwoId"));
				model.setOrderCount(jsonArray.getJSONObject(i).getInt(
						"orderCount"));
				list.add(model);
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * 判断订单是否存在
	 * 
	 * @param businessID
	 * @author 胡灵波
	 * @return
	 */
	private boolean isExistByBusinessId(OrderReq req) {
		Order order = orderDao.selectIsExistByBusinessId(req);
		if (order == null) {
			return false;
		} else {
			return true;
		}
	} 
	/*
	 * C端任务统计
	 * wangchao
	 */
	@Override
	public OrderStatisticsCResp getOrderGrabStatisticsC(
			OrderStatisticsCReq orderStatisticsCReq) {
		OrderStatisticsCResp orderStatisticsResp = orderDao
				.getOrderGrabStatisticsC(orderStatisticsCReq);
		List<DaySatisticsC> daySatisticsCs = orderDao
				.getOrderGrabStatisticsDaySatisticsC(orderStatisticsCReq);
		orderStatisticsResp.setDatas(daySatisticsCs);
		return orderStatisticsResp;
	}
	/*
	 * B端任务统计
	 * wangchao
	 */
	@Override
	public HttpResultModel<OrderStatisticsBResp> getOrderGrabStatisticsB(
			OrderStatisticsBReq orderStatisticsBReq) {
		HttpResultModel<OrderStatisticsBResp> resultModel = new HttpResultModel<OrderStatisticsBResp>();

		// 注释掉对用户状态的判断
		if (businessDao.getUserStatus(orderStatisticsBReq.getBusinessId())
				.getStatus() != BusinessStatusEnum.AuditPass.value()) {
			resultModel.setStatus(QueryOrderReturnEnum.ErrStatus.value());
			resultModel.setMessage(QueryOrderReturnEnum.ErrStatus.desc());
			return resultModel;
		}

		OrderStatisticsBResp orderStatisticsResp = orderDao
				.getOrderGrabStatisticsB(orderStatisticsBReq);// 当月数据总览统计
		List<ServiceClienter> serviceClienters = orderDao
				.getOrderGrabStatisticsServiceClienterB(orderStatisticsBReq); // 获取每天发单骑士信息
		List<DaySatisticsB> daySatisticsBs = orderDao
				.getOrderGrabStatisticsDaySatisticsB(orderStatisticsBReq); // B端任务统计接口
																		// 天数据列表
		serviceClienters.forEach(action -> action
				.setClienterPhoto(
					 	ParseHelper.ToString(action.getClienterPhoto(), "")==""?"": 
						PropertyUtils.getProperty("ImageClienterServicePath")+ action.getClienterPhoto()));
		for (DaySatisticsB daySatisticsB : daySatisticsBs) {
			List<ServiceClienter> temp = serviceClienters
					.stream()
					.filter(t -> t.getPubDate().equals(daySatisticsB.getMonthDate()))
					.collect(Collectors.toList());
			
			daySatisticsB.setServiceClienters(temp);
		}
		orderStatisticsResp.setDatas(daySatisticsBs);
		resultModel.setResult(orderStatisticsResp);
		return resultModel;
	}

	@Override
	public HttpResultModel<List<QueryOrder>> getCompliteOrderGrab(
			QueryOrderReq query, int type) {
		query.setStatus(OrderStatus.Complite.value());
		HttpResultModel<List<QueryOrder>> res = new HttpResultModel<List<QueryOrder>>();
		if ((type == 0 && businessDao.getUserStatus(query.getBusinessId())
				.getStatus() != BusinessStatusEnum.AuditPass.value()) // B端判断B端逻辑
				|| (type == 1 && clienterService.getUserStatus(
						query.getClienterId()).getStatus() != ClienterStatusEnum.AuditPass
						.value())) {// C端判断C端逻辑
			res.setStatus(QueryOrderReturnEnum.ErrStatus.value());
			res.setMessage(QueryOrderReturnEnum.ErrStatus.desc());
			return res;
		}
		query.setorderBy(1);
		res.setResult(orderDao.queryOrderGrab(query));
		return res;
	}

	/**
	 * 后台E单订单列表页面
	 * 
	 * @author CaoHeYang
	 * @Date 20150728
	 * @param search
	 *            查询条件实体
	 * @return
	 */
	@Override
	public PagedResponse<ShanSongOrderListModel> getShanSongOrders(PagedOrderSearchReq search){
		return orderDao.getShanSongOrders(search);
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
	public List<ExportShanSongOrder> exportShanSongOrder(PagedOrderSearchReq search){
		return orderDao.exportShanSongOrder(search);
	}
	
	/**
	 * 根据订单号查订单信息
	 * 
	 * @author CaoHeYang
	 * @param ordernNo
	 *            订单号
	 * @Date 20151126
	 * @return
	 */
 @Override
	public ShanSongOrderListModel getShanSongOrderByNo(String orderNo) {
		if (orderNo == null || orderNo.isEmpty()) {
			return null;
		}
		ShanSongOrderListModel orderListModel = orderDao.getShanSongOrderByNo(orderNo);
		return orderListModel;
	}
 
 

	// region 普通械式
	// endregion 
	
	// region 快单模式
    /**
	 * 发布订单功能  快单模式 api
	 * 
	 * @param req
	 * @return
	 * @author 胡灵波
	 * @Date 2015年10月30日 11:45:19
	 */
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public HttpResultModel<OrderResp> PushOrder(OrderReq req) {

		req.setOrderfrom(OrderFrom.FastOrder.value());
		req.setPlatform(2);
		req.setIspay(true);
		
		HttpResultModel<OrderResp> resp = new HttpResultModel<OrderResp>();

		if(req.getOrdercount()>50)
		{
			resp.setStatus(PublishOrderReturnEnum.PushOrderCountErr.value());
			resp.setMessage(PublishOrderReturnEnum.PushOrderCountErr.desc());
			return resp;
		}
		//发单时判断 该商户下的以及区域是否够九个，不够提示 wangchao增加判断
		OrderRegionReq orrOrderRegionReq = new OrderRegionReq();
		orrOrderRegionReq.setBusinessId(req.getBusinessid());
		orrOrderRegionReq.setStatus(1);
		List<OrderRegion> listOrderRegions= orderRegionDao.getOrderRegion(orrOrderRegionReq); 
		if(listOrderRegions == null ){
			resp.setStatus(PublishOrderReturnEnum.PushErrRegionNotEnough.value());
			resp.setMessage(PublishOrderReturnEnum.PushErrRegionNotEnough.desc());
			return resp;
		}else{
			List<OrderRegion> firstOrderRegionList=	listOrderRegions.stream().filter(k->k.getParentid().equals(0)).collect(Collectors.toList());
			if(firstOrderRegionList.size()!=9){
				resp.setStatus(PublishOrderReturnEnum.PushErrRegionNotEnough.value());
				resp.setMessage(PublishOrderReturnEnum.PushErrRegionNotEnough.desc());
				return resp;
			}
		}
		
		// 时间戳
		if (req.getTimeSpan() != null && !req.getTimeSpan().equals("")) {
						if (isExistByBusinessId(req)) {
							resp.setStatus(PublishOrderReturnEnum.OrderHasExist.value());
							resp.setMessage(PublishOrderReturnEnum.OrderHasExist.desc());
							return resp;
						}
		}
		
		if(req.getBusinessid()==null)
		{
			resp.setStatus(PublishOrderReturnEnum.BusinessEmpty.value());
			resp.setMessage(PublishOrderReturnEnum.BusinessEmpty.desc());
			return resp;
		}
				
		// 获取商户信息讯(读串)
		BusinessModel businessModel = businessDao.getBusiness((long) req
				.getBusinessid());
		if(businessModel==null)
		{
			resp.setStatus(PublishOrderReturnEnum.BusinessEmpty.value());
			resp.setMessage(PublishOrderReturnEnum.BusinessEmpty.desc());
			return resp;
		}
		// 字符串转化为列表
		List<OrderRegionReq> list = converAnswerFormString(req.getListOrderRegionStr());
		req.setListOrderRegion(list);//赋值
		if (list == null || list.size() < 1) {
					resp.setStatus(PublishOrderReturnEnum.OrderRegionNull.value());
					resp.setMessage(PublishOrderReturnEnum.OrderRegionNull.desc());
					return resp;
		}	

		PublishOrderReturnEnum returnEnum = verificationPushOrder(req,
				businessModel);
		if (returnEnum != PublishOrderReturnEnum.VerificationSuccess) {
			resp.setStatus(returnEnum.value());
			resp.setMessage(returnEnum.desc());
			return resp;
		}

		// 订单主表		
		Order order = fillOrder(req, businessModel);
		order.setStatus((byte) OrderStatus.Cancel.value());
		int orderId = orderDao.insert(order);
		if (orderId <= 0) {
			throw new TransactionalRuntimeException("保存订单出错");
		}

		// 写入订单Other表
		OrderOther orderOther = fillOrderOther(req, order, businessModel);
		int orderOtherId = orderOtherDao.insert(orderOther);
		if (orderOtherId <= 0) {
			throw new TransactionalRuntimeException("保存订单其它出错");
		}
		// 写入订单明细表
		List<OrderChild> listOrderChild = fillOrderChildApi(req, businessModel,
				order);
		int orderChildID = orderChildDao.insertList(listOrderChild);
		if (orderChildID <= 0) {
			throw new TransactionalRuntimeException("保存订单明细出错");
		}	

		// 扣除商家结算费
		BusinessBalanceRecord balanceRecord = new BusinessBalanceRecord();
		balanceRecord.setBusinessid(req.getBusinessid());
		if (order.getGroupbusinessid()!=null && order.getGroupbusinessid() > 0) {
			balanceRecord.setAmount(0d);
			balanceRecord.setGroupamount(order.getSettlemoney());
			balanceRecord.setGroupid(order.getGroupbusinessid());
			balanceRecord.setBalance(businessModel.getBalanceprice());
		} else {
			balanceRecord.setAmount(order.getSettlemoney());
			balanceRecord.setGroupamount(0);
			balanceRecord.setGroupid(0);
		}

		balanceRecord.setStatus((short) BusinessBalanceRecordStatus.Success
				.value());
		balanceRecord
				.setRecordtype((short) BusinessBalanceRecordRecordType.PublishOrder
						.value());
		balanceRecord.setOperator(businessModel.getName());
		balanceRecord.setWithwardid((long) order.getId());
		balanceRecord.setRelationno(order.getOrderno());
		balanceRecord.setRemark("配送费支出金额");
		int bbcId = businessService.updateForWithdrawC(0, balanceRecord);	

		// 记录发单日志
		OrderSubsidiesLog record = new OrderSubsidiesLog();
		record.setOrderid(order.getId());
		record.setOrderstatus(OrderStatus.New.value());
		record.setOptid(req.getBusinessid());
		record.setPrice(0d);
		record.setOptname(businessModel.getName());
		record.setRemark(TaskStatus.PublishOrder.desc());
		record.setPlatform(SuperPlatform.NewApiPush.value());
		int ordersubsidiesId = orderSubsidiesLogDao.insert(record);
		if (ordersubsidiesId <= 0) {
			throw new TransactionalRuntimeException("记录订单日志错误");
		}

		// 记录补贴日志
		if (order.getAdjustment() > 0) {
			OrderSubsidiesLog adjustRecord = new OrderSubsidiesLog();
			adjustRecord.setOrderid(order.getId());
			adjustRecord.setPrice(order.getAdjustment());
			adjustRecord.setOrderstatus(OrderStatus.New.value());
			adjustRecord.setOptid(req.getBusinessid());
			adjustRecord.setOptname(TaskStatus.PublishOrder.desc());
			adjustRecord.setRemark("补贴加钱,订单金额:" + order.getAmount()
					+ "-佣金补贴策略id:" + order.getCommissionformulamode());
			adjustRecord.setPlatform(SuperPlatform.Business.value());
			int orderSubsidieslogId = orderSubsidiesLogDao.insert(adjustRecord);
			if (orderSubsidieslogId <= 0)
				throw new TransactionalRuntimeException("记录补贴日志错误");
		}

		resp.setStatus(PublishOrderReturnEnum.Success.value());
		resp.setMessage(PublishOrderReturnEnum.Success.desc());
		return resp;
	}
	// endregion
	
	// region 闪送模式
	
	/**
	 * 发布订单功能  闪送模式 api
	 * 
	 * @param req
	 * @return
	 * @author 胡灵波
	 * @Date 2015年10月30日 11:45:19
	 */
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public HttpResultModel<OrderResp> FlashPushOrder(OrderDraftReq req) {

		HttpResultModel<OrderResp> resp = new HttpResultModel<OrderResp>();
		OrderResp oResp=new OrderResp();
		//验证
		FlashPushOrderEnum returnEnum = verificationFlashPushOrder(req);
		if (returnEnum != FlashPushOrderEnum.VerificationSuccess) {
			resp.setStatus(returnEnum.value());
			resp.setMessage(returnEnum.desc());
			return resp;
		}
		
		//已登 录商户 未登录商户
		BusinessModel businessModel=new BusinessModel(); 
		if(req.getBusinessid()!=null && req.getBusinessid()>0)
		{
			businessModel = businessDao.getBusiness((long) req.getBusinessid());
			if (businessModel==null) {
				resp.setStatus(FlashPushOrderEnum.BusinessNotExits.value());
				resp.setMessage(FlashPushOrderEnum.BusinessNotExits.desc());
				return resp;	
			}
			if(businessModel.getName()==null)
				businessModel.setName("");
		}
		else
		{
			if(req.getBusinessphoneno()==null || req.getBusinessphoneno().equals(""))
			{
				resp.setStatus(FlashPushOrderEnum.BusinessPhonenoIsNull.value());
				resp.setMessage(FlashPushOrderEnum.BusinessPhonenoIsNull.desc());
				return resp;
			}
			if(req.getVerificationcode()==null || req.getVerificationcode().equals(""))
			{
				resp.setStatus(FlashPushOrderEnum.VerificationCodeIsNull.value());
				resp.setMessage(FlashPushOrderEnum.VerificationCodeIsNull.desc());
				return resp;
			}
			//验证吗
			String key = String.format(RedissCacheKey.PostRegisterInfo_B, req.getBusinessphoneno());
			String verificationCode= netRedisService.get(key, String.class);
			
			if(verificationCode==null || !verificationCode.equals(req.getVerificationcode())){
				resp.setStatus(FlashPushOrderEnum.VerificationCodeErr.value());
				resp.setMessage(FlashPushOrderEnum.VerificationCodeErr.desc());
				return resp;
			}
			businessModel = businessDao.getBusinessByPhoneNo(req.getBusinessphoneno());
			if(businessModel==null){
				//创建
				Business bModel=new Business();
				bModel.setName("");//姓名
				bModel.setPhoneno(req.getBusinessphoneno());
				bModel.setPhoneno2(req.getBusinessphoneno());
				bModel.setPassword(MD5Util.MD5(req.getVerificationcode().trim()));				
				bModel.setStatus((byte)0);//审核未通过
				bModel.setRegisterFrom(2);//注册来源				
				int bId= businessDao.insertSelective(bModel);
				if(bId<1)
				{
					resp.setStatus(FlashPushOrderEnum.CreateBusinessErr.value());
					resp.setMessage(FlashPushOrderEnum.CreateBusinessErr.desc());
					return resp;				
				}
				businessModel = businessDao.getBusiness((long) bModel.getId());
			}		
		}		
		if (businessModel==null) {
			resp.setStatus(FlashPushOrderEnum.BusinessNotExits.value());
			resp.setMessage(FlashPushOrderEnum.BusinessNotExits.desc());
			return resp;	
		}
		if(businessModel.getIsenable().equals(0))
		{
			resp.setStatus(FlashPushOrderEnum.BusinessIsEnableErr.value());
			resp.setMessage(FlashPushOrderEnum.BusinessIsEnableErr.desc());
			return resp;	
		}

		// 订单主表		
		Order order = fillFlashPushOrder(req, businessModel);	
		int orderId = orderDao.insert(order);
		if (orderId <= 0) {
			throw new TransactionalRuntimeException("保存订单出错");
		}

		// 写入订单Other表
		OrderOther orderOther = fillFlashPushOrderOther(req, order, businessModel);
		int orderOtherId = orderOtherDao.insertSelective(orderOther);
		if (orderOtherId <= 0) {
			throw new TransactionalRuntimeException("保存订单其它出错");
		}
		// 写入订单明细表
		List<OrderChild> listOrderChild = fillFlashPushOrderChild(req, businessModel,
				order);
		int orderChildID = orderChildDao.insertList(listOrderChild);
		if (orderChildID <= 0) {
			throw new TransactionalRuntimeException("保存订单明细出错");
		}	

		// 记录发单日志
		OrderSubsidiesLog record = new OrderSubsidiesLog();
		record.setOrderid(order.getId());
		record.setOrderstatus(OrderStatus.New.value());
		record.setOptid(req.getBusinessid());
		record.setPrice(0d);
		record.setOptname(businessModel.getName());
		record.setRemark(TaskStatus.PublishOrder.desc());
		record.setPlatform(SuperPlatform.SSApiPush.value());
		int ordersubsidiesId = orderSubsidiesLogDao.insert(record);
		if (ordersubsidiesId <= 0) {
			throw new TransactionalRuntimeException("记录订单日志错误");
		}

		// 记录补贴日志
		if (order.getAdjustment() > 0) {
			OrderSubsidiesLog adjustRecord = new OrderSubsidiesLog();
			adjustRecord.setOrderid(order.getId());
			adjustRecord.setPrice(order.getAdjustment());
			adjustRecord.setOrderstatus(OrderStatus.New.value());
			adjustRecord.setOptid(req.getBusinessid());
			adjustRecord.setOptname(TaskStatus.PublishOrder.desc());
			adjustRecord.setRemark("补贴加钱,订单金额:" + order.getAmount()
					+ "-佣金补贴策略id:" + order.getCommissionformulamode());
			adjustRecord.setPlatform(SuperPlatform.Business.value());
			int orderSubsidieslogId = orderSubsidiesLogDao.insert(adjustRecord);
			if (orderSubsidieslogId <= 0)
				throw new TransactionalRuntimeException("记录补贴日志错误");
		}
		/*//订单小费
		if(req.getTipamount()>0)
		{
			OrderTipCost otcModel=new OrderTipCost();
			otcModel.setOrderid(order.getId());
			otcModel.setAmount(req.getTipamount());
			otcModel.setCreatename(businessModel.getName());			
			
			int otcId=orderTipCostDao.insertSelective(otcModel);
			if (otcId <= 0)
				throw new TransactionalRuntimeException("记录小费错误");
		}*/
		
		oResp.setMealssettlemode(order.getMealssettlemode());		
		oResp.setOrderId(order.getId());
		oResp.setPickupcode(order.getPickupcode());
		oResp.setIsAllowCashPay(businessModel.getIsAllowCashPay());
		oResp.setBusinessId(businessModel.getId());
		oResp.setStatus(businessModel.getStatus());
		oResp.setCity(businessModel.getCity());
		oResp.setDistrictid(businessModel.getDistrictid());
		oResp.setDistrict(businessModel.getDistrict());
		oResp.setAddress(businessModel.getAddress());
		oResp.setLandline(businessModel.getLandline());
		oResp.setName(businessModel.getName());
		oResp.setCityid(businessModel.getCityid());
		oResp.setPhoneno(businessModel.getPhoneno());
		oResp.setDistribsubsidy(businessModel.getDistribsubsidy());
		oResp.setOriginalbusiid(businessModel.getOriginalbusiid());
		oResp.setAppkey(businessModel.getAppkey());		
		oResp.setBalanceprice(businessModel.getBalanceprice());		   
		
		resp.setResult(oResp);
		resp.setStatus(PublishOrderReturnEnum.Success.value());
		resp.setMessage(PublishOrderReturnEnum.Success.desc());
		return resp;
	}
	
	/**
	 * 余额支付 闪送模式 
	 * 
	 * @param req
	 *            参数
	 * @author 胡灵波
	 * @Date 2015年12月14日 11:24:01
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public HttpResultModel<OrderBlancePayResp> OrderBalancePay(OrderBlancePayReq req)
	{
		HttpResultModel<OrderBlancePayResp> resp = new HttpResultModel<OrderBlancePayResp>();

		if(req.getOrderId()<1)
		{
			resp.setStatus(FlashPushOrderEnum.OrderIdIsNull.value());
			resp.setMessage(FlashPushOrderEnum.OrderIdIsNull.desc());
			return resp;
		}		
		OrderChild ocModel= orderChildDao.selectByOrderId(req.getOrderId());
		if(ocModel==null )
		{
			resp.setStatus(FlashPushOrderEnum.OrderChildId.value());
			resp.setMessage(FlashPushOrderEnum.OrderChildId.desc());
			return resp;
		}
		req.setOrderChildId(ocModel.getId().intValue());		
		
		// 获取商户信息讯(读串)
		BusinessModel businessModel = businessDao.getBusiness((long) req
				.getBusinessId());
		if(businessModel==null)
		{
			resp.setStatus(PublishOrderReturnEnum.BusinessEmpty.value());
			resp.setMessage(PublishOrderReturnEnum.BusinessEmpty.desc());
			return resp;
		}
		
		Order oModel=orderDao.selectByPrimaryKey(req.getOrderId());
		if(oModel==null)
		{
			resp.setStatus(FlashPushOrderEnum.OrderIdIsNull.value());
			resp.setMessage(FlashPushOrderEnum.OrderIdIsNull.desc());
			return resp;
		}

		if(req.getType()==1)
		{
			if(oModel.getStatus()!=50)
			{
				resp.setStatus(FlashPushOrderEnum.OrderIdIsPay.value());
				resp.setMessage(FlashPushOrderEnum.OrderIdIsPay.desc());
				return resp;
			}
			double zfAmount= oModel.getAmount()+oModel.getTipamount();
			if(zfAmount>businessModel.getBalanceprice())
			{
				resp.setStatus(FlashPushOrderEnum.BlanceErr.value());
				resp.setMessage(FlashPushOrderEnum.BlanceErr.desc());
				return resp;			
			}

			//更新订单状态
			Order order=new Order();
			order.setIspay(true);
			order.setStatus((byte)0);
			order.setId(req.getOrderId());
			int oId=orderDao.updateByPrimaryKeySelective(order);
			if(oId<0)
				throw new TransactionalRuntimeException("更新订单状态错误");
			
			//PayStatus
			OrderChild orderchild=new OrderChild();
			orderchild.setPaystatus((short)1);	
			orderchild.setPaytype((short)0);//余额
			orderchild.setId((long)req.getOrderChildId());
			int ocId=orderChildDao.updateByPrimaryKeySelective(orderchild);
			if(oId<0)
				throw new TransactionalRuntimeException("更新子订单状态错误");
			
			// 扣除商家结算费
			BusinessBalanceRecord balanceRecord = new BusinessBalanceRecord();
			balanceRecord.setBusinessid(oModel.getBusinessid());
			double amount=oModel.getAmount()+oModel.getTipamount();
			balanceRecord.setAmount(amount);
			balanceRecord.setGroupamount(0);
			balanceRecord.setGroupid(0);
			balanceRecord.setStatus((short) BusinessBalanceRecordStatus.Success
					.value());
			balanceRecord
					.setRecordtype((short) BusinessBalanceRecordRecordType.PublishOrder
							.value());
			balanceRecord.setOperator(ParseHelper.ToString(businessModel.getName(),""));
			balanceRecord.setWithwardid((long) oModel.getId());
			balanceRecord.setRelationno(oModel.getOrderno());
			balanceRecord.setRemark("配送费支出金额");
			int bbcId = businessService.updateForWithdrawC(0, balanceRecord);		
			if(bbcId<0)
				throw new TransactionalRuntimeException("记录商户流水错误");		
	

			OrderTipCost otcModel=new OrderTipCost();
			otcModel.setOrderid(oModel.getId());
			otcModel.setAmount(amount);
			otcModel.setTipamount(oModel.getTipamount());
			otcModel.setCreatename(businessModel.getName());	
			int otcId=orderTipCostDao.insertSelective(otcModel);
			if (otcId <= 0)
				throw new TransactionalRuntimeException("记录小费错误");			
						
		}
		else
		{//抢单
			if(req.getTipamount()<=0)
			{
				resp.setStatus(PublishOrderReturnEnum.BusinessEmpty.value());
				resp.setMessage(PublishOrderReturnEnum.BusinessEmpty.desc());
				return resp;
			}			
			
			if(req.getTipamount()>businessModel.getBalanceprice())
			{
				resp.setStatus(FlashPushOrderEnum.BlanceErr.value());
				resp.setMessage(FlashPushOrderEnum.BlanceErr.desc());
				return resp;			
			}
			
			Order order=new Order();
			order.setIspay(true);
			order.setStatus((byte)0);
			order.setId(req.getOrderId());
			int oId=orderDao.updateByPrimaryKeySelective(order);
			if(oId<0)
				throw new TransactionalRuntimeException("更新订单状态错误");
			
			//PayStatus
			OrderChild orderchild=new OrderChild();
			orderchild.setPaystatus((short)1);	
			orderchild.setPaytype((short)0);//余额
			orderchild.setId((long)req.getOrderChildId());
			int ocId=orderChildDao.updateByPrimaryKeySelective(orderchild);
			if(oId<0)
				throw new TransactionalRuntimeException("更新子订单状态错误");
			
			// 扣除商家结算费
			BusinessBalanceRecord balanceRecord = new BusinessBalanceRecord();
			balanceRecord.setBusinessid(oModel.getBusinessid());			
			balanceRecord.setAmount(req.getTipamount());
			balanceRecord.setGroupamount(0);
			balanceRecord.setGroupid(0);
			balanceRecord.setStatus((short) BusinessBalanceRecordStatus.Success
							.value());
			balanceRecord
								.setRecordtype((short) BusinessBalanceRecordRecordType.PublishOrder
										.value());
			balanceRecord.setOperator(businessModel.getName());
			balanceRecord.setWithwardid((long) oModel.getId());
			balanceRecord.setRelationno(oModel.getOrderno());
			balanceRecord.setRemark("配送费支出金额");
			int bbcId = businessService.updateForWithdrawC(0, balanceRecord);		
			if(bbcId<0)
				throw new TransactionalRuntimeException("记录商户流水错误");
			
			//小费 			
			OrderTipCost otcModel=new OrderTipCost();
			otcModel.setOrderid(oModel.getId());
			otcModel.setAmount(req.getTipamount());
			otcModel.setTipamount(req.getTipamount());
			otcModel.setCreatename(businessModel.getName());		
			int otcId=orderTipCostDao.insertSelective(otcModel);
			if (otcId <= 0)
				throw new TransactionalRuntimeException("记录小费错误");			

			//更新小费
			Order order2=new Order();			
			double amount2=oModel.getTipamount()+ req.getTipamount();
			order2.setTipamount(amount2);		
			order2.setId(req.getOrderId());
			int oId2=orderDao.updateByPrimaryKeySelective(order2);
			if(oId2<0)
				throw new TransactionalRuntimeException("更新订单小费错误");
		}	

		resp.setStatus(PublishOrderReturnEnum.Success.value());
		resp.setMessage(PublishOrderReturnEnum.Success.desc());
		return resp;			
	}
	/** 
	 * 获取订单详情  闪送模式 api
	 * 
	 * @author 胡灵波
	 * @param req
	 * @Date 2015年11月27日 12:03:02
	 * @return
	 */
	@Override
	public HttpResultModel<OrderDetailResp> GetOrderDetails(OrderDetailReq req) {		
		HttpResultModel<OrderDetailResp> resp=new HttpResultModel<OrderDetailResp>(); 
		OrderDetailResp odResp=new OrderDetailResp();
		int orderId=req.getOrderId();		
		if (orderId<1)
		{
			resp.setStatus(OrderDetailGet.OrderIdIsNULL.value());
			resp.setMessage(OrderDetailGet.OrderIdIsNULL.desc());
			return resp;
		}

		Order oModel=orderDao.selectByPrimaryKey(orderId);
		OrderOther ooModel= orderOtherDao.selectByOrderId(orderId);		
		List<OrderChild> ocList=orderChildDao.getOrderChildByOrderId(orderId);
		BusinessModel businessModel = businessDao.getBusiness((long) oModel.getBusinessid());		
		Clienter clienter= clienterDao.selectByPrimaryKey(oModel.getClienterid());
		if(clienter !=null){
			odResp.setClienterName(clienter.getTruename());
			odResp.setClienterPhoneNo(clienter.getPhoneno()); 
		}
		odResp.setId(oModel.getId());
		odResp.setOrderno(oModel.getOrderno());
		odResp.setPickupaddress(oModel.getPickupaddress());
		odResp.setPubdate(oModel.getPubdate());
		odResp.setRecevicename(oModel.getRecevicename());
		odResp.setRecevicephoneno(oModel.getRecevicephoneno());
		odResp.setReceviceaddress(oModel.getReceviceaddress());
		odResp.setActualdonedate(oModel.getActualdonedate());
		odResp.setIspay(oModel.getIspay());
		odResp.setAmount(oModel.getAmount());
		odResp.setOrdercommission(oModel.getOrdercommission());
		odResp.setDistribsubsidy(oModel.getDistribsubsidy());
		odResp.setWebsitesubsidy(oModel.getWebsitesubsidy());
		odResp.setRemark(oModel.getRemark());	 
		odResp.setStatus(oModel.getStatus());	
		odResp.setClienterid(oModel.getClienterid());	 
		odResp.setBusinessid(oModel.getBusinessid());	 
		odResp.setRecevicecity(oModel.getRecevicecity());	 
		odResp.setRecevicelongitude(oModel.getRecevicelongitude());	 
		odResp.setRecevicelatitude(oModel.getRecevicelatitude());			
		odResp.setOrderfrom(oModel.getOrderfrom());	 
		odResp.setOriginalorderid(oModel.getOriginalorderid());	 
		odResp.setOriginalorderno(oModel.getOriginalorderno());	 
		odResp.setQuantity(oModel.getQuantity());	 
		odResp.setWeight(oModel.getWeight());	 
		odResp.setReceiveprovince(oModel.getReceiveprovince());	 
		odResp.setReceivearea(oModel.getReceivearea());	 
		odResp.setReceiveprovincecode(oModel.getReceiveprovincecode());	 
		odResp.setReceivecitycode(oModel.getReceivecitycode());	 
		odResp.setReceiveareacode(oModel.getReceiveareacode());	 
		odResp.setOrdertype(oModel.getOrdertype());	 
		odResp.setKm(oModel.getKm());		
		odResp.setGuojuqty(oModel.getGuojuqty());	 
		odResp.setLujuqty(oModel.getLujuqty());	 
		odResp.setSongcandate(oModel.getSongcandate());	 
		odResp.setOrdercount(oModel.getOrdercount());		
		odResp.setCommissionrate(oModel.getCommissionrate());	 
		odResp.setPayment(oModel.getPayment());	 
		odResp.setCommissionformulamode(oModel.getCommissionformulamode());	 
		odResp.setAdjustment(oModel.getAdjustment());	
		odResp.setBusinesscommission(oModel.getBusinesscommission());			
		odResp.setSettlemoney(oModel.getSettlemoney());	 
		odResp.setDealcount(oModel.getDealcount());	 
		if(req.getBusinessId()!=null && req.getBusinessId()>0)
		{
			odResp.setPickupcode(oModel.getPickupcode()); 
		}
		else
		{
			odResp.setPickupcode("");
		}
		odResp.setOthercancelreason(oModel.getOthercancelreason());	 		
		odResp.setCommissiontype(oModel.getCommissiontype());	 
		odResp.setCommissionfixvalue(oModel.getCommissionfixvalue());	 
		odResp.setBusinessgroupid(oModel.getBusinessgroupid());			
		odResp.setTimespan(oModel.getTimespan());	 
		odResp.setInvoice(oModel.getInvoice());	 
		odResp.setBusinessreceivable(oModel.getBusinessreceivable());	 
		odResp.setMealssettlemode(oModel.getMealssettlemode());	 
		odResp.setFinishdatelength(oModel.getFinishdatelength());	 
		odResp.setFinishall(oModel.getFinishall());	 
		odResp.setRealordercommission(oModel.getRealordercommission());	 
		odResp.setGroupbusinessid(oModel.getGroupbusinessid());	 
		odResp.setBasecommission(oModel.getBasecommission());	 
		odResp.setPlatform(oModel.getPlatform());	 
		//
		if(oModel.getPlatform()==1)
			odResp.setPlatformstr(OrderPlatform.EDaiSong.desc());
		if(oModel.getPlatform()==2)
			odResp.setPlatformstr(OrderPlatform.FastOrder.desc());
		if(oModel.getPlatform()==3)
			odResp.setPlatformstr(OrderPlatform.FlashOrder.desc());
		
		odResp.setPubname(oModel.getPubname());	 
		odResp.setPubphoneno(oModel.getPubphoneno());		
		odResp.setTaketype(oModel.getTaketype()); 
		odResp.setProductname(oModel.getProductname());
		odResp.setIscomplain(oModel.getIscomplain());
		odResp.setPickuplongitude(oModel.getPickuplongitude());
		odResp.setPickuplatitude(oModel.getPickuplatitude());;
		odResp.setIscomplain(oModel.getIscomplain());	
			
		odResp.setListOrderChild(ocList);

		odResp.setNeeduploadcount(ooModel.getNeeduploadcount());	
		odResp.setReceiptpic(ooModel.getReceiptpic());
		odResp.setHaduploadcount(ooModel.getHaduploadcount());
		odResp.setIsjoinwithdraw(ooModel.getIsjoinwithdraw());
		odResp.setPublongitude(ooModel.getPublongitude());
		odResp.setPublatitude(ooModel.getPublatitude());
		odResp.setGrabtime(ooModel.getGrabtime());
		odResp.setGrablongitude(ooModel.getGrablongitude());
		odResp.setGrablatitude(ooModel.getGrablatitude());
		odResp.setCompletelongitude(ooModel.getCompletelongitude());
		odResp.setCompletelatitude(ooModel.getCompletelatitude());
		odResp.setTaketime(ooModel.getTaketime());
		odResp.setTakelongitude(ooModel.getTakelongitude());
		odResp.setTakelatitude(ooModel.getTakelatitude());
		odResp.setPubtograbdistance(ooModel.getPubtograbdistance());
		odResp.setGrabtocompletedistance(ooModel.getGrabtocompletedistance());
		odResp.setPubtocompletedistance(ooModel.getPubtocompletedistance());
		odResp.setOnekeypuborder(ooModel.getOnekeypuborder());
		odResp.setIsnotrealorder(ooModel.getIsnotrealorder());
		odResp.setIsorderchecked(ooModel.getIsorderchecked());
		odResp.setCancelTime(ooModel.getCancelTime());
		odResp.setIsAllowCashPay(ooModel.getIsAllowCashPay());  //是否允许现金支付
		odResp.setExpectedDelivery(ooModel.getExpecteddelivery());
		//取货之前，
		double a =0d;
		if (oModel.getPlatform().intValue()==3) {//闪送模式时，取骑士到取货点的距离
			a = MapUtils.GetShortDistance(req.getLongitude(),req.getLatitude(),ParseHelper.ToDouble(oModel.getPickuplongitude(),0),ParseHelper.ToDouble(oModel.getPickuplatitude(),0));
		}else {//骑士到商户的距离
			a = MapUtils.GetShortDistance(req.getLongitude(),req.getLatitude(),ParseHelper.ToDouble(businessModel.getLongitude(),0),ParseHelper.ToDouble(businessModel.getLatitude(),0));
		}			
		 a= (new BigDecimal(a)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();	
		
		 BigDecimal bg = new BigDecimal(a/1000);
		 double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();		
	     String strA = a < 1000 ? String.valueOf(a).concat("m"): String.valueOf(f1).concat("km");  
	     odResp.setPubtocurrentdistance(strA);
		//取货之后，骑士到客户的距离	     
		 double b = MapUtils.GetShortDistance(req.getLongitude(),req.getLatitude(),ParseHelper.ToDouble(oModel.getRecevicelongitude(),0),ParseHelper.ToDouble(oModel.getRecevicelatitude(),0));
		 b= (new BigDecimal(b)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	     BigDecimal bg2 = new BigDecimal(b/1000);
		 double f2 = bg2.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		 String strB  = b < 1000 ? String.valueOf(b).concat("m")  : String.valueOf(f2).concat("km");   	     
		odResp.setRecevicetocurrentdistance(strB);	
		odResp.setExpectedTakeTime(ooModel.getExpectedtaketime());
		odResp.setName(businessModel.getName());
		odResp.setPhoneno(businessModel.getPhoneno());
		odResp.setPhoneno2(businessModel.getPhoneno2());
		odResp.setAddress(businessModel.getAddress());
		odResp.setLandline(businessModel.getLandline());  
		odResp.setCity(businessModel.getCity());
		odResp.setBalancePrice(businessModel.getBalanceprice());
		odResp.setLatitude(businessModel.getLatitude());
		odResp.setLongitude(businessModel.getLongitude());			
	
				
		odResp.setIsmodifyticket(true);
        if (ooModel.getHaduploadcount() >=  oModel.getOrdercount() && oModel.getStatus().byteValue() == OrderStatus.Complite.value())
        {
        	odResp.setIsmodifyticket(false);
        }
		resp.setStatus(OrderDetailGet.Success.value());
		resp.setMessage(OrderDetailGet.Success.desc()); 
		
		resp.setResult(odResp);
		return resp;
	}
    
	/**
	 * 闪送管理后台取消订单 闪送械式 管理后台
	 * 
	 * @author CaoHeYang
	 * @param cancelOrder
	 * @date 20151126
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public ResponseBase shanSongCancelOrder(OptOrder cancelOrder) {
		ResponseBase responseBase = new ResponseBase();
		OrderListModel orderModel = orderDao.getOrderByNoId(
				cancelOrder.getOrderNo(), cancelOrder.getOrderId());
		if (orderModel == null) {
			responseBase.setResponseCode(ResponseCode.PARAMETER_NULL_ERROR);
			responseBase.setMessage("未查询到订单信息！");
			return responseBase;
		}
		orderModel.setOptUserName(cancelOrder.getOptUserName()); // 操作人
		orderModel.setRemark(cancelOrder.getOptLog()); // 操作日志
		if (orderModel.getStatus() == ShanSongOrderStatus.Cancel.value()||orderModel.getStatus() ==
				ShanSongOrderStatus.PayClose.value()) {
			responseBase.setResponseCode(ResponseCode.PARAMETER_NULL_ERROR);
			responseBase.setMessage("订单已为取消或交易关闭状态，不能再次取消操作！");
			return responseBase;
		}

		if (orderModel.getIsJoinWithdraw() == 1) {
			responseBase.setResponseCode(ResponseCode.PARAMETER_NULL_ERROR);
			responseBase.setMessage("订单已分账，不能取消订单！");
			return responseBase;
		}
		Integer orderTaskPayStatus = orderChildDao
				.getOrderTaskPayStatus(cancelOrder.getOrderId());
		// 线上结算 餐费未线上支付模式并且餐费有支付
		if (orderModel.getMealsSettleMode() == MealsSettleMode.LineOn.value()
				&& orderTaskPayStatus > 0 && !orderModel.getIsPay()) {
			responseBase.setResponseCode(ResponseCode.PARAMETER_NULL_ERROR);
			responseBase.setMessage("餐费有支付，不能取消订单！");
			return responseBase;
		}
		//取消订单还是交易关闭
		int cancelStatus=orderModel.getStatus()==ShanSongOrderStatus.WaitPay.value() ?ShanSongOrderStatus.PayClose.value():
			ShanSongOrderStatus.Cancel.value();  
		
		// 取消订单
		Order tempCanelOrder = new Order();
		tempCanelOrder.setId(cancelOrder.getOrderId());
		tempCanelOrder.setStatus((byte)cancelStatus);
		tempCanelOrder.setOthercancelreason(cancelOrder.getOptLog());
		orderDao.updateByPrimaryKeySelective(tempCanelOrder);
		// 记录取消订单日志
		OrderSubsidiesLog record = new OrderSubsidiesLog();
		record.setOrderid(cancelOrder.getOrderId());
		record.setOrderstatus(cancelStatus);
		record.setOptid(cancelOrder.getOptUserId());
		record.setPrice(orderModel.getOrderCommission()); // 佣金
		record.setOptname(cancelOrder.getOptUserName());
		record.setPlatform(SuperPlatform.ManagementBackGround.value());
		record.setRemark(cancelOrder.getOptUserName() + "通过后台管理系统取消订单,用户操作描述：【"
				+ cancelOrder.getOptLog() + "】");
		orderSubsidiesLogDao.insert(record);
		// 更新取消订单时间
		OrderOther orderOther = new OrderOther();
		orderOther.setOrderid(cancelOrder.getOrderId());
		orderOther.setCancelTime(new Date());
		orderOtherDao.updateByPrimaryKeySelective(orderOther);
		// 已完成订单 子订单全部已支付
		if (orderModel.getStatus() == OrderStatus.Complite.value()
				&& orderTaskPayStatus == 2
				&& orderModel.getHadUploadCount() == orderModel
						.getNeedUploadCount()) // 已完成订单
		{
			// 更新骑士余额 插流水
			ClienterMoney clienterMoney = new ClienterMoney();
			clienterMoney.setClienterId(orderModel.getClienterId());
			clienterMoney.setAmount(-orderModel.getOrderCommission());
			clienterMoney
					.setStatus(ClienterBalanceRecordStatus.Success.value());
			clienterMoney
					.setRecordType(ClienterBalanceRecordRecordType.CancelOrder
							.value());
			clienterMoney.setOperator(cancelOrder.getOptUserName());
			clienterMoney.setWithwardId((long) orderModel.getId()); // 订单id
			clienterMoney.setRelationNo(orderModel.getOrderNo()); // 关联单号
			clienterMoney.setRemark(orderModel.getRemark());
			clienterService.updateCBalanceAndWithdraw(clienterMoney);
		}
		//非 未支付状态下需要加商户余额
		if (orderModel.getStatus() != ShanSongOrderStatus.WaitPay.value()) {
			// 更新商家余额可提现金额 插流水
			BusinessMoney businessMoney = new BusinessMoney();
			businessMoney.setAmount(orderModel.getSettleMoney());// 金额
			businessMoney.setBusinessId(orderModel.getBusinessId());// 商户Id
			businessMoney.setStatus((short) BusinessBalanceRecordStatus.Success
					.value());
			businessMoney
					.setRecordType((short) BusinessBalanceRecordRecordType.CancelOrder
							.value()); // 取消订单
			businessMoney.setOperator(cancelOrder.getOptUserName());
			businessMoney.setWithwardId((long) orderModel.getId()); // 订单id
			businessMoney.setRelationNo(orderModel.getOrderNo()); // 关联单号
			businessMoney.setRemark(orderModel.getRemark()); // 注释
			businessService.updateBBalanceAndWithdraw(businessMoney);
		}
		responseBase.setMessage("订单取消成功");
		return responseBase;
	}
	/** 
	 * api发单数据验证  闪送模式
	 * 
	 * @author 胡灵波
	 * @param req
	 * @Date 2015年11月26日 13:38:18
	 * @return
	 */
	private FlashPushOrderEnum verificationFlashPushOrder(OrderDraftReq req) {
		if(req.getPubname()==null || req.getPubname().equals(""))
		{
			return FlashPushOrderEnum.PubNameIsNULL;
		}
		if(req.getPubphoneno()==null || req.getPubphoneno().equals(""))
		{	
			return FlashPushOrderEnum.PubPhoneNoIsNULL;
		}
		if(req.getPubaddress()==null || req.getPubaddress().equals(""))
		{			
			return FlashPushOrderEnum.PubAddressIsNULL;
		}
		if(req.getTaketype()==null)
		{	
			return FlashPushOrderEnum.TakeTypeIsNULL;
		}		
		if(req.getRecevicename()==null || req.getRecevicename().equals(""))
		{
			return FlashPushOrderEnum.ReceviceNameIsNULL;
		}
		if(req.getRecevicephoneno()==null || req.getRecevicephoneno().equals(""))
		{
			return FlashPushOrderEnum.RecevicePhoneNoIsNull;
		}
		if(req.getReceviceaddress()==null || req.getReceviceaddress().equals(""))
		{		
			return FlashPushOrderEnum.ReceviceAddressIsNull;
		}	
		if(req.getProductname()==null || req.getProductname().equals(""))
		{		
			return FlashPushOrderEnum.ProductNameIsNull;
		}
		if(req.getAmount()<=0)
		{		
			return FlashPushOrderEnum.AmountIsErr;
		}
		if(req.getWeight()<1 )
		{			
			return FlashPushOrderEnum.WeightIsErr;
		}
		if(req.getKm()==null)
		{			
			return FlashPushOrderEnum.KMIsNull;
		}
		if(req.getTaketype()==1 && req.getTaketime()==null)
		{
			return FlashPushOrderEnum.TaketimeErr;
		}		
		
		return FlashPushOrderEnum.VerificationSuccess;
	}
	
	/**
	 * 
	 * 
	 * @author 胡灵波
	 * @param req
	 * @param businessModel
	 *            商家信息
	 * @return
	 */
	private Order fillFlashPushOrder(OrderDraftReq req, BusinessModel businessModel) {
		Order order = new Order();
		order.setOrderno(OrderNoHelper.generateOrderCode(businessModel.getId()));
		req.setOrderfrom(OrderFrom.FlashOrder.value());
		req.setPlatform(3);//闪送	
		order.setOrderfrom(req.getOrderfrom());
		order.setStatus((byte) OrderStatus.Draft.value());		
		order.setOrdercount(1);		
		order.setBusinessid(req.getBusinessid());		
		order.setTimespan(null);	
		order.setIspay(false);
		order.setBusinessid(businessModel.getId());
		
		order.setPubname(req.getPubname());//发货人
		order.setPubdate(new Date());//发货日期	
		order.setPubphoneno(req.getPubphoneno());//发货人手机号
		order.setPickupaddress(req.getPubaddress());//发货人地址 	
		order.setPickuplatitude(req.getPublatitude());
		order.setPickuplongitude(req.getPublongitude());
		order.setTaketype(req.getTaketype());//取货状态默认0立即，1预约	
		Random random = new Random();
	    int x = random.nextInt(899999);
		x = x+100000;
		order.setPickupcode(String.valueOf(x));//取货吗			
		order.setRecevicename(req.getRecevicename());//收货人姓名
		order.setRecevicephoneno(req.getRecevicephoneno());//收货人手机号
		order.setReceviceaddress(req.getReceviceaddress());//收货人地址
		order.setRecevicelongitude(req.getRecevicelongitude());//收货人经度
		order.setRecevicelatitude(req.getRecevicelatitude());//收货人维度
		order.setRecevicecity(null);//收货人城市
		order.setReceiveprovince(null);//收货人省份
		order.setReceivearea(null);//收货区域
		order.setReceiveprovincecode(null);//收货人城市代码
		order.setReceivecitycode(null);//收货人城市代码
		order.setReceiveareacode(null);////收货区域 代码	
		order.setProductname(req.getProductname());//物品名称
		order.setRemark(req.getRemark());//备注
		order.setAmount(req.getAmount());//金额				
		order.setWeight(req.getWeight());//订单总重量
		order.setKm(req.getKm());//	距离		

		order.setCommissionformulamode(businessModel.getStrategyId());
		order.setBusinesscommission(businessModel.getBusinesscommission());
		order.setBusinessgroupid(businessModel.getBusinessgroupid());
		order.setCommissiontype(businessModel.getCommissiontype());
		order.setCommissionfixvalue(businessModel.getCommissionfixvalue());
		order.setMealssettlemode(businessModel.getMealssettlemode()); // 餐费结算方式（0：线下结算
																		// 1：线上结算）
		order.setDistribsubsidy(0.0);
		OrderCommission orderCommission = new OrderCommission();
		orderCommission.setAmount(req.getAmount());
		orderCommission.setBusinessCommission(businessModel
				.getBusinesscommission());
		orderCommission.setBusinessGroupId(businessModel.getBusinessgroupid());
		orderCommission.setCommissionFixValue(businessModel
				.getCommissionfixvalue());
		orderCommission.setCommissionType(businessModel.getCommissiontype());
		orderCommission.setDistribSubsidy(businessModel.getDistribsubsidy());
		orderCommission.setOrderCount(0);
		orderCommission.setStrategyId(businessModel.getStrategyId());
		
		OrderPriceBaseProvider orderPriceService = CommissionFactory
				.GetCommission(businessModel.getStrategyId());
//		order.setOrdercommission(orderPriceService
//				.getCurrenOrderCommission(orderCommission));
		order.setWebsitesubsidy(orderPriceService
				.getOrderWebSubsidy(orderCommission));
		order.setCommissionrate(orderPriceService
				.getCommissionRate(orderCommission));
		order.setAdjustment(orderPriceService.getAdjustment(orderCommission));
		order.setBasecommission(orderPriceService
				.getBaseCommission(orderCommission));
		//扣商家
		Double settleMoney=req.getAmount();
		order.setSettlemoney(settleMoney);
		order.setOrdercommission(settleMoney);
		// 如果当前商家的余额不够支付订单了，则消费集团的金额
		if (businessModel.getGroupBusinessID() > 0
				&& businessModel.getBalanceprice() < settleMoney) {
			order.setGroupbusinessid(businessModel.getGroupBusinessID());
		}

		order.setBusinessreceivable(Double.valueOf(0));// 退还商家金额
	    order.setBusinessreceivable((double)0);	
		order.setPlatform(req.getPlatform());// 新平台
        order.setTipamount(req.getTipamount());
		return order;
	}
	
	/**
	 * api发布订单组织OrderOther对象  闪送模式 
	 * 
	 * @author 胡灵波
	 * @param req
	 * @param
	 * @return
	 */
	private OrderOther fillFlashPushOrderOther(OrderDraftReq req, Order order,
			BusinessModel businessModel) {
		OrderOther orderOther = new OrderOther();
		orderOther.setOrderid(order.getId());
		orderOther.setNeeduploadcount(1);
		orderOther.setHaduploadcount(1);
		if(req.getTaketype()==1)
		{
			//orderOther.setTaketime(req.getTaketime());
			orderOther.setExpectedtaketime(req.getTaketime());
		}
		else 
		{
			orderOther.setExpectedtaketime(new Date());
		}
		orderOther.setTakelongitude((double)0);
		orderOther.setTakelatitude((double)0);		
		orderOther.setPublongitude(req.getCurrentlongitude());
		orderOther.setPublatitude(req.getCurrentlatitude());
		orderOther.setOnekeypuborder(businessModel.getOnekeypuborder());
		orderOther.setIsorderchecked(businessModel.getIsOrderChecked());
		orderOther.setIsAllowCashPay(businessModel.getIsAllowCashPay());

		return orderOther;
	}
	/**
	 * 商家发单 插入子订单  闪送模式
	 * 
	 * @param req
	 * @param businessModel
	 * @param order
	 * 胡灵波
	 * 2015年11月26日 17:08:22
	 */
	private List<OrderChild> fillFlashPushOrderChild(OrderDraftReq req, BusinessModel businessModel,
			Order order)
	{
		   List<OrderChild> listOrderChild = new ArrayList<OrderChild>();
		
			OrderChild child = new OrderChild();		
			child.setChildid(1);
			child.setCreateby(businessModel.getName());
			child.setUpdateby(businessModel.getName());
			child.setDeliveryprice(order.getDistribsubsidy());
			child.setOrderid(order.getId());
			child.setTotalprice(req.getAmount());
			child.setGoodprice(req.getAmount());
			child.setPaystyle((short)3);
			child.setPaystatus((short)0);
			child.setOriginalorderno("");
			child.setWxcodeurl("");
			child.setPayprice(0d);
			child.setHasuploadticket(false);
			child.setThirdpaystatus((short) 0);			
				
			//以下属性是智能调度用到的属性
			child.setBusinessid(businessModel.getId());
			child.setStatus((short)0);
			child.setOrderRegionOneId(0);
			child.setOrderRegionTwoId(0);
			child.setOrderCommission(0d);
			child.setSettleMoney(0d);
			child.setCommissionRate(0d);
			child.setBaseCommission(0d);
			child.setWebsiteSubsidy(0d);
			child.setAdjustment(0d);
			child.setPlatform(3);
			listOrderChild.add(child);			
			
			return listOrderChild;
		}	
	// endregion

	@Override
	public HttpResultModel<QueryOrderBResp> shanSongQueryOrderB(QueryShanSongOrderReq query) {
		HttpResultModel<QueryOrderBResp> resultModel = new HttpResultModel<QueryOrderBResp>();
		// 验证商家状态
		BusinessStatus b=  businessDao.getUserStatus(query.getBusinessId());
		if(b != null){
//			if (b.getStatus() != BusinessStatusEnum.AuditPass.value()) {
//				resultModel.setStatus(QueryOrderReturnEnum.ErrStatus.value());
//				resultModel.setMessage(QueryOrderReturnEnum.ErrStatus.desc());
//				return resultModel;
//			}
	/*		if(b.getIsEnable() != 1 ){
				resultModel.setStatus(QueryOrderReturnEnum.BusinessIsNotEnable.value());
				resultModel.setMessage(QueryOrderReturnEnum.BusinessIsNotEnable.desc());
				return resultModel;
			}*/
		}else{
			resultModel.setStatus(QueryOrderReturnEnum.BusinessNotExist.value());
			resultModel.setMessage(QueryOrderReturnEnum.BusinessNotExist.desc());
			return resultModel;
		}
		QueryOrderBResp queryOrderBResp = orderDao.shanSongQueryOrderCountB(query);
		String sscancelorderTime =globalConfigService.getConfigValueByKey(0, RedissCacheKey.SSCancelOrder);
		queryOrderBResp.setSsCancelOrder(ParseHelper.ToInt(sscancelorderTime,24));
		queryOrderBResp.setOrders(orderDao.shanSongQueryOrderB(query));
		resultModel.setResult(queryOrderBResp);
		return resultModel;
	}
}
