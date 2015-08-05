package com.edaisong.business.common.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.edaisong.business.config.WebConst;
import com.edaisong.business.entity.CookieModel;
import com.edaisong.core.cache.redis.RedisService;
import com.edaisong.core.util.JsonUtil;
import com.edaisong.core.web.CookieUtils;

import java.io.IOException;

public class PermissionFilter implements Filter {
	//@Autowired
	//private RedisService redisService;
	private final String loginUri = "/account/login";
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

		// �жϸ�uri�Ƿ���ҪȨ����֤
		// int permissionCode = this.getPermissionCode_MutiLevel(uri);
		// if (permissionCode== PermissionConfig.UNDEFINE) {
		// filterChain.doFilter(request, response);
		// return;
		// }
		
		//�Ƿ��ѵ�¼
/*		boolean isLogin = checkLogin(request);
		if (isLogin) {
			request.getRequestDispatcher(loginUri).forward(request, response);
			return;
		}*/

		String failUri = "";
		Object misUser = null;
		// if(null==misUser){
		// request.setAttribute("message", "�ף��޷���ȡ���Ȩ����Ϣ�������µ�¼mis��");
		// request.getRequestDispatcher(failUri).forward(request,response);
		// return;
		// }
		// Ȩ����֤�ж�

		// if (!misUser.isIllegal(permissionCode)) {
		// Ȩ��δͨ��
		// request.setAttribute("message", "�ף���û��Ȩ�޷��ʸ���Ϣ����������ʵ��˻���¼mis��");
		// request.getRequestDispatcher(failUri).forward(request, response);
		// return;
		// }
		filterChain.doFilter(request, response);// Ȩ��ͨ��
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void destroy() {

	}
	
	/*private boolean checkLogin(HttpServletRequest request){
		boolean isLogin = false;
		final String cookieKey = WebConst.LOGIN_COOKIE_NAME; 
		String cookieValue = CookieUtils.getCookie(request, cookieKey);
		if(cookieValue != null){
			CookieModel cookieModel = JsonUtil.str2obj(cookieValue, CookieModel.class);
			if(cookieModel != null){
				if(cookieModel.getVersion() == request.getServletContext().getInitParameter("cookieVersion")){
					Object loginStatusValue = redisService.get(cookieModel.getValue(),Object.class);
					if(loginStatusValue != null){
						isLogin = true;
					}
				}
			}
		}
		return isLogin;
	}*/
}
