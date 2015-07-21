package com.edaisong.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.api.service.inter.ITestUserTblService;
import com.edaisong.entity.domain.TestUserRecord;
import com.edaisong.entity.resp.BooleanResp;

@Controller
@RequestMapping("testUser")
public class TestUserController {
	@Autowired
	private ITestUserTblService service;

	private final static String LIST_VIEW = "testUser/list";

	@RequestMapping("list")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) {
		List<TestUserRecord> list = service.selectAllTestUsers();
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "管理员");
		model.addObject("currenttitle", "管理测试账号");
		model.addObject("viewPath", LIST_VIEW);
		model.addObject("testUsers", list);
		return model;
	}

	@ResponseBody
	@RequestMapping(value = "add", method = { RequestMethod.POST })
	public BooleanResp add(String phoneNo) {
		BooleanResp resp = new BooleanResp();
		if (phoneNo == null || phoneNo.length() <= 0) {
			resp.setMessage("电话号码不能为空");
			resp.setData(false);
		} else {
			int ret = service.insert(phoneNo);
			if(ret > 0){
				resp.setMessage("成功");
				resp.setData(true);
			}else {
				resp.setMessage("新增失败,请重试");
				resp.setData(false);
			}
			
		}
		return resp;
	}
	
	@ResponseBody
	@RequestMapping(value = "delete", method = { RequestMethod.POST })
	public BooleanResp delete(String phoneNo) {
		BooleanResp resp = new BooleanResp();
		if (phoneNo == null || phoneNo.length() <= 0) {
			resp.setMessage("电话号码不能为空");
			resp.setData(false);
		} else {
			service.deleteByPhoneNo(phoneNo);
			resp.setMessage("成功");
			resp.setData(true);
		}
		return resp;
	}
}
