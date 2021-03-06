package com.edaisong.api.service.impl;

import java.lang.Double;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edaisong.api.common.CommissionFactory;
import com.edaisong.api.common.OrderPriceBaseProvider;
import com.edaisong.api.common.OrderSettleMoneyHelper;
import com.edaisong.api.dao.inter.IBusinessBalanceRecordDao;
import com.edaisong.api.dao.inter.IBusinessDao;
import com.edaisong.api.dao.inter.IClienterBalanceRecordDao;
import com.edaisong.api.dao.inter.IClienterDao;
import com.edaisong.api.dao.inter.IClienterLocationDao;
import com.edaisong.api.dao.inter.IGroupBusinessDao;
import com.edaisong.api.dao.inter.IOrderChildDao;
import com.edaisong.api.dao.inter.IOrderDao;
import com.edaisong.api.dao.inter.IOrderDetailDao;
import com.edaisong.api.dao.inter.IOrderOtherDao;
import com.edaisong.api.dao.inter.IOrderSubsidiesLogDao;
import com.edaisong.api.redis.RedisService;
import com.edaisong.api.service.inter.IBusinessService;
import com.edaisong.api.service.inter.IClienterService;
import com.edaisong.api.service.inter.IOrderService;
import com.edaisong.core.consts.RedissCacheKey;
import com.edaisong.core.enums.BusinessBalanceRecordRecordType;
import com.edaisong.core.enums.BusinessBalanceRecordStatus;
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
import com.edaisong.core.enums.OrderFrom;
import com.edaisong.core.enums.OrderOperationCommon;
import com.edaisong.core.enums.OrderStatus;
import com.edaisong.core.enums.PublishOrderReturnEnum;
import com.edaisong.core.enums.SuperPlatform;
import com.edaisong.core.enums.TaskStatus;
import com.edaisong.core.enums.returnenums.QueryOrderReturnEnum;
import com.edaisong.core.util.JsonUtil;
import com.edaisong.core.util.OrderNoHelper;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.core.util.PropertyUtils;
import com.edaisong.entity.BusinessBalanceRecord;
import com.edaisong.entity.ClienterBalanceRecord;
import com.edaisong.entity.GroupBusiness;
import com.edaisong.entity.Order;
import com.edaisong.entity.OrderChild;
import com.edaisong.entity.OrderDetail;
import com.edaisong.entity.OrderOther;
import com.edaisong.entity.OrderSubsidiesLog;
import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.common.Location;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.common.ResponseBase;
import com.edaisong.entity.domain.BusiPubOrderTimeStatisticsModel;
import com.edaisong.entity.common.ResponseCode;
import com.edaisong.entity.domain.BusTaskList;
import com.edaisong.entity.domain.BusinessModel;
import com.edaisong.entity.domain.BusinessOrderSummaryModel;
import com.edaisong.entity.domain.ClienterStatus;
import com.edaisong.entity.domain.DaySatisticsB;
import com.edaisong.entity.domain.DaySatisticsC;
import com.edaisong.entity.domain.ExportOrder;
import com.edaisong.entity.domain.OrderCommission;
import com.edaisong.entity.domain.OrderListModel;
import com.edaisong.entity.domain.OrderMapDetail;
import com.edaisong.entity.domain.QueryOrder;
import com.edaisong.entity.domain.ServiceClienter;
import com.edaisong.entity.req.OptOrder;
import com.edaisong.entity.req.BusinessMoney;
import com.edaisong.entity.req.CancelOrderBusinessReq;
import com.edaisong.entity.req.ClienterMoney;
import com.edaisong.entity.req.OrderDetailBusinessReq;
import com.edaisong.entity.req.OrderOtherSearch;
import com.edaisong.entity.req.OrderReq;
import com.edaisong.entity.req.OrderStatisticsBReq;
import com.edaisong.entity.req.OrderStatisticsCReq;
import com.edaisong.entity.req.PagedBusTaskListReq;
import com.edaisong.entity.req.PagedCustomerSearchReq;
import com.edaisong.entity.req.PagedOrderSearchReq;
import com.edaisong.entity.req.QueryOrderReq;
import com.edaisong.entity.resp.BusinessBalanceInfoResp;
import com.edaisong.entity.resp.CancelOrderBusinessResp;
import com.edaisong.entity.resp.OrderDetailBusinessResp;
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
	 * 
	 * @author CaoHeYang
	 * @Date 20150728
	 * @param orderid
	 *            订单id
	 * @return
	 */
	@Override
	public OrderMapDetail getOrderMapDetail(int orderid) {
		OrderMapDetail orderMapDetail = orderDao.getOrderMapDetail(orderid);
		if (orderMapDetail != null) {
			Date startTime = new Date();
			Date endTime = new Date();
			if (orderMapDetail.getPubDate() == null || orderMapDetail.getPubDate().indexOf("1900-01-01") >= 0) {
				orderMapDetail.setPubDate("暂无");
			} else {
				startTime = ParseHelper.ToDate(orderMapDetail.getPubDate());
			}
			if (orderMapDetail.getGrabTime() == null || orderMapDetail.getGrabTime().indexOf("1900-01-01") >= 0) {
				orderMapDetail.setGrabTime("暂无");
			} else {
				endTime = ParseHelper.ToDate(orderMapDetail.getGrabTime());
			}
			if (orderMapDetail.getTakeTime() == null || orderMapDetail.getTakeTime().indexOf("1900-01-01") >= 0) {
				orderMapDetail.setTakeTime("暂无");
			} else {
				endTime = ParseHelper.ToDate(orderMapDetail.getTakeTime());
			}
			if (orderMapDetail.getActualDoneDate() == null || orderMapDetail.getActualDoneDate().indexOf("1900-01-01") >= 0) {
				orderMapDetail.setActualDoneDate("暂无");
			} else {
				endTime = ParseHelper.ToDate(orderMapDetail.getActualDoneDate());
			}
			if (orderMapDetail.getGrabLatitude() == 0 || orderMapDetail.getGrabLongitude() == 0) {
				orderMapDetail.setGrabLongitude(orderMapDetail.getPubLongitude());
				orderMapDetail.setGrabLatitude(orderMapDetail.getPubLatitude());
			}
			if (orderMapDetail.getTakeLatitude() == 0 || orderMapDetail.getTakeLongitude() == 0) {
				orderMapDetail.setTakeLongitude(orderMapDetail.getPubLongitude());
				orderMapDetail.setTakeLatitude(orderMapDetail.getPubLatitude());
			}
			if (orderMapDetail.getCompleteLatitude() == 0 || orderMapDetail.getCompleteLongitude() == 0) {
				orderMapDetail.setCompleteLongitude(orderMapDetail.getPubLongitude());
				orderMapDetail.setCompleteLatitude(orderMapDetail.getPubLatitude());
			}
			// 开始时间小于结束时间才获取实时坐标
			if (startTime.compareTo(endTime) < 0 && orderMapDetail.getClienterId() > 0) {
				orderMapDetail.setLocations(clienterLocationDao.getLocationsByTime(startTime, endTime, orderMapDetail.getClienterId()));
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
	public OrderDetailBusinessResp getOrderDetailBusiness(OrderDetailBusinessReq para) {
		OrderDetailBusinessResp modelsBusinessResp = new OrderDetailBusinessResp();
		modelsBusinessResp.setOrderModel(orderDao.getOrderDetailBusiness(para));
		modelsBusinessResp.setOrderChilds(orderChildDao.getOrderChildByOrderInfo(para.getOrderNo(), para.getBusinessId()));
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
	public CancelOrderBusinessResp cancelOrderBusiness(CancelOrderBusinessReq req) {
		CancelOrderBusinessResp resp = new CancelOrderBusinessResp();
		if (req.getOrderId() <= 0 || req.getOrderNo().isEmpty() || req.getOrderNo() == null || req.getBusinessId() <= 0) {
			resp.setResponseCode(CancelOrderBusinessReturnEnum.OrderEmpty.value());
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
			resp.setResponseCode(CancelOrderBusinessReturnEnum.CancelOrderError.value());
			resp.setMessage(CancelOrderBusinessReturnEnum.CancelOrderError.desc());
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
	public void cancelOrderBusinessTrans(CancelOrderBusinessReq req, Order orderRe) {
		// 更新订单为 取消状态 参数
		Order updateModel = new Order();
		updateModel.setId(req.getOrderId());
		updateModel.setOrderno(req.getOrderNo());
		updateModel.setBusinessid(req.getBusinessId());
		updateModel.setStatus((byte) OrderStatus.New.value()); // 查询状态属于待接单的
		updateModel.setOthercancelreason("商家取消订单");
		updateModel.setAmount(orderRe.getSettlemoney()); // 取消订单涉及到的金额数目此处取到的当前订单的
															// 结算费 即商家应付
		if (orderDao.cancelOrderBusiness(updateModel) > 0){ /* 取消订单 针对订单表的逻辑 */
			BusinessBalanceRecord businessBalanceRecord = new BusinessBalanceRecord();
			businessBalanceRecord.setStatus((short) BusinessBalanceRecordStatus.Success.value()); // 流水状态(1、交易成功																					// 2、交易中）
			businessBalanceRecord.setRecordtype((short) BusinessBalanceRecordRecordType.CancelOrder.value()); // 取消订单
			businessBalanceRecord.setOperator("商家:" + req.getBusinessId()); // 商家id
			businessBalanceRecord.setWithwardid((long) req.getOrderId()); // 订单id
			businessBalanceRecord.setRelationno(req.getOrderNo()); // 关联单号
			businessBalanceRecord.setRemark("商户取消订单返回配送费"); // 注释
			businessBalanceRecord.setBusinessid(req.getBusinessId());// 商户Id
			if (orderRe.getGroupbusinessid()>0) {
				businessBalanceRecord.setAmount(0d);
				businessBalanceRecord.setGroupamount(orderRe.getSettlemoney());
				businessBalanceRecord.setGroupid(orderRe.getGroupbusinessid());
			}
			else {
				businessBalanceRecord.setAmount(orderRe.getSettlemoney());
				businessBalanceRecord.setGroupamount(0d);
				businessBalanceRecord.setGroupid(0);
			}
			businessService.updateForWithdrawC(1,businessBalanceRecord);
			OrderOther orderOther = new OrderOther();
			orderOther.setOrderid(req.getOrderId());
			orderOther.setCancelTime(new Date());
			orderOtherDao.updateByPrimaryKeySelective(orderOther);
		}else {
			throw new RuntimeException("更新订单状态为取消状态时失败");
		}
	}

	/**
	 * 商户发布订单功能
	 * 
	 * @param req
	 * @return
	 * @author zhaohailong
	 * @Date 2015年8月6日 09:56:25
	 */
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public OrderResp AddOrder(OrderReq req) {
		OrderResp resp = new OrderResp();
		BusinessModel businessModel = businessDao.getBusiness(req.getBusinessid());
		// 校验是否可以正常发单
		PublishOrderReturnEnum returnEnum = verificationAddOrder(req, businessModel);
		if (returnEnum != PublishOrderReturnEnum.Success) {
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
		if (order.getGroupbusinessid()>0) {
			balanceRecord.setAmount(0d);
			balanceRecord.setGroupamount(order.getSettlemoney());
			balanceRecord.setGroupid(order.getGroupbusinessid());
			balanceRecord.setBalance(businessModel.getBalanceprice());
		}else {
			balanceRecord.setAmount(order.getSettlemoney());
			balanceRecord.setGroupamount(0);
			balanceRecord.setGroupid(0);
		}

		balanceRecord.setStatus((short) BusinessBalanceRecordStatus.Success.value());
		balanceRecord.setRecordtype((short) BusinessBalanceRecordRecordType.PublishOrder.value());
		balanceRecord.setOperator(businessModel.getName());
		balanceRecord.setWithwardid((long) order.getId());
		balanceRecord.setRelationno(order.getOrderno());
		balanceRecord.setRemark("配送费支出金额");
		businessService.updateForWithdrawC(0,balanceRecord);

		// 记录补贴日志
		if (order.getAdjustment() > 0) {
			OrderSubsidiesLog adjustRecord = new OrderSubsidiesLog();
			adjustRecord.setOrderid(order.getId());
			adjustRecord.setPrice(order.getAdjustment());
			adjustRecord.setOrderstatus(OrderStatus.New.value());
			adjustRecord.setOptid(req.getBusinessid());
			adjustRecord.setOptname(TaskStatus.PublishOrder.desc());
			adjustRecord.setRemark("补贴加钱,订单金额:" + order.getAmount() + "-佣金补贴策略id:" + order.getCommissionformulamode());
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
		if (req.getListOrderChild() != null && req.getListOrderChild().size() > 0) {
			fillOrderChild(req, businessModel, order);
			orderChildDao.insertList(req.getListOrderChild());
		}
		resetOrderHasExist(req.getBusinessid());
		return resp;
	}

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
	 * 商家发单 插入子订单
	 * 
	 * @param req
	 * @param businessModel
	 * @param order
	 */
	private void fillOrderChild(OrderReq req, BusinessModel businessModel, Order order) {
		if (req.getListOrderChild() != null && req.getListOrderChild().size() > 0) {
			OrderChild child = null;
			short payStatus = 0;
			if (req.getIspay() || (!req.getIspay() && businessModel.getMealssettlemode() == MealsSettleMode.LineOff.value())) {
				payStatus = 1;
			}
			for (int i = 0; i < req.getListOrderChild().size(); i++) {
				child = req.getListOrderChild().get(i);
				child.setChildid(i + 1);
				child.setCreateby(businessModel.getName());
				child.setUpdateby(businessModel.getName());
				child.setDeliveryprice(order.getDistribsubsidy());
				child.setOrderid(order.getId());
				child.setTotalprice(child.getGoodprice() + child.getDeliveryprice());
				child.setPaystatus(payStatus);
				child.setOriginalorderno("");
				child.setWxcodeurl("");
				child.setPayprice(0d);
				child.setHasuploadticket(false);
				child.setThirdpaystatus((short) 0);
			}
		}
	}

	/**
	 * 发布订单根据请求参数，商家信息装配订单信息
	 * 
	 * @author ZhaoHaiLong
	 * @param req
	 * @param businessModel
	 *            商家信息
	 * @return
	 */
	private Order fillOrder(OrderReq req, BusinessModel businessModel) {
		Order order = new Order();
		order.setOrderno(OrderNoHelper.generateOrderCode(req.getBusinessid()));// 临时
		order.setRecevicename(req.getRecevicename());
		order.setRecevicephoneno(req.getRecevicephoneno());
		if (businessModel.getOnekeypuborder()!=null&&
			businessModel.getOnekeypuborder()>0&&
			(req.getReceviceaddress()==null||
			 req.getReceviceaddress().isEmpty())) {
			order.setReceviceaddress(null);
		}else {
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
		order.setRecevicelongitude(0d); // TODO 暂时默认0
		order.setRecevicelatitude(0d);// TODO 暂时默认0
		order.setTimespan(String.valueOf((new Date()).getTime()));
		order.setRecevicecity(businessModel.getCity()); // TODO 配送城市 暂时取商家的

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
		orderCommission.setBusinessCommission(businessModel.getBusinesscommission());
		orderCommission.setBusinessGroupId(businessModel.getBusinessgroupid());
		orderCommission.setCommissionFixValue(businessModel.getCommissionfixvalue());
		orderCommission.setCommissionType(businessModel.getCommissiontype());
		orderCommission.setDistribSubsidy(businessModel.getDistribsubsidy());
		orderCommission.setOrderCount(req.getOrdercount());
		orderCommission.setStrategyId(businessModel.getStrategyId());

		OrderPriceBaseProvider orderPriceService = CommissionFactory.GetCommission(businessModel.getStrategyId());
		order.setOrdercommission(orderPriceService.getCurrenOrderCommission(orderCommission));
		order.setWebsitesubsidy(orderPriceService.getOrderWebSubsidy(orderCommission));
		order.setCommissionrate(orderPriceService.getCommissionRate(orderCommission));
		order.setAdjustment(orderPriceService.getAdjustment(orderCommission));
		order.setBasecommission(orderPriceService.getBaseCommission(orderCommission));
		
		Double settleMoney = OrderSettleMoneyHelper.GetSettleMoney(req.getAmount(), businessModel.getBusinesscommission(),
				businessModel.getCommissionfixvalue(), req.getOrdercount(), businessModel.getDistribsubsidy(), req.getOrderfrom());
		order.setSettlemoney(settleMoney);
		
		//如果当前商家的余额不够支付订单了，则消费集团的金额
		if (businessModel.getGroupBusinessID()>0&&
			businessModel.getBalanceprice()<settleMoney) {
			order.setGroupbusinessid(businessModel.getGroupBusinessID());
		}

		order.setBusinessreceivable(Double.valueOf(0));// 退还商家金额
		if (!req.getIspay() && order.getMealssettlemode() == MealsSettleMode.LineOn.value()) {
			Double money = req.getAmount() + (businessModel.getDistribsubsidy() * req.getOrdercount());

			order.setBusinessreceivable(money);
		}

		return order;
	}

	/**
	 * 商户发单数据验证
	 * 
	 * @author CaoHeYang
	 * @param req
	 * @param businessModel
	 * @Date 20150818
	 * @return
	 */
	private PublishOrderReturnEnum verificationAddOrder(OrderReq req, BusinessModel businessModel) {
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
			if (req.getRecevicephoneno() == null || req.getRecevicephoneno().isEmpty())// 手机号
			{
				return PublishOrderReturnEnum.RecevicePhoneIsNULL;
			}
			if (req.getReceviceaddress() == null || req.getReceviceaddress().isEmpty()) {
				return PublishOrderReturnEnum.ReceviceAddressIsNULL;
			}
		}
		if (req.getListOrderChild().size() > 15 || req.getListOrderChild().size() <= 0 || req.getOrdercount() != req.getListOrderChild().size()) {
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
		
		Double settleMoney = OrderSettleMoneyHelper.GetSettleMoney(req.getAmount(), businessModel.getBusinesscommission(),
				businessModel.getCommissionfixvalue(), req.getOrdercount(), businessModel.getDistribsubsidy(), req.getOrderfrom());
		
		if (businessModel.getBalanceprice() < settleMoney) {
			if (businessModel.getGroupBusinessID()>0){
				GroupBusiness groupBusiness=groupBusinessDao.select(businessModel.getGroupBusinessID());
				if (groupBusiness.getAmount()<settleMoney&&
					groupBusiness.getIsAllowOverdraft()==0) {
					return PublishOrderReturnEnum.GroupBalancePriceLack;
				}
			}else if (businessModel.getIsallowoverdraft() == 0) {
				//商家不允许透支
				return PublishOrderReturnEnum.BusiBalancePriceLack;
			}
		}
		
		return PublishOrderReturnEnum.Success;
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
		BusinessModel businessModel = businessDao.getBusiness(req.getBusinessid());
		if (businessModel == null) {
			resp.setResponseCode(PublishOrderReturnEnum.BusinessEmpty.value());
			resp.setMessage(PublishOrderReturnEnum.BusinessEmpty.desc());
			return resp;
		}
		Double settleMoney = OrderSettleMoneyHelper.GetSettleMoney(req.getAmount(), businessModel.getBusinesscommission(),
				businessModel.getCommissionfixvalue(), req.getOrdercount(), businessModel.getDistribsubsidy(), req.getOrderfrom());
		resp.setBalanceprice(businessModel.getBalanceprice());
		resp.setSettleMoney(settleMoney);

		return resp;
	}

	@Override
	public BusinessOrderSummaryModel getBusinessOrderSummary(int businessId) {
		return orderDao.getBusinessOrderSummary(businessId);
	}

	@Override
	public List<BusiPubOrderTimeStatisticsModel> getBusiPubOrderTimeStatistics(int businessId, Date startTime, Date endTime) {
		return orderDao.getBusiPubOrderTimeStatistics(businessId, startTime, endTime);
	}

	@Override
	public PagedResponse<OrderListModel> customerGetOrders(PagedCustomerSearchReq req) {
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
		OrderListModel orderListModel = orderDao.getOrderByNoId(orderNo, orderId);
		if (orderListModel != null) {
			List<OrderDetail> orderDetails = orderDetailDao.getOrderDetailIByNoId(orderNo, orderId);
			orderListModel.setOrderDetailList(orderDetails);
			List<OrderChild> orderChilds = orderChildDao.getOrderChildByOrderInfo(orderNo, 0);
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
		OrderListModel orderModel = orderDao.getOrderByNoId(cancelOrder.getOrderNo(), cancelOrder.getOrderId());
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
		Integer orderTaskPayStatus = orderChildDao.getOrderTaskPayStatus(cancelOrder.getOrderId());
		// 线上结算 餐费未线上支付模式并且餐费有支付
		if (orderModel.getMealsSettleMode() == MealsSettleMode.LineOn.value() && orderTaskPayStatus > 0 && !orderModel.getIsPay()) {
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
		record.setRemark(cancelOrder.getOptUserName() + "通过后台管理系统取消订单,用户操作描述：【" + cancelOrder.getOptLog() + "】");
		orderSubsidiesLogDao.insert(record);
		// 更新取消订单时间
		OrderOther orderOther = new OrderOther();
		orderOther.setOrderid(cancelOrder.getOrderId());
		orderOther.setCancelTime(new Date());
		orderOtherDao.updateByPrimaryKeySelective(orderOther);
		// 已完成订单 子订单全部已支付
		if (orderModel.getStatus() == OrderStatus.Complite.value() && orderTaskPayStatus == 2
				&& orderModel.getHadUploadCount() == orderModel.getNeedUploadCount()) // 已完成订单
		{
			// 更新骑士余额 插流水
			ClienterMoney clienterMoney = new ClienterMoney();
			clienterMoney.setClienterId(orderModel.getClienterId());
			clienterMoney.setAmount(-orderModel.getOrderCommission());
			clienterMoney.setStatus(ClienterBalanceRecordStatus.Success.value());
			clienterMoney.setRecordType(ClienterBalanceRecordRecordType.CancelOrder.value());
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
		businessMoney.setStatus((short) BusinessBalanceRecordStatus.Success.value());
		businessMoney.setRecordType((short) BusinessBalanceRecordRecordType.CancelOrder.value()); // 取消订单
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
		OrderListModel orderModel = orderDao.getOrderByNoId(auditOkOrder.getOrderNo(), auditOkOrder.getOrderId());
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
		clienterMoney.setAmount(orderModel.getOrderCommission() == null ? 0 : orderModel.getOrderCommission());
		clienterMoney.setStatus(ClienterBalanceRecordStatus.Success.value());
		clienterMoney.setRecordType(ClienterBalanceRecordRecordType.OrderCommission.value());
		clienterMoney.setOperator(auditOkOrder.getOptUserName());
		clienterMoney.setWithwardId((long) orderModel.getId()); // 订单id
		clienterMoney.setRelationNo(orderModel.getOrderNo()); // 关联单号
		clienterMoney.setRemark("管理后台审核通过加可提现");
		clienterService.updateCAllowWithdrawPrice(clienterMoney);
		// 更新已提现状态
		orderOtherDao.updateJoinWithdraw(auditOkOrder.getOrderId());
		// 更新审核状态
		orderOtherDao.updateAuditStatus(auditOkOrder.getOrderId(), OrderAuditStatus.Through.value());
		// 写入订单日志
		OrderSubsidiesLog orderSubsidiesLog = new OrderSubsidiesLog();
		orderSubsidiesLog.setOrderid(auditOkOrder.getOrderId());
		orderSubsidiesLog.setPrice(orderModel.getOrderCommission() == null ? 0 : orderModel.getOrderCommission());
		orderSubsidiesLog.setOptname(auditOkOrder.getOptUserName());
		orderSubsidiesLog.setRemark("审核通过，增加" + (orderModel.getOrderCommission() == null ? 0 : orderModel.getOrderCommission()) + "元可提现金额");
		orderSubsidiesLog.setOptid(auditOkOrder.getOptUserId());
		orderSubsidiesLog.setOrderstatus(OrderOperationCommon.AuditStatusOk.value());
		orderSubsidiesLog.setPlatform(SuperPlatform.ManagementBackGround.value());
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
		OrderListModel orderModel = orderDao.getOrderWriteByNoId(auditRefuseOrder.getOrderNo(), auditRefuseOrder.getOrderId());
		ResponseBase responseBase = auditRefuseCheck(auditRefuseOrder, orderModel);
		if (responseBase.getResponseCode() != ResponseCode.SUCESS) {
			return responseBase;
		}
		// 如果要扣除的金额大于0， 写流水
		if (orderModel.getOrderCommission() > orderModel.getSettleMoney()) {
			ClienterBalanceRecord currenModel = clienterBalanceRecordDao.getByOrderId(orderModel.getId());
			if (currenModel == null) {
				double diffOrderCommission = orderModel.getSettleMoney() - orderModel.getOrderCommission();
				double disOrderCommission = -diffOrderCommission;
				// 更新骑士余额
				ClienterMoney clienterMoney = auditRefuseGetClienterMoney(orderModel, auditRefuseOrder);
				clienterMoney.setRemark(auditRefuseOrder.getOptLog());
				clienterMoney.setAmount(diffOrderCommission);
				clienterMoney.setStatus(ClienterBalanceRecordStatus.Success.value());
				clienterMoney.setRecordType(ClienterBalanceRecordRecordType.Abnormal.value());
				clienterService.updateCAccountBalance(clienterMoney);

				// 更新订单真实佣金 更新无效订单(状态，原因)
				OrderOtherSearch orderOtherSearch = auditRefuseGetOrderOtherSearch(orderModel, auditRefuseOrder);
				orderOtherSearch.setRealOrderCommission(disOrderCommission);
				// 写入订单日志
				OrderSubsidiesLog orderSubsidiesLog = auditRefuseGetOrderSubsidiesLog(orderModel, auditRefuseOrder);
				orderSubsidiesLog.setPrice(diffOrderCommission);
				orderSubsidiesLog.setRemark("扣除" + disOrderCommission + "元无效订单金额");
				auditRefusePartial(orderOtherSearch, orderSubsidiesLog);
			}
		}
		// 更新订单真实佣金
		double realOrderCommission = orderModel.getOrderCommission() == null ? 0 : orderModel.getOrderCommission();
		realOrderCommission = realOrderCommission > orderModel.getSettleMoney() ? orderModel.getSettleMoney() : realOrderCommission;
		// 更新骑士可提现余额
		ClienterMoney clienterMoney = auditRefuseGetClienterMoney(orderModel, auditRefuseOrder);
		clienterMoney.setRemark(auditRefuseOrder.getOptLog());
		clienterMoney.setAmount(realOrderCommission);
		clienterMoney.setStatus(ClienterAllowWithdrawRecordStatus.Success.value());
		clienterMoney.setRecordType(ClienterAllowWithdrawRecordType.OrderCommission.value());
		clienterMoney.setRemark("管理后台审核拒绝加可提现");
		clienterService.updateCAllowWithdrawPrice(clienterMoney);

		// 订单other操作
		OrderOtherSearch orderOtherSearch = auditRefuseGetOrderOtherSearch(orderModel, auditRefuseOrder);
		orderOtherSearch.setRealOrderCommission(realOrderCommission);
		// 写入订单日志
		OrderSubsidiesLog orderSubsidiesLog = auditRefuseGetOrderSubsidiesLog(orderModel, auditRefuseOrder);
		orderSubsidiesLog.setPrice(realOrderCommission);
		orderSubsidiesLog.setRemark("增加" + realOrderCommission + "元可提现金额");
		auditRefusePartial(orderOtherSearch, orderSubsidiesLog);
		// 更新已提现状态
		orderOtherDao.updateJoinWithdraw(auditRefuseOrder.getOrderId());
		// 更新审核状态
		orderOtherDao.updateAuditStatus(auditRefuseOrder.getOrderId(), OrderAuditStatus.Refuse.value());
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
	private void auditRefusePartial(OrderOtherSearch orderOtherSearch, OrderSubsidiesLog orderSubsidiesLog) {
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
	private ResponseBase auditRefuseCheck(OptOrder auditRefuseOrder, OrderListModel orderModel) {
		ResponseBase responseBase = new ResponseBase();
		if (auditRefuseOrder.getOptLog() == null || auditRefuseOrder.getOptLog().isEmpty()) {
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
	private ClienterMoney auditRefuseGetClienterMoney(OrderListModel orderModel, OptOrder auditRefuseOrder) {
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
	private OrderSubsidiesLog auditRefuseGetOrderSubsidiesLog(OrderListModel orderModel, OptOrder auditRefuseOrder) {
		OrderSubsidiesLog orderSubsidiesLog = new OrderSubsidiesLog();
		orderSubsidiesLog.setOrderid(orderModel.getId());
		orderSubsidiesLog.setOrderstatus(OrderOperationCommon.AuditStatusRefuse.value());
		orderSubsidiesLog.setPlatform(SuperPlatform.ManagementBackGround.value());
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
	private OrderOtherSearch auditRefuseGetOrderOtherSearch(OrderListModel orderModel, OptOrder auditRefuseOrder) {
		OrderOtherSearch orderOtherSearch = new OrderOtherSearch();
		orderOtherSearch.setOrderId(orderModel.getId());
		orderOtherSearch.setDeductCommissionReason(auditRefuseOrder.getOptLog());
		orderOtherSearch.setDeductCommissionType(DeductCommissionType.People.value());
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
	public HttpResultModel<OrderStatisticsBResp> getOrderStatisticsB(OrderStatisticsBReq orderStatisticsBReq) {
		HttpResultModel<OrderStatisticsBResp> resultModel = new HttpResultModel<OrderStatisticsBResp>();
		
		//注释掉对用户状态的判断
		if (businessDao.getUserStatus(orderStatisticsBReq.getBusinessId()).getStatus() != BusinessStatusEnum.AuditPass.value()) {
			resultModel.setStatus(QueryOrderReturnEnum.ErrStatus.value());
			resultModel.setMessage(QueryOrderReturnEnum.ErrStatus.desc());
			return resultModel;
		}
		
		OrderStatisticsBResp orderStatisticsResp = orderDao.getOrderStatistics(orderStatisticsBReq);//当月数据总览统计
		List<ServiceClienter> serviceClienters = orderDao.getOrderStatisticsServiceClienterB(orderStatisticsBReq); //获取每天发单骑士信息
		List<DaySatisticsB> daySatisticsBs = orderDao.getOrderStatisticsDaySatistics(orderStatisticsBReq); // B端任务统计接口
																											// 天数据列表
		serviceClienters.forEach(action->action.setClienterPhoto(PropertyUtils.getProperty("ImageClienterServicePath")+action.getClienterPhoto()));
		for (DaySatisticsB daySatisticsB : daySatisticsBs) {
			List<ServiceClienter> temp = serviceClienters.stream().filter(t -> t.getPubDate().equals(daySatisticsB.getMonthDate()))
					.collect(Collectors.toList());
			;
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
	public OrderStatisticsCResp getOrderStatisticsC(OrderStatisticsCReq orderStatisticsCReq) {
		OrderStatisticsCResp orderStatisticsResp = orderDao.getOrderStatisticsC(orderStatisticsCReq);
		List<DaySatisticsC> daySatisticsCs = orderDao.getOrderStatisticsDaySatisticsC(orderStatisticsCReq);
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
		//验证商家状态
		if (businessDao.getUserStatus(query.getBusinessId()).getStatus() != BusinessStatusEnum.AuditPass.value()) {
			resultModel.setStatus(QueryOrderReturnEnum.ErrStatus.value());
			resultModel.setMessage(QueryOrderReturnEnum.ErrStatus.desc());
			return resultModel;
		}
		QueryOrderBResp queryOrderBResp=orderDao.queryOrderB(query);
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
//		if (clienterService.getUserStatus(query.getClienterId()).getStatus() != ClienterStatusEnum.AuditPass.value()) {
//			resultModel.setStatus(QueryOrderReturnEnum.ErrStatus.value()).setMessage(QueryOrderReturnEnum.ErrStatus.desc());
//			return resultModel;
//		}//TODO 茹化肖注释  参见BUG7705 【我的任务】资格取消后，进入我的任务，订单提示账号状态出错
		QueryOrderCResp m = orderDao.queryOrderC(query);
		if (query.getLongitude()!=null&&query.getLongitude()!=0		//需要计算骑士距离门店距离
				&&query.getLatitude()!=null&&query.getLatitude()!=0
				&&query.getStatus()==OrderStatus.Delivery.value()) 
		{
			List<QueryOrder> orders= orderDao.queryDeliveryOrderC(query);
			orders.forEach(action->action.setDistance(action.getDistance_OrderBy()<1000?action.getDistance_OrderBy()+"m": 
				new   BigDecimal(action.getDistance_OrderBy()*0.001).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue()+"km"));
			m.setOrders(orders);
		}else 
		{  //不需要计算骑士距离门店距离
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
	public HttpResultModel<List<QueryOrder>> getCompliteOrder(QueryOrderReq query, int type) {
		query.setStatus(OrderStatus.Complite.value());
		HttpResultModel<List<QueryOrder>> res = new HttpResultModel<List<QueryOrder>>();
		if ((type == 0 && businessDao.getUserStatus(query.getBusinessId()).getStatus() != BusinessStatusEnum.AuditPass.value())  //B端判断B端逻辑
				|| (type == 1 && clienterService.getUserStatus(query.getClienterId()).getStatus() != ClienterStatusEnum.AuditPass.value())) {//C端判断C端逻辑
			res.setStatus(QueryOrderReturnEnum.ErrStatus.value());
			res.setMessage(QueryOrderReturnEnum.ErrStatus.desc());
			return res;
		}
		query.setorderBy(1);
		res.setResult(orderDao.queryOrder(query));
		return res;
	}
	/**
	 * 门店任务审核列表 
	 * 茹化肖
	 * 2015年9月17日15:07:56
	 */
	@Override
	public PagedResponse<BusTaskList> busTaskList(PagedBusTaskListReq req) {
		
		if(req.getStartDate().equals("开始日期"))
		{
			req.setStartDate("");
		}
		if(req.getStartDate()!=null&&!req.getStartDate().equals(""))
		{
			req.setStartDate(req.getStartDate()+" 00:00:00");
		}
		if(req.getEndDate().equals("结束日期"))
		{
			req.setEndDate("");
		}
		if(req.getEndDate()!=null&&!req.getEndDate().equals(""))
		{
			req.setEndDate(req.getEndDate()+" 23:59:59");
		}
		if(req.getSelectValue().equals("门店名称/注册电话"))
		{
			req.setSelectValue("");
		}
		if(req.getCityName().equals("-1")||req.getCityName().equals("全部城市"))
		{
			req.setCityName("");
		}
		return this.orderDao.busTaskList(req);
	}

}
