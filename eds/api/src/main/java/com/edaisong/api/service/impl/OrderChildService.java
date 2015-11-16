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

import com.edaisong.api.dao.inter.IBusinessDao;
import com.edaisong.api.dao.inter.IGroupDao;
import com.edaisong.api.dao.inter.IOrderChildDao;
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
import com.edaisong.entity.OrderChild;
import com.edaisong.entity.OrderRegion;
import com.edaisong.entity.OrderSubsidiesLog;
import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.common.ResponseBase;
import com.edaisong.entity.domain.BusinessModel;
import com.edaisong.entity.domain.GroupModel;
import com.edaisong.entity.req.GroupReq;
import com.edaisong.entity.resp.OrderGrabResp;

import java.util.Date;
@Service
public class OrderChildService implements IOrderChildService {

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
     * 取消订单 （取消前一天快单发单且未被抢单的子订单时）
     * @param 日期
     * @author 胡灵波
     * @Date 2015年11月5日 11:40:37
     * @return
     */
	@Override
	public HttpResultModel<OrderGrabResp> cancelOrderChild(String createtime) {

		HttpResultModel<OrderGrabResp> resp=new HttpResultModel<OrderGrabResp>();
		
		List<Integer> list=orderChildDao.updateCancel(createtime);
		/*for(int i=0;i<list.size();i++)
		{
			int id=list.get(0);
			OrderChild currOcModel= orderChildDao.selectByPrimaryKey(id);			
			BusinessModel businessModel = businessDao.getBusiness(currOcModel
					.getBusinessid());
			
			//更新商户余额 ，可提现余额	
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
			balanceRecord.setRemark("返回商户");
			businessService.updateForWithdrawC(0, balanceRecord);
			
			//更新区余数量
			int orderRegionId=0;
			OrderRegion orderRegion = new OrderRegion();
			int OneId = currOcModel.getOrderRegionOneId();
			int TwoId = currOcModel.getOrderRegionTwoId();
			int orderCount = 1;
			if (TwoId > 0)// 二级
			{
				orderRegion.setId(TwoId);
				orderRegion.setWaitingcount(orderCount);
				orderRegionId = orderRegionDao
						.updateByPrimaryKeySelective(orderRegion);

				orderRegion.setId(OneId);
				orderRegion.setWaitingcount(orderCount);
				orderRegion.setHaschild(true);
				orderRegionId = orderRegionDao
						.updateByPrimaryKeySelective(orderRegion);
			} else {
				orderRegion.setId(OneId);
				orderRegion.setWaitingcount(orderCount);
				orderRegionId = orderRegionDao
						.updateByPrimaryKeySelective(orderRegion);
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
		}*/
		return resp;
	}
	
   
}
