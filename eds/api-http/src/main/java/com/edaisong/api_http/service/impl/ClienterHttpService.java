package com.edaisong.api_http.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.service.inter.IClienterService;
import com.edaisong.api_http.service.inter.IClienterHttpService;
import com.edaisong.core.enums.returnenums.HttpReturnRnums;
import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.req.MyBusinessReq;
import com.edaisong.entity.resp.MyBusinessResp;
@Service
public class ClienterHttpService implements IClienterHttpService {

	@Autowired
	private IClienterService iClienterService;
	/*
	 * 获取我的商户
	 * wangchao
	 */
	@Override
	public HttpResultModel<List<MyBusinessResp>> getMyBusiness(
			MyBusinessReq myBusinessReq) {
		HttpResultModel<List<MyBusinessResp>> result = new HttpResultModel<List<MyBusinessResp>>();
		result.setStatus(HttpReturnRnums.Success.value());
		result.setMessage(HttpReturnRnums.Success.desc());
		List<MyBusinessResp> listMyBusinessResp = new ArrayList<MyBusinessResp>();
		
		listMyBusinessResp = iClienterService.getMyBusiness(myBusinessReq);
		
		result.setResult(listMyBusinessResp);
		return null;
	}

}
