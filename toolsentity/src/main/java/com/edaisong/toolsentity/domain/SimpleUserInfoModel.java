package com.edaisong.toolsentity.domain;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 登录后存储用户登录信息实体类
 * 
 * @author pengyi
 * @date 20150901
 *
 */
public class SimpleUserInfoModel implements Serializable {
	@JsonProperty("Id")
	private int id;
	
	@JsonProperty("Password")
	private String password;
	
	@JsonProperty("UserName")
	private String userName;
	
	@JsonProperty("LoginName")
	private String loginName;
	
	@JsonProperty("GroupId")
	private int groupId;
	
	@JsonProperty("RoleId")
	private int roleId;
	
	@JsonProperty("AccountType")
	private int accountType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}
}
