package com.edaisong.api_http.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.service.inter.IBusinessMessageService;
import com.edaisong.api.service.inter.IClienterMessageService;
import com.edaisong.api_http.entity.ResultModel;
import com.edaisong.api_http.service.inter.IMessageHttpService;
import com.edaisong.core.enums.returnenums.NewMessageReturnEnum;
import com.edaisong.entity.BusinessMessage;
import com.edaisong.entity.ClienterMessage;
import com.edaisong.entity.resp.MessageResp;

@Service
public class MessageHttpService implements IMessageHttpService {

	@Autowired
	private IBusinessMessageService businessMessageService;
	@Autowired
	private IClienterMessageService clienterMessageService;

	// @Autowired
	// private IClienterMessageService clienterMessageService;
	/**
	 * B端商户登陆后获取顶端未读公告
	 * 
	 * @author CaoHeYang
	 * @date 20150909
	 * @param data
	 * @return
	 */
	@Override
	public ResultModel<MessageResp> newMessageB(String data) {
		ResultModel<MessageResp> returnmodel = new ResultModel<MessageResp>();
		int businessId = 36;
		BusinessMessage message = businessMessageService.getLatestMessage(businessId);
		if (message.getIsread() == 0) { // 有未读消息
			MessageResp messageResp = new MessageResp();
			messageResp.setId(message.getId());
			messageResp.setContent(message.getContent());
			returnmodel.setResult(messageResp);
		}else {
			returnmodel.setStatus(NewMessageReturnEnum.NoNews.value());
			returnmodel.setMessage(NewMessageReturnEnum.NoNews.desc());
		}
		return returnmodel;
	}

	/**
	 * B端商户登陆后获取顶端未读公告
	 * 
	 * @author CaoHeYang
	 * @date 20150909
	 * @param data
	 * @return
	 */
	@Override
	public ResultModel<MessageResp> newMessageC(String data) {
		ResultModel<MessageResp> returnmodel = new ResultModel<MessageResp>();
		int clienterId = 2090;
		ClienterMessage message = clienterMessageService.getLatestMessage(clienterId);
		if (message.getIsread() == 0) { // 有未读消息
			MessageResp messageResp = new MessageResp();
			messageResp.setId(message.getId());
			messageResp.setContent(message.getContent());
			returnmodel.setResult(messageResp);
		}else {
			returnmodel.setStatus(NewMessageReturnEnum.NoNews.value());
			returnmodel.setMessage(NewMessageReturnEnum.NoNews.desc());
		}
		return returnmodel;
	}

}
