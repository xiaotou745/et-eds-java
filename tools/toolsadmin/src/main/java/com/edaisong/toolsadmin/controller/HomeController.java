package com.edaisong.toolsadmin.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController {
	
	@RequestMapping("/welcome")
	public ModelAndView welcome(HttpServletRequest request){
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("currenttitle", "Welcome");
		model.addObject("subtitle","Welcome");
		model.addObject("viewPath", "home/welcome");
		return model;
	}
	
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
