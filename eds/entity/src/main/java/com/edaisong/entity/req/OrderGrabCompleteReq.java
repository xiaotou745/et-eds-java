package com.edaisong.entity.req;

import java.util.Date;

public class OrderGrabCompleteReq {
	private Integer orderGrabChildId;
	private Integer clienterId;	
	private Double doneLongitude;
	private Double doneLatitude;
	
	public Integer getOrderGrabChildId() {
		return orderGrabChildId;
	}
	public void setOrderGrabChildId(Integer orderGrabChild) {
		this.orderGrabChildId = orderGrabChild;
	}
	public Integer getClienterId() {
		return clienterId;
	}
	public void setClienterId(Integer clienterId) {
		this.clienterId = clienterId;
	}
	public Double getDoneLongitude() {
		return doneLongitude;
	}
	public void setDoneLongitude(Double doneLongitude) {
		this.doneLongitude = doneLongitude;
	}
	public Double getDoneLatitude() {
		return doneLatitude;
	}
	public void setDoneLatitude(Double doneLatitude) {
		this.doneLatitude = doneLatitude;
	}
   
}