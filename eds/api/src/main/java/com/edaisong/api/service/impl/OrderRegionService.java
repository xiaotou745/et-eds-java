package com.edaisong.api.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IOrderRegionDao;
import com.edaisong.api.service.inter.IOrderRegionService;
import com.edaisong.entity.OrderRegion;

@Service
public class OrderRegionService implements IOrderRegionService {

	private IOrderRegionDao iOrderRegionDao;
	/*
	 * 获取商户的区域信息
	 * wangchao
	 */
	@Override
	public List<OrderRegion> getOrderRegion(Integer businessId) {
		 
		return iOrderRegionDao.getOrderRegion(businessId);
	}

}
