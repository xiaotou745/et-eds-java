package com.edaisong.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.api.service.impl.BIService;
import com.edaisong.api.service.inter.IBIService;
import com.edaisong.entity.DeliveryCompany;
import com.edaisong.entity.Everyday;
import com.edaisong.entity.domain.AreaModel;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;


@Controller
@RequestMapping("bi")
public class BIController {
	private IBIService biService;
	@RequestMapping("everyday")
	public ModelAndView everyday(){
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "每日数据统计");
		view.addObject("currenttitle", "每日数据统计"); 
		view.addObject("viewPath", "bi/everyday");
		List<Everyday> list= biService.queryEveryDay();
		view.addObject("everyDayData",list);
		return view;
	}
	
	
}
