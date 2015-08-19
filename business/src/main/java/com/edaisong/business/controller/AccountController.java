package com.edaisong.business.controller;

import java.io.IOException;
import java.util.Date;

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
import com.edaisong.business.common.ServerUtil;
import com.edaisong.business.common.WebConst;
import com.edaisong.business.entity.resp.LoginResp;
import com.edaisong.core.cache.redis.RedisService;
import com.edaisong.core.consts.RedissCacheKey;
import com.edaisong.core.util.CookieUtils;
import com.edaisong.entity.Business;
import com.edaisong.entity.resp.BusinessLoginResp;

@Controller
@RequestMapping("account")
public class AccountController {
	@Autowired
	private IBusinessService businessService;
	@Autowired
	private RedisService redisService;

/*	@RequestMapping(value = "login", method = { RequestMethod.GET })
	public ModelAndView LoginConfig(HttpServletRequest request, HttpServletResponse response) throws IOException {
		boolean isLogin = checkIsLogin(request);
		if(isLogin){
			response.sendRedirect(request.getContextPath()+"/index.jsp");
			return null;
		}
		ModelAndView mv = new ModelAndView("account/login");
		return mv;
	}*/

	@RequestMapping("code")
	public ModelAndView code(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("account/code");
		return mv;
	}

	@RequestMapping(value = "login", method = { RequestMethod.POST })
	public @ResponseBody LoginResp login(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String phoneNo, @RequestParam String password, @RequestParam String code,
			@RequestParam boolean rememberMe) {
		Object sessionCode = request.getSession().getAttribute("code");
		//一次性验证码,防止暴力破解
		request.getSession().removeAttribute("code");
		LoginResp resp = new LoginResp();
		// 如果已登录,直接返回
		boolean isLogin = ServerUtil.checkIsLogin(request);
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
		BusinessLoginResp businessResp = businessService.login(phoneNo, password);
		if (!businessResp.isLoginSuccess()) {
			resp.setMessage(businessResp.getMessage());
			resp.setSuccess(false);
			return resp;
		}
		// 登录成功,写cookie
		int cookieMaxAge = 2 * 60 * 24;
		// 选择记住我,默认cookie24小时,否则2小时
		if (rememberMe) {
			cookieMaxAge = 60 * 60 * 24;
		}
		Business business = businessResp.getBusiness();
		Date lastLoginTime = new Date();//更新最后登录时间
		businessService.updateLastLoginTime(business.getId(), lastLoginTime);
		business.setLastLoginTime(lastLoginTime);
		String key = String.format("%s_%s", RedissCacheKey.LOGIN_COOKIE_KEY,business.getPhoneno());//UUID.randomUUID().toString();
		redisService.set(key, business, cookieMaxAge);
		CookieUtils.setCookie(request,response, WebConst.LOGIN_COOKIE_NAME, key, cookieMaxAge,
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
	 * @throws IOException 
	 */
	@RequestMapping(value = "logoff")
	public void logoff(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 删除登录cookie
		Cookie cookie = CookieUtils.getCookieByName(WebConst.LOGIN_COOKIE_NAME, request);
		if (cookie != null) {
		    	redisService.remove(cookie.getValue());
			CookieUtils.deleteCookie(request, response, cookie);
		}
		response.sendRedirect(request.getContextPath() + "/");
	}

	/**
	 * 是否登录
	 * @param request
	 * @return
	 */
/*	private boolean checkIsLogin(HttpServletRequest request) {
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
		return isLogin;
	}*/
}
