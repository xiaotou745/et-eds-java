package com.edaisong.core.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	/**
	 * 设置 Cookie（生成时间为1天）
	 * @param name 名称
	 * @param value 值
	 */
	public static void setCookie(HttpServletRequest request,HttpServletResponse response, String name, String value) {
		setCookie(request,response, name, value, 60*60*24);
	}
	
	/**
	 * 设置 Cookie
	 * @param name 名称
	 * @param value 值
	 * @param maxAge 生存时间（单位秒）
	 * @param uri 路径
	 */
	public static void setCookie(HttpServletRequest request,HttpServletResponse response, String name, String value, int maxAge) {
		setCookie(request,response,name,value,maxAge,false);
	}
	/**
	 * 设置 Cookie
	 * @param name 名称
	 * @param value 值
	 * @param maxAge 生存时间（单位秒）
	 * @param uri 路径
	 * @param httpOnly 是否是httpOnly
	 */
	public static void setCookie(HttpServletRequest request,HttpServletResponse response, String name, String value, int maxAge,boolean httpOnly) {
		Cookie cookie = new Cookie(name, null);
		cookie.setPath(getPath(request));
		cookie.setMaxAge(maxAge);
		//cookie.setDomain(".edaisong.com.cn");
		try {
			cookie.setValue(URLEncoder.encode(value, "utf-8"));
			cookie.setHttpOnly(httpOnly);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		response.addCookie(cookie);
	}
	
	/**
	 * 设置 Cookie
	 * @param request
	 * @param response
	 * @param name
	 * @param value
	 * @param maxAge
	 * @param httpOnly
	 * @param domain
	 * @param path
	 */
	public static void setCookie(HttpServletRequest request,HttpServletResponse response, String name, String value, int maxAge,boolean httpOnly
			,String domain,String path) {
		Cookie cookie = new Cookie(name, null);
		if(path == null) 
			path = getPath(request);
		cookie.setPath(path);
		if(domain != null){
			cookie.setDomain(domain);
		}
		cookie.setMaxAge(maxAge);
		try {
			cookie.setValue(URLEncoder.encode(value, "utf-8"));
			cookie.setHttpOnly(httpOnly);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		response.addCookie(cookie);
	}
	
	/**
	 * 获得指定Cookie的值
	 * @param name 名称
	 * @return 值
	 */
	public static String getCookie(HttpServletRequest request, String name) {
		return getCookie(request, null, name, false);
	}
	/**
	 * 获得指定Cookie的值，并删除。
	 * @param name 名称
	 * @return 值
	 */
	public static String getCookie(HttpServletRequest request, HttpServletResponse response, String name) {
		return getCookie(request, response, name, true);
	}
	/**
	 * 获得指定Cookie的值
	 * @param request 请求对象
	 * @param response 响应对象
	 * @param name 名字
	 * @param isRemove 是否移除
	 * @return 值
	 */
	public static String getCookie(HttpServletRequest request, HttpServletResponse response, String name, boolean isRemove) {
		String value = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					try {
						value = URLDecoder.decode(cookie.getValue(), "utf-8");
						return value;
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					if (isRemove) {
						cookie.setMaxAge(0);
						response.addCookie(cookie);
					}
				}
			}
		}
		return value;
	}
	
	/**
     * 删除cookie
     * @param request
     * @param response
     * @param cookie
     */
    public static void deleteCookie(HttpServletRequest request,
            HttpServletResponse response, Cookie cookie) {
        if (cookie != null) {
        	String path = cookie.getPath();
        	if(path == null){
        		path = getPath(request);
        	}
            cookie.setPath(path);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
    }
    
    /**
     * 删除cookie
     * @param request
     * @param response
     * @param cookieName
     */
    public static void deleteCookie(HttpServletRequest request,
            HttpServletResponse response, String cookieName) {
    	Cookie[] cookies = request.getCookies();
    	for (Cookie cookie : cookies) {
    		String name = cookie.getName();
    		if(name.equals(cookieName)){
    			String path = cookie.getPath();
            	if(path == null){
            		path = getPath(request);
            	}
                cookie.setPath(path);
                cookie.setMaxAge(0);
                response.addCookie(cookie);
    		}
		}
    	
/*    	Cookie cookie = getCookieByName(cookieName, request);
        if (cookie != null) {
        	String path = cookie.getPath();
        	if(path == null){
        		path = getPath(request);
        	}
            cookie.setPath(path);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }*/
    }
    
    /**
     * 获得cookie路径    目前先指定为根目录,因为使用的是nginx负载均衡,如果设置为contextPath地址操作cookie的时候会有问题
     * @param request
     * @return
     */
    private static String getPath(HttpServletRequest request) {
/*        String path = request.getContextPath();
        return (path == null || path.length() == 0) ? "/" : path;*/
        return "/";
    }
}
