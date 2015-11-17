package com.edaisong.entity.domain;

import com.edaisong.entity.BusinessClienterRelation;

public class BusinessClienterRelationModel extends BusinessClienterRelation{
	private String trueName;
	private String phoneNo;
	private int isCooperation;
	
	public int getIsCooperation() {
		return isCooperation;
	}

	public void setIsCooperation(int isCooperation) {
		this.isCooperation = isCooperation;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	/*public String getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}

	public BigDecimal getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}

	public BigDecimal getAllowWithdrwaPrice() {
		return allowWithdrwaPrice;
	}

	public void setAllowWithdrwaPrice(BigDecimal allowWithdrwaPrice) {
		this.allowWithdrwaPrice = allowWithdrwaPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	private String workStatus;
	private BigDecimal accountBalance;
	private BigDecimal allowWithdrwaPrice;
	private String status;*/
}
