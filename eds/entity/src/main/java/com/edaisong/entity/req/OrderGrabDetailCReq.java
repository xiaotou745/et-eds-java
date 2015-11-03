package com.edaisong.entity.req;

public class OrderGrabDetailCReq {
	
	private Integer clienterId;
	
	private Integer grabOrderId;
	
	 
	private Integer grabOrderChildId;
	
	private String grabOrderNo;
	private Integer hadCompleteOrderGrab;
	
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

	public Integer getGrabOrderChildId() {
		return grabOrderChildId;
	}

	public void setGrabOrderChildId(Integer grabOrderChildId) {
		this.grabOrderChildId = grabOrderChildId;
	}

	public String getGrabOrderNo() {
		return grabOrderNo;
	}

	public void setGrabOrderNo(String grabOrderNo) {
		this.grabOrderNo = grabOrderNo;
	}

	public Integer getHadCompleteOrderGrab() {
		return hadCompleteOrderGrab;
	}

	public void setHadCompleteOrderGrab(Integer hadCompleteOrderGrab) {
		this.hadCompleteOrderGrab = hadCompleteOrderGrab;
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
}
