package com.edaisong.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository; 

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IOrderRegionDao;
import com.edaisong.entity.OrderRegion;
@Repository
public class OrderRegionDao extends DaoBase implements IOrderRegionDao {

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(OrderRegion record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(OrderRegion record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public OrderRegion selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(OrderRegion record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(OrderRegion record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<OrderRegion> getOrderRegion(Integer businessId) {
		return getReadOnlySqlSessionUtil().selectList(
				"com.edaisong.api.dao.inter.IOrderRegionDao.getOrderRegion", businessId);
	}

}
