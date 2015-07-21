package com.edaisong.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dal.dao.inter.ITestUserTblDao;
import com.edaisong.api.service.inter.ITestUserTblService;
import com.edaisong.entity.TestUserTbl;
import com.edaisong.entity.domain.TestUserRecord;

@Service
public class TestUserTblService implements ITestUserTblService {
	@Autowired
	private ITestUserTblDao dao;

	@Override
	public List<TestUserRecord> selectAllTestUsers() {
		return dao.selectAllTestUsers();
	}

	public int deleteByPrimaryKey(Integer id) {
		return dao.deleteByPrimaryKey(id);
	}
	
	public int insert(String phoneNo){
		return dao.insert(phoneNo);
	}
	
	public TestUserTbl selectByPrimaryKey(Integer id) {
		return dao.selectByPrimaryKey(id);
	}
	
	public int updateByPrimaryKey(TestUserTbl record) {
		return dao.updateByPrimaryKey(record);
	}

	@Override
	public int deleteByPhoneNo(String phoneNo) {
		return dao.deleteByPhoneNo(phoneNo);
	}
}
