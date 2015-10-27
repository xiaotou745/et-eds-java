package com.edaisong.toolsadmin.common;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.edaisong.toolsapi.common.LoginHelper;
import com.edaisong.toolsapi.service.inter.IAuthorityMenuClassService;
import com.edaisong.toolscore.consts.GlobalSettings;
import com.edaisong.toolscore.util.PropertyUtils;
import com.edaisong.toolsentity.MenuEntity;

public class AuthInteceptor extends HandlerInterceptorAdapter {
	@Autowired
	private IAuthorityMenuClassService authorityMenuClassService;
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String basePath =PropertyUtils.getProperty("java.toolsadmin.url");
		if (handler instanceof HandlerMethod) {
			// 判断是否登录
			boolean isLogin = LoginUtil.checkIsLogin(request,response);
			if (!isLogin&& 
				!request.getServletPath().equals("/account/login") && 
				!request.getServletPath().equals("/account/code")) {
				response.sendRedirect(basePath + "/");
				return false;
			}
			if (isLogin) {//用户登录后，检查用户是否有当前页面的权限，没有则跳转到订单列表页
				//用户登录后，将当前用户id和名称保存起来，用于记录操作日志
				request.setAttribute("userID", UserContext.getCurrentContext(request).getId());
				request.setAttribute("userName", UserContext.getCurrentContext(request).getLoginName());

				if (needCheckPageAuth(request)) {
					List<MenuEntity> menuList=authorityMenuClassService.getMenuListByUserID(UserContext.getCurrentContext(request).getId());
					for (MenuEntity menuEntity : menuList) {
						if (request.getServletPath().equals(menuEntity.getJavaUrl())) {
							return true;
						}
					}
					response.sendRedirect(basePath+"/order/list");
					return false;
				}
			}
		}
		
		return true;
	}
	private boolean needCheckPageAuth(HttpServletRequest request){
		//如果当前请求是从ajax中来的，或当前请求的页面为订单列表或验证码页面，则不进行权限判断
		String requestType = request.getHeader("X-Requested-With"); 
		if (!(requestType!=null&&requestType.equals("XMLHttpRequest"))&&
			!request.getServletPath().equals("/account/code")&&
			!request.getServletPath().equals("/account/logoff")) {
			return false;
			
		}
		return false;
	}
}
