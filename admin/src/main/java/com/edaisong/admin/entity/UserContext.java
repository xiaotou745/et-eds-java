package com.edaisong.admin.entity;

import javax.servlet.http.HttpServletRequest;

import com.edaisong.api.common.SpringBeanHelper;
import com.edaisong.core.cache.redis.RedisService;
import com.edaisong.core.consts.GlobalSettings;
import com.edaisong.core.util.CookieUtils;
import com.edaisong.entity.Account;

public class UserContext {
	private Account account;
	private boolean isEmpty;

	private final static UserContext empty = new UserContext(null,true);
	private final static RedisService redisService;
	static{
		redisService = SpringBeanHelper.getCustomBeanByType(RedisService.class);
	}
	
	public UserContext(Account account,boolean isEmpty){
		this.account = account;
		this.isEmpty = isEmpty;
	}

	public Account getAccount() {
		return account;
	}
	
	public boolean isEmpty() {
		return isEmpty;
	}

	public static UserContext getCurrentContext(HttpServletRequest request) {
		final String cookieKey = GlobalSettings.LOGIN_COOKIE_NAME;
		String cookieValue = CookieUtils.getCookie(request, cookieKey);
		if (cookieValue != null) {
			return new UserContext(redisService.get(cookieValue, Account.class),false);
		}
		return empty;
	}
}
