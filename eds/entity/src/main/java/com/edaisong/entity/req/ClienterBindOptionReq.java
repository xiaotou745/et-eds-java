package com.edaisong.entity.req;

import java.util.Date;

/**
 * 商户绑定骑士操作请求
 * @author pengyi
 *
 */
public class ClienterBindOptionReq {
	// 商家Id
	private int businessId;
	// 骑士Id
	private int clienterId;
	// 操作人
	private String optName;
	// 添加时间
	private Date insertTime;
	// 操作描述
	private String remark;
	// 是否绑定(0:否 1:是)
	private int isBind;
	// 操作人Id
	private int optId;	
	public int getOptId() {
		return optId;
	}
	public void setOptId(int optId) {
		this.optId = optId;
	}
	public int getBusinessId() {
		return businessId;
	}
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}
	public int getClienterId() {
		return clienterId;
	}
	public void setClienterId(int clienterId) {
		this.clienterId = clienterId;
	}
	public String getOptName() {
		return optName;
	}
	public void setOptName(String optName) {
		this.optName = optName;
	}
	public Date getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getIsBind() {
		return isBind;
	}
	public void setIsBind(int isBind) {
		this.isBind = isBind;
	}
}
