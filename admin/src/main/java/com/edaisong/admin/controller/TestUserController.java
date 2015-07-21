package com.edaisong.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.api.service.inter.ITestUserTblService;
import com.edaisong.entity.domain.testuser.TestUserRecord;

@Controller
@RequestMapping("testUser")
public class TestUserController {
	@Autowired
	private ITestUserTblService service;
	
	private final static String LIST_VIEW = "testUser/list";
	
	@RequestMapping("list")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response){
		List<TestUserRecord> list = service.selectAllTestUsers();
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "管理员");
		model.addObject("currenttitle", "管理测试账号");
		model.addObject("viewPath", LIST_VIEW);
		model.addObject("testUsers", list);
		return model;
	}
}
