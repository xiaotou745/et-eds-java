package com.edaisong.business.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.api.dao.inter.IBusinessMessageDao;
import com.edaisong.api.service.inter.IOrderService;
import com.edaisong.business.common.UserContext;
import com.edaisong.entity.Business;
import com.edaisong.entity.BusinessMessage;
import com.edaisong.entity.domain.BusiPubOrderTimeStatisticsModel;
import com.edaisong.entity.domain.BusinessOrderSummaryModel;

@Controller
public class IndexController {
	@Autowired
	private IOrderService orderService;
	@Autowired
	private IBusinessMessageDao businessMessageDao;
	
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
		BusinessMessage message = businessMessageDao.getLatestMessage(business.getId());
		//获得查询订单统计的开始时间和结束时间
		Calendar c = Calendar.getInstance();
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), 0, 0, 0);
		Date startTime = c.getTime();
		c.add(Calendar.DATE, 1);
		Date endTime = c.getTime();
		List<BusiPubOrderTimeStatisticsModel> statistics = orderService
				.getBusiPubOrderTimeStatistics(business.getId(),startTime,endTime);
		model.addObject("subtitle", "");
		model.addObject("currenttitle", "首页");
		model.addObject("viewPath", "index");
		model.addObject("bos", bos);
		model.addObject("message", message);
		model.addObject("pubOrderTimestatistics", statistics);
		
		return model;
	}
}
