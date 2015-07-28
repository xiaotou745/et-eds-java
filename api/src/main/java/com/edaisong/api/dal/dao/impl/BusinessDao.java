package com.edaisong.api.dal.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.edaisong.api.dal.dao.inter.IBusinessDao;
import com.edaisong.entity.Business;
@Repository
public class BusinessDao extends DaoBase implements IBusinessDao{

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Business record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(Business record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Business selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Business record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Business record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Business> getBusinessList() {
		IBusinessDao iBusinessDao=getReadOnlySqlSessionUtil().getMapper(IBusinessDao.class);
		return iBusinessDao.getBusinessList();
	}

}
