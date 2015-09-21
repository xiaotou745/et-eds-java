package com.edaisong.business.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edaisong.core.cache.redis.RedisService;
import com.edaisong.core.util.CookieUtils;
import com.edaisong.core.util.SpringBeanHelper;

/**
 * 登录工具
 * 为了兼容老后台,目前不使用全局的LoginHelper类
 * @author pengyi
 * @date 20150828
 *
 */
public class LoginUtil {
	public final static String BUSINESS_LOGIN_COOKIE_NAME = "ltoken_business";//登录Cookie name
	public final static String BUSINESS_JSESSIONID = "BUSINESS_JSESSIONID";

	/**
	 * 是否登录
	 * 
	 * @param request
	 * @return
	 */
	public static boolean checkIsLogin(HttpServletRequest request, HttpServletResponse response) {
		boolean isLogin= UserContext.getCurrentContext(request)!=null;
		if (!isLogin) {
			CookieUtils.deleteCookie(request, response,"business", LoginUtil.BUSINESS_LOGIN_COOKIE_NAME);
		}
		return isLogin;
	}
}
