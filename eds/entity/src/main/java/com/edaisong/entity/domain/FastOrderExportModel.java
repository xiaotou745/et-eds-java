package com.edaisong.entity.domain;

public class FastOrderExportModel {
	private String grabOrderNo;
	private String businessName;
	private String businessPhoneNo;
	private String clienterName;
	private String clienterPhoneNo;
	private String grabTime;
	private String pickUpTime;
	private String actualDoneDate;
	private Double orderCommission;
	private Integer orderCount;
	private Double distribSubsidy;
	private Double websiteSubsidy;
	private Integer adjustment;
	public String getGrabOrderNo() {
		return grabOrderNo;
	}
	public void setGrabOrderNo(String grabOrderNo) {
		this.grabOrderNo = grabOrderNo;
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
	public String getGrabTime() {
		return grabTime;
	}
	public void setGrabTime(String grabTime) {
		this.grabTime = grabTime;
	}
	public String getPickUpTime() {
		return pickUpTime;
	}
	public void setPickUpTime(String pickUpTime) {
		this.pickUpTime = pickUpTime;
	}
	public String getActualDoneDate() {
		return actualDoneDate;
	}
	public void setActualDoneDate(String actualDoneDate) {
		this.actualDoneDate = actualDoneDate;
	}
	public Double getOrderCommission() {
		return orderCommission;
	}
	public void setOrderCommission(Double orderCommission) {
		this.orderCommission = orderCommission;
	}
	public Integer getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}
	public Double getDistribSubsidy() {
		return distribSubsidy;
	}
	public void setDistribSubsidy(Double distribSubsidy) {
		this.distribSubsidy = distribSubsidy;
	}
	public Double getWebsiteSubsidy() {
		return websiteSubsidy;
	}
	public void setWebsiteSubsidy(Double websiteSubsidy) {
		this.websiteSubsidy = websiteSubsidy;
	}
	public Integer getAdjustment() {
		return adjustment;
	}
	public void setAdjustment(Integer adjustment) {
		this.adjustment = adjustment;
	}
}
