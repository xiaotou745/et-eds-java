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
			String basePath =PropertyUtils.getProperty("static.business.url");
			if (!isLogin&& 
				!request.getServletPath().equals("/account/login") && 
				!request.getServletPath().equals("/account/code")&&
				!isPay(request)) {
				response.sendRedirect(basePath + "/");
				return false;
			}
			if (isLogin){
				UserContext userContext=UserContext.getCurrentContext(request);
				//用户登录后，将当前用户id和名称保存起来，用于记录操作日志
				request.setAttribute("userID", userContext.getBusinessID());
				request.setAttribute("userName", userContext.getBusinessName());
				if (!userContext.isEmpty()&&
						userContext.getBusinessType()==1&&
						!request.getServletPath().equals("/group/recharge")&&
						!request.getServletPath().equals("/account/logoff")&&
						!isPay(request)) {
						response.sendRedirect(basePath + "/group/recharge");
						return false;
					}
			}
		}
		return true;
	}
	private boolean isPay(HttpServletRequest request){
		if(request.getServletPath().equals("/group/alipayapi")||
				request.getServletPath().equals("/group/notify_url")||
				request.getServletPath().equals("/group/return_url")){
			return true;
		}
		return false;
	}
}
