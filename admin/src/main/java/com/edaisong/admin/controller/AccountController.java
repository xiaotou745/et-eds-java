package com.edaisong.admin.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.api.service.inter.IAccountService;
import com.edaisong.api.service.inter.IDeliveryCompanyService;
import com.edaisong.api.service.inter.IPublicProvinceCityService;
import com.edaisong.entity.Account;
import com.edaisong.entity.DeliveryCompany;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.AreaModel;
import com.edaisong.entity.req.AccountReq;


//import java.util.function.Predicate;
@Controller
@RequestMapping("account")
public class AccountController {

	@Autowired
	private IAccountService accountService;
	@Autowired
	private IPublicProvinceCityService publicProvinceCityService;
	@Autowired
	private IDeliveryCompanyService deliveryCompanyService;

	@RequestMapping("list")
	public ModelAndView list() {
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "用户设置");
		view.addObject("currenttitle", "用户管理");

		List<AreaModel> listArea = publicProvinceCityService.getOpenCityByJiBie(1);
		List<DeliveryCompany> listDc = deliveryCompanyService
				.getDeliveryCompanyList();

		view.addObject("listArea", listArea);
		view.addObject("listDc", listDc);
		view.addObject("viewPath", "account/list");
		return view;
	}

	@RequestMapping("listdo")
	public ModelAndView list(AccountReq req) {
		PagedResponse<Account> resp = accountService.queryAccount(req);
		ModelAndView view = new ModelAndView();
		view.addObject("viewPath", "account/listdo");
		view.addObject("listData", resp);
		return view;
	}
}
