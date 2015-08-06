package com.edaisong.api_http.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.service.inter.IBusinessService;
import com.edaisong.api_http.entity.TestServiceReq;
import com.edaisong.api_http.entity.TestServiceResp;
import com.edaisong.api_http.service.inter.ITestService;
import com.edaisong.entity.domain.BusinessDetailModel;

@Service
public class TestService implements ITestService {

	@Autowired
	private IBusinessService businessService;
	@Override
	public TestServiceResp selectByID(TestServiceReq req) {
		TestServiceResp resp=new TestServiceResp();
		BusinessDetailModel listData = businessService.getBusinessDetailByID(123);
		resp.setResultList(listData);
		return resp;
	}

	@Override
	public TestServiceResp getList() {
		TestServiceResp resp=new TestServiceResp();
		BusinessDetailModel listData = businessService.getBusinessDetailByID(123);
		resp.setResultList(listData);
		return resp;
	}

}
