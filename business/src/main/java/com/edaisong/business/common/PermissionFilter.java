package com.edaisong.business.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edaisong.api.business.SpringBeanHelper;
import com.edaisong.core.cache.redis.RedisService;

public class PermissionFilter implements Filter {
	private RedisService redisService;
	private final String loginUri = "/account/login";
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		redisService = SpringBeanHelper.getCustomBeanByType(RedisService.class);
	}
	
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletReponse, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletReponse;

		// MisUser misUser = MisUserUtil.getCurrentMisUser(request);

		String uri = request.getServletPath().split("\\.")[0];
		if (uri.endsWith("/")) {
			uri = uri.substring(0, uri.length() - 1);
		}

		// 判断该uri是否需要权限验证
		// int permissionCode = this.getPermissionCode_MutiLevel(uri);
		// if (permissionCode== PermissionConfig.UNDEFINE) {
		// filterChain.doFilter(request, response);
		// return;
		// }
		
		//判断是否登录
		boolean isLogin = ServerUtil.checkIsLogin(request);
		if (isLogin) {
			request.getRequestDispatcher(loginUri).forward(request, response);
			return;
		}

		String failUri = "";
		Object misUser = null;
		// if(null==misUser){
		// request.setAttribute("message", "亲，无法获取你的权限信息，请重新登录mis！");
		// request.getRequestDispatcher(failUri).forward(request,response);
		// return;
		// }
		// 权限认证判定

		// if (!misUser.isIllegal(permissionCode)) {
		// 权限未通过
		// request.setAttribute("message", "亲，您没有权限访问该信息，请更换合适的账户登录mis！");
		// request.getRequestDispatcher(failUri).forward(request, response);
		// return;
		// }
		filterChain.doFilter(request, response);// 权限通过
	}

	@Override
	public void destroy() {

	}
}
