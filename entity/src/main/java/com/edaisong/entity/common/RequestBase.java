package com.edaisong.entity.common;

import java.util.List;

public class RequestBase {

	private List<String> authorityCityNameList;

	private int cityAuthType;

	/**
	 * 用户权限类型（1：全部城市权限 2：部分城市权限）
	 * 
	 * @return
	 */
	public int getCityAuthType() {
		return cityAuthType;
	}

	/**
	 * 用户权限类型（1：全部城市权限 2：部分城市权限）
	 * 
	 * @param cityAuthType
	 */
	public void setCityAuthType(int cityAuthType) {
		this.cityAuthType = cityAuthType;
	}

	/**
	 * 用户所有权限城市名称集合
	 * @return the authorityCityNameList
	 */
	public List<String> getAuthorityCityNameList() {
		return authorityCityNameList;
	}

	/**
	 * 用户所有权限城市名称集合
	 * @param authorityCityNameList the authorityCityNameList to set
	 */
	public void setAuthorityCityNameList(List<String> authorityCityNameList) {
		this.authorityCityNameList = authorityCityNameList;
	}
}
