package com.edaisong.business.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.api.service.inter.IOrderService;
import com.edaisong.business.entity.UserContext;
import com.edaisong.entity.Business;
import com.edaisong.entity.domain.BusinessOrderSummaryModel;

@Controller
public class IndexController {
	@Autowired
	private IOrderService orderService;
	
	@RequestMapping("index")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UserContext context = UserContext.getCurrentContext(request);
		ModelAndView model = new ModelAndView("businessView");
		Business business = context.getBusiness();
		if (context.isEmpty() || business == null) {
			response.sendRedirect(request.getContextPath() + "/");
			return model;
		}
		BusinessOrderSummaryModel bos = orderService.getBusinessOrderSummary(business.getId());
		//Date lastLoginTime = business.getLastLoginTime();
		model.addObject("subtitle", "");
		model.addObject("currenttitle", "首页");
		model.addObject("viewPath", "index");
		model.addObject("bos", bos);
		
		return model;
	}
}
