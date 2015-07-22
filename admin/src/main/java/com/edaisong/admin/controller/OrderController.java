package com.edaisong.admin.controller;

import java.util.List;

import javassist.expr.NewArray;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;







import com.edaisong.api.service.inter.ITestService;
import com.edaisong.entity.req.TestServiceReq;
import com.edaisong.entity.resp.TestServiceResp;

@Controller
@RequestMapping("order")
public class OrderController {
	 @Autowired
	 private ITestService testService;
	 
	@RequestMapping("list")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response){
		TestServiceReq req = new TestServiceReq();
		req.setRecordType(9);
		req.setOperateTime("2015-01-01");
		TestServiceResp resp = testService.selectBusinessBalanceByID(req);
		
		ModelAndView model = new ModelAndView("orderlist");
		model.addObject("subtitle", "骑士管理");
		model.addObject("currenttitle", "骑士提现");
		model.addObject("listData", resp.getResultList());
		
		return model;
	}
}
