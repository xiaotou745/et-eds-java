package com.edaisong.entity.req;


import java.lang.Double;
import java.util.Date;

import com.edaisong.entity.common.RequestBase;

public class ClienterReq extends RequestBase{
		
	private String trueName;
	private String phoneNo;	
	private Byte status;//审核状态       
	private String recommendPhone;
	private int code;
	private int deliveryCompanyId ;	
	private int currentPage;
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
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	public String getRecommendPhone() {
		return recommendPhone;
	}
	public void setRecommendPhone(String recommendPhone) {
		this.recommendPhone = recommendPhone;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getDeliveryCompanyId() {
		return deliveryCompanyId;
	}
	public void setDeliveryCompanyId(int deliveryCompanyId) {
		this.deliveryCompanyId = deliveryCompanyId;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}	
	
}

