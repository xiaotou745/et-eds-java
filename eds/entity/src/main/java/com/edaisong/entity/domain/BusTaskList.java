package com.edaisong.entity.domain;
/**
 * 
 * 
 * 门店任务审核列表
 * @author ofmyi_000
 * 2015年9月17日14:55:09
 *
 */
public class BusTaskList {
	
	private String name;
	private String phoneNo;
	private int businessId;
	private int orderCount;
	private int taskCount;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public int getBusinessId() {
		return businessId;
	}
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}
	public int getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}
	public int getTaskCount() {
		return taskCount;
	}
	public void setTaskCount(int taskCount) {
		this.taskCount = taskCount;
	}

}
