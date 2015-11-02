package com.edaisong.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.api.service.inter.IOrderGrabService;
import com.edaisong.api.service.inter.IOrderSubsidiesLogService;
import com.edaisong.api.service.inter.IPublicProvinceCityService;
import com.edaisong.entity.OrderGrab;
import com.edaisong.entity.OrderSubsidiesLog;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.AreaModel;
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
	
//	/**
//	 * 订单列表导出数据
//	 * @author zhaohl
//	 * @Date 20151102
//	 * @param superManPhone 骑士电话
//	 * @param superManName  骑士姓名
//	 * @param businessPhone  商户电话
//	 * @param businessName 商户姓名
//	 * @param orderStatus 订单状态
//	 * @param businessCity 城市
//	 * @param orderPubStart 开始时间
//	 * @param orderPubEnd 结束时间
//	 * @param groupId 集团id
//	 */
//	@RequestMapping(value="exportorder" )
//	public void exportorder(PagedOrderSearchReq searchReq,HttpServletResponse response)throws Exception{
//	   List<ExportOrder> records=	 orderService.exportOrder(searchReq) ;
//	   if(records.size() > 0){
//			//导出数据
//			String fileName = "e代送-%s-订单数据";
//			fileName = String.format(fileName, searchReq.getOrderPubStart() + "到" +searchReq.getOrderPubEnd());
//			ExcelExportData data = new ExcelUtils.ExcelExportData();
//			data.setTitles(new String[]{"商户提款流水记录"});
//			data.setColumnNames(new ArrayList<String[]>());
//			data.setFieldNames(new ArrayList<String[]>());
//			data.setDataMap(new LinkedHashMap<String, List<?>>());
//			//add data
//			data.getColumnNames().add(new String[]{"订单号","商户信息","骑士信息","发布时间","完成时间","订单金额",
//					"订单总金额","订单佣金","订单数量","外送费用","每单补贴","任务补贴","商家结算"});
//			data.getFieldNames().add(new String[]{"orderNo","businessInfo","clienterInfo","pubDate","actualDoneDate","amount"
//					,"totalAmount","orderCommission","orderCount","distribSubsidy","websiteSubsidy","adjustment","settleMoney"});
//			data.getDataMap().put(fileName, records);
//			byte[] datas = ExcelUtils.export2ByteArray(data);
//			response.setContentType("application/ms-excel");
//			response.setHeader("Content-Disposition", "attachment; filename="+new String((fileName+".xls").getBytes("utf-8"),"iso8859-1"));
//			response.setHeader("Content-Length",String.valueOf(datas.length));
//			response.getOutputStream().write(datas,0,datas.length);
//			return;
//		}else {
//			//如果查询到的数据为空,则跳转到收支详情页
//			String basePath = PropertyUtils.getProperty("java.admin.url");
//			response.sendRedirect(basePath+"/fastorder/list");
//		}
//	}
	/**
	 * 快单详情页面
	 * @author zhaohl
	 * @Date 20151102
	 * @return
	 */
	@RequestMapping("detail")
	public ModelAndView detail(Long orderid){
		ModelAndView model = new ModelAndView("adminView");
		OrderGrab orderListModel =orderGrabService.selectById(orderid);
	   if (orderListModel==null) {
		   throw new RuntimeException("没有找到orderid="+orderid+"的订单");
	   }
	    List<OrderSubsidiesLog> orderSubsidiesLogs=new ArrayList<>();
		model.addObject("subtitle", "订单列表");
		model.addObject("currenttitle", "订单详情");
		model.addObject("viewPath", "fastorder/detail");
		model.addObject("orderListModel", orderListModel);
		model.addObject("orderSubsidiesLogs", orderSubsidiesLogs);
		return model;
	}
}
