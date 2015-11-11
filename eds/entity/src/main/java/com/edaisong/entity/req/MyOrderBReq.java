package com.edaisong.entity.req;

import com.edaisong.entity.common.PagedRequestBase;

public class MyOrderBReq extends PagedRequestBase{
	
	private Integer businessId;
	private Integer orderRegionOneId;
	private Integer orderRegionTwoId; 
	/*
	 * 任务状态  取货中    配送中    已完成
	 */
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
}
