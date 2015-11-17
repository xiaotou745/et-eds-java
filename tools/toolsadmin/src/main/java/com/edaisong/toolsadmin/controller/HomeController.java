package com.edaisong.toolsadmin.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.toolsadmin.common.UserContext;
import com.edaisong.toolsapi.service.inter.IAuthorityMenuClassService;
import com.edaisong.toolscore.util.SpringBeanHelper;
import com.edaisong.toolsentity.MenuEntity;


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
	public ModelAndView list(HttpServletRequest request){		
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "工具欢迎页");
		model.addObject("currenttitle", "工具欢迎页");	
		model.addObject("viewPath", "home/toolsindex");
		return model;
	}
}
