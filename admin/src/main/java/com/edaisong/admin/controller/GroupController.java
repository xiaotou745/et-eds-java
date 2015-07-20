package com.edaisong.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.api.service.inter.IGroupService;
import com.edaisong.entity.req.GroupReq;
import com.edaisong.entity.resp.GroupResp;

@Controller
@RequestMapping("group")
public class GroupController {
	 @Autowired
	 private IGroupService  groupService;
	 
	@RequestMapping("list")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response){
		
//		long num=1;
//		GroupReq req = new GroupReq();
//		req.setId(num);	
//	
//		GroupResp resp =groupService.getGroupListByID(req);
//		
////		ModelAndView model = new ModelAndView("Group/list");
////		model.addObject("subtitle", "管理员");
////		model.addObject("currenttitle", "集团管理");
////		model.addObject("listData", resp.getGroupList());
////		return model;
//		
//		ModelAndView model = new ModelAndView("adminView");
//		model.addObject("subtitle", "管理员");
//		model.addObject("currenttitle", "集团管理");
//		model.addObject("listData", resp.getGroupList());
//		model.addObject("viewPath", "group/list");
//		return model;
		
		
		GroupReq req=new GroupReq();		
		req.setGroupName("全时");
		
		GroupResp resp =groupService.getGroupList(req);		

		
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "管理员");
		model.addObject("currenttitle", "集团管理");
		model.addObject("listData", resp.getGroupList());
		model.addObject("viewPath", "group/list");
		return model;

	}
}
