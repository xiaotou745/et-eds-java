package com.edaisong.business.entity;

import javax.servlet.http.HttpServletRequest;

import com.edaisong.api.business.SpringBeanHelper;
import com.edaisong.business.common.WebConst;
import com.edaisong.core.cache.redis.RedisService;
import com.edaisong.core.util.CookieUtils;
import com.edaisong.core.util.JsonUtil;
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
		final String cookieKey = WebConst.LOGIN_COOKIE_NAME;
		String cookieValue = CookieUtils.getCookie(request, cookieKey);
		if (cookieValue != null) {
			CookieModel cookieModel = JsonUtil.str2obj(cookieValue, CookieModel.class);
			if (cookieModel != null) {
				return new UserContext(redisService.get(cookieModel.getValue(), Business.class),false);
			}
		}
		return empty;
	}
}
