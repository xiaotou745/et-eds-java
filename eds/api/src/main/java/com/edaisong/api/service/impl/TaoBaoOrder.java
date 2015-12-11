package com.edaisong.api.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.edaisong.api.service.inter.ITaoBaoOrder;
import com.edaisong.entity.taobao.req.TaoBaoAccessTokenReq;
import com.taobao.api.internal.tmc.Message;
import com.taobao.api.internal.tmc.MessageHandler;
import com.taobao.api.internal.tmc.MessageStatus;
import com.taobao.api.internal.tmc.TmcClient;
import com.taobao.api.internal.util.WebUtils;
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

	@Override
	public String getAccessToken(TaoBaoAccessTokenReq taoBaoAccessTokenReq) {
		
		String url="https://oauth.taobao.com/token"; 
	    Map<String,String> props=new HashMap<String,String>();
	 
	    
	      props.put("grant_type",taoBaoAccessTokenReq.getGrant_type());
	 
	      /*测试时，需把test参数换成自己应用对应的值*/
	 
	      props.put("code","test");
	 
	      props.put("client_id","test");
	 
	      props.put("client_secret","test");
	 
	      props.put("redirect_uri","http://www.test.com");
	 
	      props.put("view","web");
	 
	      String s="";
	 
	      try{ 
	    	  s=WebUtils.doPost(url, props, 30000, 30000); 
	      }catch(IOException e){ 
	          e.printStackTrace();
	      }
		return null;
	}
	
}
