package com.edaisong.toolsadmin.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.edaisong.toolscore.util.CookieUtils;

/**
 * 登录工具
 * @author pengyi
 * @date 20150828
 *
 */
public class LoginUtil {
	public final static String ADMIN_JSESSIONID = "TOOLSADMIN_JSESSIONID";
	public final static String LOGIN_COOKIE_NAME = "toolsadmin_edaisongcom";
	public static boolean checkIsLogin(HttpServletRequest request, HttpServletResponse response){
		boolean isLogin= UserContext.getCurrentContext(request)!=null;
		if (!isLogin) {
			CookieUtils.deleteCookie(request, response,"toolsadmin", LoginUtil.LOGIN_COOKIE_NAME);
		}
		return isLogin;
	}
}
