package com.edaisong.business.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edaisong.api.service.impl.BusinessService;
import com.edaisong.api.service.inter.IBusinessService;
import com.edaisong.business.config.WebConst;
import com.edaisong.business.entity.CookieModel;
import com.edaisong.core.cache.redis.RedisService;
import com.edaisong.core.consts.RedissCacheKey;
import com.edaisong.core.util.JsonUtil;
import com.edaisong.core.web.CookieUtils;
import com.edaisong.entity.resp.BusinessLoginResp;

/**
 * 登录过滤器
 * @author pengyi
 * @date 20150804
 */
public class LoginFilter implements Filter{
	private RedisService redisService;
	private IBusinessService businessService;
	private int maxLoginCount;//5分钟内最大登录次数
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		redisService = new RedisService();
		businessService = new BusinessService();
		maxLoginCount = Integer.parseInt(filterConfig.getInitParameter("maxLoginCount"));
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletReponse, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		HttpServletResponse response = (HttpServletResponse)servletReponse;
		
		String phoneNo = request.getParameter("phoneNo");
		if(phoneNo != null){
			String loginCountCacheKey = RedissCacheKey.LOGIN_COUNT_B + phoneNo;
			Integer loginCount = redisService.get(loginCountCacheKey, Integer.class);
			loginCount = loginCount == null ? 0 : loginCount;
			if (loginCount >= maxLoginCount) {
				BusinessLoginResp resp = new BusinessLoginResp();
				resp.setLoginSuccess(false);
				resp.setMessage("您当前登录的次数大于10，请5分钟后重试");
				businessService.addLoginLog(phoneNo,"5分钟内登录次数超过10次",false);
				response.getWriter().write(JsonUtil.obj2string(resp));
				return;
			}
		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
