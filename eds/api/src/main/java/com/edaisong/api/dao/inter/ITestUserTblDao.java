package com.edaisong.api.dao.inter;

import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.TestUserRecord;
import com.edaisong.entity.req.PagedTestUserReq;

public interface ITestUserTblDao {
	int insert(String phoneNo);

	int deleteTestClienter(String phoneNo);
	int deleteTestOrder(String phoneNo);
	int deleteTestBusiness(String phoneNo);
	
	int deleteByPhoneNo(String phoneNo);
	PagedResponse<TestUserRecord> getPagedList(PagedTestUserReq req);
}