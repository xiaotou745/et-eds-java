package com.edaisong.api.service.inter;

import java.util.List;

import com.edaisong.entity.domain.testuser.TestUserRecord;

public interface ITestUserTblService {
	List<TestUserRecord> selectAllTestUsers();
}
