package com.edaisong.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IBusinessMessageDao;
import com.edaisong.api.service.inter.IBusinessMessageService;
import com.edaisong.entity.BusinessMessage;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.PagedBusinessMessageReq;

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

	/**
	 * 获得消息列表(分页)
	 * @author pengyi
	 * @date 20150821
	 */
	@Override
	public PagedResponse<BusinessMessage> getMessages(PagedBusinessMessageReq search) {
		return businessMessageDao.getMessages(search);
	}

}
