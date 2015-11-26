package com.edaisong.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IOrderRegionLogDao;
import com.edaisong.entity.OrderRegionLog;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.PagedBusinessMessageReq;

@Repository
public class OrderRegionLogDao extends DaoBase implements IOrderRegionLogDao {

	@Override
	public int insert(OrderRegionLog record) {
		return getMasterSqlSessionUtil().insert(
				"IOrderRegionLogDao.insert", record);
	}

	@Override
	public List<OrderRegionLog> getList(Long businessId) {
		return getReadOnlySqlSessionUtil().selectList(
				"IOrderRegionLogDao.getList",
				businessId);
	}

	@Override
	public PagedResponse<OrderRegionLog> getPagedList(
			PagedBusinessMessageReq req) {
		return getReadOnlySqlSessionUtil().selectPageList("IOrderRegionLogDao.getPagedList", req);
	}

}
