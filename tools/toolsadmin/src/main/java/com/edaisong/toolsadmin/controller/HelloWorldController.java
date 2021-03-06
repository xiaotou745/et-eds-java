package com.edaisong.toolsadmin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("ajaxdemo")
public class HelloWorldController {
	@RequestMapping("hello")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse res) {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("adminView");
		modelAndView.addObject("data", "hello word");
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("zhagnsan", "sex");
		paramMap.put("name", "old");
		modelAndView.addObject("listdata", paramMap);
		modelAndView.addObject("viewPath", "ajaxdemo");
		return modelAndView;
	}
}
