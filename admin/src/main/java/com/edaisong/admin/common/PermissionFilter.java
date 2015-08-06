package com.edaisong.admin.common;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PermissionFilter implements Filter {

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
	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void destroy() {

	}
}