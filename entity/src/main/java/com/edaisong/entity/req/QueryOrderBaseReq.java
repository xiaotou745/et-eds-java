package com.edaisong.entity.req;

import com.edaisong.entity.common.PagedRequestBase;

/**
 * 移动端查询订单列表
 * 
 * @author CaoHeYang
 * @date 20150910
 */
public class QueryOrderBaseReq extends PagedRequestBase {
	private Integer businessId;
	private Integer status;

	/**
	 * 
	 * @return
	 */
	public Integer getBusinessId() {
		return businessId;
	}

	/**
	 * 
	 * @param businessId
	 */
	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}

	/**
	 * 订单状态
	 * 
	 * @return
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 订单状态
	 * 
	 * @param status
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
}
