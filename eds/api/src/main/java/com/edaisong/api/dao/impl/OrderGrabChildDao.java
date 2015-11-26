package com.edaisong.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IOrderGrabChildDao;
import com.edaisong.entity.OrderChild;
import com.edaisong.entity.OrderGrabChild;

@Repository
public class OrderGrabChildDao extends DaoBase implements IOrderGrabChildDao {

	@Override
	public int insertList(List<OrderGrabChild> record) {
		return getMasterSqlSessionUtil().insert(
				"IOrderGrabChildDao.insertList", record);
	}	
	@Override
	public int updateByPrimaryKeySelective(OrderGrabChild record) {
		return getMasterSqlSessionUtil().update(
				"IOrderGrabChildDao.updateByPrimaryKeySelective", record);
	}
	
	@Override
	public int updateByGraborderidSelective(OrderGrabChild record) {
		return getMasterSqlSessionUtil().update(
				"IOrderGrabChildDao.updateByGraborderidSelective", record);
	}
	


	@Override
	public List<OrderGrabChild> selectByGrabOrderId(Long grabOrderId) {
		return getReadOnlySqlSessionUtil()
				.selectList(
						"IOrderGrabChildDao.selectByGrabOrderId",
						grabOrderId);
	}
	
	@Override
	public OrderGrabChild selectTop1ByGrabOrderId(Long grabOrderId) {
		return getMasterSqlSessionUtil().selectOne(
						"IOrderGrabChildDao.selectTop1ByGrabOrderId",
						grabOrderId);
	}
	
	@Override
	public List<OrderGrabChild>  selectCompletedOrderByGrabOrderId(Long grabOrderId) {
		return getMasterSqlSessionUtil().selectList(
						"IOrderGrabChildDao.selectCompletedOrderByGrabOrderId",
						grabOrderId);
	}
	
	@Override
	public OrderGrabChild selectByPrimaryKey(Integer id) {
		return getMasterSqlSessionUtil().selectOne(
				"IOrderGrabChildDao.selectByPrimaryKey", id);
	}
	
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
	public int updateByPrimaryKey(OrderGrabChild record) {
			return 0;
	}	


	
}
