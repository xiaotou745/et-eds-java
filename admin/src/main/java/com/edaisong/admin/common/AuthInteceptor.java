package com.edaisong.admin.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.edaisong.api.common.LoginHelper;
import com.edaisong.core.consts.GlobalSettings;
import com.edaisong.core.util.PropertyUtils;

public class AuthInteceptor extends HandlerInterceptorAdapter {
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {
			// 判断是否登录
			boolean isLogin = LoginHelper.checkIsLogin(request,response,GlobalSettings.ADMIN_LOGIN_COOKIE_NAME);
			if (!isLogin
					&& (!request.getServletPath().equals("/account/login") && !request.getServletPath().equals(
							"/account/code"))) {
				String basePath =PropertyUtils.getProperty("static.business.url");
				response.sendRedirect(basePath + "/");
				return false;
			}
		}
		return true;
	}
}
