package com.edaisong.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edaisong.api.service.inter.IBusinessWithdrawFormService;
import com.edaisong.entity.domain.BusinessWithdrawFormModel;

@RequestMapping("businesswithdrawform")
@Controller
public class BusinessWithdrawFormController {
	@Autowired
	private IBusinessWithdrawFormService businessWithdrawFormService;
	@RequestMapping("businesswithdrawdetail")
	public @ResponseBody BusinessWithdrawFormModel businessWithdrawDetail(String withwardId){
		return businessWithdrawFormService.getBusinessWithdrawListById(withwardId);
	}
}
