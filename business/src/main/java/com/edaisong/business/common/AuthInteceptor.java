package com.edaisong.business.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.edaisong.core.util.PropertyUtils;

public class AuthInteceptor extends HandlerInterceptorAdapter {
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {
			// 判断是否登录
			boolean isLogin = LoginUtil.checkIsLogin(request,response,LoginUtil.BUSINESS_LOGIN_COOKIE_NAME);
			if (!isLogin
					&& (!request.getServletPath().equals("/account/login") && !request.getServletPath().equals(
							"/account/code"))) {
				String basePath =PropertyUtils.getProperty("static.business.url");
				response.sendRedirect(basePath + "/");
				return false;
			}
			if (isLogin){
				//用户登录后，将当前用户id和名称保存起来，用于记录操作日志
				request.setAttribute("userID", UserContext.getCurrentContext(request).getBusiness().getId());
				request.setAttribute("userName", UserContext.getCurrentContext(request).getBusiness().getName());
			}
		}
		return true;
	}
}
