package com.edaisong.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IGroupBusinessRechargeDao;
import com.edaisong.entity.GroupBusinessRecharge;
import com.edaisong.entity.domain.GroupBusinessBalance;

@Repository
public class GroupBusinessRechargeDao extends DaoBase implements
		IGroupBusinessRechargeDao {

	@Override
	public int insert(GroupBusinessRecharge record) {
		return getMasterSqlSessionUtil().insert(
				"IGroupBusinessRechargeDao.insert",
				record);
	}

	@Override
	public int update(GroupBusinessRecharge record) {
		return getMasterSqlSessionUtil().update(
				"IGroupBusinessRechargeDao.update",
				record);
	}

	@Override
	public GroupBusinessRecharge getByOrderNo(String orderNO) {
		return getMasterSqlSessionUtil().selectOne(
				"IGroupBusinessRechargeDao.getByOrderNo",
				orderNO);
	}

	@Override
	public List<GroupBusinessBalance> getGroupBalance(int groupBusinessID) {
		return getMasterSqlSessionUtil().selectList("IGroupBusinessRechargeDao.getGroupBalance", groupBusinessID);
	}

}
