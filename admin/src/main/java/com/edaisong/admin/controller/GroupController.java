package com.edaisong.admin.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.api.service.impl.GroupApiConfigService;
import com.edaisong.api.service.inter.IGroupApiConfigService;
import com.edaisong.api.service.inter.IGroupService;
import com.edaisong.entity.Group;
import com.edaisong.entity.GroupApiConfig;
import com.edaisong.entity.req.GroupReq;
import com.edaisong.entity.resp.GroupResp;

@Controller
@RequestMapping("group")
public class GroupController {
	 @Autowired
	 private IGroupService  groupService;
	 @Autowired
	 private IGroupApiConfigService groupApiConfigService;
	 
	@RequestMapping("list")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response){
		
		GroupReq req=new GroupReq();	
		GroupResp resp =groupService.getGroupList(req);		
		
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "管理员");
		model.addObject("currenttitle", "集团管理");
		model.addObject("listData", resp.getGroupList());
		model.addObject("viewPath", "group/GroupManager");
		return model;		
	}
	
	@RequestMapping("selectlist")
	@ResponseBody
	public ModelAndView Selectlist(HttpServletRequest request, HttpServletResponse response){
				
		String groupName=request.getParameter("groupname");
		String appkey=request.getParameter("appkey");
		
		GroupReq req=new GroupReq();	
		if(groupName!=null && groupName!="")
			req.setGroupName(groupName);		
		if(appkey!=null && appkey!="")
			req.setAppKey(appkey);	
		GroupResp resp =groupService.getGroupList(req);				
		//HashMap map = new HashMap();
		//map.put("list", resp);		
		//ModelAndView model = new ModelAndView("group/GroupManagerList",map);
		
		ModelAndView model = new ModelAndView("group/GroupManagerList");
		model.addObject("listData", resp.getGroupList());
		return model;		
	}
	
	@RequestMapping("addgroup")
	@ResponseBody
	public String addgroup(HttpServletRequest request, HttpServletResponse response){

		String groupName=request.getParameter("groupname");		
		Group record=new Group();		
		record.setGroupname(groupName);
		record.setCreatename("admin");		
		groupService.Add(record);				

		return "ok";  
	}
	
	@RequestMapping("updategroup")
	@ResponseBody
	public String updategroup(HttpServletRequest request, HttpServletResponse response){

		Long id=Long.parseLong(request.getParameter("id"));	
		String groupName=request.getParameter("groupname");		
		Group record=new Group();
		record.setId(id);;	
		record.setGroupname(groupName);		
		groupService.Update(record);				

		return "ok";  
	}
	
	
	@RequestMapping("updatestatus")
	@ResponseBody
	public String updatestatus(HttpServletRequest request, HttpServletResponse response){

		Long id=Long.parseLong(request.getParameter("id"));		
		String status= request.getParameter("status");
		if(status.equals("1"))
		{
			status="0";
		}
		else
		{
			 status="1";
		}
		
		byte bstatus=Byte.parseByte(status);		
		Group record=new Group();
		record.setId(id);;	
		record.setIsvalid(bstatus);;		
		groupService.Update(record);				

		return "ok";  
	}	

	@RequestMapping("addgroupapiconfig")
	@ResponseBody
	public String addgroupapiconfig(HttpServletRequest request, HttpServletResponse response){

		String appkey=request.getParameter("appkey");
		String appversion=request.getParameter("appversion");		
		long groupid=Long.parseLong(request.getParameter("groupid"));		
		
		GroupApiConfig record=new GroupApiConfig();		
		record.setAppkey(appkey);
		record.setAppsecret("");
		record.setAppversion(appversion);
		record.setGroupid(groupid);
		
		groupApiConfigService.Add(record);				

		return "ok";  
	}
	
}
