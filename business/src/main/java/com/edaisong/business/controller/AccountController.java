package com.edaisong.business.controller;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edaisong.api.service.inter.IBusinessService;
import com.edaisong.business.config.WebConst;
import com.edaisong.business.entity.CookieModel;
import com.edaisong.core.cache.redis.RedisService;
import com.edaisong.core.util.JsonUtil;
import com.edaisong.core.web.CookieUtils;
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
	/**
	 * 登录
	 * 
	 * @author pengyi
	 * @param req
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "login", method = { RequestMethod.POST })
	public @ResponseBody BusinessLoginResp login(@RequestBody BusinessLoginReq req, HttpServletRequest request,
			HttpServletResponse response) {
		BusinessLoginResp resp = new BusinessLoginResp();
/*		//如果已登录,直接返回
		String cookieValue = CookieUtils.getCookie(request, cookieKey);
		Object loginStatusValue = businessService.getLoginStatus(cookieValue);
		if(loginStatusValue != null){
			resp.setMessage("登录成功");
			resp.setLoginSuccess(true);
			return resp;
		}*/
		resp = businessService.login(req);
		// 登录成功,写cookie
		if (resp.isLoginSuccess()) {
			int cookieMaxAge = 2 * 60 * 24;
			// 选择记住我,默认cookie24小时,否则2小时
			if (req.getRememberMe() == 1) {
				cookieMaxAge = 60 * 60 * 24;
			}
			String key = UUID.fromString(req.getPhoneNo()).toString();
			CookieModel cookieModel = new CookieModel();
			cookieModel.setValue(key);
			cookieModel.setVersion(request.getServletContext().getInitParameter("cookieVersion"));
			redisService.set(key, resp.getBid(), cookieMaxAge);
			CookieUtils.setCookie(response, WebConst.LOGIN_COOKIE_NAME, JsonUtil.obj2string(cookieModel), cookieMaxAge, true);
		}
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
		Cookie cookie = CookieUtils.getCookieByName(WebConst.LOGIN_COOKIE_NAME,request);
		if(cookie != null){
			CookieUtils.deleteCookie(request, response, cookie);
			redisService.set(WebConst.LOGIN_COOKIE_NAME, null, -1);
		}
		return new ResponseBase();
	}
}
