package com.edaisong.api.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edaisong.api.dao.inter.IBusinessBalanceRecordDao;
import com.edaisong.api.dao.inter.IGroupBusinessDao;
import com.edaisong.api.dao.inter.IGroupBusinessRechargeDao;
import com.edaisong.api.service.inter.IGroupBusinessRechargeService;
import com.edaisong.core.enums.BusinessBalanceRecordRecordType;
import com.edaisong.core.enums.BusinessBalanceRecordStatus;
import com.edaisong.entity.BusinessBalanceRecord;
import com.edaisong.entity.GroupBusiness;
import com.edaisong.entity.GroupBusinessRecharge;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.GroupBusinessBalance;
import com.edaisong.entity.req.PagedGroupBusinessRechargeReq;

@Service
public class GroupBusinessRechargeService implements
		IGroupBusinessRechargeService {
	@Autowired
	private IGroupBusinessRechargeDao groupBusinessRechargeDao;
	@Autowired
	private IGroupBusinessDao groupBusinessDao;
	@Autowired
	private IBusinessBalanceRecordDao businessBalanceRecordDao;

	@Override
	public int insert(GroupBusinessRecharge record) {
		return groupBusinessRechargeDao.insert(record);
	}

	@Override
	public GroupBusinessRecharge getByOrderNo(String orderNO) {
		return groupBusinessRechargeDao.getByOrderNo(orderNO);
	}
	/**
	 * 充值回调
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public int recharge(GroupBusinessRecharge record) {
		//1.更新单子状态
		int result= groupBusinessRechargeDao.update(record);
		if (result>0) {
			//2.查询商家信息
			GroupBusinessRecharge recharge=	groupBusinessRechargeDao.getByOrderNo(record.getOrderno());
			//3.更新集团金额
			int rs=groupBusinessDao.recharge(recharge.getGroupbusinessid(), recharge.getPayamount());
			//4.插入集团流水
			if (rs>0) {
				GroupBusiness groupBusiness=groupBusinessDao.select(recharge.getGroupbusinessid());
				BusinessBalanceRecord rechargeRecord=new BusinessBalanceRecord();
				rechargeRecord.setBusinessid(0);
				rechargeRecord.setAmount(groupBusiness.getAmount());
				rechargeRecord.setGroupamount(recharge.getPayamount());
				rechargeRecord.setStatus((short)BusinessBalanceRecordStatus.Success.value());
				rechargeRecord.setRecordtype((short)BusinessBalanceRecordRecordType.Recharge.value());
				rechargeRecord.setOperator(groupBusiness.getGroupbusiname());
				rechargeRecord.setWithwardid((long)recharge.getId());
				rechargeRecord.setRelationno(recharge.getOrderno());
				rechargeRecord.setRemark("易代送商家中心集团充值："+groupBusiness.getGroupbusiname());
				rechargeRecord.setGroupid(recharge.getGroupbusinessid());
				rechargeRecord.setGroupafterbalance(groupBusiness.getAmount());
				rechargeRecord.setBalance(0d);
				businessBalanceRecordDao.groupInsert(rechargeRecord);
			}
		}
		return result;
	}

	@Override
	public List<GroupBusinessBalance> getGroupBalance(int groupBusinessID) {
		return groupBusinessRechargeDao.getGroupBalance(groupBusinessID);
	}

	@Override
	public PagedResponse<GroupBusinessRecharge> getGroupBusinessRechargelist(
			PagedGroupBusinessRechargeReq search) {
		return groupBusinessRechargeDao.getGroupBusinessRechargelist(search);
	
	}
	

}
