package com.edaisong.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("deliverycompany")
public class DeliveryCompanyController {
	 @Autowired
	//private DeliveryCompanyProvider deliveryCompanyProvider = new DeliveryCompanyProvider();
	 
	@RequestMapping("list")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response){
		return null;
	}
}

 