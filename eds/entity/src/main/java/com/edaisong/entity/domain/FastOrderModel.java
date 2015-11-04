package com.edaisong.entity.domain;

import java.util.Date;

public class FastOrderModel {
	private Long id ;
    private String orderNo;
    private Integer orderFrom;
    private String businessName;
    private String businessPhoneNo;
    private String clienterName;
    private String clienterPhoneNo;
    private Date grabTime;
    private String orderRegionOneName;
    private String orderRegionTwoName;
    private Date actualDoneDate;
	private Double  orderCommission;
	private Double singleOrderCommission;
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getOrderFrom() {
		return orderFrom;
	}
	public void setOrderFrom(Integer orderFrom) {
		this.orderFrom = orderFrom;
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
	public Date getGrabTime() {
		return grabTime;
	}
	public void setGrabTime(Date grabTime) {
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
	public Date getActualDoneDate() {
		return actualDoneDate;
	}
	public void setActualDoneDate(Date actualDoneDate) {
		this.actualDoneDate = actualDoneDate;
	}
	public Integer getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Double getOrderCommission() {
		return orderCommission;
	}
	public void setOrderCommission(Double orderCommission) {
		this.orderCommission = orderCommission;
	}
	public Double getSingleOrderCommission() {
		return singleOrderCommission;
	}
	public void setSingleOrderCommission(Double singleOrderCommission) {
		this.singleOrderCommission = singleOrderCommission;
	}
	private Integer orderCount ;
    private Integer status;
}
