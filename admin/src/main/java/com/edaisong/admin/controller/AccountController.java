package com.edaisong.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.api.service.impl.PublicProvinceCityService;
import com.edaisong.api.service.inter.IAccountService;
import com.edaisong.api.service.inter.IPublicProvinceCityService;
import com.edaisong.entity.Account;
import com.edaisong.entity.common.ResponsePageList;
import com.edaisong.entity.domain.AreaModel;
import com.edaisong.entity.req.AccountReq;
import com.edaisong.entity.resp.AccountResp;
import com.edaisong.entity.resp.TestServiceResp;

@Controller
@RequestMapping("account")
public class AccountController {

	@Autowired
	private IAccountService accountService;
	
	@Autowired
	private IPublicProvinceCityService iPublicProvinceCityService;
	

	@RequestMapping("list")
	public ModelAndView list() {
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "用户设置");
		view.addObject("currenttitle", "用户管理");
		List<AreaModel> listArea=iPublicProvinceCityService.getOpenCityListFromRedis();
		view.addObject("listArea", listArea);
		view.addObject("viewPath", "account/list");
		return view;
	}

	@RequestMapping("listdo")
	public ModelAndView list(AccountReq req) {
		ResponsePageList<Account> resp = accountService.queryAccount(req);
		ModelAndView view = new ModelAndView();
		view.addObject("viewPath", "account/listdo");
		view.addObject("listData", resp);
		return view;
	}
}
