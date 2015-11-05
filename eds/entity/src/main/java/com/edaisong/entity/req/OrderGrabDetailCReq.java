package com.edaisong.entity.req;

public class OrderGrabDetailCReq {
	
	private Integer clienterId;
	
	private Integer grabOrderId;
	 
	private Integer status;
	
	private String grabOrderNo; 
	
	private double clienterLongitude;
	
	private double clienterLatitude;
	public Integer getClienterId() {
		return clienterId;
	}

	public void setClienterId(Integer clienterId) {
		this.clienterId = clienterId;
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
 

	public double getClienterLongitude() {
		return clienterLongitude;
	}

	public void setClienterLongitude(double clienterLongitude) {
		this.clienterLongitude = clienterLongitude;
	}

	public double getClienterLatitude() {
		return clienterLatitude;
	}

	public void setClienterLatitude(double clienterLatitude) {
		this.clienterLatitude = clienterLatitude;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	} 
}
