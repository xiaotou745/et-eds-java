package com.edaisong.api.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edaisong.api.dao.inter.IBusinessBalanceRecordDao;
import com.edaisong.api.dao.inter.IGroupBusinessDao;
import com.edaisong.api.dao.inter.IGroupBusinessRechargeDao;
import com.edaisong.api.service.inter.IGroupBusinessRechargeService;
import com.edaisong.core.enums.BusinessBalanceRecordRecordType;
import com.edaisong.entity.BusinessBalanceRecord;
import com.edaisong.entity.GroupBusiness;
import com.edaisong.entity.GroupBusinessRecharge;

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
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public int recharge(GroupBusinessRecharge record,String remark,String operatorName) {
		if (record.getPayamount()<1||record.getPayamount()>100000) {
			return -1;
		}
		
		int result= groupBusinessRechargeDao.update(record);
		if (result>0) {
			GroupBusiness oldGroupBusiness=groupBusinessDao.select(record.getGroupbusinessid());
			int rs=groupBusinessDao.recharge(record.getGroupbusinessid(), record.getPayamount());
			if (rs>0) {
				GroupBusinessRecharge recharge=	groupBusinessRechargeDao.getByOrderNo(record.getOrderno());
				BusinessBalanceRecord rechargeRecord=new BusinessBalanceRecord();
				rechargeRecord.setBusinessid(0);
				rechargeRecord.setAmount(record.getPayamount());
				rechargeRecord.setStatus((short)1);
				rechargeRecord.setBalance(0d);
				rechargeRecord.setRecordtype((short)BusinessBalanceRecordRecordType.Recharge.value());
				rechargeRecord.setOperator(operatorName);
				rechargeRecord.setOperatetime(new Date());
				rechargeRecord.setWithwardid((long)recharge.getId());
				rechargeRecord.setRelationno(record.getOrderno());
				rechargeRecord.setRemark(remark);
				rechargeRecord.setIsenable((short)1);
				rechargeRecord.setGroupid(record.getGroupbusinessid());
				rechargeRecord.setGroupafterbalance(oldGroupBusiness.getAmount()+record.getPayamount());
				rechargeRecord.setGroupbeforebalance(oldGroupBusiness.getAmount());
				businessBalanceRecordDao.insert(rechargeRecord);
			}
		}
		return result;
	}


}
