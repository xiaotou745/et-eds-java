package com.edaisong.business.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.api.service.inter.IBusinessBalanceRecordService;
import com.edaisong.business.entity.UserContext;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.entity.BusinessBalanceRecord;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.OrderListModel;
import com.edaisong.entity.req.PagedCustomerSearchReq;
import com.edaisong.entity.req.PagedTransDetailReq;

/**
 * 商户中心控制器 2015年8月4日15:20:52 茹化肖
 * 
 * 交易明细
 * 
 * */
@Controller
@RequestMapping("transdetail")
public class TransDetailController {
	@Autowired
	private IBusinessBalanceRecordService businessBalanceRecordService;

	/**
	 * 交易明细页面 2015年8月4日15:20:28 茹化肖
	 * */
	@RequestMapping("list")
	public ModelAndView transdetail() {
		ModelAndView view = new ModelAndView("businessView");
		view.addObject("subtitle", "交易详情");
		view.addObject("currenttitle", "交易记录");
		view.addObject("viewPath", "transdetail/list");
		return view;
	}

	/**
	 * 交易明细列表 2015年8月4日15:22:26 茹化肖
	 * */
	@RequestMapping("listdo")
	public ModelAndView listdo(Integer timeType,PagedTransDetailReq searchWebReq,HttpServletRequest request) {
		Date tDate=new Date();
		switch (timeType) {
		case 0://今天的订单
			searchWebReq.setStartDate(ParseHelper.ToDateString(tDate, "yyyy-MM-dd"));
			searchWebReq.setEndDate(ParseHelper.ToDateString(ParseHelper.plusDate(tDate,2,1), "yyyy-MM-dd"));
			break;
		case 1://7天的订单
			searchWebReq.setStartDate(ParseHelper.ToDateString(ParseHelper.plusDate(tDate,2,-7), "yyyy-MM-dd"));
			searchWebReq.setEndDate(ParseHelper.ToDateString(ParseHelper.plusDate(tDate,2,1), "yyyy-MM-dd"));
			break;
		case 2://30天的订单
			searchWebReq.setStartDate(ParseHelper.ToDateString(ParseHelper.plusDate(tDate,1,-1), "yyyy-MM-dd"));
			searchWebReq.setEndDate(ParseHelper.ToDateString(ParseHelper.plusDate(tDate,2,1), "yyyy-MM-dd"));
			break;
		default:
			break;
		}
		searchWebReq.setBusinessID(UserContext.getCurrentContext(request).getBusiness().getId());
		PagedResponse<BusinessBalanceRecord> result=businessBalanceRecordService.getTransDetailList(searchWebReq);
		ModelAndView view = new ModelAndView("transdetail/listdo");
		view.addObject("result", result);
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
		PagedResponse<BusinessBalanceRecord> resp = businessBalanceRecordService.customerGetTransDetailList(req);
		ModelAndView view = new ModelAndView("transdetail/listdo");
		view.addObject("result", resp);
		return view;
	}
}
