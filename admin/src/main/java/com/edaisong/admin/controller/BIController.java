package com.edaisong.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.entity.DeliveryCompany;
import com.edaisong.entity.domain.AreaModel;


@Controller
@RequestMapping("bi")
public class BIController {
	
	@RequestMapping("everyday")
	public ModelAndView everyday(){
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "每日数据统计");
		view.addObject("currenttitle", "每日数据统计");
		view.addObject("viewPath", "bi/everyday");
		return view;
	}
}
