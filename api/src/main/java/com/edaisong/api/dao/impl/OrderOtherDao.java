package com.edaisong.api.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IOrderOtherDao;
import com.edaisong.entity.OrderOther;
import com.edaisong.entity.domain.OrderDetailBusiness;

@Repository
public class OrderOtherDao extends DaoBase implements IOrderOtherDao   {

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(OrderOther record) {
		// TODO Auto-generated method stub
		return getMasterSqlSessionUtil().insert(
				"com.edaisong.api.dal.dao.inter.IOrderOtherDao.insertSelective", record);		         
	}

	@Override
	public int insertSelective(OrderOther record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public OrderOther selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(OrderOther record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(OrderOther record) {
		// TODO Auto-generated method stub
		return 0;
	}


  

}
