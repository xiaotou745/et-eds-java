package com.edaisong.entity.req;

import java.lang.Double;
import java.util.Date;
import java.util.List;

import com.edaisong.entity.OrderChild;

public class OrderDetailReq {
	private Integer orderId;

    private Integer businessId;
    
    private Integer clienterId;
    
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

	public Integer getClienterId() {
		return clienterId;
	}

	public void setClienterId(Integer clienterId) {
		this.clienterId = clienterId;
	}


	    

}
