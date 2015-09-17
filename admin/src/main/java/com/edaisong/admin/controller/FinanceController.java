package com.edaisong.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.edaisong.api.service.inter.IOrderService;
import com.edaisong.api.service.inter.IPublicProvinceCityService;
import com.edaisong.entity.domain.AreaModel;
import com.edaisong.entity.req.PagedBusTaskListReq;

@Controller
@RequestMapping("finance")
public class FinanceController {
	@Autowired
	private IOrderService orderService;
	//开放城市
	 @Autowired
	 private IPublicProvinceCityService  publicProvinceCityService;
	@RequestMapping("bustasklist")
	public ModelAndView list() {
		List<AreaModel> areaListData=publicProvinceCityService.getOpenCityListFromRedis();
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("areaListData", areaListData);
		model.addObject("subtitle", "财务管理");
		model.addObject("currenttitle", "门店任务审核");
		model.addObject("viewPath", "finance/bustasklist");
		return model;
	}
	
	@RequestMapping("bustasklistdo")
	public ModelAndView listdo(PagedBusTaskListReq req) {
		ModelAndView model = new ModelAndView("bustasklistdo");
		model.addObject("listData",orderService.busTaskList(req));
		return model;
	}
}
