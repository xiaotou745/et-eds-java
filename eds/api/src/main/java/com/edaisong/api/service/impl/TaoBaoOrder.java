package com.edaisong.api.service.impl;

import com.edaisong.api.service.inter.ITaoBaoOrder;
import com.taobao.api.internal.tmc.Message;
import com.taobao.api.internal.tmc.MessageHandler;
import com.taobao.api.internal.tmc.MessageStatus;
import com.taobao.api.internal.tmc.TmcClient;
import com.taobao.top.link.LinkException;

public class TaoBaoOrder implements ITaoBaoOrder {

	/**
	 * 淘点点发单
	 * 
	 * @date 2015年11月11日 13:35:57
	 * @author haichao
	 * @return
	 * @throws LinkException
	 * */
	@Override
	public void releaseOrder() throws LinkException {
		TmcClient client = new TmcClient("ws://mc.api.taobao.com/", "23264088",
				"ce0f4f3668d039b6db74aa82904cca43", "default");
		client.setMessageHandler(new MessageHandler() {
			@Override
			public void onMessage(Message message, MessageStatus status)
					throws Exception {
				// 处理消息操作
				String msg = message.getContent();
				System.out.println(msg);
			}
		});
		client.connect();
	}

}
