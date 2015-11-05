package com.edaisong.business.common;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.edaisong.core.security.AES;
import com.edaisong.core.util.CookieUtils;

public class UserContext {
	
	private Map<String, String>  userInfo; 

	private UserContext(Map<String, String> paramMap) {
		this.userInfo = paramMap;
	}

	public int getBusinessType() {
		return Integer.parseInt(userInfo.get("businessType"));
	}

	public int getBusinessID() {
		return Integer.parseInt(userInfo.get("businessID"));
	}

	public String getBusinessName() {
		return userInfo.get("businessName");
	}
	public static  UserContext getCurrentContext(HttpServletRequest request) {
		final String cookieKey = LoginUtil.BUSINESS_LOGIN_COOKIE_NAME;
		String cookieValue = CookieUtils.getCookie(request, cookieKey);
		if (cookieValue != null&&!cookieValue.isEmpty()) {
			String edcrCookie=AES.aesDecrypt(cookieValue);
			String [] userInfo=edcrCookie.split(";");
			if (userInfo.length>=3) {
				for (String item : userInfo) {
					if (item==null||item.isEmpty()) {
						return null;
					}
				}
				Map<String, String> paramMap = new HashMap<>();
				paramMap.put("businessType", userInfo[0]);
				paramMap.put("businessID", userInfo[1]);
				paramMap.put("businessName", userInfo[2]);
				return new UserContext(paramMap);
			}
		}
		return null;
	}
}
