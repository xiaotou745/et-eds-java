package com.edaisong.admin.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.admin.common.UserContext;
import com.edaisong.api.service.inter.IAccountCityRelationService;
import com.edaisong.api.service.inter.IGroupBusinessService;
import com.edaisong.api.service.inter.IGroupService;
import com.edaisong.api.service.inter.IOrderService;
import com.edaisong.api.service.inter.IOrderSubsidiesLogService;
import com.edaisong.api.service.inter.IPublicProvinceCityService;
import com.edaisong.core.util.ExcelUtils;
import com.edaisong.core.util.JsonUtil;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.core.util.PropertyUtils;
import com.edaisong.core.util.StringUtils;
import com.edaisong.entity.GroupBusiness;
import com.edaisong.entity.OrderSubsidiesLog;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.common.ResponseBase;
import com.edaisong.entity.domain.AreaModel;
import com.edaisong.entity.domain.ExportOrder;
import com.edaisong.entity.domain.ExportShanSongOrder;
import com.edaisong.entity.domain.OrderListModel;
import com.edaisong.entity.domain.OrderMapDetail;
import com.edaisong.entity.domain.ShanSongOrderListModel;
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
	 @Autowired
	 private IAccountCityRelationService accountCityRelationService;
	 @Autowired
	 private IGroupBusinessService groupBusinessService;
	/**
	 * 订单列表页面 
	 * @author CaoHeYang
	 * @Date 20150728
	 * @return
	 */
	@RequestMapping("list")
	public ModelAndView order(HttpServletRequest request){
		List<AreaModel> areaListData=UserContext.getCurrentContext(request).getUserCity();
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "订单管理");
		model.addObject("currenttitle", "订单管理");
		model.addObject("areaListData", areaListData);   //下拉城市
		GroupReq groupReq = new GroupReq();
		groupReq.setIsValid(1);
		model.addObject("groupListData", iGroupService.getGroupList(groupReq));   //下拉集团   
		model.addObject("groupBusiness", groupBusinessService.get()); //获取集团商户  achao
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
		searchWebReq.setAuthCityStr(UserContext.getCurrentContext(request).getUserCityStr());
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
	public OrderMapDetail ordermap(int orderid){
		return orderService.getOrderMapDetail(orderid);
	}
	
	/**
	 * 订单列表导出数据
	 * @author CaoHeYang
	 * @date 20150906
	 * @param superManPhone 骑士电话
	 * @param superManName  骑士姓名
	 * @param businessPhone  商户电话
	 * @param businessName 商户姓名
	 * @param orderStatus 订单状态
	 * @param businessCity 城市
	 * @param orderPubStart 开始时间
	 * @param orderPubEnd 结束时间
	 * @param groupId 集团id
	 */
	@RequestMapping(value="exportorder" )
	public void exportorder(PagedOrderSearchReq searchReq,HttpServletResponse response)throws Exception{
	   List<ExportOrder> records=	 orderService.exportOrder(searchReq) ;
	   if(records.size() > 0){
			String fileName = "e代送-%s-订单数据";
			fileName = String.format(fileName, searchReq.getOrderPubStart()+ "到" + searchReq.getOrderPubEnd());
			LinkedHashMap<String, String> columnTitiles = new LinkedHashMap<String, String>();
			columnTitiles.put("订单号", "orderNo");
			columnTitiles.put("商户信息", "businessInfo");
			columnTitiles.put("骑士信息", "clienterInfo");
			columnTitiles.put("发布时间", "pubDate");
			columnTitiles.put("完成时间", "actualDoneDate");
			columnTitiles.put("订单金额", "amount");
			columnTitiles.put("订单总金额", "totalAmount");
			columnTitiles.put("订单佣金", "orderCommission");
			columnTitiles.put("订单数量", "orderCount");
			columnTitiles.put("外送费用", "distribSubsidy");
			columnTitiles.put("每单补贴", "websiteSubsidy");
			columnTitiles.put("任务补贴", "adjustment");
			columnTitiles.put("商家结算", "settleMoney");
			ExcelUtils.export2Excel(fileName, "商户提款流水记录", columnTitiles,records, request, response);
			return;
		}else {
			//如果查询到的数据为空,则跳转到收支详情页
			String basePath = PropertyUtils.getProperty("java.admin.url");
			response.sendRedirect(basePath+"/order/list");
		}
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
	    List<OrderSubsidiesLog> orderSubsidiesLogs=orderSubsidiesLogService.GetOrderOptionLog((long)orderid);
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
		auditOkOrder.setOptUserName(UserContext.getCurrentContext(request).getLoginName());
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
		auditrefuse.setOptUserName(UserContext.getCurrentContext(request).getLoginName());
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
		cancelorder.setOptUserName(UserContext.getCurrentContext(request).getLoginName());
		ResponseBase responseBase= orderService.cancelOrder(cancelorder);
		return responseBase;
	}
	
	
	
	/**
	 * 订单列表页面 
	 * @author CaoHeYang
	 * @Date 20150728
	 * @return
	 */
	@RequestMapping("shansonglist")
	public ModelAndView shansonglist(HttpServletRequest request){
		List<AreaModel> areaListData=UserContext.getCurrentContext(request).getUserCity();
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "订单管理");
		model.addObject("currenttitle", "E单列表");
		model.addObject("areaListData", areaListData);   //下拉城市
		model.addObject("viewPath", "order/shansonglist");
		return model;
	}

	/**
	 * 订单列表页面 
	 * @author CaoHeYang
	 * @Date 20150728
	 * @return
	 */
	@RequestMapping("shansonglistdo")
	public ModelAndView shansonglistdo(PagedOrderSearchReq searchWebReq,HttpServletRequest request){
		searchWebReq.setAuthCityStr(UserContext.getCurrentContext(request).getUserCityStr());
        PagedResponse<ShanSongOrderListModel> resp = orderService.getShanSongOrders(searchWebReq);
		ModelAndView view = new ModelAndView();
		view.addObject("viewPath", "order/shansonglistdo");
		view.addObject("listData", resp);
		return view;
	}
	/**
	 * E单列表导出数据
	 * @author CaoHeYang
	 * @date 20150906
	 * @param superManPhone 骑士电话
	 * @param superManName  骑士姓名
	 * @param businessPhone  商户电话
	 * @param businessName 商户姓名
	 * @param orderStatus 订单状态
	 * @param businessCity 城市
	 * @param orderPubStart 开始时间
	 * @param orderPubEnd 结束时间
	 * @param groupId 集团id
	 */
	@RequestMapping(value="exportshansongorder" )
	public void exportshansongorder(PagedOrderSearchReq searchReq,HttpServletResponse response)throws Exception{
	   List<ExportShanSongOrder> records=	 orderService.exportShanSongOrder(searchReq) ;
	   if(records.size() > 0){
			String fileName = "e代送-%s-E单数据";
			fileName = String.format(fileName, searchReq.getOrderPubStart()+ "到" + searchReq.getOrderPubEnd());
			LinkedHashMap<String, String> columnTitiles = new LinkedHashMap<String, String>();
			columnTitiles.put("订单号", "orderNo");
			columnTitiles.put("发货账号", "businessPhoneNo");
			columnTitiles.put("发货人姓名", "pubName");
			columnTitiles.put("发货人电话", "pubPhoneNo");
			columnTitiles.put("发货地址", "pickUpAddress");
			columnTitiles.put("收货人姓名", "receviceName");
			columnTitiles.put("收货人电话", "recevicePhoneNo");
			columnTitiles.put("收货人地址", "receviceAddress");
			columnTitiles.put("重量", "weight");
			columnTitiles.put("距离", "km");
			columnTitiles.put("配送费", "amount");
			columnTitiles.put("订单状态", "status");
			columnTitiles.put("支付方式", "payType");
			columnTitiles.put("取货码", "pickupCode");
			columnTitiles.put("发单时间", "pubDate");
			ExcelUtils.export2Excel(fileName, "闪送订单记录", columnTitiles,records, request, response);
			return;
		}else {
			String basePath = PropertyUtils.getProperty("java.admin.url");
			response.sendRedirect(basePath+"/order/shansonglist");
		}
	}
	
	/**
	 * 订单详情页面
	 * @author CaoHeYang
	 * @Date 20150827
	 * @return
	 */
	@RequestMapping("shansongdetail")
	public ModelAndView shansongdetail(String orderno, int orderid){
		ModelAndView model = new ModelAndView("adminView");
       ShanSongOrderListModel orderListModel =orderService.getShanSongOrderByNo(orderno);
	   if (orderListModel==null) {
		   throw new RuntimeException("没有找到orderno="+orderno+"的订单");
	   }
	    List<OrderSubsidiesLog> orderSubsidiesLogs=orderSubsidiesLogService.GetOrderOptionLog((long)orderid);
		model.addObject("subtitle", "订单管理");
		model.addObject("currenttitle", "E单详情");
		model.addObject("viewPath", "order/shansongdetail");
		model.addObject("orderListModel", orderListModel);
		model.addObject("orderSubsidiesLogs", orderSubsidiesLogs);
		model.addObject("isShowAuditBtn", isShowAuditBtn(orderListModel));
		return model;
	}
	
	/**
	 * 取消订单
	 * 该方法在订单超时列表页也调用
	 * @author CaoHeYang
	 * @param auditOkOrder
	 * @Date 20150828
	 * @return
	 */
	@RequestMapping(value="shansongcancelorder",method= {RequestMethod.POST})
	@ResponseBody
	public ResponseBase shansongcancelorder(OptOrder cancelorder){
		cancelorder.setOptUserId(UserContext.getCurrentContext(request).getId());
		cancelorder.setOptUserName(UserContext.getCurrentContext(request).getLoginName());
		ResponseBase responseBase= orderService.shanSongCancelOrder(cancelorder);
		return responseBase;
	}
	
	
}
