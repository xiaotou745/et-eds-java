package com.edaisong.business.controller;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.api.service.inter.IBusinessService;
import com.edaisong.business.common.WebConst;
import com.edaisong.business.entity.CookieModel;
import com.edaisong.business.entity.resp.LoginResp;
import com.edaisong.core.cache.redis.RedisService;
import com.edaisong.core.util.JsonUtil;
import com.edaisong.core.web.CookieUtils;
import com.edaisong.entity.common.ResponseBase;
import com.edaisong.entity.resp.BusinessLoginResp;

@Controller
@RequestMapping("account")
public class AccountController {
	@Autowired
	private IBusinessService businessService;
	@Autowired
	private RedisService redisService;

	@RequestMapping(value = "login", method = { RequestMethod.GET })
	public ModelAndView LoginConfig(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("account/login");
		return mv;
	}

	@RequestMapping("code")
	public ModelAndView code(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("account/code");
		return mv;
	}

	@RequestMapping(value = "login", method = { RequestMethod.POST })
	public @ResponseBody LoginResp login(HttpServletRequest request, HttpServletResponse response, @RequestParam String phoneNo, 
			@RequestParam String password, @RequestParam String code, @RequestParam boolean rememberMe) {
		LoginResp resp = new LoginResp();
		// 如果已登录,直接返回
		boolean isLogin = false;
		final String cookieKey = WebConst.LOGIN_COOKIE_NAME;
		String cookieValue = CookieUtils.getCookie(request, cookieKey);
		if (cookieValue != null) {
			CookieModel cookieModel = JsonUtil.str2obj(cookieValue, CookieModel.class);
			if (cookieModel != null) {
				Object loginStatusValue = redisService.get(cookieModel.getValue(), Object.class);
				if (loginStatusValue != null) {
					isLogin = true;
				}
			}
		}
		// 如果已登录,直接返回已登录
		if (isLogin) {
			resp.setSuccess(true);
			return resp;
		}
		
		Object sessionCode = request.getSession().getAttribute("code");
		//验证码不正确
		if(sessionCode == null || !sessionCode.toString().equals(code)){
			resp.setMessage("验证码不正确");
			resp.setSuccess(false);
			return resp;
		}
		BusinessLoginResp business = businessService.login(phoneNo, password);
		if (!business.isLoginSuccess()) {
			resp.setMessage(business.getMessage());
			resp.setSuccess(false);
			return resp;
		}
		// 登录成功,写cookie
		int cookieMaxAge = 2 * 60 * 24;
		// 选择记住我,默认cookie24小时,否则2小时
		if (rememberMe) {
			cookieMaxAge = 60 * 60 * 24;
		}
		String key = UUID.randomUUID().toString();
		CookieModel cookieModel = new CookieModel();
		cookieModel.setValue(key);
		cookieModel.setVersion(request.getServletContext().getInitParameter("cookieVersion"));
		redisService.set(key, business.getBusiness(), cookieMaxAge);
		CookieUtils.setCookie(response, WebConst.LOGIN_COOKIE_NAME, JsonUtil.obj2string(cookieModel), cookieMaxAge,
				true);
		resp.setSuccess(true);
		return resp;
	}

	/**
	 * 注销
	 * 
	 * @author pengyi
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "logoff", method = { RequestMethod.POST })
	public @ResponseBody ResponseBase logoff(HttpServletRequest request, HttpServletResponse response) {
		// 删除登录cookie
		Cookie cookie = CookieUtils.getCookieByName(WebConst.LOGIN_COOKIE_NAME, request);
		if (cookie != null) {
			CookieUtils.deleteCookie(request, response, cookie);
			redisService.set(WebConst.LOGIN_COOKIE_NAME, null, -1);
		}
		return new ResponseBase();
	}
}
