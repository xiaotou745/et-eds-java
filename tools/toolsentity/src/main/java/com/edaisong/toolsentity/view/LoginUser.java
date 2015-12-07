package com.edaisong.toolsentity.view;

import java.io.Serializable;

public class LoginUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3317536626049192207L;
	
	private Integer id;
	private String loginName;
	private String userName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
