package com.edaisong.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IClienterMessageDao;
import com.edaisong.api.service.inter.IClienterMessageService;
import com.edaisong.entity.ClienterMessage;
/**
 * 骑士站内消息
 * @author CaoHeYang
 * @date 20150909
 */
@Service
public class ClienterMessageService implements IClienterMessageService {
	@Autowired
 private IClienterMessageDao clienterMessageDao;
	/**
	 * 获取最新消息
	 * @author CaoHeYang
	 * @param clienterId
	 * @date 20150909
	 * @return
	 */
	@Override
	public ClienterMessage getLatestMessage(int clienterId) {
		return clienterMessageDao.getLatestMessage(clienterId);
	}

}
