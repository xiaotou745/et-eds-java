package com.edaisong.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IBusinessMessageDao;
import com.edaisong.api.service.inter.IBusinessMessageService;
import com.edaisong.entity.BusinessMessage;

@Service
public class BusinessMessageService implements IBusinessMessageService{

	@Autowired
	private IBusinessMessageDao businessMessageDao;
	
	/**
	 * 获得最新一条商户消息
	 * @author pengyi
	 * @date 20150818
	 */
	@Override
	public BusinessMessage getLatestMessage(int businessId) {
		return businessMessageDao.getLatestMessage(businessId);
	}

}
