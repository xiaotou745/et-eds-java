package com.edaisong.business.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.api.service.inter.IBusinessService;
import com.edaisong.api.service.inter.IOrderService;
import com.edaisong.business.common.UserContext;
import com.edaisong.entity.domain.BusiPubOrderTimeStatisticsModel;
import com.edaisong.entity.domain.BusinessOrderSummaryModel;
import com.edaisong.entity.domain.GroupTodayStatistics;

/**
 * 集团统计模块
 * @author CaoHeYang
 *
 */
@Controller
@RequestMapping("groupstatistics")
public class GroupStatisticsController {
	@Autowired
	IOrderService orderService;
	@RequestMapping("today")
	public ModelAndView today(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("businessView");
		model.addObject("subtitle", "首页");
		model.addObject("currenttitle", "今日统计");
		model.addObject("viewPath", "groupstatistics/today");
       GroupTodayStatistics g=orderService.groupTodayStatistics(UserContext.getCurrentContext(request).getBusinessID());
       BusinessOrderSummaryModel b=orderService.groupTodayOrderStatistics(null, 
    		   UserContext.getCurrentContext(request).getBusinessID());
       List<BusiPubOrderTimeStatisticsModel> bdays=orderService.groupTodayOrderStatisticsReport(null, 
    		   UserContext.getCurrentContext(request).getBusinessID());
	   return model;
	}
}
