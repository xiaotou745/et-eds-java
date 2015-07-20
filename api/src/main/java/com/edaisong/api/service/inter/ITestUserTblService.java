package com.edaisong.api.service.inter;

import java.util.List;

import com.edaisong.entity.TestUserTbl;
import com.edaisong.entity.domain.testuser.TestUserRecord;

public interface ITestUserTblService {
	List<TestUserRecord> selectAllTestUsers();
	int deleteByPrimaryKey(Integer id);
	int insert(String phoneNo);
	TestUserTbl selectByPrimaryKey(Integer id);
	int updateByPrimaryKey(TestUserTbl record);
}
