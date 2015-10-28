package com.edaisong.toolsadmin.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("home")
public class HomeController {
	@RequestMapping("index")
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "welcome");
		model.addObject("currenttitle", "welcome");
		model.addObject("viewPath", "home/history");
		return model;
	}
	
	
	@RequestMapping("toolsindex")
	public ModelAndView list(){		
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "工具欢迎页");
		model.addObject("currenttitle", "工具欢迎页");	
		model.addObject("viewPath", "home/toolsindex");
		return model;
	}
}
