package com.edaisong.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.api.service.inter.IQuartzService;
import com.edaisong.entity.QuartzServiceModel;

@Controller
@RequestMapping("service")
public class ServiceControler {

	@Autowired
	IQuartzService quartzService;

	/**
	 * @author haichao
	 * @date 2015年12月10日 10:15:58 获取所有服务列表
	 * */
	@RequestMapping("list")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) {
		List<QuartzServiceModel> list = quartzService.query();
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "服务管理");
		model.addObject("currenttitle", "服务管理");
		model.addObject("viewPath", "service/list");
		model.addObject("listData", list); // 获取所有服务列表

		return model;
	}

	@RequestMapping("listdo")
	public ModelAndView listdo() {
		List<QuartzServiceModel> list = quartzService.query();
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "服务管理");
		model.addObject("currenttitle", "服务管理");
		model.addObject("viewPath","service/listdo");
		model.addObject("listData", list); // 获取所有服务列表

		return model;
	}

	@ResponseBody
	@RequestMapping(value="updatestatus")
	public int updateStatus(int id, int status) {
		return quartzService.mainJob(id, status);
	}
}
