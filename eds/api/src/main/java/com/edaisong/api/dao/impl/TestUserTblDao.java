package com.edaisong.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.ITestUserTblDao;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.TestUserRecord;
import com.edaisong.entity.req.PagedTestUserReq;

@Repository
public class TestUserTblDao extends DaoBase implements ITestUserTblDao {
	@Override
	public int insert(String phoneNo) {
		return getMasterSqlSessionUtil().insert("ITestUserTblDao.insert", phoneNo);
	}


	@Override
	public int deleteByPhoneNo(String phoneNo) {
		return getMasterSqlSessionUtil().delete("ITestUserTblDao.deleteByPhoneNo", phoneNo);
	}

	@Override
	public PagedResponse<TestUserRecord> getPagedList(PagedTestUserReq req) {
		return getReadOnlySqlSessionUtil().selectPageList("ITestUserTblDao.getPagedList", req);
	}

	@Override
	public int deleteTestClienter(String phoneNo) {
		return getMasterSqlSessionUtil().delete("ITestUserTblDao.deleteTestClienter", phoneNo);
	}

	@Override
	public int deleteTestOrder(String phoneNo) {
		return getMasterSqlSessionUtil().delete("ITestUserTblDao.deleteTestOrder", phoneNo);
	}

	@Override
	public int deleteTestBusiness(String phoneNo) {
		return getMasterSqlSessionUtil().delete("ITestUserTblDao.deleteTestBusiness", phoneNo);
	}

}
