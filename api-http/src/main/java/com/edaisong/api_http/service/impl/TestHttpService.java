package com.edaisong.api_http.service.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.service.inter.ITestService;


import com.edaisong.api_http.service.inter.ITestHttpService;
import com.edaisong.entity.req.TestServiceReq;
import com.edaisong.entity.resp.TestServiceResp;

@Path("/urlService")
@Service
public class TestHttpService implements ITestHttpService {

	@Autowired
	private ITestService testService;

	@POST
	@Path("/getlist")
	//post和get不能并存
	 //@Consumes(MediaType.APPLICATION_JSON)//当前方法接收的参数类型
	 //@Produces("application/xml")//当前类的所有方法都返回json格式的数据
	//@Consumes({"application/xml","application/json","application/x-www-form-urlencoded"})
	@Override
	public TestServiceResp getList(TestServiceReq req) {
		System.out.println("post的参数值:" + req + "结束");
		if(req==null){
			req=new TestServiceReq();
			req.setRecordType(9);
			req.setOperateTime("2015-1-1");
			System.out.println("post的参数值=null");
		}else
		{
			System.out.println("post的参数值非null");
		}
		System.out.println("post的参数值:" + req.getOperateTime()+"---"+req.getRecordType() + "结束");
		return testService.selectBusinessBalanceByID(req);
	}

	@GET
	@Path("/getdata")
	// @Consumes(MediaType.APPLICATION_JSON)//当前方法接收的参数类型
	 //@Produces("application/xml")//当前类的所有方法都返回json格式的数据
	//@Consumes({"application/xml","application/json","application/x-www-form-urlencoded"})
	public TestServiceResp getdata() {
		
		//System.out.println("pOST的参数值:" + msg + "结束");
			TestServiceReq req=new TestServiceReq();
			req.setRecordType(9);
			req.setOperateTime("2015-1-1");
	          
		return testService.selectBusinessBalanceByID(req);
	}
}
