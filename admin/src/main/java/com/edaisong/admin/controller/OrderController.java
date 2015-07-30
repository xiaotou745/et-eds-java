package com.edaisong.admin.controller;

import java.util.ArrayList;
import java.util.List;
import javassist.expr.NewArray;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.edaisong.api.service.impl.OrderService;
import com.edaisong.api.service.inter.IOrderService;
import com.edaisong.api.service.inter.ITestService;
import com.edaisong.entity.Account;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.OpenCityModel;
import com.edaisong.entity.domain.OrderListModel;
import com.edaisong.entity.req.AccountReq;
import com.edaisong.entity.req.PagedOrderSearchReq;
import com.edaisong.entity.req.TestServiceReq;
import com.edaisong.entity.resp.TestServiceResp;

@Controller
@RequestMapping("order")
public class OrderController {
	 @Autowired
	 private ITestService testService;
	 @Context
	 private HttpServletRequest  request;
	 @Context
	 private HttpServletResponse response;
	 @Autowired
	 private IOrderService orderService;
	 
	/**
	 * 订单列表页面 
	 * @author CaoHeYang
	 * @Date 20150728
	 * @return
	 */
	@RequestMapping("list")
	public ModelAndView order(){
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "订单管理");
		model.addObject("currenttitle", "订单管理");
		model.addObject("viewPath", "order/list");
		return model;
	}
	
	/**
	 * 订单列表页面 
	 * @author CaoHeYang
	 * @Date 20150728
	 * @return
	 */
	@RequestMapping("listdo")
	public ModelAndView order(PagedOrderSearchReq searchWebReq){
		PagedResponse<OrderListModel> resp = orderService.getOrders(searchWebReq);
		ModelAndView view = new ModelAndView();
		view.addObject("viewPath", "order/listdo");
		view.addObject("listData", resp);
		return view;
	}
	
	
	
}
