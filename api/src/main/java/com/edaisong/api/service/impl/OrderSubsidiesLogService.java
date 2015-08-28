package com.edaisong.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IOrderSubsidiesLogDao;
import com.edaisong.api.service.inter.IOrderSubsidiesLogService;
import com.edaisong.entity.OrderSubsidiesLog;
@Service
public class OrderSubsidiesLogService implements IOrderSubsidiesLogService {
	@Autowired
	private IOrderSubsidiesLogDao orderSubsidiesLogDao;
	 /**
     * 获取订单操作日志
     * @author CaoHeYang
     * @param orderId 订单id
     * @date 20150827  
     * @return
     */
	@Override
	public List<OrderSubsidiesLog> GetOrderOptionLog(int orderId) {
		// TODO Auto-generated method stub
		return orderSubsidiesLogDao.GetOrderOptionLog(orderId);
	}
}
