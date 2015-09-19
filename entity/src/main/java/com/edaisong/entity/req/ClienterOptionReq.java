package com.edaisong.entity.req;

import java.lang.Double;
import java.util.Date;

import com.edaisong.entity.common.RequestBase;

public class ClienterOptionReq extends RequestBase {

	private Integer id; // 主键
	private Integer clienterId;// 骑士Id
	private Integer optId;// 操作人Id
	private String optName;// 操作人
	private Date insertTime;// 写入时间
	private Integer platform;// 平台属性
	private String remark;// 操作描述
	private Double rechargeAmount;// 调整金额
	private Short recordType;
	private Long withwardId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getClienterId() {
		return clienterId;
	}

	public void setClienterId(Integer clienterId) {
		this.clienterId = clienterId;
	}

	public Integer getOptId() {
		return optId;
	}

	public void setOptId(Integer optId) {
		this.optId = optId;
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

	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Double getRechargeAmount() {
		return rechargeAmount;
	}

	public void setRechargeAmount(Double rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}

	public Short getRecordType() {
		return recordType;
	}

	public void setRecordType(Short recordType) {
		this.recordType = recordType;
	}

	public Long getWithwardId() {
		return withwardId;
	}

	public void setWithwardId(Long withwardId) {
		this.withwardId = withwardId;
	}
}
