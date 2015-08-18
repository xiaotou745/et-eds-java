package com.edaisong.business.common;

import javax.servlet.http.HttpServletRequest;

import com.edaisong.api.common.SpringBeanHelper;
import com.edaisong.business.entity.CookieModel;
import com.edaisong.core.cache.redis.RedisService;
import com.edaisong.core.util.CookieUtils;
import com.edaisong.core.util.JsonUtil;

public class ServerUtil {
	private final static RedisService redisService;
	static{
		redisService = SpringBeanHelper.getCustomBeanByType(RedisService.class);
	}
	/**
	 * 是否登录
	 * @param request
	 * @return
	 */
	public static boolean checkIsLogin(HttpServletRequest request) {
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
	}
	
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
