package com.edaisong.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.api.service.inter.IGroupApiConfigService;
import com.edaisong.api.service.inter.IGroupService;
import com.edaisong.entity.Group;
import com.edaisong.entity.GroupApiConfig;
import com.edaisong.entity.domain.GroupModel;
import com.edaisong.entity.req.GroupReq;


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
		List<GroupModel> resultList =groupService.getGroupList(req);		
		
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "管理员");
		model.addObject("currenttitle", "集团管理");
		model.addObject("listData", resultList);
		model.addObject("viewPath", "group/list");
		return model;		
	}
	
	@RequestMapping("selectlist")
	@ResponseBody
	public ModelAndView selectlist(HttpServletRequest request, HttpServletResponse response){
				
		String groupName=request.getParameter("groupname");
		String appkey=request.getParameter("appkey");
		
		GroupReq req=new GroupReq();	
		if(groupName!=null && groupName!="")
			req.setGroupName(groupName);		
		if(appkey!=null && appkey!="")
			req.setAppKey(appkey);	
		List<GroupModel> resultList =groupService.getGroupList(req);				
		//HashMap map = new HashMap();
		//map.put("list", resp);		
		//ModelAndView model = new ModelAndView("group/GroupManagerList",map);
		
		ModelAndView model = new ModelAndView("group/grouplistdo");
		model.addObject("listData", resultList);
		return model;		
	}	
	
	@RequestMapping("addgroup")		
	//@RequestMapping(method = RequestMethod.POST) 
	//@RequestMapping(value="addgroup",method = RequestMethod.POST)
	@ResponseBody
	public String addgroup(@ModelAttribute("group") Group group){		
	
		Group record=group;		
		record.setCreatename("admin");			
		groupService.add(record);	
		return "ok";  
	}
	@RequestMapping("updategroup")
	@ResponseBody
	public String updategroup(@ModelAttribute("group") Group group){

		groupService.update(group);			
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
		groupService.update(record);				

		return "ok";  
	}	

	@RequestMapping("addgroupapiconfig")
	@ResponseBody
	public String addgroupapiconfig(@ModelAttribute("groupapiconfig") GroupApiConfig groupapiconfig){
		
		GroupApiConfig record=groupapiconfig;
		record.setAppsecret("");
		groupApiConfigService.add(record);				

		return "ok";  
	}
	
}
