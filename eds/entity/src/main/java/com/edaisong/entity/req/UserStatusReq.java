package com.edaisong.entity.req;

public class UserStatusReq {
	
	private Integer userId;
	private String version;
	 
	
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
