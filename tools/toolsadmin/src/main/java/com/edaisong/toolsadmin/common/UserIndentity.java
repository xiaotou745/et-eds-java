package com.edaisong.toolsadmin.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edaisong.toolscore.security.DES;
import com.edaisong.toolscore.security.MD5Util;
import com.edaisong.toolscore.util.CookieUtils;
import com.edaisong.toolscore.util.JsonUtil;
import com.edaisong.toolscore.util.StringUtils;
import com.edaisong.toolsentity.view.LoginUser;

public class UserIndentity {
	private LoginUser loginUser;
	private static String loginCookieKey = MD5Util.MD5("loginUser");

	private UserIndentity(LoginUser user) {
		this.loginUser = user;
	}

	/**
	 * 获取当前登录用户的ID
	 * 
	 * @return 如果当前未登录，则返回0，否则返回当前登录用户ID
	 */
	public Integer getUserId() {
		return this.loginUser == null ? 0 : this.loginUser.getId();
	}

	/**
	 * 获取当前登录用户的登录名
	 * 
	 * @return 如果当前未登录，则返回空字符串，否则返回当前登录用户的登录账号
	 */
	public String getLoginName() {
		return this.loginUser == null ? "" : this.loginUser.getLoginName();
	}

	/**
	 * 获取当前登录用户的用户名
	 * 
	 * @return 如果当前未登录，则返回空字符串，否则返回当前登录用户的用户名
	 */
	public String getUserName() {
		return this.loginUser == null ? "" : this.loginUser.getUserName();
	}

	/**
	 * 是否已登录
	 * 
	 * @return 如果已登录，返回true,否则返回false;
	 */
	public boolean isLogin() {
		return this.loginUser != null && this.loginUser.getId() > 0;
	}

	/**
	 * 登录方法
	 * 
	 * @param user
	 *            登录用户信息
	 * @param request
	 *            http request
	 * @param response
	 *            http response
	 * @throws Exception
	 */
	public void login(LoginUser user, HttpServletRequest request, HttpServletResponse response, Integer cookieMaxAge)
			throws Exception {
		String cookieValue = DES.encrypt(JsonUtil.obj2string(user));
		CookieUtils.setCookie(request, response, "toolsadmin", loginCookieKey, cookieValue, cookieMaxAge, true);
	}

	/**
	 * 注销吧少年
	 * 
	 * @param request
	 * @param response
	 */
	public void logoff(HttpServletRequest request, HttpServletResponse response) {
		CookieUtils.deleteCookie(request, response, "toolsadmin", loginCookieKey);
	}

	/**
	 * 获取当前登录用户
	 * 
	 * @param request
	 *            http上下文
	 * @return 如果未登录，返回null，否则返回当前登录用户对象
	 */
	public static UserIndentity getIndentity(HttpServletRequest request) {
		String cookieValue = CookieUtils.getCookie(request, loginCookieKey);
		if (!StringUtils.isEmpty(cookieValue)) {
			String strUser = null;
			try {
				strUser = DES.decrypt(cookieValue);
			} catch (Exception ex) {
				return new UserIndentity(null);
			}
			LoginUser user = JsonUtil.str2obj(strUser, LoginUser.class);
			if (user != null) {
				return new UserIndentity(user);
			}
		}
		return new UserIndentity(null);
	}
}
