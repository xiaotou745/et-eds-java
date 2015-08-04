package com.edaisong.api.dal.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.edaisong.api.dal.dao.inter.IBusinessGroupDao;
import com.edaisong.entity.BusinessGroup;
import com.edaisong.entity.domain.GlobalConfigModel;

@Repository
public class BusinessGroupDao extends DaoBase implements IBusinessGroupDao{

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(BusinessGroup record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(BusinessGroup record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BusinessGroup selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(BusinessGroup record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(BusinessGroup record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BusinessGroup> getBusinessGroupList() {
		String statement = "com.edaisong.api.dal.dao.inter.IBusinessGroupDao.getBusinessGroupList";
		List<BusinessGroup> model = getReadOnlySqlSessionUtil().selectList(
				statement);
		return model;
	}

}
