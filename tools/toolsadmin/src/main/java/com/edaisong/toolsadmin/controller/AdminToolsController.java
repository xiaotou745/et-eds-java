package com.edaisong.toolsadmin.controller;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.toolsapi.redis.RedisService;
import com.edaisong.toolsapi.service.inter.IAuthorityMenuClassService;
import com.edaisong.toolscore.util.StringUtils;
import com.edaisong.toolsentity.AuthorityMenuClass;
import com.edaisong.toolsentity.common.ResponseBase;
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
	private RedisService redisService;
	@Autowired
	private IAuthorityMenuClassService authorityMenuClassService;

	@RequestMapping("redismanage")
	public ModelAndView redisManage() {
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "APP控制");
		view.addObject("currenttitle", "redis查询工具"); 
		view.addObject("viewPath", "admintools/redismanage");
		return view;
	}
	/**
	 * 根据key模糊查询或精确查询
	 * @param key
	 * @param sType（1是模糊查询，2是精确查询）
	 * @return
	 */
	@RequestMapping("redisdo")
	@ResponseBody
	public String redisdo(String key,String sType) {
		if(sType.equals("1")){
			Set<String> mSet=redisService.keys(key);
			return String.join(",", mSet);
		}else {
			return redisService.get(key, String.class, false);
		}
	}
	@RequestMapping("menulist")
	public ModelAndView menuList(Integer parId) {
		parId = (parId == null ? 0 : parId);
		List<AuthorityMenuClass> resp = authorityMenuClassService.getListMenuByParId(parId);
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "APP控制");
		view.addObject("currenttitle", "菜单管理");
		view.addObject("viewPath", "admintools/menulist");
		view.addObject("listData", resp);
		view.addObject("currentMenu", authorityMenuClassService.getMenuById(parId));
		return view;
	}
	
	@RequestMapping("addnewmenu")
	@ResponseBody
	public ResponseBase addNewMenu(AuthorityMenuClass req){
		ResponseBase resp = new ResponseBase();
		if(StringUtils.isEmpty(req.getMenuname())){
			resp.setMessage("请填写菜单名称");
		}else{
			int curId = req.getParid() == null ? 0 : req.getParid();
			req.setParid(curId);
			req.setBelock(false);
			authorityMenuClassService.addMenu(req);
			
			resp.setMessage("添加菜单成功");
			resp.setResponseCode(1);
		}
		
		
		return resp;
	}
	@RequestMapping("appversion")
	public ModelAndView appversion() {
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "APP控制");
		view.addObject("currenttitle", "版本控制");
		view.addObject("viewPath", "admintools/appversion");
		return view;
	}
	@RequestMapping("dbmanage")
	public ModelAndView dbmange() {
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "APP控制");
		view.addObject("currenttitle", "数据库配置");
		view.addObject("viewPath", "admintools/dbmanage");
		return view;
	}
	@RequestMapping("export")
	public ModelAndView export() {
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "APP控制");
		view.addObject("currenttitle", "导出数据管理");
		view.addObject("viewPath", "admintools/export");
		return view;
	}
	@RequestMapping("log")
	public ModelAndView log() {
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "APP控制");
		view.addObject("currenttitle", "日志分析");
		view.addObject("viewPath", "admintools/log");
		return view;
	}
	@RequestMapping("sql")
	public ModelAndView sql() {
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "APP控制");
		view.addObject("currenttitle", "sql工具");
		view.addObject("viewPath", "admintools/sql");
		return view;
	}
}