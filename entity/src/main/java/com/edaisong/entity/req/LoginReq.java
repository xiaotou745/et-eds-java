package com.edaisong.entity.req;

public class LoginReq {
	private String phoneNo;
	private String password;
	//记住我   1:记住我        其他:否
	private byte rememberMe;
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public byte getRememberMe() {
		return rememberMe;
	}
	public void setRememberMe(byte rememberMe) {
		this.rememberMe = rememberMe;
	}
	
}
