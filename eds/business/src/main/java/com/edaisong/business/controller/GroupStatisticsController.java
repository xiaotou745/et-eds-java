package com.edaisong.business.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.api.service.inter.IBusinessService;
import com.edaisong.api.service.inter.IOrderService;
import com.edaisong.business.common.UserContext;
import com.edaisong.core.enums.OrderStatus;
import com.edaisong.entity.domain.BusiPubOrderTimeStatisticsModel;
import com.edaisong.entity.domain.BusinessOrderSummaryModel;
import com.edaisong.entity.domain.GroupTodayStatistics;
import com.edaisong.entity.domain.ServiceClienter;

/**
 * 集团统计模块
 * @author CaoHeYang
 */
@Controller
@RequestMapping("groupstatistics")
public class GroupStatisticsController {
	@Autowired
	IOrderService orderService;
	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("today")
	public ModelAndView today(HttpServletRequest request) {
       GroupTodayStatistics g=orderService.groupTodayStatistics(UserContext.getCurrentContext(request).getBusinessID());
       BusinessOrderSummaryModel b=orderService.groupTodayOrderStatistics(null, 
    		   UserContext.getCurrentContext(request).getBusinessID());
       List<BusiPubOrderTimeStatisticsModel> bdays=orderService.groupTodayOrderStatisticsReport(null, 
    		   UserContext.getCurrentContext(request).getBusinessID());
       ModelAndView model = new ModelAndView("businessView");
		model.addObject("subtitle", "首页");
		model.addObject("currenttitle", "今日统计");
		model.addObject("viewPath", "groupstatistics/today");
		model.addObject("g", g);
		model.addObject("b", b);
		if (bdays==null) {
			bdays=new ArrayList<BusiPubOrderTimeStatisticsModel>();
		}
		//待接单
		List<BusiPubOrderTimeStatisticsModel> bdaysNew = bdays.stream()
				.filter(t -> t.getStatus()==OrderStatus.New.value())
				.collect(Collectors.toList());
		//取货中
		List<BusiPubOrderTimeStatisticsModel> bdaysDelivery = bdays.stream()
				.filter(t -> t.getStatus()==OrderStatus.Delivery.value())
				.collect(Collectors.toList());
		//配送中
		List<BusiPubOrderTimeStatisticsModel> bdaysTaking = bdays.stream()
				.filter(t -> t.getStatus()==OrderStatus.Taking.value())
				.collect(Collectors.toList());
	    //已完成
		List<BusiPubOrderTimeStatisticsModel> bdaysComplite = bdays.stream()
				.filter(t -> t.getStatus()==OrderStatus.Complite.value())
				.collect(Collectors.toList());
		model.addObject("bdaysNew", bdaysNew);
		model.addObject("bdaysDelivery", bdaysDelivery);
		model.addObject("bdaysTaking", bdaysTaking);
		model.addObject("bdaysComplite", bdaysComplite);
	   return model;
	}
}
