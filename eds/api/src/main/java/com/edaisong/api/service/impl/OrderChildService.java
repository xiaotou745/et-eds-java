package com.edaisong.api.service.impl;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.edaisong.api.common.TransactionalRuntimeException;
import com.edaisong.api.dao.inter.IBusinessDao;
import com.edaisong.api.dao.inter.IGroupDao;
import com.edaisong.api.dao.inter.IOrderChildDao;
import com.edaisong.api.dao.inter.IOrderDao;
import com.edaisong.api.dao.inter.IOrderRegionDao;
import com.edaisong.api.dao.inter.IOrderSubsidiesLogDao;
import com.edaisong.api.service.inter.IBusinessService;
import com.edaisong.api.service.inter.IGroupService;
import com.edaisong.api.service.inter.IOrderChildService;
import com.edaisong.core.enums.BusinessBalanceRecordRecordType;
import com.edaisong.core.enums.BusinessBalanceRecordStatus;
import com.edaisong.core.enums.OrderStatus;
import com.edaisong.core.enums.SuperPlatform;
import com.edaisong.core.enums.TaskStatus;
import com.edaisong.entity.BusinessBalanceRecord;
import com.edaisong.entity.Group;
import com.edaisong.entity.Order;
import com.edaisong.entity.OrderChild;
import com.edaisong.entity.OrderGrab;
import com.edaisong.entity.OrderRegion;
import com.edaisong.entity.OrderSubsidiesLog;
import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.common.ResponseBase;
import com.edaisong.entity.domain.BusinessModel;
import com.edaisong.entity.domain.GroupModel;
import com.edaisong.entity.domain.OrderListModel;
import com.edaisong.entity.req.GroupReq;
import com.edaisong.entity.req.OrderChildCancelReq;
import com.edaisong.entity.resp.OrderGrabResp;

import java.util.Date;
@Service
public class OrderChildService implements IOrderChildService {

	@Autowired
	private IOrderDao orderDao;
	
	@Autowired
	private IOrderChildDao orderChildDao;
	
	@Autowired
	private IBusinessDao businessDao;
	
	@Autowired
	private IBusinessService businessService;
	
	@Autowired
	private IOrderSubsidiesLogDao orderSubsidiesLogDao;
	
	@Autowired
	private IOrderRegionDao orderRegionDao;
	

    /**
     * 取消订单 （取消前一天智能调度发单且未被抢单的子订单时）
     * @param 日期
     * @author 胡灵波
     * @Date 2015年11月5日 11:40:37
     * @return
     */
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	@Override
	public HttpResultModel<OrderGrabResp> cancelOrderChild(OrderChildCancelReq req) {

		HttpResultModel<OrderGrabResp> resp=new HttpResultModel<OrderGrabResp>();
		
		List<Integer> list=orderChildDao.updateCancel(req);

		for(int i=0;i<list.size();i++)
		{
			int id=list.get(i);		
	
			//更新订单表
			OrderChild currOcModel= orderChildDao.selectByPrimaryKey(id);	
			OrderListModel selectOLModel=orderDao.getOrderWriteByNoId(null, currOcModel.getOrderid());
			int count=selectOLModel.getOrderCount();		
			Order updateOModel=new Order();		
			updateOModel.setId(currOcModel.getOrderid());				
			updateOModel.setOrdercount(count-1);
			int updateOrderGrabId= orderDao.updateByPrimaryKeySelective(updateOModel);
			if (updateOrderGrabId <= 0) {				
				throw new TransactionalRuntimeException("更新订单主表状诚错误");
			}				
			
			//更新商户余额 ，可提现余额	
			BusinessModel businessModel = businessDao.getBusiness((long)currOcModel
					.getBusinessid());
			BusinessBalanceRecord balanceRecord = new BusinessBalanceRecord();
			balanceRecord.setBusinessid(currOcModel.getBusinessid());
			if (businessModel.getGroupBusinessID() > 0) {
				balanceRecord.setAmount(0d);
				balanceRecord.setGroupamount(currOcModel.getSettleMoney());
				balanceRecord.setGroupid(businessModel.getGroupBusinessID());
				balanceRecord.setBalance(businessModel.getBalanceprice());
			} else {
				balanceRecord.setAmount(currOcModel.getSettleMoney());
				balanceRecord.setGroupamount(0);
				balanceRecord.setGroupid(0);
			}

			balanceRecord.setStatus((short) BusinessBalanceRecordStatus.Success
					.value());
			balanceRecord
					.setRecordtype((short) BusinessBalanceRecordRecordType.CancelOrder
							.value());
			balanceRecord.setOperator(businessModel.getName());
			balanceRecord.setWithwardid((long) id);
			balanceRecord.setRelationno("");
			balanceRecord.setRemark("系统取消订单返还配送费支出金额");
			businessService.updateForWithdrawC(0, balanceRecord);
			
			//更新区余数量
			int orderRegionId=0;
			OrderRegion orderRegion = new OrderRegion();
			int OneId = currOcModel.getOrderRegionOneId();
			int TwoId = currOcModel.getOrderRegionTwoId();
			int orderCount = 1;
			if (TwoId > 0)// 二级
			{
				//更新数量			
				OrderRegion orModelTwo=new OrderRegion();
				orModelTwo.setId(TwoId);
				orModelTwo.setWaitingcount(-orderCount);			
				int orderRegionTwoId=orderRegionDao.updateCountByPrimaryKeySelective(orModelTwo);
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
			
				int orderRegionOneId=orderRegionDao.updateCountByPrimaryKeySelective(orModelOne);		
				if (orderRegionOneId <= 0) {
					throw new TransactionalRuntimeException("更新一级区域错误");
				}	
			} else {
				OrderRegion orModelOne=new OrderRegion();
				orModelOne.setId(OneId);
				orModelOne.setWaitingcount(-orderCount);				
				int orderRegionOneId=orderRegionDao.updateCountByPrimaryKeySelective(orModelOne);	
				if (orderRegionOneId <= 0) {
					throw new TransactionalRuntimeException("更新一级区域错误");
				}
			}
			//写入订单日志
			OrderSubsidiesLog record = new OrderSubsidiesLog();
			record.setOrderid(id);
			record.setOrderstatus(OrderStatus.Cancel.value());
			record.setOptid(businessModel.getId());
			record.setPrice(0d);
			record.setOptname(businessModel.getName());
			record.setRemark(TaskStatus.CancelOrder.desc());
			record.setPlatform(SuperPlatform.NewSystemCancel.value());
			int ordersubsidiesId = orderSubsidiesLogDao.insert(record);
			if (ordersubsidiesId <= 0) {
				throw new TransactionalRuntimeException("记录订单日志错误");
			}
		}
		
		return resp;

	}
   
}
