package com.edaisong.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IOrderGrabDao;
import com.edaisong.entity.OrderGrab;
import com.edaisong.entity.domain.OrderGrabDetailModel;
import com.edaisong.entity.req.OrderDetailCReq;
@Repository
public class OrderGrabDao extends DaoBase implements IOrderGrabDao {

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(OrderGrab record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(OrderGrab record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public OrderGrab selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(OrderGrab record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(OrderGrab record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public OrderGrabDetailModel getMyOrderDetailC(
			OrderDetailCReq orderDetailCReq) {
		return getReadOnlySqlSessionUtil().selectOne(
				"com.edaisong.api.dao.inter.IOrderGrabDao.getMyOrderDetailC", orderDetailCReq);
	}

}
