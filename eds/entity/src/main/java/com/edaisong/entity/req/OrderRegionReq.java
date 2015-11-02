package com.edaisong.entity.req;

import java.lang.Double;
import java.util.Date;
import java.util.List;

import com.edaisong.entity.OrderChild;

public class OrderRegionReq {

    private Integer businessId;
	
	private Integer status;

	public Integer getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
		private Integer orderRegionOneId;
		
		private Integer orderRegionTwoId;
		
		private Integer orderCount;
		
		private double amount;
	

		public Integer getOrderRegionOneId() {
			return orderRegionOneId;
		}

		public void setOrderRegionOneId(Integer orderRegionOneId) {
			this.orderRegionOneId = orderRegionOneId;
		}

		public Integer getOrderRegionTwoId() {
			return orderRegionTwoId;
		}

		public void setOrderRegionTwoId(Integer orderRegionTwoId) {
			this.orderRegionTwoId = orderRegionTwoId;
		}

		public Integer getOrderCount() {
			return orderCount;
		}

		public void setOrderCount(Integer orderCount) {
			this.orderCount = orderCount;
		}
		
		public double getAmount() {
			return amount;
		}

		public void setAmount(double amount) {
			this.amount = amount;
		}
		
}
