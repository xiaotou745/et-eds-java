package com.edaisong.business.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("group")
public class GroupController {
	@RequestMapping("recharge")
	public ModelAndView list() {
		ModelAndView view = new ModelAndView("businessView");
		view.addObject("subtitle", "");
		view.addObject("currenttitle", "充值");
		view.addObject("viewPath", "group/recharge");
		return view;
	}
}
