package com.edaisong.admin.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录工具
 * @author pengyi
 * @date 20150828
 *
 */
public class LoginUtil {
	public final static String ADMIN_JSESSIONID = "ADMIN_JSESSIONID";
	public final static String LOGIN_COOKIE_NAME = "userinfo_edaisong";
	public static boolean checkIsLogin(HttpServletRequest request, HttpServletResponse response){
		UserContext context = UserContext.getCurrentContext(request);
		return context.getAccount() != null && context.getAccount().getId() > 0;
	}
}
