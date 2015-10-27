package com.edaisong.toolsapi.common;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edaisong.toolsapi.redis.RedisService;
import com.edaisong.toolscore.util.CookieUtils;
import com.edaisong.toolscore.util.SpringBeanHelper;

public class LoginHelper {
	private final static RedisService redisService;
	static {
		redisService = SpringBeanHelper.getCustomBeanByType(RedisService.class);
	}

	

	/**
	 * 存储验证码到redis中
	 * 
	 * @author pengyi
	 * @param code
	 */
	public static void storeAuthCode2Redis(String from,String code , String cookieKey, HttpServletRequest request, HttpServletResponse response) {
		String redisKey = UUID.randomUUID().toString();
		redisService.set(redisKey, code);
	    CookieUtils.setCookie(request, response,from, cookieKey, redisKey, 5 * 24);
	}

	/**
	 * 获取验证码
	 * 
	 * @param request
	 * @author pengyi
	 * @return
	 */
	public static String getAuthCode(HttpServletRequest request,String cookieKey) {
		String codeCookie = CookieUtils.getCookie(request, cookieKey);
		if (codeCookie != null) {
			return redisService.get(codeCookie, String.class);
		}
		return null;
	}

	/**
	 * 删除验证码的cookie
	 * @param request
	 * @param response
	 */
	public static void removeAuthCodeCookie(HttpServletRequest request, HttpServletResponse response,String from,String cookieKey) {
		CookieUtils.deleteCookie(request, response,from, cookieKey);
	}
}
