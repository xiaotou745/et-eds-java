package com.edaisong.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edaisong.api.common.TransactionalRuntimeException;
import com.edaisong.api.dao.inter.IOrderGrabDao;
import com.edaisong.api.service.inter.IClienterService;
import com.edaisong.api.service.inter.IOrderGrabService;
import com.edaisong.entity.OrderGrab;
import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.BusinessModel;
import com.edaisong.entity.domain.FastOrderMapDetail;
import com.edaisong.entity.domain.FastOrderModel;  
import com.edaisong.entity.domain.MyOrderGrabCModel;
import com.edaisong.entity.domain.QueryOrder;
import com.edaisong.entity.req.ClienterMoney;
import com.edaisong.entity.req.HadFinishOrderReq;
import com.edaisong.entity.req.MyOrderGrabCReq; 
import com.edaisong.entity.req.MyOrderGrabCReq;import com.edaisong.entity.req.OrderGrabCompleteReq;
import com.edaisong.entity.req.OrderGrabConfirmTakeReq;
import com.edaisong.entity.req.OrderGrabDetailCReq;
import com.edaisong.entity.req.OrderReq;
import com.edaisong.entity.req.PagedFastOrderSearchReq;
import com.edaisong.entity.req.QueryOrderReq;
import com.edaisong.entity.resp.HadFinishOrderResp;
import com.edaisong.entity.resp.MyOrderGrabCResp;
import com.edaisong.entity.resp.MyOrderGrabDetailCResp;
 













import java.util.ArrayList;
import java.util.Date; 

