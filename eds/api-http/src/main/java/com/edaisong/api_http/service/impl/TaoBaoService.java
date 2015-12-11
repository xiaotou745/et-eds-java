package com.edaisong.api_http.service.impl;

import org.springframework.stereotype.Service;

import com.edaisong.api.service.inter.ITaoBaoOrder;
import com.edaisong.api_http.service.inter.ITaoBaoHttpService;
import com.edaisong.entity.common.HttpResultModel;

@Service
public class TaoBaoService implements ITaoBaoHttpService{

	private ITaoBaoOrder iTaoBaoOrder;
	@Override
	public HttpResultModel<Object> gettaobaotoken() {
		// TODO Auto-generated method stub
		return null;
	}

}
