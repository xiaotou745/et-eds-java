package com.edaisong.business.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.api.service.inter.IBusinessService;
import com.edaisong.api.service.inter.IOrderService;
import com.edaisong.business.common.UserContext;
import com.edaisong.core.enums.OrderStatus;
import com.edaisong.core.util.ExcelUtils;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.core.util.PropertyUtils;
import com.edaisong.entity.domain.BusiPubOrderTimeStatisticsModel;
import com.edaisong.entity.domain.BusinessOrderSummaryModel;
import com.edaisong.entity.domain.ExportStatistics;
import com.edaisong.entity.domain.GroupOrderDaystatistics;
import com.edaisong.entity.domain.GroupOrderstatistics;
import com.edaisong.entity.domain.GroupTodayStatistics;
import com.edaisong.entity.domain.OrderListModel;
import com.edaisong.entity.domain.ServiceClienter;
import com.edaisong.entity.req.PagedOrderSearchReq;

/**
 * 集团统计模块
 * 
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
		GroupTodayStatistics g = orderService.groupTodayStatistics(UserContext
				.getCurrentContext(request).getBusinessID());
		BusinessOrderSummaryModel b = orderService.groupTodayOrderStatistics(
				null, UserContext.getCurrentContext(request).getBusinessID());
		List<BusiPubOrderTimeStatisticsModel> bdays = orderService
				.groupTodayOrderStatisticsReport(null, UserContext
						.getCurrentContext(request).getBusinessID());
		ModelAndView model = new ModelAndView("businessView");
		model.addObject("subtitle", "首页");
		model.addObject("currenttitle", "今日统计");
		model.addObject("viewPath", "groupstatistics/today");
		model.addObject("g", g);
		model.addObject("b", b);
		if (bdays == null) {
			bdays = new ArrayList<BusiPubOrderTimeStatisticsModel>();
		}
		// 待接单
		List<BusiPubOrderTimeStatisticsModel> bdaysNew = bdays.stream()
				.filter(t -> t.getStatus() == OrderStatus.New.value())
				.collect(Collectors.toList());
		// 取货中
		List<BusiPubOrderTimeStatisticsModel> bdaysDelivery = bdays.stream()
				.filter(t -> t.getStatus() == OrderStatus.Delivery.value())
				.collect(Collectors.toList());
		// 配送中
		List<BusiPubOrderTimeStatisticsModel> bdaysTaking = bdays.stream()
				.filter(t -> t.getStatus() == OrderStatus.Taking.value())
				.collect(Collectors.toList());
		// 已完成
		List<BusiPubOrderTimeStatisticsModel> bdaysComplite = bdays.stream()
				.filter(t -> t.getStatus() == OrderStatus.Complite.value())
				.collect(Collectors.toList());
		model.addObject("bdaysNew", bdaysNew);
		model.addObject("bdaysDelivery", bdaysDelivery);
		model.addObject("bdaysTaking", bdaysTaking);
		model.addObject("bdaysComplite", bdaysComplite);
		return model;
	}

	
	@RequestMapping("statistics")
	public ModelAndView statistics(PagedOrderSearchReq req,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView("businessView");
		model.addObject("subtitle", "首页");
		model.addObject("currenttitle", "订单统计");
		model.addObject("viewPath", "groupstatistics/statistics");
		model.addObject("pubStart", ParseHelper.ToDateString(ParseHelper.plusDate(new Date(), 2, -7), "yyyy-MM-dd"));
		model.addObject("pubEnd", ParseHelper.ToDateString(new Date(), "yyyy-MM-dd"));
		return model;
	}
	
	@RequestMapping("statisticsdo")
	public ModelAndView statisticsdo(PagedOrderSearchReq req,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		model.addObject("viewPath", "groupstatistics/statisticsdo");
		req.setGroupBusinessId(UserContext.getCurrentContext(request).getBusinessID());
		req.setOrderStatus(OrderStatus.Complite.value());
		GroupOrderstatistics list1=orderService.groupOrderstatistics(req);
		model.addObject("g", list1);
		req.setOrderStatus(OrderStatus.Cancel.value());
		GroupOrderstatistics list2=orderService.groupOrderstatistics(req);
		model.addObject("g1", list2);
		return model;
	}
	
	
	/**
	 * 集团订单统计
	 * 
	 * @author caoheyang 
	 * @Date 20160222
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("exportstatistics")
	public void exportStatistics(Integer timeType,PagedOrderSearchReq searchWebReq,HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		searchWebReq.setGroupBusinessId(UserContext.getCurrentContext(request).getBusinessID());
		searchWebReq.setOrderPubStart("2016-01-12");
		searchWebReq.setOrderPubEnd("2016-03-12");
		searchWebReq.setOrderStatus(OrderStatus.Cancel.value());
		searchWebReq.setGroupBusinessId(UserContext.getCurrentContext(request).getBusinessID());
	    List<ExportStatistics> records=	 orderService.exportStatistics(searchWebReq);
	    if(records.size() > 0){
				String fileName = "e代送-%s-集团订单统计";
				fileName = String.format(fileName, searchWebReq.getOrderPubStart()+ "到" + searchWebReq.getOrderPubEnd());
				LinkedHashMap<String, String> columnTitiles = new LinkedHashMap<String, String>();
				columnTitiles.put("日期", "monthDate");
				columnTitiles.put("门店名称", "businessName");
				columnTitiles.put("订单数量", "compliteCount");
				columnTitiles.put("配送费支出", "totalSettleMoney");
				columnTitiles.put("消费集团金额", "groupPay");
				columnTitiles.put("门店自费金额", "businessPay");
				columnTitiles.put("菜品总金额", "totalAmount");
				columnTitiles.put("取消订单量", "canelCount");
				ExcelUtils.export2Excel(fileName, "集团订单统计", columnTitiles,records, request, response);
				return;
			}else {
				String basePath = PropertyUtils.getProperty("java.business.url");
				response.sendRedirect(basePath+"/groupstatistics/exportstatistics");
			}
	}
}
