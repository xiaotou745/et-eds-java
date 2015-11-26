package com.edaisong.api.dao.impl;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IBusinessWithdrawFormDao;
import com.edaisong.entity.BusinessWithdrawForm;
import com.edaisong.entity.domain.BusinessWithdrawFormModel;

@Repository
public class BusinessWithdrawFormDao extends DaoBase implements IBusinessWithdrawFormDao{
	@Override
	public int deleteByPrimaryKey(Long id) {
		throw new NotImplementedException();
	}

	@Override
	public int insert(BusinessWithdrawForm record) {
		throw new NotImplementedException();
	}

	@Override
	public int insertSelective(BusinessWithdrawForm record) {
		throw new NotImplementedException();
	}

	@Override
	public BusinessWithdrawForm selectByPrimaryKey(Long id) {
		throw new NotImplementedException();
	}

	@Override
	public int updateByPrimaryKeySelective(BusinessWithdrawForm record) {
		throw new NotImplementedException();
	}

	@Override
	public int updateByPrimaryKey(BusinessWithdrawForm record) {
		throw new NotImplementedException();
	}

	@Override
	public BusinessWithdrawFormModel getBusinessWithdrawListById(String withwardId) {
		return getReadOnlySqlSessionUtil().selectOne("IBusinessWithdrawFormDao.getBusinessWithdrawListById", withwardId);
	}
}
