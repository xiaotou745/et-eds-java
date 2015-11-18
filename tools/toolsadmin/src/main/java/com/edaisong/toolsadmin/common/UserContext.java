package com.edaisong.toolsadmin.common;

import javax.servlet.http.HttpServletRequest;

import com.edaisong.toolsapi.service.inter.IAuthorityMenuClassService;
import com.edaisong.toolscore.security.AES;
import com.edaisong.toolscore.util.CookieUtils;
import com.edaisong.toolscore.util.JsonUtil;
import com.edaisong.toolscore.util.SpringBeanHelper;
import com.edaisong.toolsentity.domain.SimpleUserInfoModel;

public class UserContext {
	private SimpleUserInfoModel account;
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

	public String getLoginName() {
		return account.getLoginName();
	}
	public String getUserName() {
		return account.getUserName();
	}
	public static  UserContext getCurrentContext(HttpServletRequest request) {
		final String cookieKey = LoginUtil.LOGIN_COOKIE_NAME;
		String cookieValue = CookieUtils.getCookie(request, cookieKey);
		if (cookieValue != null&&!cookieValue.isEmpty()) {
			String edcrCookie=AES.aesDecrypt(cookieValue);
			SimpleUserInfoModel account = JsonUtil.str2obj(edcrCookie,SimpleUserInfoModel.class);
			if (account != null&&
				account.getUserName()!=null&&
				!account.getUserName().isEmpty()&&
				account.getLoginName()!=null&&
				!account.getLoginName().isEmpty()) {
				return new UserContext(account);
			}
		}
		return null;
	}
}
