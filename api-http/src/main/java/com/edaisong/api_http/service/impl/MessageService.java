package com.edaisong.api_http.service.impl;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import com.edaisong.api_http.entity.ResultModel;
import com.edaisong.api_http.service.inter.IMessageService;
import com.edaisong.entity.resp.MessageResp;

public class MessageService implements IMessageService {

	/**
	 * B端商户登陆后获取顶端未读公告
	 * @author CaoHeYang
	 * @date 20150909
	 * @param data 
	 * @return
	 */
	@Override
	public ResultModel<MessageResp> newMessageB(String data) {
		ResultModel<MessageResp> model=new ResultModel<MessageResp>();
		return model;
	}

	/**
	 * B端商户登陆后获取顶端未读公告
	 * @author CaoHeYang
	 * @date 20150909
	 * @param data 
	 * @return
	 */
	@Override
	public ResultModel<MessageResp> newMessageC(String data) {
		ResultModel<MessageResp> model=new ResultModel<MessageResp>();
		return model;
	}

}
