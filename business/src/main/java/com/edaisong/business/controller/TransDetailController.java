package com.edaisong.business.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.api.service.inter.IBusinessFinanceService;
import com.edaisong.business.entity.UserContext;
import com.edaisong.entity.BusinessBalanceRecord;
import com.edaisong.entity.common.PagedResponse;
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
	private IBusinessFinanceService businessFinanceService;

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
	public ModelAndView listdo(PagedTransDetailReq par,HttpServletRequest request) {
		ModelAndView view = new ModelAndView("transdetail/listdo");
		par.setBusinessid(UserContext.getCurrentContext(request).getBusiness().getId());
		PagedResponse<BusinessBalanceRecord> result=businessFinanceService.getTransDetailList(par);
		view.addObject("result", result);
		return view;
	}
}
