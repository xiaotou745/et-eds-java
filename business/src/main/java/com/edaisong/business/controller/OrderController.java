package com.edaisong.business.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.api.service.impl.OrderService;
import com.edaisong.api.service.inter.IOrderService;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.AreaModel;
import com.edaisong.entity.domain.OrderListModel;
import com.edaisong.entity.req.CancelOrderBusinessReq;
import com.edaisong.entity.req.GroupReq;
import com.edaisong.entity.req.OrderDetailBusinessReq;
import com.edaisong.entity.req.OrderReq;
import com.edaisong.entity.req.PagedOrderSearchReq;
import com.edaisong.entity.resp.CancelOrderBusinessResp;
import com.edaisong.entity.resp.OrderResp;
import com.edaisong.api.service.inter.IClienterService;
import com.edaisong.core.util.JsonUtil;
import com.edaisong.core.util.NumberHelper;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.entity.resp.BusinessLoginResp;

@Controller
@RequestMapping("order")
public class OrderController {
	@Autowired
	IOrderService orderService;

	/**
	 * 订单列表页面
	 * 
	 * @author zhaohailong
	 * @Date 20150806
	 * @return
	 */
	@RequestMapping("list")
	public ModelAndView list() {
		ModelAndView view = new ModelAndView("businessView");
		view.addObject("subtitle", "订单");
		view.addObject("currenttitle", "订单管理");
		view.addObject("viewPath", "order/list");
		return view;
	}

	/**
	 * 订单列表页面
	 * 
	 * @author zhaohailong
	 * @Date 20150806
	 * @return
	 */
	@RequestMapping("listdo")
	public ModelAndView listdo(Integer timeType,PagedOrderSearchReq searchWebReq) {
		Date tDate=new Date();
		switch (timeType) {
		case 0://今天的订单
			searchWebReq.setOrderPubStart(ParseHelper.ToDateString(tDate, "yyyy-MM-dd"));
			searchWebReq.setOrderPubEnd(ParseHelper.ToDateString(ParseHelper.plusDate(tDate,2,1), "yyyy-MM-dd"));
			break;
		case 1://7天的订单
			searchWebReq.setOrderPubStart(ParseHelper.ToDateString(ParseHelper.plusDate(tDate,2,-7), "yyyy-MM-dd"));
			searchWebReq.setOrderPubEnd(ParseHelper.ToDateString(ParseHelper.plusDate(tDate,2,1), "yyyy-MM-dd"));
			break;
		case 2://30天的订单
			searchWebReq.setOrderPubStart(ParseHelper.ToDateString(ParseHelper.plusDate(tDate,1,-1), "yyyy-MM-dd"));
			searchWebReq.setOrderPubEnd(ParseHelper.ToDateString(ParseHelper.plusDate(tDate,2,1), "yyyy-MM-dd"));
			break;
		default:
			break;
		}
		PagedResponse<OrderListModel> resp = orderService
				.getOrders(searchWebReq);
		ModelAndView view = new ModelAndView("order/listdo");
		view.addObject("listData", resp);
		return view;
	}

	/**
	 * 订单详情
	 * 
	 * @author CaoHeYang
	 * @Date 20150805
	 * @return
	 */
	@RequestMapping(value = "detail", method = { RequestMethod.GET })
	public ModelAndView detail(String orderno) {
		 String numberString=NumberHelper.getcode();
		
		
		OrderDetailBusinessReq req=new OrderDetailBusinessReq();
		req.setOrderNo(orderno);
		req.setBusinessId(2047);
		ModelAndView model = new ModelAndView("businessView");
		model.addObject("subtitle", "订单中心");
		model.addObject("currenttitle", "订单详情");
		model.addObject("viewPath", "order/detail");
		model.addObject("modelDatas", orderService.getOrderDetailBusiness(req));
		return model;
	}

	/**
	 * 商户后台取消订单
	 * 
	 * @author CaoHeYang
	 * @Date 20150805
	 * @return
	 */
	@RequestMapping(value = "canelorder", method = { RequestMethod.POST })
	@ResponseBody
	public CancelOrderBusinessResp login(@RequestBody CancelOrderBusinessReq req) {
		CancelOrderBusinessResp resp = orderService.cancelOrderBusiness(req);
		return resp;
	}

	/**
	 * 商户后台发布订单
	 * 
	 * @author 胡灵波
	 * @Date 2015年8月6日 13:37:00
	 * @return
	 */
	@RequestMapping(value = "publish")
	public ModelAndView publish(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("businessView");
		model.addObject("subtitle", "订单中心");
		model.addObject("currenttitle", "发布任务");
		model.addObject("viewPath", "order/publish");
		return model;

	}

	/**
	 * 确定发布订单
	 * 
	 * @author 胡灵波
	 * @Date 2015年8月11日 14:09:32
	 * @return
	 */
	@RequestMapping(value = "add", method = { RequestMethod.POST })
	@ResponseBody
	public OrderResp add() {
		OrderResp resp = new OrderResp();
		OrderReq req = new OrderReq();
		resp = orderService.AddOrder(req);

		return resp;
	}
}
