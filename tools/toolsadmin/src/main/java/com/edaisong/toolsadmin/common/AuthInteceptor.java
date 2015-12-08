package com.edaisong.toolsadmin.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.edaisong.toolscore.util.PropertyUtils;

public class AuthInteceptor extends HandlerInterceptorAdapter {
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String basePath = PropertyUtils.getProperty("java.toolsadmin.url");
		if (handler instanceof HandlerMethod) {
			// 判断是否登录
			UserIndentity indentity = UserIndentity.getIndentity(request);
			if (!indentity.isLogin() && !request.getServletPath().equals("/account/code")
					&& !request.getServletPath().equals("/account/login")) {
				response.sendRedirect(basePath + "/");
				return false;
			}
			request.setAttribute("servletPath", request.getServletPath());
			// if (isLogin) {// 用户登录后，检查用户是否有当前页面的权限，没有则跳转到订单列表页
			// // 用户登录后，将当前用户id和名称保存起来，用于记录操作日志
			// request.setAttribute("userID",
			// UserContext.getCurrentContext(request).getId());
			// request.setAttribute("userName",
			// UserContext.getCurrentContext(request).getLoginName());
			//
			// if (needCheckPageAuth(request)) {
			// List<MenuInfo> menuList =
			// menuInfoService.getMenuListByUserID(UserContext
			// .getCurrentContext(request).getId());
			// for (MenuInfo menuEntity : menuList) {
			// if (request.getServletPath().equals(menuEntity.getUrl())) {
			// return true;
			// }
			// }
			// response.sendRedirect(basePath + "/ordermanage/auditorder");
			// return false;
			// }
			// }
		}

		return true;
	}

	private boolean needCheckPageAuth(HttpServletRequest request) {
		return false;
		// 需要处理类似/order/detail这种页面。需要设置按钮的url
		// 如果当前请求是从ajax中来的，或当前请求的页面为订单列表或验证码页面，则不进行权限判断
		// String requestType = request.getHeader("X-Requested-With");
		// if (!(requestType!=null&&requestType.equals("XMLHttpRequest"))&&
		// !request.getServletPath().equals("/account/code")&&
		// !request.getServletPath().equals("/ordermanage/auditorder")&&
		// !request.getServletPath().equals("/account/logoff")) {
		// return true;
		//
		// }
		// return false;
	}
}
