package com.edaisong.business.common;

import javax.servlet.http.HttpServletRequest;

import com.edaisong.core.cache.redis.RedisService;
import com.edaisong.core.consts.GlobalSettings;
import com.edaisong.core.util.CookieUtils;
import com.edaisong.core.util.SpringBeanHelper;
import com.edaisong.entity.Business;

public class UserContext {
	private Business business;
	private int businessType;
	private final static UserContext empty = new UserContext(null);
	private final static RedisService redisService;
	static{
		redisService = SpringBeanHelper.getCustomBeanByType(RedisService.class);
	}
	
	public UserContext(Business business){
		this.business = business;
		this.businessType=0;
	}
	public boolean isEmpty() {
		return business==null;
	}

	public static UserContext getCurrentContext(HttpServletRequest request) {
		final String cookieKey = LoginUtil.BUSINESS_LOGIN_COOKIE_NAME;
		String cookieValue = CookieUtils.getCookie(request, cookieKey);
		if (cookieValue != null) {
			return new UserContext(redisService.get(cookieValue, Business.class));
		}
		return empty;
	}

	public int getBusinessType() {
		return businessType;
	}
	public int getBusinessID() {
		return business.getId();
	}
	public String getBusinessName() {
		return business.getName();
	}

}
