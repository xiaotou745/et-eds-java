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
import com.edaisong.core.util.JsonUtil;
import com.edaisong.entity.OrderSubsidiesLog;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.common.ResponseBase;
import com.edaisong.entity.domain.AreaModel;
import com.edaisong.entity.domain.OrderListModel;
import com.edaisong.entity.domain.OrderMapDetail;
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
	@ResponseBody
	public String ordermap(int orderid){
		String str= JsonUtil.obj2string(orderService.getOrderMapDetail(orderid)) ; 
		return str;
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
		model.addObject("isShowAuditBtn", isShowAuditBtn(orderListModel));
		return model;
	}
	
	/**
	 * 订单详情页面是否显示审核按钮
	 * @author CaoHeYang
	 * @param orderModel
	 * @date 20150901
	 * @return
	 */
	  private boolean isShowAuditBtn(OrderListModel orderModel)
      {
          //只有在已完成订单并且已上传完小票的情况下显示该按钮
          if (orderModel != null && /*已完成*/ orderModel.getFinishAll() == 1 && /*订单未分账*/ orderModel.getIsJoinWithdraw() == 0
              && orderModel.getIsEnable() == 1)
          {
              return true;
          }
          return false;
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
	public ResponseBase auditok(OptOrder auditOkOrder){
		auditOkOrder.setOptUserId(UserContext.getCurrentContext(request).getId());
		auditOkOrder.setOptUserName(UserContext.getCurrentContext(request).getName());
		ResponseBase responseBase= orderService.auditOk(auditOkOrder);
		return responseBase;
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
		ResponseBase responseBase= orderService.auditRefuse(auditrefuse);
		return responseBase;
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
