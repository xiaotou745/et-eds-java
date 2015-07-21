package com.edaisong.admin.controller;

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
	public ModelAndView list(AccountReq request, AccountResp response) {
		AccountReq req = new AccountReq();
		AccountResp resp = accountService.queryAccount(req);
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "管理后台用户管理");
		view.addObject("currenttitle", "管理后台用户管理");
		view.addObject("viewPath", "/account/list");
		view.addObject("listData", resp);
		return view;
	}
}
