package com.edaisong.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IOrderGrabDao;
import com.edaisong.api.service.inter.IOrderGrabService;
import com.edaisong.entity.OrderGrab;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.FastOrderMapDetail;
import com.edaisong.entity.domain.FastOrderModel;
import com.edaisong.entity.domain.OrderGrabDetailModel;
import com.edaisong.entity.req.MyOrderGrabCReq;
import com.edaisong.entity.req.OrderGrabDetailCReq;
import com.edaisong.entity.req.PagedFastOrderSearchReq;
import com.edaisong.entity.resp.MyOrderGrabCResp;
import com.edaisong.entity.resp.MyOrderGrabDetailResp;

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
import com.edaisong.api.dao.inter.IOrderGrabChildDao;
import com.edaisong.api.dao.inter.IOrderGrabDao;
import com.edaisong.api.dao.inter.IOrderOtherDao;
import com.edaisong.api.dao.inter.IOrderRegionDao;
import com.edaisong.api.dao.inter.IOrderSubsidiesLogDao;
import com.edaisong.api.redis.RedisService;
import com.edaisong.api.service.inter.IBusinessService;
import com.edaisong.api.service.inter.IClienterService;
import com.edaisong.api.service.inter.IOrderGrabService;
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
import com.edaisong.core.enums.OrderGrabReturnEnum;
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
import com.edaisong.entity.OrderGrabChild;
import com.edaisong.entity.OrderOther;
import com.edaisong.entity.OrderRegion;
import com.edaisong.entity.OrderSubsidiesLog;
import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.common.Location;
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
import com.edaisong.entity.domain.FastOrderDetail;
import com.edaisong.entity.domain.FastOrderExportModel;
import com.edaisong.entity.domain.InStoreTask;
import com.edaisong.entity.domain.OrderCommission;
import com.edaisong.entity.domain.OrderListModel;
import com.edaisong.entity.domain.OrderMapDetail;
import com.edaisong.entity.domain.QueryOrder;
import com.edaisong.entity.domain.RegionOrderDetail;
import com.edaisong.entity.domain.RegionOrderTotal;
import com.edaisong.entity.domain.ServiceClienter;
import com.edaisong.entity.req.InStoreTaskReq;
import com.edaisong.entity.req.OptOrder;
import com.edaisong.entity.req.BusinessMoney;
import com.edaisong.entity.req.CancelOrderBusinessReq;
import com.edaisong.entity.req.ClienterMoney;
import com.edaisong.entity.req.OrderDetailBusinessReq;
import com.edaisong.entity.req.OrderGrabReq;
import com.edaisong.entity.req.OrderOtherSearch;
import com.edaisong.entity.req.OrderPushReq;
import com.edaisong.entity.req.OrderRegionReq;
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
import com.edaisong.entity.resp.OrderGrabResp;
import com.edaisong.entity.resp.OrderPushResp;
import com.edaisong.entity.resp.OrderResp;
import com.edaisong.entity.resp.OrderStatisticsBResp;
import com.edaisong.entity.resp.QueryOrderBResp;
import com.edaisong.entity.resp.OrderStatisticsCResp;
import com.edaisong.entity.resp.QueryOrderCResp;
import com.edaisong.entity.OrderGrabChild;
@Service
public class OrderGrabService implements IOrderGrabService {

	
	@Autowired
	private IOrderGrabDao orderGrabDao;
   @Autowired
	private IOrderChildDao orderChildDao;
	@Autowired
	private IOrderGrabChildDao orderGrabChildDao;
	@Autowired
	private IOrderSubsidiesLogDao orderSubsidiesLogDao;
@Override
	public int deleteById(Long id) {
		return orderGrabDao.deleteById(id);
	}

	@Override
	public int insert(OrderGrab record) {
		return orderGrabDao.insert(record);
	}

	@Override
	public FastOrderDetail selectById(Long id) {
		FastOrderDetail result= orderGrabDao.selectById(id);
		List<OrderGrabChild>  childs=orderGrabChildDao.selectByGrabOrderId(id);
		result.setOrderChilds(childs);
		return result;
	}

	@Override
	public PagedResponse<FastOrderModel> query(PagedFastOrderSearchReq req) {
		return orderGrabDao.query(req);
	}

	@Override
	public List<FastOrderMapDetail> getMapDetailById(Long id) {
		return orderGrabDao.getMapDetailById(id);
	} 
	
