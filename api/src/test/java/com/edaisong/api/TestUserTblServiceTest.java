package com.edaisong.api;

import java.util.List;

import junit.framework.TestCase;



import com.edaisong.api.service.inter.ITestUserTblService;
import com.edaisong.core.util.SpringBeanHelper;
import com.edaisong.entity.domain.TestUserRecord;

public class TestUserTblServiceTest extends TestCase{
	ITestUserTblService testService;
	public TestUserTblServiceTest(){
		testService =  SpringBeanHelper
				.getCustomBeanByType(ITestUserTblService.class);
	}
	
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
	}
	
	public void testSelectAllTestUsers(){
		List<TestUserRecord> list = testService.selectAllTestUsers();
		System.out.println(list.size());
//		assertTrue(list.size()>0);
	}
	
	public void testAddTestUser(){
		String phoneNo = "123456789000";
		int c = testService.insert(phoneNo);
//		assertTrue(c > 0);
	}
	
	public void testDeleteTestUser(){
		String phoneNo = "123456789000";
		int c = testService.deleteByPhoneNo(phoneNo);
		//assertTrue(c > 0);
	}
}
