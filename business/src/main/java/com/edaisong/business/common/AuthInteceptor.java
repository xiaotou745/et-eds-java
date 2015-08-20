package com.edaisong.business.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInteceptor extends HandlerInterceptorAdapter {
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {
			// 判断是否登录
			boolean isLogin = ServerUtil.checkIsLogin(request,response);
			if (!isLogin
					&& (!request.getServletPath().equals("/account/login") && !request.getServletPath().equals(
							"/account/code"))) {
				response.sendRedirect(request.getContextPath() + "/");
				return false;
			}
		}
		return true;
	}
}
