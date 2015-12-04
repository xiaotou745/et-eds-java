package com.edaisong.entity.req;

/**
 * 版本检查参数model
 * 
 * @author CaoHeYang
 * @Date 20151013
 */
public class VersionCheckReq {
	private int platForm;
	private int userType;

	/**
	 * 客户端类型 1:Android 2 :IOS 默认Android
	 * 
	 * @return
	 */
	public int getPlatForm() {
		return platForm;
	}

	/**
	 * 客户端类型 1:Android 2 :IOS 默认Android
	 * 
	 * @param platForm
	 */
	public void setPlatForm(int platForm) {
		this.platForm = platForm;
	}

	/**
	 * 用户版本 1 骑士 2 商家 默认1骑士
	 * 
	 * @return
	 */
	public int getUserType() {
		return userType;
	}

	/**
	 * 用户版本 1 骑士 2 商家 默认1骑士
	 * 
	 * @param userType
	 */
	public void setUserType(int userType) {
		this.userType = userType;
	}

}
