package com.edaisong.entity.req;

import java.util.Date;

public class QuartzUpdateReq {
	private int id;
	private int status;
	private String updateName;
	private Date firstFireTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getUpdateName() {
		return updateName;
	}
	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}
	public Date getFirstFireTime() {
		return firstFireTime;
	}
	public void setFirstFireTime(Date firstFireTime) {
		this.firstFireTime = firstFireTime;
	}
}
