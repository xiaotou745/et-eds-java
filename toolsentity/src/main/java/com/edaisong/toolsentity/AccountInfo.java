package com.edaisong.toolsentity;

import java.util.Date;

public class AccountInfo {
    private Integer id;

    private String passWord;

    private String userName;

    private String loginName;

    private Integer status;

    private Integer accountType;

    private Integer roleId;

    private Date lastChangeTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    

    public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
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

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Date getLastChangeTime() {
		return lastChangeTime;
	}

	public void setLastChangeTime(Date lastChangeTime) {
		this.lastChangeTime = lastChangeTime;
	}

	public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    
}