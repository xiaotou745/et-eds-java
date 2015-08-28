package com.edaisong.entity;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class Account implements Serializable{
	@JsonProperty("Id")
    private Integer id;
	
	@JsonProperty("Password")
    private String password;
	
	@JsonProperty("UserName")
    private String username;
	
	@JsonProperty("LoginName")
    private String loginname;
	
	@JsonProperty("Status")
    private Integer status;
	
	@JsonProperty("AccountType")
    private Integer accounttype;
	
	@JsonProperty("FadateTime")
    private Date fadatetime;
	
	@JsonProperty("Fauser")
    private String fauser;
	
	@JsonProperty("LcDatetime")
    private Date lcdatetime;
	
	@JsonProperty("LcUser")
    private String lcuser;
	
	@JsonProperty("GroupId")
    private Integer groupid;
	
	@JsonProperty("RoleId")
    private Integer roleid;
	
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname == null ? null : loginname.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(Integer accounttype) {
        this.accounttype = accounttype;
    }

    public Date getFadatetime() {
        return fadatetime;
    }

    public void setFadatetime(Date fadatetime) {
        this.fadatetime = fadatetime;
    }

    public String getFauser() {
        return fauser;
    }

    public void setFauser(String fauser) {
        this.fauser = fauser == null ? null : fauser.trim();
    }

    public Date getLcdatetime() {
        return lcdatetime;
    }

    public void setLcdatetime(Date lcdatetime) {
        this.lcdatetime = lcdatetime;
    }

    public String getLcuser() {
        return lcuser;
    }

    public void setLcuser(String lcuser) {
        this.lcuser = lcuser == null ? null : lcuser.trim();
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }
}