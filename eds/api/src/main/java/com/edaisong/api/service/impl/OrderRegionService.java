package com.edaisong.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edaisong.api.dao.inter.IOrderDao;
import com.edaisong.api.dao.inter.IOrderRegionDao;
import com.edaisong.api.service.inter.IOrderRegionService; 
import com.edaisong.entity.OrderRegion;
import com.edaisong.entity.req.OrderRegionReq;

@Service
public class OrderRegionService implements IOrderRegionService {
	@Autowired
	private IOrderRegionDao orderRegionDao;
	@Autowired
	private IOrderDao orderDao;
	/*
	 * 获取商户的区域信息
	 * wangchao
	 */ 
	@Override
	public List<OrderRegion> getOrderRegion(OrderRegionReq orderRegionReq) {
		return orderRegionDao.getOrderRegion(orderRegionReq);
	}
	@Override
	public Integer updateRegionList(List<OrderRegion> regionList) {
		return orderRegionDao.updateRegionList(regionList);
	}
	@Override
	public Integer insertRegionList(List<OrderRegion> regionList) {
		for (OrderRegion orderRegion : regionList) {
			if (orderRegion.getParentid()>0) {
				orderRegion.setHaschild(false);
			}else {
				long  temp=regionList.stream().filter(t ->t.getParentid().equals(orderRegion.getId())).count();
				if (temp>0) {
					orderRegion.setHaschild(true);
				}
			}
		}
		return orderRegionDao.insertRegionList(regionList);
	}
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public int deleteById(Integer id) {
		Long numCount=orderDao.queryIngOrderByRegionId((long)id);
		if (numCount<=0) {
			OrderRegion oldRegion=orderRegionDao.getById(id);
	
			List<Integer> deleteIds=new ArrayList<Integer>();
			deleteIds.add(id);
			if (oldRegion.getParentid()>0) {//当前删除的是二级区域，则需要判断当前区域的父区域是否还有二级区域
				List<OrderRegion> childList=orderRegionDao.getByParentId(oldRegion.getParentid());
				long  temp=childList.stream().filter(t ->t.getStatus().equals(1)).count();
				if (temp<=1) {
					orderRegionDao.updateHasNoChild(oldRegion.getParentid());
				}
			}else {//当前删除的是一级区域，则需要删除当前区域下的所有二级区域
				List<OrderRegion> childList=orderRegionDao.getByParentId(id);
				for (OrderRegion orderRegion : childList) {
					deleteIds.add(orderRegion.getId());
				}
			}
			return orderRegionDao.deleteByIds(deleteIds);
		}
		return -1;
	} 
}
