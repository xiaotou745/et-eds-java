package com.edaisong.admin.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



import com.edaisong.api.service.inter.IPublicProvinceCityService;
import com.edaisong.entity.*;
import com.edaisong.entity.domain.*;

@Controller
@RequestMapping("openCityManager")
public class OpenCityManagerController {
	
	/**
	 * @author CaoHeYang
	 */
	@Autowired
    private IPublicProvinceCityService publicProvinceCityService;
	
	@RequestMapping("openCityManager")
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) {
	    String city="北京";
		List<OpenCityModel> citys=  publicProvinceCityService.getOpenCityList(city);
		
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "管理员");
		model.addObject("currenttitle", "开通城市管理");
		model.addObject("viewPath", "openCityManager/openCityManager");
		return model;
	}
}