	@Override
	public OrderGrabResp GrabOrder(OrderGrabReq req)	
	{
		OrderGrabResp resp=new OrderGrabResp();
		
		//抢单主表
		OrderGrab orderGrab=new OrderGrab();
		orderGrab.setBusinessid(req.getBusinessId());	
		orderGrab.setClienterid(req.getClienterId());		
		orderGrab.setOrderRegionOneId(req.getOrderRegionOneId());		
		orderGrab.setOrderRegionOneName(req.getOrderRegionOneName());		
		orderGrab.setOrderRegionTwoId(req.getOrderRegionTwoId());		
		orderGrab.setOrderRegionTwoName(req.getOrderRegionTwoName());
		orderGrab.setOrderCount(req.getOrderCount());		
		orderGrab.setGraborderno(OrderNoHelper.generateOrderCode(req.getClienterId()));
		orderGrab.setStatus((byte)OrderStatus.Delivery.value());
		orderGrab.setGrabtime(new Date());
		orderGrab.setGrablongitude(req.getGrabLongitude());
		orderGrab.setGrablatitude(req.getGrabLatitude());				
		int orderGrabId=orderGrabDao.insertSelective(orderGrab);		

		if(req.getOrderRegionTwoId()>0)//二级区域
		{
			//更新抢单状态
			List<Integer>  listOrderChild=orderChildDao.updateGradTwo(req);	
			
			List<OrderGrabChild> listOrderGrabChild= fillOrderGrabChild(listOrderChild,orderGrab,req);
			
			//写入抢单子表
			int ogcId= orderGrabChildDao.insertList(listOrderGrabChild);
			
			//更新数量			
			OrderRegion orModelTwo=new OrderRegion();
			orModelTwo.setId(req.getOrderRegionTwoId());
			orModelTwo.setWaitingcount(-listOrderChild.size());
			orModelTwo.setDistributioning(listOrderChild.size());
			orderRegionDao.updateByPrimaryKeySelective(orModelTwo);
			
			OrderRegion orModelOne=new OrderRegion();
			orModelOne.setId(req.getOrderRegionOneId());
			orModelOne.setWaitingcount(-listOrderChild.size());		
			orModelTwo.setDistributioning(listOrderChild.size());
			orderRegionDao.updateByPrimaryKeySelective(orModelOne);		
		}
		else//一级区域
		{
			List<Integer>  listOrderChild=orderChildDao.updateGradOne(req);	
			List<OrderGrabChild> listOrderGrabChild= fillOrderGrabChild(listOrderChild,orderGrab,req);
			
			//写入抢单表
			int ogcId= orderGrabChildDao.insertList(listOrderGrabChild);
			
			OrderRegion orModelOne=new OrderRegion();
			orModelOne.setId(req.getOrderRegionOneId());
			orModelOne.setWaitingcount(-listOrderChild.size());			
			orderRegionDao.updateByPrimaryKeySelective(orModelOne);	
		}					
		
		// 记录发单日志
		OrderSubsidiesLog record = new OrderSubsidiesLog();
		record.setOrderid(orderGrab.getId());
		record.setOrderstatus(OrderStatus.Delivery.value());
		record.setOptid(req.getClienterId());
		record.setPrice(0d);
		record.setOptname("");//临时
		record.setRemark(TaskStatus.OrderHadRush.desc());
		record.setPlatform(SuperPlatform.Clienter.value());
		int ordersubsidiesId = orderSubsidiesLogDao.insert(record);

		if(orderGrabId>0)
					{
						resp.setResponseCode(OrderGrabReturnEnum.Success.value());
						resp.setMessage(OrderGrabReturnEnum.Success.desc());
						return resp;
					}	
		return resp;
	}
	@Override
	public List<MyOrderGrabCResp> getMyOrderGrabC(
			MyOrderGrabCReq myOrderGrabCReq) { 
		return orderGrabDao.getMyOrderGrabC(myOrderGrabCReq);
	}

	@Override
	public MyOrderGrabDetailResp getMyOrderGrabDetailC(
			OrderGrabDetailCReq orderGrabDetailCReq) { 
		return orderGrabDao.getMyOrderGrabDetailC(orderGrabDetailCReq);
	}

	@Override
	public int add(OrderGrab record) { 
		return orderGrabDao.insertSelective(record);
	}

	@Override
	public List<FastOrderExportModel> exportOrder(PagedFastOrderSearchReq req) {
		return orderGrabDao.exportOrder(req);
	}
	
	
	/**
	 * 骑士抢单  插入子订单
	 * 
	 * @param req
	 * @param businessModel
	 * @param order
	 */
	private List<OrderGrabChild> fillOrderGrabChild(List<Integer> list,OrderGrab orderGrab,OrderGrabReq req) {
		List<OrderGrabChild> listOrderGrabChild=new ArrayList<OrderGrabChild>();
		
		for(int i=0;i<list.size();i++)
		{
			OrderGrabChild model=new OrderGrabChild();
			int childid=list.get(i);
			model.setOrderchildid(childid);			
			model.setGraborderid(orderGrab.getId());//产表Id							
			model.setChildid(i + 1);
			model.setBusinessid(req.getBusinessId());
			model.setStatus((byte)OrderStatus.Delivery.value());			
			
			//获取订单
			OrderChild ocModel= orderChildDao.selectByPrimaryKey(childid);		
			model.setOrderid(ocModel.getOrderid());//订单id			
			model.setOrderCommission(ocModel.getOrderCommission());
		    model.setWebsiteSubsidy(ocModel.getWebsiteSubsidy());
			model.setCommissionRate(ocModel.getCommissionRate());
			model.setAdjustment(ocModel.getAdjustment());
			model.setBaseCommission(ocModel.getBaseCommission());
			model.setSettleMoney(ocModel.getSettleMoney());
			listOrderGrabChild.add(model);
		}

		return listOrderGrabChild;
	}
}
