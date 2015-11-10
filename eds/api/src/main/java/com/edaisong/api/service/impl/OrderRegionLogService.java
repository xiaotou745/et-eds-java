package com.edaisong.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edaisong.api.dao.inter.IOrderRegionLogDao;
import com.edaisong.api.service.inter.IOrderRegionLogService;
import com.edaisong.entity.OrderRegionLog;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.PagedBusinessMessageReq;
@Repository
public class OrderRegionLogService implements IOrderRegionLogService {

	@Autowired
	private IOrderRegionLogDao orderRegionLogDao;
	@Override
	public int insert(OrderRegionLog record) {
return orderRegionLogDao.insert(record);
	}

	@Override
	public List<OrderRegionLog> getList(Long businessId) {
return orderRegionLogDao.getList(businessId);
	}

	@Override
	public PagedResponse<OrderRegionLog> getPagedList(
			PagedBusinessMessageReq req) {
		return orderRegionLogDao.getPagedList(req);
	}

}
