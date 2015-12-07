package com.edaisong.toolsapi.service;

public class ServiceCacheKeys {
	/**
	 * 所有菜单缓存KEY
	 */
	public final static String MENU_CACHE_KEY = "menus:menus";

	/**
	 * 每个角色缓存KEY
	 */
	public final static String ROLE_PRIVILEGE_CACHE_KEY = "roles:role_%d_menuids";
	
	/**
	 * 角色下的用户ID列表
	 */
	public final static String ROLE_USERIDS_CACHE_KEY = "roles:role_%d_userids";

	/**
	 * 已经记录了权限的用户
	 */
	public final static String USER_ROLEIDS_CACHE_KEY = "users:user_%d_roleids";
	
	/**
	 * 用户菜单权限列表
	 */
	public final static String USER_PRIVILEGE_CACHE_KEY = "users:user_%d_menuids";
}
