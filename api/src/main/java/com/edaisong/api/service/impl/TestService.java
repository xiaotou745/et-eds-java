package com.edaisong.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edaisong.api.dal.dao.inter.IServiceTestDao;
import com.edaisong.api.service.inter.ITestService;
import com.edaisong.entity.BusinessBalanceRecord;
import com.edaisong.entity.req.TestServiceReq;
import com.edaisong.entity.resp.TestServiceResp;

@Service
public class TestService implements ITestService {

	@Autowired
	private IServiceTestDao dao;

    @Transactional(rollbackFor = Exception.class,timeout=30)
	@Override
	public TestServiceResp selectBusinessBalanceByID(TestServiceReq req) {
    	dao.addBusinessBalance(null);
    		int res=dao.addBusinessBalance(null);
//    		if (res==1) {
//   			 throw new RuntimeException(); 
//			}

TestServiceResp resp = new TestServiceResp();
     		List<BusinessBalanceRecord> listData = dao.selectBusinessBalanceByID(
     				req.getRecordType(), req.getOperateTime());
    		resp.setResultList(listData);
    		return resp;
	}

	@Override
	public TestServiceResp getList() {
		TestServiceResp resp = new TestServiceResp();
		TestServiceReq req = new TestServiceReq();
		req.setRecordType(9);
		req.setOperateTime("2015-1-1");
		List<BusinessBalanceRecord> listData = dao.selectBusinessBalanceByID(req.getRecordType(), req.getOperateTime());
		resp.setResultList(listData);
		return resp;
	}
}
