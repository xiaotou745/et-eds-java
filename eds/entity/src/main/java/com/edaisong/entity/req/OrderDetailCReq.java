package com.edaisong.entity.req;

public class OrderDetailCReq {
	
	private Integer clienterId;
	
	private Integer grabOrderId;
	
	private Integer grabOrderChildId;
	
	private String grabOrderNo;

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
}
