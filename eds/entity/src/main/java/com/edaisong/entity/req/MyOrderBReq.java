package com.edaisong.entity.req;

import com.edaisong.entity.common.PagedRequestBase;

public class MyOrderBReq extends PagedRequestBase{
	
	private Integer businessId;
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
}
