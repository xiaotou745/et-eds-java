package com.edaisong.admin.common;

import javax.servlet.http.HttpServletRequest;

import com.edaisong.api.service.inter.IAuthorityMenuClassService;
import com.edaisong.core.util.CookieUtils;
import com.edaisong.core.util.JsonUtil;
import com.edaisong.core.util.SpringBeanHelper;
import com.edaisong.entity.domain.SimpleUserInfoModel;

public class UserContext {
	private SimpleUserInfoModel account;
	private static int loginFrom;
	private static UserContext instance = null;
	private final static IAuthorityMenuClassService authorityMenuClassService;
	static {
		authorityMenuClassService = SpringBeanHelper
				.getCustomBeanByType(IAuthorityMenuClassService.class);
	}

	private UserContext(SimpleUserInfoModel account) {
		this.account = account;
	}

	/**
	 * 判断当前登录用户是否有给定菜单的权限
	 * 
	 * @author hailongzhao
	 * @date 20150828
	 * @param menuID
	 * @return
	 */
	public boolean isHasAuth(int menuID) {
		return authorityMenuClassService.checkHasAuth(account.getId(), menuID);
	}

	public int getId() {
		return account.getId();
	}

	public int getAccountType() {
		return account.getAccountType();
	}

	public String getName() {
		return account.getLoginName();
	}

	public static synchronized UserContext getCurrentContext(
			HttpServletRequest request) {
		final String cookieKey = LoginUtil.LOGIN_COOKIE_NAME;
		String cookieValue = CookieUtils.getCookie(request, cookieKey);
		if (cookieValue == null) {
			instance = null;
		} else if (instance == null) {
			SimpleUserInfoModel account = JsonUtil.str2obj(cookieValue,SimpleUserInfoModel.class);
			if (account != null) {
				instance = new UserContext(account);
			}
		}
		return instance;
	}
	/**
	 * 登录来源，0表示从net版后台登录，1表示从java版后台登录
	 * @author hailongzhao
	 * @date 20150916
	 * @param loginfrom
	 */
	public static int getLoginFrom() {
		return loginFrom;
	}
/**
 * 登录来源，0表示从net版后台登录，1表示从java版后台登录
 * @author hailongzhao
 * @date 20150916
 * @param loginfrom
 */
	public static void setLoginFrom(int loginfrom) {
		loginFrom = loginfrom;
	}
}
