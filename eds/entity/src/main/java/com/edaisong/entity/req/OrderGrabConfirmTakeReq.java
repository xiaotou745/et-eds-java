package com.edaisong.entity.req;

import java.util.Date;

public class OrderGrabConfirmTakeReq {
	private Integer grabOrderId;
	private Integer clienterId;
	private Double pickUpLongitude;
	private Double pickUpLatitude;
	public Integer getGrabOrderId() {
		return grabOrderId;
	}
	public void setGrabOrderId(Integer grabOrderId) {
		this.grabOrderId = grabOrderId;
	}
	public Integer getClienterId() {
		return clienterId;
	}
	public void setClienterId(Integer clienterId) {
		this.clienterId = clienterId;
	}
	public Double getPickUpLongitude() {
		return pickUpLongitude;
	}
	public void setPickUpLongitude(Double pickUpLongitude) {
		this.pickUpLongitude = pickUpLongitude;
	}
	public Double getPickUpLatitude() {
		return pickUpLatitude;
	}
	public void setPickUpLatitude(Double pickUpLatitude) {
		this.pickUpLatitude = pickUpLatitude;
	}	
   
}