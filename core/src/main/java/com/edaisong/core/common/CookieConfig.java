package com.edaisong.core.common;

import com.edaisong.core.util.PropertyUtils;

public class CookieConfig {
	public static String getCookieKey() {
		return PropertyUtils.getProperty("cookie.user", "ebu");
	}

	public static String getCookieUserIdKey() {
		return PropertyUtils.getProperty("cookie.userid", "uid");
	}

	public static String getCookieUserNameKey() {
		return PropertyUtils.getProperty("cookie.username", "un");
	}

	public static String getCookieNikeNameKey() {
		return PropertyUtils.getProperty("cookie.nikename", "uni");
	}

	public static String getCookiePasswordKey() {
		return PropertyUtils.getProperty("cookie.password", "pa");
	}

	public static String getCookieRoleIdKey() {
		return PropertyUtils.getProperty("cookie.roleid", "rid");
	}

	public static String getCookieManagerIdKey() {
		return PropertyUtils.getProperty("cookie.managerid", "mid");
	}
}
