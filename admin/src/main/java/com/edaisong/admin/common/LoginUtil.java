package com.edaisong.admin.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录工具
 * 为了兼容老后台,目前不使用全局的LoginHelper类
 * @author pengyi
 * @date 20150828
 *
 */
public class LoginUtil {
	public final static String LOGIN_COOKIE_NAME = "userinfo_edaisong_java";
	public final static String LOGIN_COOKIE_NAME_NET = "userinfo_edaisong";//.net版后台login cookie name
	public final static String MENU_LIST_COOKIE_NAME = "menulist_java";
	public final static String MENU_LIST_COOKIE_NAME_NET = "menulist";//.net版后台menulist cookie name
	public static boolean checkIsLogin(HttpServletRequest request, HttpServletResponse response){
		UserContext context = UserContext.getCurrentContext(request);
		return context.getAccount() != null && context.getAccount().getId() > 0;
	}
}
