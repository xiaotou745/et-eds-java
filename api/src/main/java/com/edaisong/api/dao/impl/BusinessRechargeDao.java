package com.edaisong.api.dao.impl;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IBusinessRechargeDao;
import com.edaisong.entity.BusinessRecharge;

public class BusinessRechargeDao extends DaoBase implements IBusinessRechargeDao{

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(BusinessRecharge record) {
		return getMasterSqlSessionUtil().insert("com.edaisong.api.dal.dao.inter.IBusinessRechargeDao.insert",record);
	}

	@Override
	public int insertSelective(BusinessRecharge record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BusinessRecharge selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(BusinessRecharge record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(BusinessRecharge record) {
		// TODO Auto-generated method stub
		return 0;
	}

}
