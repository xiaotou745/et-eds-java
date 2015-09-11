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
	@RequestMapping("index")
	public ModelAndView alipayindex() {
		ModelAndView view = new ModelAndView("group/index");
		return view;
	}
	@RequestMapping("alipayapi")
	public ModelAndView alipayapi() {
		ModelAndView view = new ModelAndView("group/alipayapi");
		return view;
	}
	@RequestMapping("notify_url")
	public ModelAndView notify_url() {
		ModelAndView view = new ModelAndView("group/notify_url");
		return view;
	}
	@RequestMapping("return_url")
	public ModelAndView return_url() {
		ModelAndView view = new ModelAndView("group/return_url");
		return view;
	}
}
