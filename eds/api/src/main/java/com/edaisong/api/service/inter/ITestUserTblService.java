package com.edaisong.api.service.inter;

import com.edaisong.entity.UserOptRecord;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.TestUserRecord;
import com.edaisong.entity.req.PagedTestUserReq;

public interface ITestUserTblService {
	int deleteByPhoneNo(String phoneNo);
	int insert(String phoneNo,UserOptRecord record);
	int deleteTestClienter(String phoneNo,UserOptRecord record);
	int deleteTestOrder(String phoneNo,UserOptRecord record);
	int deleteTestBusiness(String phoneNo,UserOptRecord record);
	PagedResponse<TestUserRecord> getPagedList(PagedTestUserReq req);
}
