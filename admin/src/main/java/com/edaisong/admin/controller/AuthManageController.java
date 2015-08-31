package com.edaisong.admin.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.api.service.inter.IAccountService;
import com.edaisong.api.service.inter.IAuthorityMenuClassService;
import com.edaisong.entity.Account;
import com.edaisong.entity.AuthorityMenuClass;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.PagedAccountReq;

@Controller
@RequestMapping("authmanage")
public class AuthManageController {
	@Autowired
	private IAccountService accountService;
	@Autowired
	private IAuthorityMenuClassService authorityMenuClassService;
	@RequestMapping("list")
	public ModelAndView list(){		
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "管理员");
		model.addObject("currenttitle", "个人账户权限管理");
		model.addObject("viewPath", "authmanage/list");
		List<AuthorityMenuClass> menuList = authorityMenuClassService.getMenuList();
		model.addObject("menuList", menuList);
		return model;
	}
	@RequestMapping("listdo")
	public ModelAndView listdo(PagedAccountReq req) {
		PagedResponse<Account> resp = accountService.queryAccount(req);
		ModelAndView model = new ModelAndView("authmanage/listdo");
		model.addObject("listData", resp);
		return model;
	}
}
