package com.edaisong.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IOrderGrabChildDao;
import com.edaisong.entity.OrderChild;
import com.edaisong.entity.OrderGrabChild;
@Repository
public class OrderGrabChildDao extends DaoBase implements IOrderGrabChildDao{

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(OrderGrabChild record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(OrderGrabChild record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public OrderGrabChild selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(OrderGrabChild record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(OrderGrabChild record) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int updateList(List<Integer> ids)
	{
		return 0;
	}
	

	@Override
	public int insertList(List<OrderGrabChild> record) {
		return getMasterSqlSessionUtil().insert(
				"com.edaisong.api.dao.inter.IOrderGrabChildDao.insertList", record);
	}
}
