package com.edaisong.business.common;

import javax.servlet.http.HttpServletRequest;

import com.edaisong.core.cache.redis.RedisService;
import com.edaisong.core.consts.GlobalSettings;
import com.edaisong.core.util.CookieUtils;
import com.edaisong.core.util.SpringBeanHelper;
import com.edaisong.entity.Business;
import com.edaisong.entity.GroupBusiness;

public class UserContext {
	private Business business;
	private GroupBusiness groupBusiness;
	private int businessType;
	private final static UserContext empty = new UserContext();
	private final static RedisService redisService;
	static {
		redisService = SpringBeanHelper.getCustomBeanByType(RedisService.class);
	}

	public UserContext() {
		this.business = null;
		this.groupBusiness = null;
		this.businessType = 0;
	}

	public UserContext(Business business) {
		this.business = business;
		this.groupBusiness = null;
		this.businessType = 0;
	}

	public UserContext(GroupBusiness groupBusiness) {
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

	public static UserContext getCurrentContext(HttpServletRequest request) {
		final String cookieKey = LoginUtil.BUSINESS_LOGIN_COOKIE_NAME;
		String cookieValue = CookieUtils.getCookie(request, cookieKey);
		if (cookieValue != null) {
			Object obj = redisService.get(cookieValue, Object.class);
			if (obj != null) {
				if (obj instanceof Business) {
					return new UserContext((Business) obj);
				} else if (obj instanceof GroupBusiness) {
					return new UserContext((GroupBusiness) obj);
				}
			} else {
				return empty;
			}

		}
		return empty;
	}

	public int getBusinessType() {
		return businessType;
	}

	public int getBusinessID() {
		if (businessType == 0) {
			return business.getId();
		} else {
			return groupBusiness.getId();
		}
	}

	public String getBusinessName() {
		if (businessType == 0) {
			return business.getName();
		} else {
			return groupBusiness.getGroupbusiname();
		}
	}

}
