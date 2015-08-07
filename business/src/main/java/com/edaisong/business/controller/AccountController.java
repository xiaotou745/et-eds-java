package com.edaisong.business.controller;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.descriptor.web.LoginConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.api.service.inter.IBusinessService;
import com.edaisong.business.common.WebConst;
import com.edaisong.business.entity.CookieModel;
import com.edaisong.core.cache.redis.RedisService;
import com.edaisong.core.consts.RedissCacheKey;
import com.edaisong.core.util.JsonUtil;
import com.edaisong.core.web.CookieUtils;
import com.edaisong.entity.Business;
import com.edaisong.entity.common.ResponseBase;
import com.edaisong.entity.req.BusinessLoginReq;
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

	/**
	 * 登录
	 * 
	 * @author pengyi
	 * @param req
	 * @param request
	 * @param response
	 * @return
	 */
	/*@RequestMapping(value = "login", method = { RequestMethod.POST })
	public @ResponseBody BusinessLoginResp login(HttpServletRequest request, HttpServletResponse response) {
		boolean isLogin = false;
		BusinessLoginResp resp = new BusinessLoginResp();
		// 如果已登录,直接返回
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
			resp.setMessage("已登录");
			resp.setLoginSuccess(true);
			return resp;
		}
		String phoneNo = request.getParameter("phoneNo");
		String password = request.getParameter("password");
		BusinessLoginReq req = new BusinessLoginReq();
		req.setPassword(password);
		req.setPhoneNo(phoneNo);

		resp = businessService.login(req);
		// 登录成功,写cookie
		if (resp.isLoginSuccess()) {
			int cookieMaxAge = 2 * 60 * 24;
			// 选择记住我,默认cookie24小时,否则2小时
			if (req.getRememberMe() == 1) {
				cookieMaxAge = 60 * 60 * 24;
			}
			String key = UUID.randomUUID().toString();
			CookieModel cookieModel = new CookieModel();
			cookieModel.setValue(key);
			cookieModel.setVersion(request.getServletContext().getInitParameter("cookieVersion"));
			redisService.set(key, resp.getBid(), cookieMaxAge);
			CookieUtils.setCookie(response, WebConst.LOGIN_COOKIE_NAME, JsonUtil.obj2string(cookieModel), cookieMaxAge,
					true);
		}
		return resp;
	}*/

	@RequestMapping(value = "login", method = { RequestMethod.POST })
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response, @RequestParam String phoneNo, 
			@RequestParam String password, @RequestParam String code, @RequestParam boolean rememberMe) {
		ModelAndView mv = new ModelAndView("account/login");
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
			mv.setViewName("account/code");
			return mv;
		}
		
		boolean flag = true;
		String message = "";
		Object sessionCode = request.getSession().getAttribute("code");
		//验证码不正确
		if(sessionCode == null || !sessionCode.toString().equals(code)){
			message = "验证码不正确";
			mv.addObject("message", message);
			return mv;
		}
		
		// 参数验证
		if (phoneNo == null || phoneNo.isEmpty() || password == null
				|| password.isEmpty()) {
			businessService.addLoginLog(phoneNo==null?"":phoneNo,"用户名或密码为空",false);
			message = "用户名或密码为空";
			mv.addObject("message", message);
			return mv;
		}
		Business business = businessService.login(phoneNo, password);
		if(business == null){
			message = "用户名或密码错误";
			mv.addObject("message", message);
			return mv;
		}
		//审核未通过
		if(business.getStatus() != 1){
			message = "您的商铺尚未验证通过";
			mv.addObject("message", message);
			businessService.addLoginLog(phoneNo,"商铺尚未验证通过",false);
			return mv;
		}
		String loginCountCacheKey = RedissCacheKey.LOGIN_COUNT_B + phoneNo;
		Integer loginCount = redisService.get(loginCountCacheKey, Integer.class);
		loginCount = loginCount == null ? 0 : loginCount;
		redisService.set(loginCountCacheKey, loginCount + 1, 5 * 60);
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
		redisService.set(key, business, cookieMaxAge);
		CookieUtils.setCookie(response, WebConst.LOGIN_COOKIE_NAME, JsonUtil.obj2string(cookieModel), cookieMaxAge,
				true);
		mv.setViewName("account/code");
		return mv;
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
