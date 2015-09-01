package com.edaisong.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.admin.common.UserContext;
import com.edaisong.api.service.inter.IGroupService;
import com.edaisong.api.service.inter.IOrderService;
import com.edaisong.api.service.inter.IOrderSubsidiesLogService;
import com.edaisong.api.service.inter.IPublicProvinceCityService;
import com.edaisong.entity.OrderSubsidiesLog;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.common.ResponseBase;
import com.edaisong.entity.domain.AreaModel;
import com.edaisong.entity.domain.OrderListModel;
import com.edaisong.entity.req.OptOrder;
import com.edaisong.entity.req.GroupReq;
import com.edaisong.entity.req.PagedOrderSearchReq;

@Controller
@RequestMapping("order")
public class OrderController {
	 @Autowired
	 private IOrderService orderService;
	 @Autowired
	 private IPublicProvinceCityService  iPublicProvinceCityService;
	 @Autowired
    private IGroupService iGroupService;
	 @Autowired
	 private IOrderSubsidiesLogService orderSubsidiesLogService;
	 @Autowired
	 private HttpServletRequest request;
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
	 * 订单列表页面   弹出 地图页面
	 * @author CaoHeYang
	 * @param searchWebReq
	 * @Date 20150827
	 * @return
	 */
	@RequestMapping(value="ordermap",method= {RequestMethod.POST})
	public ModelAndView ordermap(long orderid){
		ModelAndView view = new ModelAndView();
		view.addObject("model", orderService.getOrderMapDetail(orderid));
		view.addObject("viewPath", "order/ordermap");
		return view;
	}
	
	/**
	 * 订单详情页面
	 * @author CaoHeYang
	 * @Date 20150827
	 * @return
	 */
	@RequestMapping("detail")
	public ModelAndView detail(String orderno, int orderid){
		ModelAndView model = new ModelAndView("adminView");
	   OrderListModel orderListModel =orderService.getOrderByNoId(orderno, orderid);
	   if (orderListModel==null) {
		   throw new RuntimeException("没有找到orderno="+orderno+"的订单");
	   }
	   List<OrderSubsidiesLog> orderSubsidiesLogs=orderSubsidiesLogService.GetOrderOptionLog(orderid);
		model.addObject("subtitle", "订单列表");
		model.addObject("currenttitle", "订单详情");
		model.addObject("viewPath", "order/detail");
		model.addObject("orderListModel", orderListModel);
		model.addObject("orderSubsidiesLogs", orderSubsidiesLogs);
		return model;
	}
	
	/**
	 * 订单审核通过
	 * @author CaoHeYang
	 * @param orderid
	 * @Date 20150828
	 * @return
	 */
	@RequestMapping(value="auditok",method= {RequestMethod.POST})
	@ResponseBody
	public ResponseBase auditok(int orderid){
		OptOrder auditOkOrder=new OptOrder();
		auditOkOrder.setOrderId(orderid);
		auditOkOrder.setOptUserId(UserContext.getCurrentContext(request).getId());
		auditOkOrder.setOptUserName(UserContext.getCurrentContext(request).getName());
		ResponseBase responseBase= orderService.auditOk(auditOkOrder);
		return new ResponseBase();
	}
	/**
	 * 订单审核拒绝
	 * @author CaoHeYang
	 * @param auditOkOrder
	 * @Date 20150828
	 * @return
	 */
	@RequestMapping(value="auditrefuse",method= {RequestMethod.POST})
	@ResponseBody
	public ResponseBase auditrefuse(OptOrder auditrefuse){
		auditrefuse.setOptUserId(UserContext.getCurrentContext(request).getId());
		auditrefuse.setOptUserName(UserContext.getCurrentContext(request).getName());
		return new ResponseBase();
	}
	/**
	 * 取消订单
	 * 该方法在订单超时列表页也调用
	 * @author CaoHeYang
	 * @param auditOkOrder
	 * @Date 20150828
	 * @return
	 */
	@RequestMapping(value="cancelorder",method= {RequestMethod.POST})
	@ResponseBody
	public ResponseBase cancelorder(OptOrder cancelorder){
		cancelorder.setOptUserId(UserContext.getCurrentContext(request).getId());
		cancelorder.setOptUserName(UserContext.getCurrentContext(request).getName());
		ResponseBase responseBase= orderService.cancelOrder(cancelorder);
		return responseBase;
	}
}
