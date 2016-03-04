package com.edaisong.business.controller;

import java.util.Date;
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

import com.edaisong.api.service.inter.IBusinessBalanceRecordService;
import com.edaisong.api.service.inter.IBusinessService;
import com.edaisong.api.service.inter.IGroupBusinessRelationService;
import com.edaisong.api.service.inter.IOrderService;
import com.edaisong.entity.OrderChild;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.common.ResponseCode;
import com.edaisong.entity.domain.BusinessBalanceRecordModel;
import com.edaisong.entity.domain.OrderListModel;
import com.edaisong.entity.domain.RegionOrderDetail;
import com.edaisong.entity.domain.RegionOrderTotal;
import com.edaisong.entity.req.CancelOrderBusinessReq;
import com.edaisong.entity.req.OrderDetailBusinessReq;
import com.edaisong.entity.req.OrderReq;
import com.edaisong.entity.req.PagedBusinessBalanceRecordReq;
import com.edaisong.entity.req.PagedCustomerSearchReq;
import com.edaisong.entity.req.PagedOrderSearchReq;
import com.edaisong.entity.resp.BusinessBalanceInfoResp;
import com.edaisong.entity.resp.CancelOrderBusinessResp;
import com.edaisong.entity.resp.OrderDetailBusinessResp;
import com.edaisong.entity.resp.OrderResp;
import com.edaisong.business.common.UserContext;
import com.edaisong.core.enums.OrderFrom;
import com.edaisong.core.util.ExcelUtils;
import com.edaisong.core.util.JsonUtil;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.core.util.PropertyUtils;


@Controller
@RequestMapping("businessbalancerecord")
public class BusinessBalanceRecordController {
	@Autowired
	IBusinessBalanceRecordService businessBalanceRecordService;
	
	@Autowired
	private IGroupBusinessRelationService groupBusinessRelationService;
	
	/**
	 * 集团商户流水列表页面
	 * 
	 * @author 胡灵波
	 * @Date 2016年2月26日13:19:38
	 * @return
	 */
	@RequestMapping("grouplist")
	public ModelAndView grouplist(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("businessView");
		view.addObject("subtitle", "集团商户流水");
		view.addObject("currenttitle", "集团商户流水");
		view.addObject("viewPath", "businessbalancerecord/grouplist");
		String string=groupBusinessRelationService.getGroupBusListString(UserContext.getCurrentContext(request).getBusinessID());
		view.addObject("BusList",string );
		return view;
	}

	/**
	 * 集团商户流水列表页面
	 * 
	 * @author 胡灵波
	 * @Date 2016年2月26日13:39:35
	 * @return
	 */
	@RequestMapping("grouplistdo")
	public ModelAndView grouplistdo(Integer timeType,PagedBusinessBalanceRecordReq searchWebReq,HttpServletRequest request) {
		Date tDate=new Date();
		switch (timeType) {
		case 0://今天的商户流水
			searchWebReq.setOperateTimeStart(ParseHelper.ToDateString(tDate, "yyyy-MM-dd"));
			searchWebReq.setOperateTimeEnd(ParseHelper.ToDateString(ParseHelper.plusDate(tDate,2,1), "yyyy-MM-dd"));	
			break;
		case 1://7天的商户流水
			searchWebReq.setOperateTimeStart(ParseHelper.ToDateString(ParseHelper.plusDate(tDate,2,-7), "yyyy-MM-dd"));
			searchWebReq.setOperateTimeEnd(ParseHelper.ToDateString(ParseHelper.plusDate(tDate,2,1), "yyyy-MM-dd"));
			break;
		case 2://30天的商户流水
			searchWebReq.setOperateTimeStart(ParseHelper.ToDateString(ParseHelper.plusDate(tDate,1,-1), "yyyy-MM-dd"));
			searchWebReq.setOperateTimeEnd(ParseHelper.ToDateString(ParseHelper.plusDate(tDate,2,1), "yyyy-MM-dd"));
			break;
		default:
			break;
		}
		searchWebReq.setGroupBusinessId(UserContext.getCurrentContext(request).getBusinessID());
		PagedResponse<BusinessBalanceRecordModel> resp = businessBalanceRecordService.getGroupBalanceRecord(searchWebReq);
		ModelAndView view = new ModelAndView("businessbalancerecord/grouplistdo");
		view.addObject("listData", resp);
		return view;
	}

	/**
	 * 集团商户流水列表页面
	 * 
	 * @author 胡灵波
	 * @Date 2016年2月26日13:39:35
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("exportgrouplist")
	public void exportgrouplist(Integer timeType,PagedBusinessBalanceRecordReq searchWebReq,HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Date tDate=new Date();
		switch (timeType) {
		case 0://今天的商户流水
			searchWebReq.setOperateTimeStart(ParseHelper.ToDateString(tDate, "yyyy-MM-dd"));
			searchWebReq.setOperateTimeEnd(ParseHelper.ToDateString(ParseHelper.plusDate(tDate,2,1), "yyyy-MM-dd"));	
			break;
		case 1://7天的商户流水
			searchWebReq.setOperateTimeStart(ParseHelper.ToDateString(ParseHelper.plusDate(tDate,2,-7), "yyyy-MM-dd"));
			searchWebReq.setOperateTimeEnd(ParseHelper.ToDateString(ParseHelper.plusDate(tDate,2,1), "yyyy-MM-dd"));
			break;
		case 2://30天的商户流水
			searchWebReq.setOperateTimeStart(ParseHelper.ToDateString(ParseHelper.plusDate(tDate,1,-1), "yyyy-MM-dd"));
			searchWebReq.setOperateTimeEnd(ParseHelper.ToDateString(ParseHelper.plusDate(tDate,2,1), "yyyy-MM-dd"));
			break;
		default:
			break;
		}
		
		searchWebReq.setGroupBusinessId(UserContext.getCurrentContext(request).getBusinessID());
		List<BusinessBalanceRecordModel> records=	 businessBalanceRecordService.exportgrouplist(searchWebReq) ;
	    if(records.size() > 0){
				String fileName = "e代送-%s-集团交易流水数据";
				fileName = String.format(fileName, searchWebReq.getOperateTimeStart()+ "到" );
				LinkedHashMap<String, String> columnTitiles = new LinkedHashMap<String, String>();
				columnTitiles.put("交易类型", "strRecordtype");
				columnTitiles.put("订单号", "relationno");
				columnTitiles.put("门店名称", "BusinessName");
				columnTitiles.put("收支金额", "amount");				
				columnTitiles.put("余额", "balance");				
				columnTitiles.put("交易来源", "strForm");
				columnTitiles.put("交易状态", "strStatus");
				columnTitiles.put("交易时间", "operatetime");				
					ExcelUtils.export2Excel(fileName, "集团交易流水记录", columnTitiles,records, request, response);
				return;
			}else {
				String basePath = PropertyUtils.getProperty("java.business.url");
				response.sendRedirect(basePath+"/order/grouporderlist");
			}
	}
	
}
