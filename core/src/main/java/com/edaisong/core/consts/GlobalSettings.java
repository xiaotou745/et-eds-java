package com.edaisong.core.consts;

public final class GlobalSettings {
	public final static String PWD_SALT = "etsapi2012611bhl";//密码salt
	public final static int MAX_LOGIN_COUNT = 10;//5分钟内最大登录次数
	public final static String BUSINESS_LOGIN_COOKIE_NAME = "ltoken_business";//登录Cookie name
	public final static String ADMIN_LOGIN_COOKIE_NAME = "ltoken_admin";//登录Cookie name
	public final static String ADMIN_JSESSIONID = "ADMIN_JSESSIONID";
	public final static String BUSINESS_JSESSIONID = "BUSINESS_JSESSIONID";
	public final static String PHONE_REGEX = "^1\\d{10}$";
}
