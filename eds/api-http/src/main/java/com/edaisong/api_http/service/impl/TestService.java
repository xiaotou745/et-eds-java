package com.edaisong.api_http.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.service.inter.IBusinessService;
import com.edaisong.api_http.entity.TestServiceReq;
import com.edaisong.api_http.entity.TestServiceResp;
import com.edaisong.api_http.service.inter.ITestService;
import com.edaisong.core.enums.returnenums.HttpReturnRnums;
import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.domain.BusinessDetailModel;
import com.edaisong.entity.resp.OrderStatisticsBResp;

@Service
public class TestService implements ITestService {

	@Autowired
	private IBusinessService businessService;
	@Override
	public HttpResultModel<BusinessDetailModel> selectByID(TestServiceReq req) {
		//throw new RuntimeException("sdfsds");
		BusinessDetailModel listData = businessService.getBusinessDetailByID(123);
		HttpResultModel<BusinessDetailModel> result=new HttpResultModel<BusinessDetailModel>();
		result.setStatus(HttpReturnRnums.Success.value());
		result.setMessage(HttpReturnRnums.Success.desc());
		result.setResult(listData);
		return result;  


	}

	@Override
	public HttpResultModel<BusinessDetailModel>  getList() {
		BusinessDetailModel listData = businessService.getBusinessDetailByID(123);
		HttpResultModel<BusinessDetailModel> result=new HttpResultModel<BusinessDetailModel>();
		result.setStatus(HttpReturnRnums.Success.value());
		result.setMessage(HttpReturnRnums.Success.desc());
		result.setResult(listData);
		return result;  
	}

}
