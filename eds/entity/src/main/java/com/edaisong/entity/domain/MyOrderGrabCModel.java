package com.edaisong.entity.domain;

import com.edaisong.core.util.DistanceConvert;

public class MyOrderGrabCModel {

	private String businessName; 
	private String businessPhoneNo;
	private String businessAddress;
	private String grabTime;
	private String orderRegionOneName;
	private String orderRegionTwoName;
	private Integer orderCount;
	private Integer grabOrderId;
	private String grabOrderNo; 
	private String distanceToBusiness;
	private String actualDoneDate;
	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
 
	public String getBusinessAddress() {
		return businessAddress;
	}

	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
	}

	public String getGrabTime() {
		if(grabTime == null){grabTime = "";}
		return grabTime;
	}

	public void setGrabTime(String grabTime) {
		this.grabTime = grabTime;
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

	public String getGrabOrderNo() {
		return grabOrderNo;
	}

	public void setGrabOrderNo(String grabOrderNo) {
		this.grabOrderNo = grabOrderNo;
	}
 

	public String getDistanceToBusiness() { 
		return DistanceConvert.ToString(distanceToBusiness,"");
	}

	public void setDistanceToBusiness(String distanceToBusiness) {
		this.distanceToBusiness = distanceToBusiness;
	}

	public String getBusinessPhoneNo() {
		return businessPhoneNo;
	}

	public void setBusinessPhoneNo(String businessPhoneNo) {
		this.businessPhoneNo = businessPhoneNo;
	}

	public String getActualDoneDate() {
		if(actualDoneDate == null){actualDoneDate = "";}
		return actualDoneDate;
	}

	public void setActualDoneDate(String actualDoneDate) {
		this.actualDoneDate = actualDoneDate;
	}
}
