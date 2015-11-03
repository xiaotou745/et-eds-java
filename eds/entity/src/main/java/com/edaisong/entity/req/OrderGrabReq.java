package com.edaisong.entity.req;

import java.util.Date;

public class OrderGrabReq {
	private Integer businessId;
	private Integer clienterId;
	private Integer orderRegionOneId;
	private String orderRegionOneName;
	private Integer orderRegionTwoId;
	private String  orderRegionTwoName;
	private Integer orderCount;	
	private Integer id;
	

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBusinessId() {
		return businessId;
	}
	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}
	public double getGrabLongitude() {
		return grabLongitude;
	}
	public void setGrabLongitude(double grabLongitude) {
		this.grabLongitude = grabLongitude;
	}
	public double getGrabLatitude() {
		return grabLatitude;
	}
	public void setGrabLatitude(double grabLatitude) {
		this.grabLatitude = grabLatitude;
	}
	private double grabLongitude;
	private double grabLatitude;
	
	public Integer getClienterId() {
		return clienterId;
	}
	public void setClienterId(Integer clienterId) {
		this.clienterId = clienterId;
	}
	public Integer getOrderRegionOneId() {
		return orderRegionOneId;
	}
	public void setOrderRegionOneId(Integer orderRegionOneId) {
		this.orderRegionOneId = orderRegionOneId;
	}
	public String getOrderRegionOneName() {
		return orderRegionOneName;
	}
	public void setOrderRegionOneName(String orderRegionOneName) {
		this.orderRegionOneName = orderRegionOneName;
	}
	public Integer getOrderRegionTwoId() {
		return orderRegionTwoId;
	}
	public void setOrderRegionTwoId(Integer orderRegionTwoId) {
		this.orderRegionTwoId = orderRegionTwoId;
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
	public void setOrderCount(Integer ordercount) {
		this.orderCount = ordercount;
	}	
   
}