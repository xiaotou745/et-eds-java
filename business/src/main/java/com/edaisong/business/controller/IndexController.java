package com.edaisong.business.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
	@RequestMapping("index")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("businessView");
		model.addObject("subtitle", "");
		model.addObject("currenttitle", "首页");
		model.addObject("viewPath", "index");
		return model;
	}
}
