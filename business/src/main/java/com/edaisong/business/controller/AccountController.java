package com.edaisong.business.controller;

import java.io.IOException;
import java.util.Date;
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

import com.edaisong.api.common.LoginHelper;
import com.edaisong.api.service.inter.IBusinessService;
import com.edaisong.api.service.inter.IGroupBusinessService;
import com.edaisong.business.common.LoginResp;
import com.edaisong.business.common.LoginUtil;
import com.edaisong.core.cache.redis.RedisService;
import com.edaisong.core.consts.GlobalSettings;
import com.edaisong.core.consts.RedissCacheKey;
import com.edaisong.core.util.CookieUtils;
import com.edaisong.core.util.PropertyUtils;
import com.edaisong.entity.Business;
import com.edaisong.entity.GroupBusiness;
import com.edaisong.entity.common.ResponseCode;
import com.edaisong.entity.resp.BusinessLoginResp;

@Controller
@RequestMapping("account")
public class AccountController {
	@Autowired
	private IBusinessService businessService;
	@Autowired
	private IGroupBusinessService groupBusinessService;
	@Autowired
	private RedisService redisService;

	@RequestMapping("code")
	public ModelAndView code(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("account/code");
		return mv;
	}

	@RequestMapping(value = "login", method = { RequestMethod.POST })
	@ResponseBody
	public LoginResp login(HttpServletRequest request, HttpServletResponse response,
			String phoneNo, String password,String code,
			int rememberMe,int userType) {
		//Object sessionCode = request.getSession().getAttribute("code");
		String sessionCode = LoginHelper.getAuthCode(request,LoginUtil.BUSINESS_JSESSIONID);
		//一次性验证码,防止暴力破解
		//request.getSession().removeAttribute("code");
		LoginHelper.removeAuthCodeCookie(request, response,LoginUtil.BUSINESS_JSESSIONID);
		LoginResp resp = new LoginResp();
		// 如果已登录,直接返回
		boolean isLogin = LoginUtil.checkIsLogin(request,response,LoginUtil.BUSINESS_LOGIN_COOKIE_NAME);
		// 如果已登录,直接返回已登录
		if (isLogin) {
			resp.setSuccess(true);
			return resp;
		}

		
		// 验证码不正确
		if (sessionCode == null || !sessionCode.toString().toLowerCase().equals(code.toLowerCase())) {
			resp.setMessage("验证码不正确");
			resp.setSuccess(false);
			return resp;
		}

		BusinessLoginResp businessResp=null;
		if (userType==0) {
			businessResp = businessService.login(phoneNo, password);
		}else {
			GroupBusiness business = groupBusinessService.login(phoneNo, password);
		}
		if (businessResp.getResponseCode()!=ResponseCode.SUCESS) {
			resp.setMessage(businessResp.getMessage());
			resp.setSuccess(false);
			return resp;
		}
		// 登录成功,写cookie
		int cookieMaxAge = 2 * 60 * 24;
		// 选择记住我,默认cookie24小时,否则随浏览器的关闭而失效
		if (rememberMe==1) {
			cookieMaxAge = 60 * 60 * 24;
		}
		Business business = businessResp.getBusiness();
		Date lastLoginTime = new Date();//更新最后登录时间
		businessService.updateLastLoginTime(business.getId(), lastLoginTime);
		business.setLastLoginTime(lastLoginTime);
		String key = String.format("%s_business_%s_%s", RedissCacheKey.LOGIN_COOKIE_KEY,business.getPhoneno(),UUID.randomUUID().toString());
		redisService.set(key, business, cookieMaxAge);
		if(!(rememberMe==1)){
			cookieMaxAge = -1;//如果不是记住我,则让cookie的失效时间跟着浏览器走
		}
		CookieUtils.setCookie(request,response, LoginUtil.BUSINESS_LOGIN_COOKIE_NAME, key, cookieMaxAge,
				true);
		//设置账户cookie
		CookieUtils.setCookie(request,response, "username", phoneNo, 365 * 60 * 60 * 24,
				false);
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
	 * @throws IOException 
	 */
	@RequestMapping(value = "logoff")
	public void logoff(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 删除登录cookie
		Cookie cookie = CookieUtils.getCookieByName(LoginUtil.BUSINESS_LOGIN_COOKIE_NAME, request);
		if (cookie != null) {
		    redisService.remove(cookie.getValue());
			CookieUtils.deleteCookie(request, response, cookie.getName());
		}
		response.sendRedirect(PropertyUtils.getProperty("static.business.url") + "/");
	}
}
