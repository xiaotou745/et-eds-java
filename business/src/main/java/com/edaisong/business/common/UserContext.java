package com.edaisong.business.common;

import javax.servlet.http.HttpServletRequest;

import com.edaisong.core.cache.redis.RedisService;
import com.edaisong.core.util.CookieUtils;
import com.edaisong.core.util.SpringBeanHelper;
import com.edaisong.entity.Business;
import com.edaisong.entity.GroupBusiness;

public class UserContext {
	private Business business;
	private GroupBusiness groupBusiness;
	private int businessType;
	private static UserContext instance = null;
	private final static UserContext empty = new UserContext();
	private final static RedisService redisService;
	static {
		redisService = SpringBeanHelper.getCustomBeanByType(RedisService.class);
	}

	private UserContext() {
		this.business = null;
		this.groupBusiness = null;
		this.businessType = 0;
	}

	private UserContext(Business business) {
		this.business = business;
		this.groupBusiness = null;
		this.businessType = 0;
	}

	private UserContext(GroupBusiness groupBusiness) {
		this.business = null;
		this.groupBusiness = groupBusiness;
		this.businessType = 1;
	}

	public boolean isEmpty() {
		if (businessType == 0) {
			return business == null;
		}
		return groupBusiness == null;
	}
	public int getBusinessType() {
		return businessType;
	}

	public int getBusinessID() {
		if (businessType == 0) {
			if (business==null) {
				return -1;
			}
			return business.getId();
		} else {
			if (groupBusiness==null) {
				return -1;
			}
			return groupBusiness.getId();
		}
	}

	public String getBusinessName() {
		if (businessType == 0) {
			if (business==null) {
				return "";
			}
			return business.getName();
		} else {
			if (groupBusiness==null) {
				return "";
			}
			return groupBusiness.getGroupbusiname();
		}
	}
	public static void resetContext() {
		instance = null;
	}
	public static synchronized UserContext getCurrentContext(HttpServletRequest request) {
		if (instance == null) {
			final String cookieKey = LoginUtil.BUSINESS_LOGIN_COOKIE_NAME;
			String cookieValue = CookieUtils.getCookie(request, cookieKey);
			if (cookieValue != null) {
				Object obj = redisService.get(cookieValue, Object.class);
				if (obj != null) {
					if (obj instanceof Business) {
						instance = new UserContext((Business) obj);
					} else if (obj instanceof GroupBusiness) {
						instance = new UserContext((GroupBusiness) obj);
					}
				}
			}
			if (instance == null) {
				instance=empty;
			}
		}
		return instance;
	}
}
