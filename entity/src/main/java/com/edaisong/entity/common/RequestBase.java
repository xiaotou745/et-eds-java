package com.edaisong.entity.common;

public class RequestBase {

	private String authorityCityNameListStr;

	private int userType;

	/**
	 * 用户所有权限城市名称集合串
	 * 
	 * @return
	 */
	public String getAuthorityCityNameListStr() {
		return authorityCityNameListStr;
	}

	/**
	 * 用户所有权限城市名称集合串
	 * 
	 * @param authorityCityNameListStr
	 */
	public void setAuthorityCityNameListStr(String authorityCityNameListStr) {
		this.authorityCityNameListStr = authorityCityNameListStr;
	}

	/**
	 * 用户类型（1：全部城市权限 2：部分城市权限）
	 * 
	 * @return
	 */
	public int getUserType() {
		return userType;
	}

	/**
	 * 用户类型（1：全部城市权限 2：部分城市权限）
	 * 
	 * @param userType
	 */
	public void setUserType(int userType) {
		this.userType = userType;
	}
}
