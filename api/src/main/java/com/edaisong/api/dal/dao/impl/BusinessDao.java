package com.edaisong.api.dal.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.edaisong.api.dal.dao.inter.IBusinessDao;
import com.edaisong.entity.Business;
import com.edaisong.entity.domain.BusinessModel;
import com.edaisong.entity.domain.GlobalConfigModel;
import com.edaisong.entity.req.BusinessReq;
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
	public List<BusinessModel> getBusinessList(BusinessReq req) {
//		Map<String, Object> paramMap = new HashMap<>();
//		paramMap.put("groupid", id);
		List<BusinessModel> model = getReadOnlySqlSessionUtil().selectList(
				"com.edaisong.api.dal.dao.inter.IBusinessDao.getBusinessList", req);
		return model;
	}

}
