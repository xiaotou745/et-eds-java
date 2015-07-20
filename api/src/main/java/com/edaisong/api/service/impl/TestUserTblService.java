package com.edaisong.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.edaisong.api.dal.dao.inter.ITestUserTblDao;
import com.edaisong.api.service.inter.ITestUserTblService;
import com.edaisong.entity.domain.testuser.TestUserRecord;

public class TestUserTblService implements ITestUserTblService {
	@Autowired
	private ITestUserTblDao dao;

	@Override
	public List<TestUserRecord> selectAllTestUsers() {
		return dao.selectAllTestUsers();
	}

}
