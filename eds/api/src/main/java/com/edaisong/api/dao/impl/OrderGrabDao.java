package com.edaisong.api.dao.impl;

import java.util.List;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IOrderGrabDao;
import com.edaisong.entity.OrderGrab;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.FastOrderMapDetail;
import com.edaisong.entity.domain.FastOrderModel;
import com.edaisong.entity.domain.OrderGrabDetailModel;
import com.edaisong.entity.req.OrderDetailCReq;
import com.edaisong.entity.req.PagedFastOrderSearchReq;

public class OrderGrabDao extends DaoBase implements IOrderGrabDao {

	@Override
	public int deleteById(Long id) {
		return getMasterSqlSessionUtil().delete(
				"com.edaisong.api.dao.inter.IOrderGrabDao.deleteById", id);
	}

	@Override
	public int insert(OrderGrab record) {
		return getMasterSqlSessionUtil().insert(
				"com.edaisong.api.dao.inter.IOrderGrabDao.insert", record);
	}

	@Override
	public OrderGrab selectById(Long id) {
		return getReadOnlySqlSessionUtil().selectOne(
				"com.edaisong.api.dao.inter.IOrderGrabDao.selectById", id);
	}

	@Override
	public PagedResponse<FastOrderModel> query(PagedFastOrderSearchReq req) {
		return getReadOnlySqlSessionUtil().selectPageList(
				"com.edaisong.api.dao.inter.IOrderGrabDao.query", req);
	}

	@Override
	public List<FastOrderMapDetail> getMapDetailById(Long id) {
		return getReadOnlySqlSessionUtil()
				.selectList(
						"com.edaisong.api.dao.inter.IOrderGrabDao.getMapDetailById",
						id);
	}
	@Override
	public OrderGrabDetailModel getMyOrderDetailC(
			OrderDetailCReq orderDetailCReq) {
		return getReadOnlySqlSessionUtil().selectOne(
				"com.edaisong.api.dao.inter.IOrderGrabDao.getMyOrderDetailC", orderDetailCReq);
	}
}
