package com.edaisong.entity.domain;

import java.util.Date;
import java.util.List;

import com.edaisong.entity.OrderGrab;
import com.edaisong.entity.OrderGrabChild;

public class FastOrderDetail extends OrderGrab{
	private Integer orderFrom;
	private String businessName;
	private String businessPhoneNo;
	private String businessPhoneNo2;
	private String businessAddress;
	private String clienterName;
	private String clienterPhoneNo;
	private List<OrderGrabChild> orderChilds;

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

	public String getBusinessPhoneNo2() {
		return businessPhoneNo2;
	}

	public void setBusinessPhoneNo2(String businessPhoneNo2) {
		this.businessPhoneNo2 = businessPhoneNo2;
	}

	public String getBusinessAddress() {
		return businessAddress;
	}

	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
	}

	public String getClienterName() {
		return clienterName;
	}

	public void setClienterName(String clienterName) {
		this.clienterName = clienterName;
	}

	public List<OrderGrabChild> getOrderChilds() {
		return orderChilds;
	}

	public void setOrderChilds(List<OrderGrabChild> orderChilds) {
		this.orderChilds = orderChilds;
	}

	public String getClienterPhoneNo() {
		return clienterPhoneNo;
	}

	public void setClienterPhoneNo(String clienterPhoneNo) {
		this.clienterPhoneNo = clienterPhoneNo;
	}

}
