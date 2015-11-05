package com.edaisong.entity.resp;

import java.util.Date;

public class MyOrderGrabDetailCResp {
	private String orderRegionOneName;
	private String orderRegionTwoName;
	private Integer orderCount;
	private Integer grabOrderId;
	private String grabOrderNo; 
	private double distanceToBusiness; 
	private Integer hadCompleteOrderGrab; 

    private Integer businessId;
    
    private String businessName;
    
    private String businessPhoneNo;
    
    private String businessAddress;
     
    private Byte status;

    private String actualDoneDate;

    private String grabTime;

    private String pickUpTime;
  
    private Double orderCommission;
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

	public double getDistanceToBusiness() {
		return distanceToBusiness;
	}

	public void setDistanceToBusiness(double distanceToBusiness) {
		this.distanceToBusiness = distanceToBusiness;
	}

	public Integer getHadCompleteOrderGrab() {
		return hadCompleteOrderGrab;
	}

	public void setHadCompleteOrderGrab(Integer hadCompleteOrderGrab) {
		this.hadCompleteOrderGrab = hadCompleteOrderGrab;
	}

	public Integer getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getBusinessPhoneNo() {
		return businessPhoneNo;
	}

	public void setBusinessPhoneNo(String businessPhoneNo) {
		this.businessPhoneNo = businessPhoneNo;
	}

	public String getBusinessAddress() {
		return businessAddress;
	}

	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
	} 

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
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

	public Double getOrderCommission() {
		return orderCommission;
	}

	public void setOrderCommission(Double orderCommission) {
		this.orderCommission = orderCommission;
	} 
}
