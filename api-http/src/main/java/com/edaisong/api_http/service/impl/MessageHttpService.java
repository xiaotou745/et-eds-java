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
import com.edaisong.entity.req.NewMessageBReq;
import com.edaisong.entity.req.NewMessageCReq;
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
	 * @param para
	 * @return
	 */
	@Override
	public ResultModel<MessageResp> newMessageB(NewMessageBReq para) {
		ResultModel<MessageResp> returnmodel = new ResultModel<MessageResp>();
		BusinessMessage message = businessMessageService.getLatestMessage(para.getBusinessId());
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
	 * C端商户登陆后获取顶端未读公告
	 * 
	 * @author CaoHeYang
	 * @date 20150909
	 * @param  para
	 * @return
	 */
	@Override
	public ResultModel<MessageResp> newMessageC(NewMessageCReq para) {
		ResultModel<MessageResp> returnmodel = new ResultModel<MessageResp>();
		ClienterMessage message = clienterMessageService.getLatestMessage(para.getClienterId());
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
