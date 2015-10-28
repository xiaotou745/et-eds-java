package com.edaisong.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.api.service.inter.IDeliveryCompanyService;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.AreaModel;
import com.edaisong.entity.domain.DeliveryStatistics;
import com.edaisong.entity.req.PagedDeliveryStatisticsReq;
import com.edaisong.entity.req.PagedOrderSearchReq;


@Controller
@RequestMapping("deliverycompany")
public class DeliveryCompanyController {
	 @Autowired
	private IDeliveryCompanyService deliveryCompanyService;
	 
	@RequestMapping("statisticslist")
	public ModelAndView statisticslist(HttpServletRequest request, HttpServletResponse response){
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "财务管理");
		model.addObject("currenttitle", "物流公司月账单");
		model.addObject("viewPath", "deliverycompany/statisticslist");
		return model;
	}
	@RequestMapping("statisticslistdo")
	public ModelAndView statisticslistdo(PagedDeliveryStatisticsReq searchWebReq){
		PagedResponse<DeliveryStatistics> data=deliveryCompanyService.getStatisticsList(searchWebReq);
		ModelAndView model = new ModelAndView("deliverycompany/statisticslistdo");
		model.addObject("listData", data);
		return model;
	}
}

 