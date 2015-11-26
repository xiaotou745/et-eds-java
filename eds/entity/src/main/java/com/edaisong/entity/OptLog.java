package com.edaisong.entity;

import java.util.Date;

public class OptLog {
	private String updateValue;
	private int optId;
	private int optType;
	private String optName;
	private Date optTime;
	public int getOptType() {
		return optType;
	}
	public void setOptType(int optType) {
		this.optType = optType;
	}
	public String getUpdateValue() {
		return updateValue;
	}
	public void setUpdateValue(String updateValue) {
		this.updateValue = updateValue;
	}
	public int getOptId() {
		return optId;
	}
	public void setOptId(int optId) {
		this.optId = optId;
	}
	public String getOptName() {
		return optName;
	}
	public void setOptName(String optName) {
		this.optName = optName;
	}
	public Date getOptTime() {
		return optTime;
	}
	public void setOptTime(Date optTime) {
		this.optTime = optTime;
	}
	
}
