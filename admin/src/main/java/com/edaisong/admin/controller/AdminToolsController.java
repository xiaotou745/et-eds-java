package com.edaisong.admin.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javassist.compiler.ast.Variable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.api.service.inter.IAdminToolsService;
import com.edaisong.api.service.inter.ITestService;
import com.edaisong.entity.domain.GlobalConfigModel;
import com.edaisong.entity.req.ConfigSaveReq;
import com.edaisong.entity.req.TestServiceReq;
import com.edaisong.entity.resp.TestServiceResp;
/*
 * 管理员工具
 * 茹化肖
 * 2015年7月20日13:31:55
 * */
@Controller
@RequestMapping("admintools")
public class AdminToolsController {
	@Autowired
	private IAdminToolsService adminToolsService;
	@RequestMapping("globalconfigmanager")
	public ModelAndView GlobalConfigManager(HttpServletRequest request, HttpServletResponse res)
	{
		List<GlobalConfigModel> data= adminToolsService.getGlobalConfigByGroupId(0);
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "管理员");
		model.addObject("currenttitle", "公共变量管理");
		model.addObject("viewPath", "admintools/globalconfigmanager");
		model.addObject("DataList",data);
		return model;
	}
	/*保存修改全局变量值*/
	@RequestMapping("saveconfig")
	@ResponseBody
	public Boolean SaveConfig(ConfigSaveReq par)
	{
		Boolean b= adminToolsService.saveConfig(par);
		return b;
	}

}