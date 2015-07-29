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
import com.edaisong.entity.common.ResponsePageList;
import com.edaisong.entity.domain.OpenCityModel;
import com.edaisong.entity.domain.OrderListModel;
import com.edaisong.entity.req.AccountReq;
import com.edaisong.entity.req.OrderSearchWebReq;
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
	 
	 
//	@RequestMapping("list")
//	public ModelAndView list(){
//		TestServiceReq req = new TestServiceReq();
//		req.setRecordType(9);
//		req.setOperateTime("2015-01-01");
//		TestServiceResp resp = testService.selectBusinessBalanceByID(req);
//		
//		ModelAndView model = new ModelAndView("orderlist");
//		model.addObject("subtitle", "骑士管理");
//		model.addObject("currenttitle", "骑士提现");
//		model.addObject("listData", resp.getResultList());
//		
//		return model;
//	}
//	
	
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
//		List<OrderListModel> orders=orderService.GetOrders(new OrderSearchWebReq()).getResultList();
//		model.addObject("listData",orders);
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
	public ModelAndView order(OrderSearchWebReq searchWebReq){
		ResponsePageList<OrderListModel> resp = orderService.GetOrders(searchWebReq);
		ModelAndView view = new ModelAndView();
		view.addObject("viewPath", "order/listdo");
		view.addObject("listData", resp);
		return view;
	}
	
	
	
}
