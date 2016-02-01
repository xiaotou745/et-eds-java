package com.edaisong.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edaisong.api.dao.inter.ITestUserTblDao;
import com.edaisong.api.dao.inter.IUserOptRecordDao;
import com.edaisong.api.service.inter.ITestUserTblService;
import com.edaisong.entity.UserOptRecord;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.TestUserRecord;
import com.edaisong.entity.req.PagedTestUserReq;

@Service
public class TestUserTblService implements ITestUserTblService {
	@Autowired
	private ITestUserTblDao testUserTblDao;
	@Autowired
	private IUserOptRecordDao userOptRecordDao;
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	@Override
	public int insert(String phoneNo,UserOptRecord record){
		userOptRecordDao.insert(record);
		return testUserTblDao.insert(phoneNo);
	}

	@Override
	public int deleteByPhoneNo(String phoneNo) {
		return testUserTblDao.deleteByPhoneNo(phoneNo);
	}

	@Override
	public PagedResponse<TestUserRecord> getPagedList(PagedTestUserReq req) {
		return testUserTblDao.getPagedList(req);
	}
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	@Override
	public int deleteTestClienter(String phoneNo,UserOptRecord record) {
		userOptRecordDao.insert(record);
		return testUserTblDao.deleteTestClienter(phoneNo);
	}
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	@Override
	public int deleteTestOrder(String phoneNo,UserOptRecord record) {
		userOptRecordDao.insert(record);
		return testUserTblDao.deleteTestOrder(phoneNo);
	}
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	@Override
	public int deleteTestBusiness(String phoneNo,UserOptRecord record) {
		userOptRecordDao.insert(record);
		return testUserTblDao.deleteTestBusiness(phoneNo);
	}

}
