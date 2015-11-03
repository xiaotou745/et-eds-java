package com.edaisong.entity.req;

import com.edaisong.entity.common.PagedRequestBase;

/**
 * 商戶端 查詢  我的骑士 入參
 * @version 3.0
 * @author CaoHeYang
 * @date 20151103
 */
public class PagedGetMyServiceClientersReq  extends PagedRequestBase{
	private int businessId;
	private Integer auditStatus;

	/**
	 * 商家id
	 * 
	 * @return
	 */
	public int getBusinessId() {
		return businessId;
	}

	/**
	 * 商家id
	 * 
	 * @param businessId
	 */
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}

	/**
	 * 0待审核，1审核通过
	 * 
	 * @return
	 */
	public Integer getAuditStatus() {
		return auditStatus;
	}

	/**
	 * 0待审核，1审核通过
	 * 
	 * @param auditStatus
	 */
	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

}
