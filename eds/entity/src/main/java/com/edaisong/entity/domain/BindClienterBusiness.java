package com.edaisong.entity.domain;

public class BindClienterBusiness {
	private Integer clienterId; 
	private Integer businessId;
	private String businessName;
	public Integer getClienterId() {
		return clienterId;
	}

	public void setClienterId(Integer clienterId) {
		this.clienterId = clienterId;
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
}
