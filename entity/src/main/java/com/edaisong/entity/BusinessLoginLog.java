package com.edaisong.entity;

import java.sql.Date;

public class BusinessLoginLog {
	private Long id;
	private String phoneNo;
	private Date createTime;
	private String description;
	private short isSuccess;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public short getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(short isSuccess) {
		this.isSuccess = isSuccess;
	}
}
