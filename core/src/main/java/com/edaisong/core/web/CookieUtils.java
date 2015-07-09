package com.edaisong.core.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CookieUtils {
	private static final String[] cookieArrayKeys = { "ebu", "ASP.NET_SessionId" };
	private static final List<String> cookieKeys = Arrays.asList(cookieArrayKeys);
	private final static Logger logger = LoggerFactory.getLogger(CookieUtils.class);

	/**
	 * 获取所有的cookie列表
	 * 
	 * @param request http请求上下文
	 * @return 返回所有的cookie列表
	 */
	public static List<Cookie> getCookies(HttpServletRequest request) {
		List<Cookie> result = new ArrayList<Cookie>();
		if (request != null) {
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				result = Arrays.asList(cookies);
				// 注：上面的方法生成的List，无法执行添加，删除操作，下面的操作则无此限制
				// java.util.Collections.addAll(result, cookies);
			}
		}
		return result;
	}

	/**
	 * 将cookie封装到Map里面
	 * 
	 * @param request http请求上下文
	 * @return 返回name及cookie键值对map
	 */
	public static Map<String, Cookie> getCookiesMap(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				logger.info("---------cookie name:{} value:{} ", cookie.getName(), cookie.getValue());
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}

	/**
	 * 根据名字获取cookie
	 * 
	 * @param request2
	 * @param name cookie名字
	 * @return
	 */
	public static Cookie getCookieByName(String name, HttpServletRequest request) {
		Map<String, Cookie> cookieMap = getCookiesMap(request);
		if (cookieMap.containsKey(name)) {
			return cookieMap.get(name);
		} else {
			return null;
		}
	}

	/**
	 * 获取需要发送的cookie键值对
	 * 
	 * @param request http请求对象
	 * @return 返回需要发送给服务器的cookie键值对
	 */
	public static Map<String, String> getSendCookieKvs(HttpServletRequest request) {
		List<Cookie> sendCookies = getSendCookies(request);
		Map<String, String> collect = sendCookies.stream().collect(Collectors.toMap(Cookie::getName, Cookie::getValue));
		return collect;
	}

	/**
	 * 获取需要发送的cookie列表
	 * 
	 * @param request http请求
	 * @return 返回指定的需要返回的cookie列表
	 */
	public static List<Cookie> getSendCookies(HttpServletRequest request) {
		List<Cookie> result = new ArrayList<Cookie>();
		if (request != null) {
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					logger.debug("cookie name:{} value:{} expires:{} domain:{} path:{}", cookie.getName(),
							cookie.getValue(), cookie.getMaxAge(), cookie.getDomain(), cookie.getPath());
					String name = cookie.getName();
					if (cookieKeys.contains(name)) {
						result.add(cookie);
					}
				}
			}
		}
		return result;
	}
}
