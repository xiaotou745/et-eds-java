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
import com.edaisong.entity.req.MyOrderGrabBReq;
import com.edaisong.entity.req.MyOrderGrabCReq;
import com.edaisong.entity.req.OrderGrabDetailBReq;
import com.edaisong.entity.req.OrderGrabDetailCReq;
import com.edaisong.entity.req.PagedFastOrderSearchReq;
import com.edaisong.entity.resp.MyOrderGrabBResp;
import com.edaisong.entity.resp.MyOrderGrabCResp;
import com.edaisong.entity.resp.MyOrderGrabDetailBResp;
import com.edaisong.entity.resp.MyOrderGrabDetailCResp;
 

import java.util.ArrayList;
import java.util.Date; 

import com.edaisong.api.dao.inter.IOrderChildDao; 
import com.edaisong.api.dao.inter.IOrderGrabChildDao; 
import com.edaisong.api.dao.inter.IOrderRegionDao;
import com.edaisong.api.dao.inter.IOrderSubsidiesLogDao; 
import com.edaisong.core.enums.OrderGrabReturnEnum; 
import com.edaisong.core.enums.OrderStatus; 
import com.edaisong.core.enums.SuperPlatform;
import com.edaisong.core.enums.TaskStatus; 
import com.edaisong.core.util.OrderNoHelper; 
import com.edaisong.entity.OrderChild; 
import com.edaisong.entity.OrderGrabChild; 
import com.edaisong.entity.OrderRegion;
import com.edaisong.entity.OrderSubsidiesLog; 
import com.edaisong.entity.domain.FastOrderDetail;
import com.edaisong.entity.domain.FastOrderExportModel; 
import com.edaisong.entity.req.OrderGrabReq; 
import com.edaisong.entity.resp.OrderGrabResp;
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
	
	@Autowired
	private IOrderRegionDao orderRegionDao;
	
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
	public HttpResultModel<OrderGrabResp> GrabOrder(OrderGrabReq req)	
	{
		HttpResultModel<OrderGrabResp> resp=new HttpResultModel<OrderGrabResp>();
		
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
						resp.setStatus(OrderGrabReturnEnum.Success.value());
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
	public MyOrderGrabDetailCResp getMyOrderGrabDetailC(
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
