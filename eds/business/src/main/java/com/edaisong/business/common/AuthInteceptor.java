package com.edaisong.business.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.edaisong.api.common.AjaxNotLoginRunTimeException;
import com.edaisong.core.util.PropertyUtils;

public class AuthInteceptor extends HandlerInterceptorAdapter {
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {
			// 判断是否登录
			boolean isLogin = LoginUtil.checkIsLogin(request, response);
			String basePath = PropertyUtils.getProperty("java.business.url");
			if (!isLogin && 
				!request.getServletPath().equals("/account/login")&& 
				!request.getServletPath().equals("/account/code")&& 
				!request.getServletPath().equals("/orderregion/todayone")&& 
				!request.getServletPath().equals("/group/return_url")) {
				if(isAjax(request)){
					throw new AjaxNotLoginRunTimeException("请重新登录");
				}else {
					response.sendRedirect(basePath + "/");
					return false;
				}
			}
			if (isLogin) {
				UserContext userContext = UserContext.getCurrentContext(request);
				// 用户登录后，将当前用户id和名称保存起来，用于记录操作日志
				request.setAttribute("userID", userContext.getBusinessID());
				request.setAttribute("userName", userContext.getBusinessName());
				if (!request.getServletPath().equals("/account/logoff")&&
					!isHasAuth(request,userContext)) {
					if (userContext.getBusinessType() == 1) {					
						response.sendRedirect(basePath + "/group/recharge");
						return false;
					} else {
						response.sendRedirect(basePath + "/index");
						return false;
					}
				}
			}
		}
		return true;
	}
	private boolean isAjax(HttpServletRequest request){
		String requestType = request.getHeader("X-Requested-With");
		if (requestType != null && requestType.equals("XMLHttpRequest")) {
			return true;
		}
		return false;
	}
	private boolean isHasAuth(HttpServletRequest request,UserContext userContext) {
		// 如果当前请求是从ajax中来的，或当前请求的页面为订单列表或验证码页面，则不进行权限判断
		if (!isAjax(request)) {
				if (userContext.getBusinessType() == 1) {// 集团商家登录
					if (request.getServletPath().equals("/group/recharge")|| 
					    request.getServletPath().equals("/group/alipayapi")||
					    request.getServletPath().equals("/group/return_url")||
					    request.getServletPath().equals("/order/grouporderlist")||
					    request.getServletPath().equals("/order/detail")||
					    request.getServletPath().equals("/order/exportgrouporders")) {
						return true;
					}
				} else {
					if (request.getServletPath().equals("/index")||
						 request.getServletPath().equals("/orderregion/regionmanage")||
						 request.getServletPath().equals("/orderregion/loglist")||
						 request.getServletPath().equals("/orderregion/todayone")||
					    request.getServletPath().equals("/order/publish")||
					    request.getServletPath().equals("/order/detail")||
					    request.getServletPath().equals("/order/list")||
					    request.getServletPath().equals("/transdetail/list")||
					    request.getServletPath().equals("/clienter/list")||
					    request.getServletPath().equals("/message/list")) {
						return true;
					}
				}
				return false;
			}		
		return true;
	}
}

