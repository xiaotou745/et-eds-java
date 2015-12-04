package com.edaisong.admin.controller;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.admin.common.UserContext;
import com.edaisong.api.service.inter.IAppVersionService;
import com.edaisong.api.service.inter.IGlobalConfigService;
import com.edaisong.api.service.inter.ITaskDistributionConfigService;
import com.edaisong.entity.BusinessMessage;
import com.edaisong.entity.GlobalConfig;
import com.edaisong.entity.TaskDistributionConfig;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.GlobalConfigModel;
import com.edaisong.entity.req.ConfigSaveReq;
import com.edaisong.entity.req.PagedGlobalConfigReq;
import com.edaisong.entity.req.TaskDistributionConfigReq;
import com.edaisong.entity.AppVersion;
import com.edaisong.entity.common.PagedRequestBase;

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
	@Autowired
	private ITaskDistributionConfigService taskDistributionConfigService;
	@Autowired
	IAppVersionService appVersionService;
	@RequestMapping("list")
	public ModelAndView globalConfigManager(HttpServletRequest request, HttpServletResponse res){
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "管理员");
		model.addObject("currenttitle", "公共配置管理");
		model.addObject("viewPath", "admintools/list");
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
	@RequestMapping(value="saveconfig",method = RequestMethod.POST)
	@ResponseBody
	public int saveConfig(ConfigSaveReq par){
		return globalConfigService.update(par);
	}
	/*添加全局变量值*/
	@RequestMapping("addconfig")
	@ResponseBody
	public int addConfig(GlobalConfig par){
		return globalConfigService.insert(par);
	}
	
	/**
	 * @author haichao 
	 * @date 2015年11月24日 15:45:23
	 * 普通任务配送费配置
	 * */
	@RequestMapping("taskdistributionconfig")
	public ModelAndView taskDistributionConfig (HttpServletRequest request, HttpServletResponse res)
	{
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "管理员");
		model.addObject("currenttitle", "普通任务配送费配置");
		model.addObject("viewPath", "admintools/taskdistributionconfig");
		List<TaskDistributionConfig> list = taskDistributionConfigService.query();
		model.addObject("listData", list);
		return model;
	}
	
	@RequestMapping("updatetaskdistributionconfig")
	@ResponseBody
	public int taskDistributionConfigDo(TaskDistributionConfigReq req,HttpServletRequest request){
		req.setOptId(UserContext.getCurrentContext(request).getId());
		req.setOptName(UserContext.getCurrentContext(request).getLoginName());
		return taskDistributionConfigService.update(req);
	}
	/**
	 * app版本控制
	 * @author hailongzhao
	 * @date 20151118
	 * @return
	 */
	@RequestMapping("appversion")
	public ModelAndView appversion() {
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "管理员");
		view.addObject("currenttitle", "版本控制");
		view.addObject("viewPath", "admintools/appversion");
		appVersionService.modify();
		return view;
	}
	@RequestMapping("appversiondo")
	public ModelAndView appversiondo(PagedRequestBase req) {
		ModelAndView view = new ModelAndView("admintools/appversiondo");
		PagedResponse<AppVersion> resp=appVersionService.queryAppVersion(req);
		view.addObject("listData", resp);
		return view;
	}

	@RequestMapping("cancelversionpublish")
	@ResponseBody
	public int cancelVersionPublish(int id, HttpServletRequest request) {
		UserContext context = UserContext.getCurrentContext(request);
		return appVersionService.cancel(id, context.getUserName());
	}

	@RequestMapping("getversionbyid")
	@ResponseBody
	public AppVersion getVersionById(int id, HttpServletRequest request) {
		return appVersionService.getByID(id);
	}

	@RequestMapping("saveversion")
	@ResponseBody
	public int saveVersion(AppVersion version, int opType,
			HttpServletRequest request) {
		UserContext context = UserContext.getCurrentContext(request);
		version.setCreateby(context.getUserName());
		version.setUpdateby(context.getUserName());

		if (opType == 3) {
			if (version.getIstiming() == 1) {
				version.setPubstatus(0);
			} else {
				version.setPubstatus(1);
				version.setTimingdate(new Date());
			}
			return appVersionService.insert(version);

		} else {
			return appVersionService.update(version);
		}
	}
}