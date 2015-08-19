package com.edaisong.api.service.impl;

import java.lang.Double;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edaisong.api.common.CommissionFactory;
import com.edaisong.api.common.OrderPriceBaseProvider;
import com.edaisong.api.common.OrderSettleMoneyHelper;
import com.edaisong.api.dao.inter.IBusinessBalanceRecordDao;
import com.edaisong.api.dao.inter.IBusinessDao;
import com.edaisong.api.dao.inter.IOrderChildDao;
import com.edaisong.api.dao.inter.IOrderDao;
import com.edaisong.api.dao.inter.IOrderOtherDao;
import com.edaisong.api.dao.inter.IOrderSubsidiesLogDao;
import com.edaisong.api.service.inter.IBusinessService;
import com.edaisong.api.service.inter.IOrderService;
import com.edaisong.core.enums.BusinessBalanceRecordRecordType;
import com.edaisong.core.enums.BusinessBalanceRecordStatus;
import com.edaisong.core.enums.BusinessStatus;
import com.edaisong.core.enums.CancelOrderBusinessReturnEnum;
import com.edaisong.core.enums.MealsSettleMode;
import com.edaisong.core.enums.OrderFrom;
import com.edaisong.core.enums.OrderStatus;
import com.edaisong.core.enums.PublishOrderReturnEnum;
import com.edaisong.core.enums.SuperPlatform;
import com.edaisong.core.enums.TaskStatus;
import com.edaisong.entity.BusinessBalanceRecord;
import com.edaisong.entity.Order;
import com.edaisong.entity.OrderChild;
import com.edaisong.entity.OrderOther;
import com.edaisong.entity.OrderSubsidiesLog;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.BusiPubOrderTimeStatisticsModel;
import com.edaisong.entity.common.ResponseCode;
import com.edaisong.entity.domain.BusinessModel;
import com.edaisong.entity.domain.BusinessOrderSummaryModel;
import com.edaisong.entity.domain.OrderCommission;
import com.edaisong.entity.domain.OrderListModel;
import com.edaisong.entity.domain.OrderMapDetail;
import com.edaisong.entity.req.CancelOrderBusinessReq;
import com.edaisong.entity.req.OrderDetailBusinessReq;
import com.edaisong.entity.req.OrderReq;
import com.edaisong.entity.req.PagedOrderSearchReq;
import com.edaisong.entity.resp.CancelOrderBusinessResp;
import com.edaisong.entity.resp.OrderDetailBusinessResp;
import com.edaisong.entity.resp.OrderResp;

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
    
    private IBusinessService businessService;
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
	public OrderMapDetail getOrderMapDetail(long orderid) {
		return orderDao.getOrderMapDetail(orderid);
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

		if (orderDao.cancelOrderBusiness(updateModel) > 0 /* 取消订单 针对订单表的逻辑 */
				&& businessDao.updateForWithdraw(orderRe.getSettlemoney(),
						req.getBusinessId()) > 0 /* 更新商户余额 */) { // 取消成功
			BusinessBalanceRecord businessBalanceRecord = new BusinessBalanceRecord();
			businessBalanceRecord.setBusinessid(req.getBusinessId());// 商户Id
			businessBalanceRecord.setAmount(orderRe.getSettlemoney());
			businessBalanceRecord
					.setStatus((short) BusinessBalanceRecordStatus.Success
							.value()); // 流水状态(1、交易成功 2、交易中）
			businessBalanceRecord
					.setRecordtype((short) BusinessBalanceRecordRecordType.CancelOrder
							.value()); // 取消订单
			businessBalanceRecord.setOperator("商家:" + req.getBusinessId()); // 商家id
			businessBalanceRecord.setWithwardid((long) req.getOrderId()); // 订单id
			businessBalanceRecord.setRelationno(req.getOrderNo()); // 关联单号
			businessBalanceRecord.setRemark("商户取消订单返回配送费"); // 注释
			businessBalanceRecordDao.insert(businessBalanceRecord); // 记录
			OrderOther orderOther=new OrderOther();
			orderOther.setOrderid(req.getOrderId());
			orderOther.setCancelTime(new Date());
			orderOtherDao.updateByPrimaryKeySelective(orderOther);
		} else {
			throw new RuntimeException("更新订单状态为取消失败");
		}
	}

	/**
	 * 商户发布订单功能
	 * 
	 * @param req
	 * @return
	 * @author 胡灵波
	 * @Date 2015年8月6日 09:56:25
	 */
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public OrderResp AddOrder(OrderReq req) {
		OrderResp resp = new OrderResp();
		BusinessModel businessModel = businessDao.getBusiness(req.getBusinessid());
		//校验是否可以正常发单
		PublishOrderReturnEnum returnEnum= verificationAddOrder(req,businessModel);
		if (returnEnum!=PublishOrderReturnEnum.Success) {
			resp.setResponseCode(ResponseCode.BUSINESS_FAILURE_ERROR);
			resp.setMessage(returnEnum.desc());
			return resp;
		}
		// 订单主表
		Order order = fillOrder(req, businessModel);
		int orderID=orderDao.insert(order);
		order.setId(orderID);
		//记录发单日志
		OrderSubsidiesLog record=new OrderSubsidiesLog();
		record.setOrderid(orderID);
		record.setOrderstatus(OrderStatus.New.value());
		record.setOptid(req.getBusinessid());
		record.setOptname(businessModel.getName());
		record.setRemark(TaskStatus.PublishOrder.desc());
		record.setPlatform(SuperPlatform.Business.value());
		orderSubsidiesLogDao.insert(record);
		
		//扣除商家结算费
		BusinessBalanceRecord balanceRecord=new BusinessBalanceRecord();
		balanceRecord.setBusinessid(req.getBusinessid());
		balanceRecord.setAmount(-order.getSettlemoney());
		balanceRecord.setStatus((short)BusinessBalanceRecordStatus.Success.value());
		balanceRecord.setRecordtype((short)BusinessBalanceRecordRecordType.PublishOrder.value());
		balanceRecord.setOperator(businessModel.getName());
		balanceRecord.setWithwardid((long)orderID);
		balanceRecord.setRelationno(order.getOrderno());
		balanceRecord.setRemark("扣除商家结算费");
		businessService.updateForWithdrawC(balanceRecord);
		
		//记录补贴日志
		if (order.getAdjustment()>0) {
			OrderSubsidiesLog adjustRecord=new OrderSubsidiesLog();
			adjustRecord.setOrderid(orderID);
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
		orderOther.setOrderid(orderID);
		orderOther.setNeeduploadcount(req.getOrdercount());
		orderOther.setHaduploadcount(0);
		orderOther.setPublongitude(businessModel.getLongitude());
		orderOther.setPublatitude(businessModel.getLatitude());
		orderOther.setOnekeypuborder(businessModel.getOnekeypuborder());
		orderOther.setIsorderchecked(businessModel.getIsOrderChecked());
		orderOtherDao.insert(orderOther);
        
		//写入OrderChild
		if (req.getListOrderChild() != null
				&& req.getListOrderChild().size() > 0) {
			fillOrderChild(req,businessModel,order);
			orderChildDao.insertList(req.getListOrderChild());
		}
		return resp;
	}
	private void fillOrderChild(OrderReq req, BusinessModel businessModel,Order order){
		if (req.getListOrderChild() != null
				&& req.getListOrderChild().size() > 0) {
			OrderChild child=null;
			short payStatus=0;
			if (req.getIspay()||!req.getIspay()
					&& req.getMealssettlemode() == MealsSettleMode.LineOff.value()) {
				payStatus=1;
			}
			for (int i = 0; i < req.getListOrderChild().size(); i++) {
			    child=req.getListOrderChild().get(i);
			    child.setChildid(i+1);
				child.setCreateby(businessModel.getName());
				child.setUpdateby(businessModel.getName());
				child.setDeliveryprice(order.getDistribsubsidy());
				child.setOrderid(order.getId());
				child.setTotalprice(child.getGoodprice()+child.getDeliveryprice());
				child.setPaystatus(payStatus);
			}
		}
	}
	private Order fillOrder(OrderReq req, BusinessModel businessModel) {
		Order order = new Order();
		order.setOrderno("no11111111");// 临时
		order.setRecevicename(req.getRecevicename());
		order.setRecevicephoneno(req.getRecevicephoneno());
		order.setReceviceaddress(req.getReceviceaddress());
		order.setIspay(req.getIspay());
		order.setAmount(req.getAmount());
		order.setRemark(req.getRemark());
		order.setOrderfrom(req.getOrderfrom());
		order.setStatus((byte) OrderStatus.New.value());
		order.setOrdercount(req.getOrdercount());
		order.setTimespan("1");
		order.setPubdate(new Date());
		order.setBusinessid(req.getBusinessid());
//		order.setRecevicelongitude(req.getRecevicelongitude());
//		order.setRecevicelatitude(req.getRecevicelatitude());
//		order.setPickupaddress(req.getPickupaddress());
		order.setRecevicecity(req.getRecevicecity());
		

		order.setCommissionformulamode(businessModel.getStrategyId());
		order.setBusinesscommission(businessModel.getBusinesscommission());
		order.setBusinessgroupid(businessModel.getGroupid());
		order.setCommissiontype(businessModel.getCommissiontype());
		order.setCommissionfixvalue(businessModel.getCommissionfixvalue());
		order.setMealssettlemode(businessModel.getMealssettlemode());
		order.setDistribsubsidy(businessModel.getDistribsubsidy());
		
		OrderCommission orderCommission = new OrderCommission();
		OrderPriceBaseProvider orderPriceService = CommissionFactory
				.GetCommission(businessModel.getStrategyId());
		order.setOrdercommission(orderPriceService
				.getCurrenOrderCommission(orderCommission));
		order.setWebsitesubsidy(orderPriceService
				.getOrderWebSubsidy(orderCommission));
		order.setCommissionrate(orderPriceService
				.getCommissionRate(orderCommission));
		Double settleMoney = OrderSettleMoneyHelper.GetSettleMoney(
				req.getAmount(), businessModel.getBusinesscommission(),
				businessModel.getCommissionfixvalue(), req.getOrdercount(),
				businessModel.getDistribsubsidy(), req.getOrderfrom());
		order.setSettlemoney(settleMoney);
		order.setAdjustment(orderPriceService.getAdjustment(orderCommission));

		order.setBusinessreceivable(Double.valueOf(0));// 退还商家金额
		if (!req.getIspay()
				&& order.getMealssettlemode() == MealsSettleMode.LineOn.value()) {
			Double money = req.getAmount()+(businessModel.getDistribsubsidy()*req.getOrdercount());
							
			order.setBusinessreceivable(money);
		}

		return order;
	}
	/**
	 * 商户发单数据验证  
	 * @author CaoHeYang
	 * @param req
	 * @param businessModel
	 * @Date 20150818
	 * @return
	 */
	private PublishOrderReturnEnum verificationAddOrder(OrderReq  req,BusinessModel businessModel){
		if (businessModel==null) {
			return PublishOrderReturnEnum.BusinessEmpty;
		}
		boolean isOneKeyPubOrder = false;
        if (businessModel != null && businessModel.getOnekeypuborder() == 1)
        {
        	 isOneKeyPubOrder = true;
        }   
        if (businessModel.getStatus()!=BusinessStatus.AuditPass.value())//验证该商户有无发布订单资格   审核通过下不允许发单
        {
        	return PublishOrderReturnEnum.HadCancelQualification;
        }
        //非一键发单模式下
        if (!isOneKeyPubOrder) {
        	 if (req.getRecevicephoneno()==null||req.getRecevicephoneno().isEmpty())//手机号
             {
        		 return PublishOrderReturnEnum.RecevicePhoneIsNULL;
             }
             if (req.getReceviceaddress()==null||req.getReceviceaddress().isEmpty())
             {
            	 return PublishOrderReturnEnum.ReceviceAddressIsNULL;
             }
		}
        if (req.getListOrderChild().size()> 15 || req.getListOrderChild().size() <= 0||req.getOrdercount()!=req.getListOrderChild().size())
        {
        	return PublishOrderReturnEnum.OrderCountError;
        }
        Double amount = 0d;
        for (int i = 0; i < req.getListOrderChild().size(); i++)//子订单价格
        {
            if (req.getListOrderChild().get(i).getGoodprice()<5)  //金额小于5不合法
            {
                return PublishOrderReturnEnum.AmountLessThanTen;
            }
            if (req.getListOrderChild().get(i).getGoodprice()>1000) //金额大于1000不合法
            {
            	return PublishOrderReturnEnum.AmountMoreThanFiveThousand;
            }
            amount=amount+req.getListOrderChild().get(i).getGoodprice();
        }
        if (req.getAmount().compareTo(amount)!=0)
        {
           return PublishOrderReturnEnum.AmountIsNotEqual; //金额有误
        }
        if (businessModel.getIsallowoverdraft()== 0) //0不允许透支
        {
    		Double settleMoney = OrderSettleMoneyHelper.GetSettleMoney(
    				req.getAmount(), businessModel.getBusinesscommission(),
    				businessModel.getCommissionfixvalue(), req.getOrdercount(),
    				businessModel.getDistribsubsidy(), req.getOrderfrom());
            if (businessModel.getBalanceprice()<settleMoney)
            {
               return PublishOrderReturnEnum.BusiBalancePriceLack;
            }
        }       
        return PublishOrderReturnEnum.Success;
	}
	
	@Override
	public BusinessOrderSummaryModel getBusinessOrderSummary(int businessId) {
		return orderDao.getBusinessOrderSummary(businessId);
	}

	@Override
	public List<BusiPubOrderTimeStatisticsModel> getBusiPubOrderTimeStatistics(int businessId) {
		return orderDao.getBusiPubOrderTimeStatistics(1812);
	}
}
