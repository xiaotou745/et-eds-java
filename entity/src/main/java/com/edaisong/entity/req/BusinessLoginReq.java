package com.edaisong.entity.req;

import com.edaisong.entity.common.RequestBase;

/**
 * 商家登录请求参数
 * @author 彭宜
 * @date 20150804
 */
public class BusinessLoginReq extends RequestBase{
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
