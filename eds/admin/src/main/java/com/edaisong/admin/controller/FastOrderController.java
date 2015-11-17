package com.edaisong.admin.controller;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.api.service.inter.IOrderGrabService;
import com.edaisong.api.service.inter.IOrderSubsidiesLogService;
import com.edaisong.api.service.inter.IPublicProvinceCityService;
import com.edaisong.core.util.ExcelUtils;
import com.edaisong.core.util.HttpClientUtils;
import com.edaisong.core.util.HttpUtil;
import com.edaisong.core.util.PropertyUtils;
import com.edaisong.entity.OrderSubsidiesLog;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.AreaModel;
import com.edaisong.entity.domain.FastOrderDetail;
import com.edaisong.entity.domain.FastOrderExportModel;
import com.edaisong.entity.domain.FastOrderMapDetail;
import com.edaisong.entity.domain.FastOrderModel;
import com.edaisong.entity.req.PagedFastOrderSearchReq;


@Controller
@RequestMapping("fastorder")
public class FastOrderController {
	 @Autowired
	 private IOrderGrabService orderGrabService;
	 @Autowired
	 private IPublicProvinceCityService  iPublicProvinceCityService;
	 @Autowired
	 private IOrderSubsidiesLogService orderSubsidiesLogService;
	/**
	 * 快单列表页面 
	 * @author zhaohl
	 * @Date 20151102
	 * @return
	 */
	@RequestMapping("list")
	public ModelAndView order(){
		List<AreaModel> areaListData=iPublicProvinceCityService.getOpenCityListFromRedis();
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "订单管理");
		model.addObject("currenttitle", "快单列表");
		model.addObject("areaListData", areaListData);   //下拉城市  
		model.addObject("viewPath", "fastorder/list");
		return model;
	}
	
	/**
	 * 订单列表页面 
	 * @author zhaohl
	 * @Date 20151102
	 * @return
	 */
	@RequestMapping("listdo")
	public ModelAndView order(PagedFastOrderSearchReq searchReq){
		PagedResponse<FastOrderModel> resp = orderGrabService.query(searchReq);
		ModelAndView view = new ModelAndView();
		view.addObject("viewPath", "fastorder/listdo");
		view.addObject("listData", resp);
		return view;
	}
	/**
	 * 订单列表页面   弹出 地图页面
	 * @author zhaohl
	 * @Date 20151102
	 * @return
	 */
	@RequestMapping("ordermap")
	@ResponseBody
	public List<FastOrderMapDetail> ordermap(Long orderid){
		return orderGrabService.getMapDetailById(orderid);
	}
	
	/**
	 * 订单列表导出数据
	 * @author zhaohl
	 * @Date 20151102
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
	public void exportorder(PagedFastOrderSearchReq searchReq,HttpServletRequest request,HttpServletResponse response)throws Exception{
	   List<FastOrderExportModel> records=	 orderGrabService.exportOrder(searchReq) ;
	   if(records.size() > 0){
			//导出数据
			String fileName = "e代送-%s-智能调度数据";
			fileName = String.format(fileName, searchReq.getOrderGrabStart() + "到" +searchReq.getOrderGrabEnd());
			//add data
			LinkedHashMap<String,String> columnTitiles=new LinkedHashMap<String,String>();
			columnTitiles.put("订单号", "grabOrderNo");
			columnTitiles.put("商户电话", "businessPhoneNo");
			columnTitiles.put("商户名称", "businessName");
			columnTitiles.put("骑士电话", "clienterPhoneNo");
			columnTitiles.put("骑士名称", "clienterName");
			columnTitiles.put("抢单时间", "grabTime");
			columnTitiles.put("取货时间", "pickUpTime");
			columnTitiles.put("完成时间", "actualDoneDate");
			columnTitiles.put("订单佣金", "orderCommission");
			columnTitiles.put("订单数量", "orderCount");
			columnTitiles.put("外送费用", "distribSubsidy");
			columnTitiles.put("任务补贴", "adjustment");
//			columnTitiles.put("每单补贴", "websiteSubsidy");
//			columnTitiles.put("佣金比例", "commissionRate");
//			columnTitiles.put("基本佣金", "baseCommission");
			
			ExcelUtils.export2Excel(fileName,"智能调度记录",columnTitiles,records,request,response);
			return;
		}else {
			//如果查询到的数据为空,则跳转到收支详情页
			String basePath = PropertyUtils.getProperty("java.admin.url");
			response.sendRedirect(basePath+"/fastorder/list");
		}
	}
	/**
	 * 智能调度详情页面
	 * @author zhaohl
	 * @Date 20151102
	 * @return
	 */
	@RequestMapping("detail")
	public ModelAndView detail(Long orderid){
		ModelAndView model = new ModelAndView("adminView");
		FastOrderDetail detailModel =orderGrabService.selectById(orderid);
	   if (detailModel==null) {
		   throw new RuntimeException("没有找到orderid="+orderid+"的订单");
	   }
	  
	    List<OrderSubsidiesLog> orderSubsidiesLogs= orderSubsidiesLogService.GetFastOrderOptionLog(orderid);
		model.addObject("subtitle", "订单列表");
		model.addObject("currenttitle", "订单详情");
		model.addObject("viewPath", "fastorder/detail");
		model.addObject("detailModel", detailModel);
		model.addObject("orderSubsidiesLogs", orderSubsidiesLogs);
		return model;
	}
}
