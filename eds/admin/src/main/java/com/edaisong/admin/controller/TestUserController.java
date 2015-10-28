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
import com.edaisong.entity.common.ResponseBase;
import com.edaisong.entity.common.ResponseCode;
import com.edaisong.entity.domain.TestUserRecord;


@Controller
@RequestMapping("testuser")
public class TestUserController {
	@Autowired
	private ITestUserTblService service;

	private final static String LIST_VIEW = "testuser/list";

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
	public ResponseBase add(String phoneNo) {
		ResponseBase resp = new ResponseBase();
		if (phoneNo == null || phoneNo.length() <= 0) {
			resp.setMessage("电话号码不能为空");
			resp.setResponseCode(ResponseCode.BUSINESS_FAILURE_ERROR);
		} else {
			int ret = 0;//service.insert(phoneNo);
			if(ret > 0){
				resp.setMessage("成功");
				resp.setResponseCode(ResponseCode.SUCESS);
			}else {
				resp.setMessage("新增失败,请重试");
				resp.setResponseCode(ResponseCode.BUSINESS_FAILURE_ERROR);
			}
			
		}
		return resp;
	}
	
	@ResponseBody
	@RequestMapping(value = "delete", method = { RequestMethod.POST })
	public ResponseBase delete(String phoneNo) {
		ResponseBase resp = new ResponseBase();
		if (phoneNo == null || phoneNo.length() <= 0) {
			resp.setMessage("电话号码不能为空");
			resp.setResponseCode(ResponseCode.BUSINESS_FAILURE_ERROR);
		} else {
			//service.deleteByPhoneNo(phoneNo);
			resp.setMessage("成功");
			resp.setResponseCode(ResponseCode.SUCESS);
		}
		return resp;
	}
}
