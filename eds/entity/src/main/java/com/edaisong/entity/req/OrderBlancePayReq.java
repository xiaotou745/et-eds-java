package com.edaisong.entity.req;

import java.util.Date;

public class OrderBlancePayReq {
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}

	private Integer orderId;

    private Integer businessId;  
    
    private Double tipamount;
    
    private Integer type;
    
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Double getTipamount() {
		return tipamount;
	}

	public void setTipamount(Double tipamount) {
		this.tipamount = tipamount;
	}

}