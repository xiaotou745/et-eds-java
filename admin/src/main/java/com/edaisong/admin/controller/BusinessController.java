package com.edaisong.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.api.service.inter.IBusinessService;
import com.edaisong.entity.req.BusinessReq;
import com.edaisong.entity.resp.BusinessResp;

/*
 * 商户管理
 * 赵海龙
 * 2015年7月27
 * */
@Controller
@RequestMapping("business")
public class BusinessController {
	 @Autowired
	 private IBusinessService  iBusinessService;
	 @RequestMapping("business")
		public ModelAndView index(HttpServletRequest request, HttpServletResponse res) {
			ModelAndView model = new ModelAndView("adminView");
			model.addObject("subtitle", "商户");
			model.addObject("currenttitle", "商户管理");
			model.addObject("viewPath", "business/index");
			return model;
	 }
	 @RequestMapping("list")
		public ModelAndView list(HttpServletRequest request, HttpServletResponse res) {
			BusinessReq req=new BusinessReq();	
			BusinessResp resp =iBusinessService.GetBusinessList(req);		
			
			ModelAndView model = new ModelAndView("adminView");
			model.addObject("subtitle", "商户");
			model.addObject("currenttitle", "商户管理");
			model.addObject("listData", resp.getResultList());
			model.addObject("viewPath", "business/list");
			return model;
	 }
}
