package com.edaisong.taobaoopenapi.service.impl;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.edaisong.core.consts.TaoBaoConsts;
import com.edaisong.core.security.AES;
import com.edaisong.core.util.HttpUtil;
import com.edaisong.core.util.PropertyUtils;
import com.edaisong.taobaoopenapi.service.inter.ITmcControlHttpService;
import com.taobao.api.internal.tmc.Message;
import com.taobao.api.internal.tmc.MessageHandler;
import com.taobao.api.internal.tmc.MessageStatus;
import com.taobao.api.internal.tmc.TmcClient;
import com.taobao.top.link.LinkException;

/**
 * 
 * @author CaoHeYang
 *
 */
@Service
public class TmcControlHttpService implements ITmcControlHttpService {
	/**
	 * tmc 获取通知
	 * 
	 * @throws LinkException
	 */
	@Override
	public void main() throws LinkException {
		TmcClient client = new TmcClient(TaoBaoConsts.Uri, TaoBaoConsts.AppKey, TaoBaoConsts.AppSecret, TaoBaoConsts.GroupName);
		client.setMessageHandler(new MessageHandler() {
			@Override
			public void onMessage(Message message, MessageStatus status) throws Exception {
				if (message.getTopic() == TaoBaoConsts.OrderDispatch) {

				} else if (message.getTopic() == TaoBaoConsts.OrderClose) {
					orderClose(message.getContent());
				} else if (message.getTopic() == TaoBaoConsts.OuterOrderDispatch) {

				}
				String msg = message.getContent();
				System.out.println(msg);
			}
		});
		client.connect();
	}

	/**
	 * 订单指派通知消息(TMC)
	 * 
	 * @author CaoHeYang
	 * @date 20151113
	 */
	private void orderDispatch() {

	}

	/**
	 * 订单关闭通知消息(TMC)
	 * 
	 * @author CaoHeYang
	 * @date 20151113
	 */
	private void orderClose(String data) {
	    String r= HttpUtil.sendPost(PropertyUtils.getProperty("TaoBaoCloseOrder"), "data="+AES.aesEncrypt(data));
	    //TODo 不成功时写日志
	}

	/**
	 * 外部订单指派通知消息(TMC)
	 * 
	 * @author CaoHeYang
	 * @date 20151113
	 */
	private void outerOrderDispatch() {

	}
}
