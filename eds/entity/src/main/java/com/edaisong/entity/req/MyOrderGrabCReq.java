package com.edaisong.entity.req;

public class MyOrderGrabCReq {
	
	private Integer clienterId;
	/*
	 * 任务状态  取货中    配送中
	 */
	private Integer status;

	private double clienterLongitude;
	
	private double clienterLatitude;
	
	public Integer getClienterId() {
		return clienterId;
	}

	public void setClienterId(Integer clienterId) {
		this.clienterId = clienterId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
