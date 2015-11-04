package com.edaisong.entity.resp;

import java.util.Date;

public class MyOrderDetailBResp {
	private Integer grabOrderId;
	private Integer clienterId;
	private String clienterName; 
	private String clienterPhoneNo;  
	private String orderRegionOneName;
	private String orderRegionTwoName; 
	private Integer status;
	private String actualDoneDate; 
    private String grabTime; 
    private String pickUpTime; 
	private Integer orderCount;  
	 
	public Integer getClienterId() {
		return clienterId;
	}
	public void setClienterId(Integer clienterId) {
		this.clienterId = clienterId;
	}
	public String getClienterName() {
		return clienterName;
	}
	public void setClienterName(String clienterName) {
		this.clienterName = clienterName;
	}
	public String getClienterPhoneNo() {
		return clienterPhoneNo;
	}
	public void setClienterPhoneNo(String clienterPhoneNo) {
		this.clienterPhoneNo = clienterPhoneNo;
	}
	public String getOrderRegionOneName() {
		return orderRegionOneName;
	}
	public void setOrderRegionOneName(String orderRegionOneName) {
		this.orderRegionOneName = orderRegionOneName;
	}
	public String getOrderRegionTwoName() {
		return orderRegionTwoName;
	}
	public void setOrderRegionTwoName(String orderRegionTwoName) {
		this.orderRegionTwoName = orderRegionTwoName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getActualDoneDate() {
		if(actualDoneDate == null){actualDoneDate = "";}
		return actualDoneDate;
	}
	public void setActualDoneDate(String actualDoneDate) {
		this.actualDoneDate = actualDoneDate;
	}
	public String getGrabTime() {
		if(grabTime == null){grabTime = "";}
		return grabTime;
	}
	public void setGrabTime(String grabTime) {
		this.grabTime = grabTime;
	}
	public String getPickUpTime() {
		if(pickUpTime == null){pickUpTime = "";}
		return pickUpTime;
	}
	public void setPickUpTime(String pickUpTime) {
		this.pickUpTime = pickUpTime;
	}
	public Integer getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	} 
	public Integer getGrabOrderId() {
		return grabOrderId;
	}
	public void setGrabOrderId(Integer grabOrderId) {
		this.grabOrderId = grabOrderId;
	}
	
}
