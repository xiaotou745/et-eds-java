package com.edaisong.admin.common;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.edaisong.api.service.inter.IAuthorityMenuClassService;
import com.edaisong.core.util.CookieUtils;
import com.edaisong.core.util.JsonUtil;
import com.edaisong.core.util.SpringBeanHelper;
import com.edaisong.entity.domain.SimpleUserInfoModel;

public class UserContext {
	private SimpleUserInfoModel account;
	private static Map<Integer, Integer>  loginFromMap=new HashMap<>();; 
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

	public static  UserContext getCurrentContext(HttpServletRequest request) {
		final String cookieKey = LoginUtil.LOGIN_COOKIE_NAME;
		String cookieValue = CookieUtils.getCookie(request, cookieKey);
		if (cookieValue != null&&!cookieValue.isEmpty()) {
			SimpleUserInfoModel account = JsonUtil.str2obj(cookieValue,SimpleUserInfoModel.class);
			if (account != null) {
				return new UserContext(account);
			}
		}
		return null;
	}
	/**
	 * 登录来源，0表示从net版后台登录，1表示从java版后台登录
	 * @author hailongzhao
	 * @date 20150916
	 * @param loginfrom
	 */
	public static int getLoginFrom(int userID) {
		if (!loginFromMap.containsKey(userID)) {
			return 0;
		}
		return loginFromMap.get(userID);
	}
/**
 * 登录来源，0表示从net版后台登录，1表示从java版后台登录
 * @author hailongzhao
 * @date 20150916
 * @param loginfrom
 */
	public static void setLoginFromJavaAdmin(int userID) {
		if (loginFromMap.containsKey(userID)) {
			loginFromMap.remove(userID);
		}
		loginFromMap.put(userID, 1);
	}
	public static void clearLoginFrom(int userID) {
		if (loginFromMap.containsKey(userID)) {
			loginFromMap.remove(userID);
		}
	}
}
