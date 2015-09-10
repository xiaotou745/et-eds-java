package com.edaisong.entity.req;

import java.util.Date;

/**
 * 集团绑定商户操作请求
 * @author pengyi
 *
 */
public class BusinessBindOptionReq {
	// 商家Id
	private int businessId;
	// 集团Id
	private int groupId;
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
	private Short optType;//1绑定   2解绑
	public Short getOptType() {
		return optType;
	}
	public void setOptType(Short optType) {
		this.optType = optType;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
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
