package com.edaisong.admin.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edaisong.api.service.inter.IAuthorityMenuClassService;
import com.edaisong.core.cache.redis.RedisService;
import com.edaisong.core.consts.GlobalSettings;
import com.edaisong.core.util.CookieUtils;
import com.edaisong.core.util.SpringBeanHelper;
import com.edaisong.entity.Account;


public class UserContext {
	private Account account;
	private boolean isEmpty;


	private final static UserContext empty = new UserContext(null,true);
	private final static RedisService redisService;
	private final static IAuthorityMenuClassService authorityMenuClassService;
	static{
		redisService = SpringBeanHelper.getCustomBeanByType(RedisService.class);
		authorityMenuClassService = SpringBeanHelper.getCustomBeanByType(IAuthorityMenuClassService.class);
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
	/**
	 * 判断当前登录用户是否有给定菜单的权限
	 * @author hailongzhao
	 * @date 20150828
	 * @param menuID
	 * @return
	 */
	public boolean isHasAuth(int menuID) {
		if (isEmpty) {
			return false;
		}
		return authorityMenuClassService.checkHasAuth(account.getId(), menuID);
	}
	public static UserContext getCurrentContext(HttpServletRequest request) {
		final String cookieKey = GlobalSettings.ADMIN_LOGIN_COOKIE_NAME;
		String cookieValue = CookieUtils.getCookie(request, cookieKey);
		if (cookieValue != null) {
			return new UserContext(redisService.get(cookieValue, Account.class),false);
		}
		return empty;
	}
}
