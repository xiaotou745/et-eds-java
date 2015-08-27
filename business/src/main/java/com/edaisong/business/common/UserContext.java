package com.edaisong.business.common;

import javax.servlet.http.HttpServletRequest;

import com.edaisong.core.cache.redis.RedisService;
import com.edaisong.core.consts.GlobalSettings;
import com.edaisong.core.util.CookieUtils;
import com.edaisong.core.util.SpringBeanHelper;
import com.edaisong.entity.Business;

public class UserContext {
	private Business business;
	private boolean isEmpty;

	private final static UserContext empty = new UserContext(null,true);
	private final static RedisService redisService;
	static{
		redisService = SpringBeanHelper.getCustomBeanByType(RedisService.class);
	}
	
	public UserContext(Business business,boolean isEmpty){
		this.business = business;
		this.isEmpty = isEmpty;
	}

	public Business getBusiness() {
		return business;
	}
	
	public boolean isEmpty() {
		return isEmpty;
	}

	public static UserContext getCurrentContext(HttpServletRequest request) {
		final String cookieKey = GlobalSettings.BUSINESS_LOGIN_COOKIE_NAME;
		String cookieValue = CookieUtils.getCookie(request, cookieKey);
		if (cookieValue != null) {
			return new UserContext(redisService.get(cookieValue, Business.class),false);
		}
		return empty;
	}
}
