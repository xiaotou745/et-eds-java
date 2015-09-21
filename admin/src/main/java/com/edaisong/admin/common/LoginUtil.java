package com.edaisong.admin.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.edaisong.core.util.CookieUtils;

/**
 * 登录工具
 * @author pengyi
 * @date 20150828
 *
 */
public class LoginUtil {
	public final static String ADMIN_JSESSIONID = "ADMIN_JSESSIONID";
	public final static String LOGIN_COOKIE_NAME = "userinfo_edaisongcom";
	public static boolean checkIsLogin(HttpServletRequest request, HttpServletResponse response){
		boolean isLogin= UserContext.getCurrentContext(request)!=null;
		if (!isLogin) {
			CookieUtils.deleteCookie(request, response,"admin", LoginUtil.LOGIN_COOKIE_NAME);
		}
		return isLogin;
	}
}
