package com.edaisong.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.api.service.inter.IAccountService;
import com.edaisong.entity.req.AccountReq;
import com.edaisong.entity.resp.AccountResp;
import com.edaisong.entity.resp.TestServiceResp;

@Controller
@RequestMapping("account")
public class AccountController {
 
	@Autowired
	private IAccountService accountService;

	@RequestMapping("list")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
		AccountReq req = new AccountReq();
		AccountResp resp = accountService.queryAccount(req);
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "管理后台用户管理");
		view.addObject("currenttitle", "管理后台用户管理");
		view.addObject("viewPath", "account/list");
//		view.addObject("listData", resp.getResultList());
		return view;
	}
	@RequestMapping("listdo")
	public ModelAndView listdo(HttpServletRequest request, HttpServletResponse response) {
		AccountReq req = new AccountReq();
		AccountResp resp = accountService.queryAccount(req);
		ModelAndView view = new ModelAndView();
		view.addObject("viewPath", "account/listdo");
		view.addObject("listData", resp.getResultList());
		return view;
	}
}
