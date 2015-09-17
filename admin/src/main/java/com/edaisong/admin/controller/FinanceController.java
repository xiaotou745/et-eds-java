package com.edaisong.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.api.service.inter.IClienterService;

@Controller
@RequestMapping("finance")
public class FinanceController {
	@Autowired
	private IClienterService clienterService;
	
	@RequestMapping("bustasklist")
	public ModelAndView list() {
		ModelAndView model = new ModelAndView("businessView");
		model.addObject("subtitle", "财务管理");
		model.addObject("currenttitle", "门店任务审核");
		model.addObject("viewPath", "finance/bustasklist");
		return model;
	}
}
