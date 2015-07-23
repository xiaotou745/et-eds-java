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
	public ModelAndView list() {
		ModelAndView view = new ModelAndView();
		view.addObject("viewPath", "account/list");
		return view;
	}
	@RequestMapping("listdo")
	public ModelAndView list(AccountReq req) {
		req = new AccountReq();
		AccountResp resp = accountService.queryAccount(req);
		ModelAndView view = new ModelAndView();
		view.addObject("viewPath", "account/listdo");
		view.addObject("listData", resp.getResultList());
		return view;
	}
}