import com.edaisong.api.dao.inter.IBusinessDao;
import com.edaisong.api.dao.inter.IOrderChildDao; 
import com.edaisong.api.dao.inter.IOrderGrabChildDao; 
import com.edaisong.api.dao.inter.IOrderRegionDao;
import com.edaisong.api.dao.inter.IOrderSubsidiesLogDao; 
import com.edaisong.core.enums.BusinessStatusEnum;
import com.edaisong.core.enums.ClienterBalanceRecordRecordType;
import com.edaisong.core.enums.ClienterBalanceRecordStatus;
import com.edaisong.core.enums.ClienterStatusEnum;
import com.edaisong.core.enums.OrderGrabReturnEnum; 
import com.edaisong.core.enums.OrderStatus; 
import com.edaisong.core.enums.PublishOrderReturnEnum;
import com.edaisong.core.enums.SuperPlatform;
import com.edaisong.core.enums.TaskStatus; 
import com.edaisong.core.enums.returnenums.QueryOrderReturnEnum;
import com.edaisong.core.util.OrderNoHelper; 
import com.edaisong.entity.Order;
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
	private IBusinessDao businessDao;
	@Autowired
	private IOrderRegionDao orderRegionDao;
	
	@Autowired
	private IClienterService clienterService;
	
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
	
	/**
	 * 骑士抢单
	 * 事务中插入数据，并更新当前插入数据不会死锁
	 * @author 胡灵波
	 * @Date 2015年11月3日 11:17:24
	 * @param req 
	 * @return
	 */	
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	@Override
	public HttpResultModel<OrderGrabResp> GrabOrder(OrderGrabReq req)	
	{
		HttpResultModel<OrderGrabResp> resp=new HttpResultModel<OrderGrabResp>();
		if (req.getBusinessId() == null) {
			resp.setStatus(OrderGrabReturnEnum.BusinessEmpty.value());
			resp.setMessage(OrderGrabReturnEnum.BusinessEmpty.desc());				
			return resp;			
		}
		if (req.getClienterId() == null) {
			resp.setStatus(OrderGrabReturnEnum.ClienterEmpty.value());
			resp.setMessage(OrderGrabReturnEnum.ClienterEmpty.desc());				
			return resp;			
		}
		
		//抢单主表
		OrderGrab orderGrab=fillOrderGrab(req);		
		int orderGrabId=orderGrabDao.insertSelective(orderGrab);		
		if (orderGrabId <= 0) {
			throw new TransactionalRuntimeException("抢单错误");
		}

		//更新订单子表
		List<Integer>  listOrderChild=null;
		if(req.getOrderRegionTwoId()>0)//二级区域
		{			
			listOrderChild=orderChildDao.updateGradTwo(req);//更新抢单状态	
		}
		else//一级区域
		{
			listOrderChild=orderChildDao.updateGradOne(req);			
		}			
		if(listOrderChild.size()<req.getOrderCount())
		{			
			throw new TransactionalRuntimeException("抢单数量不符合规则");				
		}		
			
		//写入抢单子表
			List<OrderGrabChild> listOrderGrabChild= fillOrderGrabChild(listOrderChild,orderGrab,req);
			int ogcId= orderGrabChildDao.insertList(listOrderGrabChild);
		if (ogcId <= 0) {
			throw new TransactionalRuntimeException("写入抢单明细错误");
		}			
			
		//更新抢单主表金额		
		double num1=0,num2=0,num3=0,num4=0,num5=0,num6=0,num7=0;
		for (int i=0;i<listOrderGrabChild.size();i++)
		{			
			OrderGrabChild tempOGCModel=new OrderGrabChild();
			tempOGCModel=listOrderGrabChild.get(i);
			num1+=tempOGCModel.getOrderCommission();
			num2+=tempOGCModel.getSettleMoney();
			num3+=tempOGCModel.getCommissionRate();
			num4+=tempOGCModel.getBaseCommission();
			num5+=tempOGCModel.getWebsiteSubsidy();
			num6+=tempOGCModel.getAdjustment();
			num7+=tempOGCModel.getDistribsubsidy();
		}
		OrderGrab updateOGCModel=new OrderGrab();
		updateOGCModel.setId(orderGrab.getId());
		updateOGCModel.setOrderCommission(num1);
		updateOGCModel.setSettleMoney(num2);
		updateOGCModel.setCommissionRate(num3);
		updateOGCModel.setBaseCommission(num4);
		updateOGCModel.setWebsitesubsidy(num5);
		updateOGCModel.setAdjustment(num6);
		updateOGCModel.setDistribsubsidy(num7);
		int updateOrderGrabId= orderGrabDao.updateByPrimaryKeySelective(updateOGCModel);
		if (updateOrderGrabId <= 0) {
			throw new TransactionalRuntimeException("更新抢单主表金额错误");
		}	
		
		//更新区域
		int OneId = req.getOrderRegionOneId();
		int TwoId =req.getOrderRegionTwoId();
		int orderCount = req.getOrderCount();
		if(TwoId>0)//二级区域
		{			
			//更新数量			
			OrderRegion orModelTwo=new OrderRegion();
			orModelTwo.setId(TwoId);
			orModelTwo.setWaitingcount(-orderCount);
			orModelTwo.setGrabcount(orderCount);
			int orderRegionTwoId=orderRegionDao.updateByPrimaryKeySelective(orModelTwo);
			if (orderRegionTwoId <= 0) {
				throw new TransactionalRuntimeException("更新二级区域错误");
			}			
			
			OrderRegion orModelOne=orderRegionDao.getByIdWrite(OneId);//查询，更新不会锁表
			if(orModelOne.getWaitingcount()==orderCount)
			{				
				orModelOne.setHaschild(false);
		}
			orModelOne.setId(OneId);
			orModelOne.setWaitingcount(-orderCount);		
			orModelOne.setGrabcount(orderCount);
			int orderRegionOneId=orderRegionDao.updateByPrimaryKeySelective(orModelOne);		
			if (orderRegionOneId <= 0) {
				throw new TransactionalRuntimeException("更新一级区域错误");
			}
		}
		else//一级区域
		{		
			OrderRegion orModelOne=new OrderRegion();
			orModelOne.setId(OneId);
			orModelOne.setWaitingcount(-orderCount);
			orModelOne.setGrabcount(orderCount);	
			int orderRegionOneId=orderRegionDao.updateByPrimaryKeySelective(orModelOne);	
			if (orderRegionOneId <= 0) {
				throw new TransactionalRuntimeException("更新一级区域错误");
			}
		}					
		
		// 记录抢单日志
		OrderSubsidiesLog record = new OrderSubsidiesLog();
		record.setOrderid(orderGrab.getId());
		record.setOrderstatus(OrderStatus.Delivery.value());
		record.setOptid(req.getClienterId());
		record.setPrice(0d);
		record.setOptname("");//临时
		record.setRemark(TaskStatus.OrderHadRush.desc());
		record.setPlatform(SuperPlatform.NewApiReceive.value());
		int ordersubsidiesId = orderSubsidiesLogDao.insert(record);
		if (ordersubsidiesId <= 0) {
			throw new TransactionalRuntimeException("记录订单日志错误");
		}

						resp.setStatus(OrderGrabReturnEnum.Success.value());
						resp.setMessage(OrderGrabReturnEnum.Success.desc());
		return resp;			
	}
	
	/**
	 * 骑士取货 
	 * @author 胡灵波
	 * @Date 2015年11月4日 11:16:24
	 * @param req 
	 * @return
	 */	
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	@Override
	public HttpResultModel<Integer> ConfirmTake(OrderGrabConfirmTakeReq req)
	{
		HttpResultModel<Integer> resp=new HttpResultModel<Integer>();

		if (req.getGrabOrderId() == null) {
			resp.setStatus(OrderGrabReturnEnum.OrderGrabEmpty.value());
			resp.setMessage(OrderGrabReturnEnum.OrderGrabEmpty.desc());				
			return resp;			
		}
		
		if (req.getClienterId() == null) {
			resp.setStatus(OrderGrabReturnEnum.ClienterEmpty.value());
			resp.setMessage(OrderGrabReturnEnum.ClienterEmpty.desc());				
			return resp;			
		}
		OrderGrab currOgModel= orderGrabDao.selectByPrimaryKeyWrite(req.getGrabOrderId());
		if(!currOgModel.getClienterid().equals(req.getClienterId()))
		{
			throw new TransactionalRuntimeException("抢单与取货骑士不符");
		}
		//更新抢单主表	
		OrderGrab ogModel=new OrderGrab ();		
		ogModel.setId(req.getGrabOrderId());
		ogModel.setPickuplongitude(req.getPickUpLongitude());
		ogModel.setPickuplatitude(req.getPickUpLatitude());
		ogModel.setStatus((byte)OrderStatus.Taking.value());
		int ogId=orderGrabDao.updateByPrimaryKeySelective(ogModel);
		if (ogId <= 0) {
			throw new TransactionalRuntimeException("取货主表错误");
		}
		
		//更新抢单子表
		OrderGrabChild ogcModel=new OrderGrabChild();
		ogcModel.setGraborderid(req.getGrabOrderId());
		ogcModel.setStatus((byte)OrderStatus.Taking.value());
		int ogcId= orderGrabChildDao.updateByGraborderidSelective(ogcModel);
		if (ogcId <= 0) {
			throw new TransactionalRuntimeException("取货子表错误");
		}
		
		//更新发单子表 (通过抢单子表更新原子订单表)
		List<OrderGrabChild> listOgc= orderGrabChildDao.selectByGrabOrderId((long)req.getGrabOrderId());
		List<OrderChild> listOc=new ArrayList<OrderChild>();
		for(int i=0;i<listOgc.size();i++)
		{
			OrderChild orderChildModel=new OrderChild();
			orderChildModel.setId((long)listOgc.get(i).getOrderchildid());
			orderChildModel.setStatus((short)OrderStatus.Taking.value());
			orderChildModel.setUpdateby(req.getClienterId().toString());
			listOc.add(orderChildModel);			
		}
		int updateOCId= orderChildDao.updateList(listOc);
		if (updateOCId <= 0) {
			throw new TransactionalRuntimeException("更新子订单状态错误");
		}
		
		//更新区域表		
		int OneId = currOgModel.getOrderRegionOneId();
		int TwoId =currOgModel.getOrderRegionTwoId();
		int orderCount = currOgModel.getOrdercount();
		if(TwoId>0)//二级区域
		{
			OrderRegion orModelTwo=new OrderRegion();
			orModelTwo.setId(TwoId);
			orModelTwo.setGrabcount(-orderCount);
			orModelTwo.setDistributioning(orderCount);
			int orderRegionTwoId= orderRegionDao.updateByPrimaryKeySelective(orModelTwo);
			if (orderRegionTwoId <= 0) {
				throw new TransactionalRuntimeException("更新二级区域错误");
			}
			
			OrderRegion orModelOne=new OrderRegion();
			orModelOne.setId(OneId);
			orModelOne.setGrabcount(-orderCount);		
			orModelOne.setDistributioning(orderCount);
			int orderRegionOneId=orderRegionDao.updateByPrimaryKeySelective(orModelOne);	
			if (orderRegionOneId <= 0) {
				throw new TransactionalRuntimeException("更新一级区域错误");
		}
		}
		else
		{
			OrderRegion orModelOne=new OrderRegion();
			orModelOne.setId(OneId);
			orModelOne.setGrabcount(-orderCount);		
			orModelOne.setDistributioning(orderCount);
			int orderRegionOneId=orderRegionDao.updateByPrimaryKeySelective(orModelOne);
			if (orderRegionOneId <= 0) {
				throw new TransactionalRuntimeException("更新一级区域错误");
		}
		}
		
		// 记录取货日志
		OrderSubsidiesLog record = new OrderSubsidiesLog();
		record.setOrderid(req.getGrabOrderId());
		record.setOrderstatus(OrderStatus.Taking.value());
		record.setOptid(req.getClienterId());
		record.setPrice(0d);
		record.setOptname("");//临时
		record.setRemark(TaskStatus.Taking.desc());
		record.setPlatform(SuperPlatform.NewApiConfirmtake.value());
		int ordersubsidiesId = orderSubsidiesLogDao.insert(record);		
		if (ordersubsidiesId <= 0) {
			throw new TransactionalRuntimeException("记录订单日志错误");
		}
		return resp;
	}
	
	/**
	 * 骑士完成订单
	 * @author 胡灵波
	 * @Date 2015年11月4日 11:17:46
	 * @param req 
	 * @return
	 */	
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	@Override
	public  HttpResultModel<Integer> Complete(OrderGrabCompleteReq req)
	{
		HttpResultModel<Integer> resp=new HttpResultModel<Integer>();		

		if (req.getOrderGrabChildId() == null) {
			resp.setStatus(OrderGrabReturnEnum.OrderGrabEmpty.value());
			resp.setMessage(OrderGrabReturnEnum.OrderGrabEmpty.desc());				
			return resp;			
		}
		if (req.getClienterId() == null) {
			resp.setStatus(OrderGrabReturnEnum.ClienterEmpty.value());
			resp.setMessage(OrderGrabReturnEnum.ClienterEmpty.desc());				
			return resp;			
		}
		
		//更新骑士余额		
		OrderGrabChild currOgcModel=  orderGrabChildDao.selectByPrimaryKey(req.getOrderGrabChildId());
		ClienterMoney clienterMoney = new ClienterMoney();
		clienterMoney.setAmount(currOgcModel.getOrderCommission()); 
		clienterMoney.setClienterId(req.getClienterId());
		clienterMoney
				.setRecordType(ClienterBalanceRecordRecordType.OrderCommission
						.value());
		clienterMoney.setRelationNo("");
		clienterMoney.setWithwardId(currOgcModel.getId());
		clienterMoney.setOperator(req.getClienterId().toString());
		clienterMoney.setStatus(ClienterBalanceRecordStatus.Success
				.value());
		clienterMoney.setRemark("佣金"+currOgcModel.getOrderCommission());
		clienterService.updateCBalanceAndWithdraw(clienterMoney);		
		
		//更新子订单
		OrderGrabChild ogcModel=new OrderGrabChild();
		ogcModel.setId(req.getOrderGrabChildId());
		ogcModel.setStatus((byte)OrderStatus.Complite.value());
		ogcModel.setDonelatitude(req.getDoneLatitude());
		ogcModel.setDonelongitude(req.getDoneLongitude());
		int ogId=orderGrabChildDao.updateByPrimaryKeySelective(ogcModel);
		if (ogId <= 0) {
			throw new TransactionalRuntimeException("完成订单主表错误");
		}
		
		//更新发单子表
		OrderChild orderChildModel=new OrderChild();
		orderChildModel.setId((long)currOgcModel.getOrderchildid());
		orderChildModel.setStatus((short)OrderStatus.Complite.value());
		orderChildModel.setUpdateby(req.getClienterId().toString());
		int ogcId=orderChildDao.updateByPrimaryKeySelective(orderChildModel);
		if (ogcId <= 0) {
			throw new TransactionalRuntimeException("完成订单子表错误");
		}
		
		//更新区域表			
		OrderGrab currOgModel= orderGrabDao.selectByPrimaryKeyWrite(currOgcModel.getGraborderid());
		int OneId = currOgModel.getOrderRegionOneId();
		int TwoId =currOgModel.getOrderRegionTwoId();
		int orderCount = 1;
		if(TwoId>0)//二级区域
		{
			//更新数量			
			OrderRegion orModelTwo=new OrderRegion();
			orModelTwo.setId(TwoId);
			orModelTwo.setDistributioning(-orderCount);
			orModelTwo.setDonecount(orderCount);
			int orderRegionTwoId=orderRegionDao.updateByPrimaryKeySelective(orModelTwo);
			if (orderRegionTwoId <= 0) {
				throw new TransactionalRuntimeException("更新二级区域错误");
			}
			
			OrderRegion orModelOne=new OrderRegion();
			orModelOne.setId(OneId);
			orModelOne.setDistributioning(-orderCount);		
			orModelOne.setDonecount(orderCount);
			int orderRegionOneId=orderRegionDao.updateByPrimaryKeySelective(orModelOne);	
			if (orderRegionOneId <= 0) {
				throw new TransactionalRuntimeException("更新一级区域错误");
		}
		}
		else
		{
			OrderRegion orModelOne=new OrderRegion();
			orModelOne.setId(OneId);
			orModelOne.setDistributioning(-orderCount);		
			orModelOne.setDonecount(orderCount);
			int orderRegionOneId= orderRegionDao.updateByPrimaryKeySelective(orModelOne);	
			if (orderRegionOneId <= 0) {
				throw new TransactionalRuntimeException("更新一级区域错误");
		}
		}
		
		// 记录取货日志
		OrderSubsidiesLog record = new OrderSubsidiesLog();
		record.setOrderid(req.getOrderGrabChildId());
		record.setOrderstatus(OrderStatus.Complite.value());
		record.setOptid(req.getClienterId());
		record.setPrice(0d);
		record.setOptname("");//临时
		record.setRemark(TaskStatus.OrderFinish.desc());
		record.setPlatform(SuperPlatform.NewApiComplete.value());
		int ordersubsidiesId = orderSubsidiesLogDao.insert(record);		
		if (ordersubsidiesId <= 0) {
			throw new TransactionalRuntimeException("记录订单日志错误");
		}
		return resp;
	}
	
	@Override
	public MyOrderGrabCResp getMyOrderGrabC(
			MyOrderGrabCReq myOrderGrabCReq) {
		MyOrderGrabCResp myOrderGrabCResp = new MyOrderGrabCResp();
		List<MyOrderGrabCModel> myOrderGrabCModel = orderGrabDao.getMyOrderGrabC(myOrderGrabCReq);
		myOrderGrabCResp.setMyOrderGrabCModelList(myOrderGrabCModel);
		
		MyOrderGrabCResp myOrderGrabCTotal = new MyOrderGrabCResp();
		myOrderGrabCTotal = orderGrabDao.getMyOrderGrabCTotalInfo(myOrderGrabCReq);
		myOrderGrabCResp.setPeiSongZhongTotal(myOrderGrabCTotal.getPeiSongZhongTotal());
		myOrderGrabCResp.setQuHuoZhongTotal(myOrderGrabCTotal.getQuHuoZhongTotal());
		
		return myOrderGrabCResp;
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
	
	//用户自定义方法
	/**
	 * 骑士抢单  主表
	 * @author 胡灵波
	 * @Date 2015年11月10日 13:53:50
	 * @param req

	 * @param order
	 */
	private OrderGrab fillOrderGrab(OrderGrabReq req)
	{
		OrderGrab orderGrab=new OrderGrab();
		orderGrab.setBusinessid(req.getBusinessId());	
		orderGrab.setClienterid(req.getClienterId());		
		orderGrab.setOrderRegionOneId(req.getOrderRegionOneId());		
		orderGrab.setOrderRegionOneName(req.getOrderRegionOneName());		
		orderGrab.setOrderRegionTwoId(req.getOrderRegionTwoId());		
		orderGrab.setOrderRegionTwoName(req.getOrderRegionTwoName());
		orderGrab.setOrdercount(req.getOrderCount());		
		orderGrab.setGraborderno(OrderNoHelper.generateOrderCode(req.getClienterId()));
		orderGrab.setStatus((byte)OrderStatus.Delivery.value());
		orderGrab.setGrabtime(new Date());
		orderGrab.setGrablongitude(req.getGrabLongitude());
		orderGrab.setGrablatitude(req.getGrabLatitude());				
	
		return orderGrab;			
	}
	/**
	 * 骑士抢单  插入子订单
	 * @author 胡灵波
	 * @Date 2015年11月4日 11:17:46
	 * @param req

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
			model.setDonelongitude(0.0);
			model.setDonelatitude(0.0);	
			
			//获取订单
			OrderChild ocModel= orderChildDao.selectByPrimaryKey(childid);		
			model.setOrderid(ocModel.getOrderid());//订单id			
			model.setOrderCommission(ocModel.getOrderCommission());
		    model.setWebsiteSubsidy(ocModel.getWebsiteSubsidy());
			model.setCommissionRate(ocModel.getCommissionRate());
			model.setAdjustment(ocModel.getAdjustment());
			model.setBaseCommission(ocModel.getBaseCommission());
			model.setSettleMoney(ocModel.getSettleMoney());
			model.setDistribsubsidy(ocModel.getDistribSubsidy());
			listOrderGrabChild.add(model);
		}

		return listOrderGrabChild;
	}
 

	@Override
	public HadFinishOrderResp getHadFinishOrderC(HadFinishOrderReq para) {
		HadFinishOrderResp myOrderGrabCResp = new HadFinishOrderResp();
		List<MyOrderGrabCModel> myOrderGrabCModel = orderGrabDao.getHadFinishOrderC(para);
		myOrderGrabCResp.setMyOrderGrabCModelList(myOrderGrabCModel);
		
		HadFinishOrderResp myOrderGrabCTotal = new HadFinishOrderResp();
		myOrderGrabCTotal = orderGrabDao.getHadFinishOrderCTotalInfo(para);
		myOrderGrabCResp.setYiWanChengTotal(myOrderGrabCTotal.getYiWanChengTotal());
		
		return myOrderGrabCResp;
	} 
}