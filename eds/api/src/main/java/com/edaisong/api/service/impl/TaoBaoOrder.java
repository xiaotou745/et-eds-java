package com.edaisong.api.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.edaisong.api.service.inter.ITaoBaoOrder;
import com.edaisong.core.consts.TaoBaoConsts;
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
		
		taoBaoAccessTokenReq.setCode("code");
		taoBaoAccessTokenReq.setClient_id(TaoBaoConsts.AppKey);
		taoBaoAccessTokenReq.setRedirect_uri("http://localhost:8080");
		taoBaoAccessTokenReq.setResponse_type("code");
		taoBaoAccessTokenReq.setView("web");
		//第一步获取 code
		//https://oauth.taobao.com/authorize?response_type=code&client_id=23075594&redirect_uri=http://www.oauth.net/2/&state=1212&view=web
		//String url= "https://oauth.taobao.com/authorize";
		String url = "https://oauth.tbsandbox.com/authorize";
		Map<String,String> props=new HashMap<String,String>();
		
		props.put("grant_type","authorization_code"); 
		/*测试时，需把test参数换成自己应用对应的值*/ 
	      //props.put("code",taoBaoAccessTokenReq.getCode());
	 
	      props.put("client_id",taoBaoAccessTokenReq.getClient_id());
	 
	      props.put("response_type",taoBaoAccessTokenReq.getResponse_type());
	 
	      props.put("redirect_uri",taoBaoAccessTokenReq.getRedirect_uri());
	 
	      props.put("view",taoBaoAccessTokenReq.getView());
	      String s="";
	      try{
	    	  s=WebUtils.doPost(url, props, 30000, 30000); 
	    	  System.out.println(s); 
	      }catch(IOException e){  
	          e.printStackTrace();
	      }
		return null;
	}
	
}
