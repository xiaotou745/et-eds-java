package com.edaisong.api.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IOrderRegionDao;
import com.edaisong.api.service.inter.IOrderRegionService; 
import com.edaisong.entity.OrderRegion;
import com.edaisong.entity.req.OrderRegionReq;

@Service
public class OrderRegionService implements IOrderRegionService {

	private IOrderRegionDao iOrderRegionDao;
	/*
	 * 获取商户的区域信息
	 * wangchao
	 */ 
	@Override
	public List<OrderRegion> getOrderRegion(OrderRegionReq orderRegionReq) {
		return iOrderRegionDao.getOrderRegion(orderRegionReq);
	} 
}
