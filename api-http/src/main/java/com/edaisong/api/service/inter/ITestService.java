package com.edaisong.api.service.inter;
import com.edaisong.entity.req.TestServiceReq;
import com.edaisong.entity.resp.TestServiceResp;


public interface ITestService {
	 public TestServiceResp selectBusinessBalanceByID(TestServiceReq req);
}
