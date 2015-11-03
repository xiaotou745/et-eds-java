package com.edaisong.entity.domain;

import java.util.Date;
import java.util.List;

import com.edaisong.entity.OrderGrabChild;
import com.edaisong.entity.OrderSubsidiesLog;

public class FastOrderDetail {

	private Long id;
	private String grabOrderNo;
	private String orderRegionOneName;
	private String orderRegionTwoName;
	private Date actualDoneDate;
	private Double orderCommission;
	private Double distribsubsidy;
	private Integer orderCount;
	private Integer status;
	private Double singleWebsitesubsidy;
	private Double singleAdjustment;

	private Integer orderFrom;
	private String businessName;
	private String businessPhoneNo;
	private String businessPhoneNo2;
	private String businessAddress;
	private String clienterName;
	private String clienterPhoneNo;
	private List<OrderGrabChild> orderChilds;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGrabOrderNo() {
		return grabOrderNo;
	}

	public void setGrabOrderNo(String grabOrderNo) {
		this.grabOrderNo = grabOrderNo;
	}

	public String getOrderRegionOneName() {
		return orderRegionOneName;
	}

	public void setOrderRegionOneName(String orderRegionOneName) {
		this.orderRegionOneName = orderRegionOneName;
	}

	public String getOrderRegionTwoName() {
		return orderRegionTwoName;
	}

	public void setOrderRegionTwoName(String orderRegionTwoName) {
		this.orderRegionTwoName = orderRegionTwoName;
	}

	public Date getActualDoneDate() {
		return actualDoneDate;
	}

	public void setActualDoneDate(Date actualDoneDate) {
		this.actualDoneDate = actualDoneDate;
	}

	public Double getOrderCommission() {
		return orderCommission;
	}

	public void setOrderCommission(Double orderCommission) {
		this.orderCommission = orderCommission;
	}

	public Double getDistribsubsidy() {
		return distribsubsidy;
	}

	public void setDistribsubsidy(Double distribsubsidy) {
		this.distribsubsidy = distribsubsidy;
	}

	public Integer getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Double getSingleWebsitesubsidy() {
		return singleWebsitesubsidy;
	}

	public void setSingleWebsitesubsidy(Double singleWebsitesubsidy) {
		this.singleWebsitesubsidy = singleWebsitesubsidy;
	}

	public Double getSingleAdjustment() {
		return singleAdjustment;
	}

	public void setSingleAdjustment(Double singleAdjustment) {
		this.singleAdjustment = singleAdjustment;
	}

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
