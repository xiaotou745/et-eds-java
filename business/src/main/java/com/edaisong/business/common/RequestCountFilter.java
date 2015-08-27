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



import com.edaisong.core.cache.redis.RedisService;
import com.edaisong.core.util.IPUtil;
import com.edaisong.core.util.SpringBeanHelper;

/**
 * url请求次数过滤器
 * 目前所配置的url和请求次数是直接写在代码中,以后会做到配置文件中,在服务器启动或第一次请求时初始化url和请求次数限制的HashMap集合
 * 
 * @author pengyi
 * @date 20150806
 */
public class RequestCountFilter implements Filter {
	private final String checkUrl = "/index.jsp";
	private final int maxCount = 100;
	private final int maxMinute = 5;
	private RedisService redisService;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		redisService = SpringBeanHelper.getCustomBeanByType(RedisService.class);
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String uri = request.getRequestURI().replace(request.getContextPath(), "");
		// 如果请求的地址是需要监测的url
		if (uri.equals(checkUrl)) {
			String remoteIp = IPUtil.getIpAddr(request);
			String remoteHost = request.getRemoteHost();
			int remotePort = request.getRemotePort();
			String key = String.format("%s_%s_%s_%d", checkUrl, remoteIp, remoteHost, remotePort);
			Integer count = redisService.get(key, Integer.class);
			if (count == null)
				count = 0;// 防止count为null
			if (count >= maxCount) {
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().println("您请求的次数过多,请稍候再试");
				return;
			}
			redisService.set(key, ++count, maxMinute * 60);
		}

		chain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {

	}
}
