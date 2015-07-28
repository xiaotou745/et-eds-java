package com.edaisong.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.api.service.inter.IBusinessGroupService;
import com.edaisong.api.service.inter.IBusinessService;
import com.edaisong.api.service.inter.IGroupService;
import com.edaisong.api.service.inter.IPublicProvinceCityService;
import com.edaisong.entity.BusinessGroup;
import com.edaisong.entity.domain.AreaModel;
import com.edaisong.entity.req.BusinessReq;
import com.edaisong.entity.req.GroupReq;
import com.edaisong.entity.resp.BusinessResp;
import com.edaisong.entity.resp.GroupResp;

/*
 * 商户管理
 * 赵海龙
 * 2015年7月27
 * */
@Controller
@RequestMapping("business")
public class BusinessController {
	 @Autowired
	 private IBusinessService  iBusinessService;
	 
	 @Autowired
	 private IPublicProvinceCityService  iPublicProvinceCityService;
	 
	 @Autowired
	 private IBusinessGroupService  iBusinessGroupService;
	 
	 @Autowired
	 private IGroupService  iGroupService;
	 
	 @RequestMapping("business")
		public ModelAndView index(HttpServletRequest request, HttpServletResponse res) {
			BusinessReq req=null;
			req.setStatus(-1);
			req.setGroupId(0);
			req.setMealsSettleMode(-1);
			req.setCityAuthType(1);
			BusinessResp resp =iBusinessService.GetBusinessList(req);	
			
			GroupReq groupReq=new GroupReq();
			groupReq.setIsValid(1);
			GroupResp groupResp=iGroupService.getGroupList(groupReq);
			
			List<AreaModel> areaListData=iPublicProvinceCityService.getOpenCityListFromRedis();
			List<BusinessGroup> businessGroupListData=iBusinessGroupService.getBusinessGroupList();
			

			
			ModelAndView model = new ModelAndView("adminView");
			model.addObject("subtitle", "商户");
			model.addObject("currenttitle", "商户管理");
			model.addObject("groupId", 0);
			model.addObject("groupListData", groupResp.getGroupList());
			model.addObject("areaListData", areaListData);
			model.addObject("businessGroupListData", businessGroupListData);
			model.addObject("listData", resp.getResultList());
			model.addObject("viewPath", "business/index");
			return model;
	 }
	 @RequestMapping("list")
		public ModelAndView list(HttpServletRequest request, HttpServletResponse res) {
			BusinessReq req=new BusinessReq();	
			BusinessResp resp =iBusinessService.GetBusinessList(req);		
			
			ModelAndView model = new ModelAndView("business/list");
			model.addObject("listData", resp.getResultList());
			return model;
	 }
}
