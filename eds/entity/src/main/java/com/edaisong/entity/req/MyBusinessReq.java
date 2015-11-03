package com.edaisong.entity.req;

public class MyBusinessReq {
	/*
	 * 骑士Id
	 */
	private Integer clienterId;
	
	private String clienterName;
	/*
	 * 绑定的商户状态  （默认0待审核，1审核通过，2审核拒绝）
	 */
	
	private Integer auditStatus;

	public Integer getClienterId() {
		return clienterId;
	}

	public void setClienterId(Integer clienterId) {
		this.clienterId = clienterId;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getClienterName() {
		return clienterName;
	}

	public void setClienterName(String clienterName) {
		this.clienterName = clienterName;
	}
 
}
