package com.edaisong.api;

import com.edaisong.api.business.SpringBeanHelper;
import com.edaisong.api.service.inter.IBusinessService;
import com.edaisong.entity.req.BusinessLoginReq;

import junit.framework.TestCase;

public class BusinessServiceTest extends TestCase{
	
	IBusinessService testService;
	
	/**
	 * Sets up the fixture, for example, open a network connection.
	 * This method is called before a test is executed.
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		testService =  SpringBeanHelper
				.getCustomBeanByType(IBusinessService.class);
	}
	
	public void testLogin(){
		BusinessLoginReq req = new BusinessLoginReq();
		req.setPhoneNo("18811420350");
		req.setPassword("123456");
		testService.login(req.getPhoneNo(),req.getPassword());
	}
	
	public void testAddLoginLog(){
		testService.addLoginLog("18811420350", "登录成功", true);
		testService.addLoginLog("18811420350", "登录失败", false);
	}
	
	/**
	 * Tears down the fixture, for example, close a network connection.
	 * This method is called after a test is executed.
	 */
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		
	}
}
