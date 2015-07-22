package com.edaisong.admin.controller;
import java.util.HashMap;
import java.util.Map;

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
@RequestMapping("Xiao")
public class XiaoController {
	@RequestMapping("Index")
	public ModelAndView Index(HttpServletRequest request, HttpServletResponse res)
	{
		ModelAndView modelAndView = new ModelAndView();
		
//		modelAndView.setViewName("Xiao");
//		modelAndView.addObject("data", "This is Xiao");
//		Map<String, String> paramMap = new HashMap<>();
//		paramMap.put("zhagnsan", "sex");
//		paramMap.put("name", "old");
//		modelAndView.addObject("listdata", paramMap);
//		modelAndView.addObject("viewPath", "ajaxdemo");
//		return modelAndView;
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "骑士管理");
		model.addObject("currenttitle", "骑士提现");
		model.addObject("viewPath", "Xiao");
		model.addObject("AAA","BBB");
		return model;
	}
}
