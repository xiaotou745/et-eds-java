package com.edaisong.api.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.edaisong.api.service.inter.ITaoBaoAccessService;
import com.edaisong.core.consts.TaoBaoConsts;
import com.edaisong.entity.taobao.req.TaoBaoAccessTokenReq;
import com.taobao.api.internal.util.WebUtils;

@Service
public class TaoBaoAccessService implements ITaoBaoAccessService {
	@Override
	public String getAccessToken(TaoBaoAccessTokenReq taoBaoAccessTokenReq) {
		
		taoBaoAccessTokenReq.setCode("code");
		taoBaoAccessTokenReq.setClient_id(TaoBaoConsts.AppKey);
		taoBaoAccessTokenReq.setRedirect_uri("http://localhost:8080");
		taoBaoAccessTokenReq.setResponse_type("code");
		taoBaoAccessTokenReq.setView("web");
		//第一步获取 code
		//https://oauth.taobao.com/authorize?state=1212&view=web&client_id=23264088&response_type=code&redirect_uri=http://www.edaisong.com
		//通过回调地址获取 code TQjLqsF6y9IBN9pPFJDZ4PlK271880
		//第二步 换取token  
		//https://oauth.taobao.com/token?client_id=23264088&state=1212&view=web&client_secret=ce0f4f3668d039b6db74aa82904cca43&grant_type=authorization_code&code=TQjLqsF6y9IBN9pPFJDZ4PlK271880&redirect_uri=http://www.edaisong.com
		//根据第二步返回结果 获得 token
		String url= "https://oauth.taobao.com/authorize";
		
		Map<String,String> props=new HashMap<String,String>();
		
		props.put("grant_type","authorization_code"); 
		/*测试时，需把test参数换成自己应用对应的值*/ 
	      //props.put("code",taoBaoAccessTokenReq.getCode());
	 
	      props.put("client_id",TaoBaoConsts.AppKey);
	 
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
