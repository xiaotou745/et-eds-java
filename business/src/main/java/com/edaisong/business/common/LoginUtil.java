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
	private final static RedisService redisService;
	static {
		redisService = SpringBeanHelper.getCustomBeanByType(RedisService.class);
	}
	/**
	 * 是否登录
	 * 
	 * @param request
	 * @return
	 */
	public static boolean checkIsLogin(HttpServletRequest request, HttpServletResponse response,String cookieKey) {
		// 如果已登录,直接返回
		boolean isLogin = false;
		String cookieValue = CookieUtils.getCookie(request, cookieKey);
		if (cookieValue != null) {
			Object loginStatusValue = redisService.get(cookieValue, Object.class);
			if (loginStatusValue != null){// && loginStatusValue instanceof Business) {
				isLogin = true;
			}
		}
		// 如果没有登录,清除旧的登录cookie
		if (!isLogin) {
			CookieUtils.deleteCookie(request, response, cookieKey);
		}

		return isLogin;
	}
}
