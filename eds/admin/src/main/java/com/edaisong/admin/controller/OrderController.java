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
import com.edaisong.api.service.inter.IGroupService;
import com.edaisong.api.service.inter.IOrderService;
import com.edaisong.api.service.inter.IOrderSubsidiesLogService;
import com.edaisong.api.service.inter.IPublicProvinceCityService;
import com.edaisong.core.util.ExcelUtils;
import com.edaisong.core.util.JsonUtil;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.core.util.PropertyUtils;
import com.edaisong.core.util.StringUtils;
import com.edaisong.core.util.ExcelUtils.ExcelExportData;
import com.edaisong.entity.OrderSubsidiesLog;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.common.ResponseBase;
import com.edaisong.entity.domain.AreaModel;
import com.edaisong.entity.domain.ExportOrder;
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
			//导出数据
			String fileName = "e代送-%s-订单数据";
			fileName = String.format(fileName, searchReq.getOrderPubStart() + "到" +searchReq.getOrderPubEnd());
			ExcelExportData data = new ExcelUtils.ExcelExportData();
			data.setTitles(new String[]{"商户提款流水记录"});
			data.setColumnNames(new ArrayList<String[]>());
			data.setFieldNames(new ArrayList<String[]>());
			data.setDataMap(new LinkedHashMap<String, List<?>>());
			//add data
			data.getColumnNames().add(new String[]{"订单号","商户信息","骑士信息","发布时间","完成时间","订单金额",
					"订单总金额","订单佣金","订单数量","外送费用","每单补贴","任务补贴","商家结算"});
			data.getFieldNames().add(new String[]{"orderNo","businessInfo","clienterInfo","pubDate","actualDoneDate","amount"
					,"totalAmount","orderCommission","orderCount","distribSubsidy","websiteSubsidy","adjustment","settleMoney"});
			data.getDataMap().put(fileName, records);
			byte[] datas = ExcelUtils.export2ByteArray(data);
			response.setContentType("application/ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename="+new String((fileName+".xls").getBytes("utf-8"),"iso8859-1"));
			response.setHeader("Content-Length",String.valueOf(datas.length));
			response.getOutputStream().write(datas,0,datas.length);
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
}
