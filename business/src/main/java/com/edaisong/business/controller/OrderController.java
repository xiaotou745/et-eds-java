package com.edaisong.business.controller;

import java.lang.Double;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.api.service.impl.OrderService;
import com.edaisong.api.service.inter.IBusinessService;
import com.edaisong.api.service.inter.IOrderService;
import com.edaisong.entity.OrderChild;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.common.ResponseCode;
import com.edaisong.entity.domain.AreaModel;
import com.edaisong.entity.domain.OrderListModel;
import com.edaisong.entity.req.CancelOrderBusinessReq;
import com.edaisong.entity.req.GroupReq;
import com.edaisong.entity.req.OrderDetailBusinessReq;
import com.edaisong.entity.req.OrderReq;
import com.edaisong.entity.req.PagedCustomerSearchReq;
import com.edaisong.entity.req.PagedOrderSearchReq;
import com.edaisong.entity.resp.CancelOrderBusinessResp;
import com.edaisong.entity.resp.OrderDetailBusinessResp;
import com.edaisong.entity.resp.OrderResp;
import com.edaisong.api.service.inter.IClienterService;
import com.edaisong.business.entity.UserContext;
import com.edaisong.core.enums.OrderFrom;
import com.edaisong.core.util.JsonUtil;
import com.edaisong.core.util.NumberHelper;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.entity.resp.BusinessLoginResp;

@Controller
@RequestMapping("order")
public class OrderController {
	@Autowired
	IOrderService orderService;
	@Autowired
	IBusinessService businessService;

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
	public ModelAndView listdo(Integer timeType,PagedOrderSearchReq searchWebReq,HttpServletRequest request) {
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
		searchWebReq.setBusinessID(UserContext.getCurrentContext(request).getBusiness().getId());
		PagedResponse<OrderListModel> resp = orderService
				.getOrders(searchWebReq);
		ModelAndView view = new ModelAndView("order/listdo");
		view.addObject("listData", resp);
		return view;
	}
	/**
	 * 商家中心订单列表页面右上角自定义查询
	 * @author zhaohailong
	 * @Date 20150806
	 * @return
	 */
	@RequestMapping("customerlistdo")
	public ModelAndView customerlistdo(String search,HttpServletRequest request) {
		PagedCustomerSearchReq req=new PagedCustomerSearchReq();
		req.setBusinessID(UserContext.getCurrentContext(request).getBusiness().getId());
		req.setSearch(search);
		PagedResponse<OrderListModel> resp = orderService.customerGetOrders(req);
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
	public ModelAndView detail(String orderno,HttpServletRequest request) {
		OrderDetailBusinessReq req=new OrderDetailBusinessReq();
		req.setOrderNo(orderno);
		req.setBusinessId(UserContext.getCurrentContext(request).getBusiness().getId());
		ModelAndView model = new ModelAndView("businessView");
		model.addObject("subtitle", "订单中心");
		model.addObject("currenttitle", "订单详情");
		model.addObject("viewPath", "order/detail");
		OrderDetailBusinessResp resp=orderService.getOrderDetailBusiness(req);
		if (resp==null||resp.getOrderModel()==null||resp.getOrderChilds()==null||resp.getOrderChilds().size()==0) {
			throw new RuntimeException("没有找到orderno="+orderno+"的订单");
		}
		model.addObject("modelDatas", resp);
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
	public CancelOrderBusinessResp canelorder(CancelOrderBusinessReq req,HttpServletRequest request) {
		req.setBusinessId(UserContext.getCurrentContext(request).getBusiness().getId());
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
		model.addObject("businessModel",businessService.getBusiness(UserContext.getCurrentContext(request).getBusiness().getId()));
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
	public OrderResp add(OrderReq req,HttpServletRequest request) {
		req.setListOrderChild(JsonUtil.str2list(req.getChildstr(),OrderChild.class));  //序列化得到子订单信息汇总
		OrderResp resp = new OrderResp();
		UserContext context = UserContext.getCurrentContext(request);
		if (context==null||context.getBusiness()==null||context.getBusiness().getId()<=0) {
			resp.setResponseCode(ResponseCode.BUSINESS_FAILURE_ERROR);
			resp.setMessage("没有获取到登录信息，请重新登录");
			return resp;
		}
		req.setBusinessid(context.getBusiness().getId());
		resp = orderService.AddOrder(req);

		return resp;
	}
}
