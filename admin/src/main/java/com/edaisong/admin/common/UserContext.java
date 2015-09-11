package com.edaisong.admin.common;

import javax.servlet.http.HttpServletRequest;

import com.edaisong.api.service.inter.IAuthorityMenuClassService;
import com.edaisong.core.util.CookieUtils;
import com.edaisong.core.util.JsonUtil;
import com.edaisong.core.util.SpringBeanHelper;
import com.edaisong.entity.domain.SimpleUserInfoModel;


public class UserContext {
	private SimpleUserInfoModel account;
	private static UserContext instance = null;
	private final static UserContext empty = new UserContext(null);
	private final static IAuthorityMenuClassService authorityMenuClassService;
	static{
		authorityMenuClassService = SpringBeanHelper.getCustomBeanByType(IAuthorityMenuClassService.class);
	}

	
	private UserContext(SimpleUserInfoModel account){
		this.account = account;
	}
	
	public boolean isEmpty() {
		return account==null;
	}

	/**
	 * 判断当前登录用户是否有给定菜单的权限
	 * @author hailongzhao
	 * @date 20150828
	 * @param menuID
	 * @return
	 */
	public boolean isHasAuth(int menuID) {
		if (account==null) {
			return false;
		}
		return authorityMenuClassService.checkHasAuth(account.getId(), menuID);
	}

	
	public int getId() {
		if (account==null) {
			return -1;
		}
		return account.getId();
	}

	public int getAccountType() {
		if (account==null) {
			return -1;
		}
		return account.getAccountType();
	}

	public String getName() {
		if (account==null) {
			return "";
		}
		return account.getLoginName();
	}
	public static void resetContext() {
		instance = null;
	}
	public static synchronized UserContext getCurrentContext(HttpServletRequest request) {
		if (instance==null) {
			final String cookieKey = LoginUtil.LOGIN_COOKIE_NAME;
			String cookieValue = CookieUtils.getCookie(request, cookieKey);
			if (cookieValue != null) {
				SimpleUserInfoModel account = JsonUtil.str2obj(cookieValue, SimpleUserInfoModel.class);
				if(account != null){
					instance= new UserContext(account);
				}
			}
			if (instance==null) {
				instance=empty;
			}
		}
		return instance;
	}
}

