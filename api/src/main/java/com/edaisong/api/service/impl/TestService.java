package com.edaisong.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.edaisong.api.dal.dao.inter.IServiceTestDao;
import com.edaisong.api.service.inter.ITestService;
import com.edaisong.entity.BusinessBalanceRecord;
import com.edaisong.entity.req.TestServiceReq;
import com.edaisong.entity.resp.TestServiceResp;

@Service
public class TestService implements ITestService {

	@Autowired
	private IServiceTestDao dao;

	@Override
	public TestServiceResp selectBusinessBalanceByID(TestServiceReq req) {
		TestServiceResp resp = new TestServiceResp();
		List<BusinessBalanceRecord> listData = dao.selectBusinessBalanceByID(
				req.getRecordType(), req.getOperateTime());
		resp.setResultList(listData);
		return resp;
	}
}
