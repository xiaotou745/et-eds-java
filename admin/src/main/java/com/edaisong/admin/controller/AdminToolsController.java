package com.edaisong.admin.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.api.service.inter.IGlobalConfigService;
import com.edaisong.entity.BusinessMessage;
import com.edaisong.entity.GlobalConfig;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.GlobalConfigModel;
import com.edaisong.entity.req.ConfigSaveReq;
import com.edaisong.entity.req.PagedGlobalConfigReq;
/*
 * 管理员工具
 * 茹化肖
 * @modify:pengyi
 * 2015年7月20日13:31:55
 * */
@Controller
@RequestMapping("admintools")
public class AdminToolsController {
	@Autowired
	private IGlobalConfigService globalConfigService;
//	@Autowired
//	private IBusinessFinanceService businessFinanceService;
	@RequestMapping("list")
	public ModelAndView globalConfigManager(HttpServletRequest request, HttpServletResponse res){
//		TransDetailReq par=new TransDetailReq();
//		par.setBusinessid(1791);
//		par.setCurrentPage(1);
//		par.setEndDate("");
//		par.setStartDate("");
//		par.setNumType("1");
//		par.setTransType("");
//		par.setNumString("1791150522202221066");
//		businessFinanceService.getTransDetailList(par);
		List<GlobalConfigModel> data= globalConfigService.getGlobalConfigByGroupId(0);
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "管理员");
		model.addObject("currenttitle", "公共变量管理");
		model.addObject("viewPath", "admintools/globalconfigmanager");
		model.addObject("DataList",data);
		return model;
	}
	
	@RequestMapping("globalconfigmanagerlistdo")
	public ModelAndView listdo(PagedGlobalConfigReq searchWebReq,HttpServletRequest request) {
		ModelAndView view = new ModelAndView("admintools/globalconfigmanagerlistdo");
		PagedResponse<GlobalConfigModel> resp = globalConfigService.getPagedGlobalConfigModels(searchWebReq);
		view.addObject("listData", resp);
		return view;
	}
	
	/*保存修改全局变量值*/
	@RequestMapping("saveconfig")
	@ResponseBody
	public Boolean saveConfig(ConfigSaveReq par){
		return globalConfigService.update(par)>0;
	}
	/*添加全局变量值*/
	@RequestMapping("addconfig")
	public Boolean addConfig(GlobalConfig par){
		return globalConfigService.insert(par)>0;
	}

}