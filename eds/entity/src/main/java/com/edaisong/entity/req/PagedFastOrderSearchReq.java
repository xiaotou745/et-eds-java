package com.edaisong.entity.req;

import com.edaisong.entity.common.PagedRequestBase;

public class PagedFastOrderSearchReq extends PagedRequestBase{
    private String superManPhone ; 
    private String businessPhone ;
    private String orderNo ;          
    private String superManName ; 
    private String businessName ; 
	private Integer orderStatus; 
    private String businessCity ; 
    private String orderGrabStart ;
    private String orderGrabEnd ;
    public String getSuperManPhone() {
		return superManPhone;
	}
	public void setSuperManPhone(String superManPhone) {
		this.superManPhone = superManPhone;
	}
	public String getBusinessPhone() {
		return businessPhone;
	}
	public void setBusinessPhone(String businessPhone) {
		this.businessPhone = businessPhone;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getSuperManName() {
		return superManName;
	}
	public void setSuperManName(String superManName) {
		this.superManName = superManName;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getBusinessCity() {
		return businessCity;
	}
	public void setBusinessCity(String businessCity) {
		this.businessCity = businessCity;
	}
	public String getOrderGrabStart() {
		return orderGrabStart;
	}
	public void setOrderGrabStart(String orderGrabStart) {
		this.orderGrabStart = orderGrabStart;
	}
	public String getOrderGrabEnd() {
		return orderGrabEnd;
	}
	public void setOrderGrabEnd(String orderGrabEnd) {
		this.orderGrabEnd = orderGrabEnd;
	}
}
