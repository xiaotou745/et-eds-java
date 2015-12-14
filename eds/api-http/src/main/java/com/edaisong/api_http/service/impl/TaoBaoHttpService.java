package com.edaisong.api_http.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.service.inter.ITaoBaoAccessService; 
import com.edaisong.api_http.service.inter.ITaoBaoHttpService;
import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.taobao.req.TaoBaoAccessTokenReq;

@Service
public class TaoBaoHttpService implements ITaoBaoHttpService{

	@Autowired
	private ITaoBaoAccessService iTaoBaoAccessService;
	@Override
	public HttpResultModel<Object> gettaobaotoken() {
		TaoBaoAccessTokenReq taoBaoAccessTokenReq = new TaoBaoAccessTokenReq();
		iTaoBaoAccessService.getAccessToken(taoBaoAccessTokenReq);
		return null;
	}

}
