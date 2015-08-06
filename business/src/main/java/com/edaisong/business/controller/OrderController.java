package com.edaisong.business.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.api.service.impl.OrderService;
import com.edaisong.api.service.inter.IOrderService;
import com.edaisong.entity.req.CancelOrderBusinessReq;
import com.edaisong.api.service.inter.IClienterService;import com.edaisong.entity.resp.BusinessLoginResp;
import com.edaisong.entity.resp.CancelOrderBusinessResp;
import com.edaisong.entity.resp.OrderResp;

@Controller
@RequestMapping("order")
public class OrderController {
	 //订单
	 @Autowired
	 private IOrderService  orderService;
	
	@Autowired
	IOrderService orderService;
	/**
	 * 订单详情 
	 * @author CaoHeYang
	 * @Date 20150805
	 * @return
	 */
	@RequestMapping(value = "detail", method = { RequestMethod.GET })
	public  ModelAndView detail(OrderDetailBusinessReq req) {
		ModelAndView model = new ModelAndView("");
		model.addObject("subtitle", "订单中心");
		model.addObject("currenttitle", "订单详情");
		model.addObject("viewPath", "order/detail");
		model.addObject("datas",orderService.getOrderDetailBusiness(req));
		return model;
	}
	
	/**
	 * 商户后台取消订单
	 * @author CaoHeYang
	 * @Date 20150805
	 * @return
	 */
	@RequestMapping(value = "canelorder", method = { RequestMethod.POST })
	@ResponseBody
	public CancelOrderBusinessResp login(@RequestBody CancelOrderBusinessReq req) {
		CancelOrderBusinessResp resp=orderService.cancelOrderBusiness(req);
		return resp;
	}
	
	
	
	/**
	 * 商户后台发布订单
	 * @author 胡灵波
	 * @Date 2015年8月6日 13:37:00
	 * @return
	 */
	@RequestMapping(value = "publish")
	@ResponseBody
	public OrderResp publish() {
		OrderResp resp=new OrderResp();
		OrderReq req=new OrderReq();
		resp=orderService.AddOrder(req);
		
		return resp;
	}
}
