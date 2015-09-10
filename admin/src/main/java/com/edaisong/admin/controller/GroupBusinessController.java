package com.edaisong.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.api.service.inter.IGroupBusinessService;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.GroupBusinessModel;
import com.edaisong.entity.req.PagedGroupBusinessReq;

/**
 * 集团商户controller
 * @author  pengyi 
 * @date 2015年9月9日 上午10:20:39
 * @version 1.0
 * @parameter
 * @since
 */
@Controller
@RequestMapping("groupbusiness")
public class GroupBusinessController {
	@Autowired
	 private IGroupBusinessService  groupBusinessService;
	/*
	 * 集团商户管理
	 * WangChao
	 */
	@RequestMapping("list")
	public ModelAndView list(){ 
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "集团商户管理");
		model.addObject("currenttitle", "集团商户管理"); 
		model.addObject("viewPath", "groupbusiness/list");
		return model;
	}
	/*
	 * 集团管理列表
	 * WangChao
	 */
	@RequestMapping("listdo")
	public ModelAndView listdo(PagedGroupBusinessReq req) {	 
		PagedResponse<GroupBusinessModel> resp = groupBusinessService.getPageList(req); 
		ModelAndView model = new ModelAndView("groupbusiness/listdo");
		model.addObject("listData", resp);
		return model;
	}	
	
	
}
