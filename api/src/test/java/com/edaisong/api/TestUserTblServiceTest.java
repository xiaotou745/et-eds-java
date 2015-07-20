package com.edaisong.api;

import java.util.List;

import junit.framework.TestCase;

import com.edaisong.api.business.SqlSessionFactoryPool;
import com.edaisong.api.service.inter.ITestService;
import com.edaisong.api.service.inter.ITestUserTblService;
import com.edaisong.entity.domain.testuser.TestUserRecord;

public class TestUserTblServiceTest extends TestCase{
	public void testSelectAllTestUsers(){
		ITestUserTblService testService =  SqlSessionFactoryPool
				.getCustomBeanByType(ITestUserTblService.class);
		List<TestUserRecord> list = testService.selectAllTestUsers();
		System.out.println(list.size());
		assertTrue(list.size()>0);
	}
}
