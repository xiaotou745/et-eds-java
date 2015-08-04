package com.edaisong.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.api.service.inter.IGroupService;
import com.edaisong.api.service.inter.IOrderService;
import com.edaisong.api.service.inter.IPublicProvinceCityService;
import com.edaisong.api.service.inter.ITestService;
import com.edaisong.core.common.HtmlHelper;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.AreaModel;
import com.edaisong.entity.domain.GroupModel;
import com.edaisong.entity.domain.OrderListModel;
import com.edaisong.entity.req.GroupReq;
import com.edaisong.entity.req.PagedOrderSearchReq;

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
	 
	 @Autowired
	 private IPublicProvinceCityService  iPublicProvinceCityService;
	 @Autowired
    private IGroupService iGroupService;
	/**
	 * 订单列表页面 
	 * @author CaoHeYang
	 * @Date 20150728
	 * @return
	 */
	@RequestMapping("list")
	public ModelAndView order(){
		List<AreaModel> areaListData=iPublicProvinceCityService.getOpenCityListFromRedis();
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "订单管理");
		model.addObject("currenttitle", "订单管理");
		model.addObject("areaListData", areaListData);   //下拉城市
		GroupReq groupReq = new GroupReq();
		groupReq.setIsValid(1);
		model.addObject("groupListData", iGroupService.getGroupList(groupReq));   //下拉集团   
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
	
	/**
	 * 
	 * @param searchWebReq
	 * @return
	 */
	@RequestMapping(value="ordermap",method= {RequestMethod.POST})
	public ModelAndView ordermap(long orderid){
		ModelAndView view = new ModelAndView();
		view.addObject("model", orderService.getOrderMapDetail(orderid));
		view.addObject("viewPath", "order/ordermap");
		return view;
	}
	
}
