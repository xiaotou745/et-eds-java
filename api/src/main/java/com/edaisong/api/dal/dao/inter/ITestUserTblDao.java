package com.edaisong.api.dal.dao.inter;

import java.util.List;

import com.edaisong.entity.TestUserTbl;
import com.edaisong.entity.domain.testuser.TestUserRecord;

public interface ITestUserTblDao {
	int deleteByPrimaryKey(Integer id);

	int insert(TestUserTbl record);

	int insertSelective(TestUserTbl record);

	TestUserTbl selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(TestUserTbl record);

	int updateByPrimaryKey(TestUserTbl record);

	List<TestUserRecord> selectAllTestUsers();
}