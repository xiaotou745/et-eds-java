package com.edaisong.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dal.dao.inter.IMessageDao;
import com.edaisong.api.service.inter.IMessageService;
import com.edaisong.entity.Message;
@Service
public class MessageService implements IMessageService {
	@Autowired
	private IMessageDao iMessageDao;

	@Override
	public List<Message> getMessageList() {
		return iMessageDao.getMessageList();
	}

}
