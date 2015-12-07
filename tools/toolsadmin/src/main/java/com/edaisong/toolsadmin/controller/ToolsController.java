package com.edaisong.toolsadmin.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.toolsapi.redis.RedisService;
import com.edaisong.toolscore.util.AjaxResult;
import com.edaisong.toolscore.util.JsonUtil;
import com.edaisong.toolscore.util.HttpClientUtils.HttpMethod;

@Controller
@RequestMapping("tools")
public class ToolsController {
	@Autowired
	private RedisService redisService;

	@RequestMapping("redis")
	public ModelAndView redis() {
		ModelAndView model = new ModelAndView("adminView");

		model.addObject("subtitle", "常用工具");
		model.addObject("currenttitle", "Redis管理");
		model.addObject("viewPath", "tools/redis");
		model.addObject("footerJs", "tools/_redisJs");

		Set<String> allKeys = redisService.allKeys();
		String suffxKey = redisService.getSuffxKey();
		model.addObject("dataOfKeys", allKeys);
		model.addObject("dataOfSuffxKey", suffxKey);
		return model;
	}

	@RequestMapping(value = "redis/remove", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult redisRemove(String key) {
		redisService.remove(key);
		return AjaxResult.Success();
	}

	@RequestMapping(value = "redis/get", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult redisGet(String key) {
		Object value = redisService.get(key, Object.class);
		return AjaxResult.Success(JsonUtil.obj2string(value));
	}

	@RequestMapping("redis/query")
	public ModelAndView redisQuery(String key) {
		ModelAndView model = new ModelAndView("tools/_redislist");
		String suffxKey = redisService.getSuffxKey();
		Set<String> allKeys = new HashSet<String>();
		if (StringUtils.isEmpty(key)) {
			allKeys = redisService.allKeys();
		} else {
			allKeys = redisService.keys(suffxKey + key + "*");
		}
		model.addObject("dataOfKeys", allKeys);
		model.addObject("dataOfSuffxKey", suffxKey);
		return model;
	}

	@RequestMapping("git")
	public ModelAndView git() {
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("currenttitle", "常用工具");
		model.addObject("subtitle","Git地址库");
		model.addObject("viewPath", "home/welcome");
		return model;
	}
	
	@RequestMapping("tasks")
	public ModelAndView tasks(){
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("currenttitle", "常用工具");
		model.addObject("subtitle","任务管理");
		model.addObject("viewPath", "home/welcome");
		return model;
	}
}
