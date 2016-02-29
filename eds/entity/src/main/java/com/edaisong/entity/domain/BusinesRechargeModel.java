package com.edaisong.entity.domain;

import java.lang.Double;
import java.util.Date;

public class BusinesRechargeModel {

	private int businessId;

	private int optId;

	private String optName;

	private Date insertTime;

	private int platform;

	private String remark;

	private Double rechargeAmount;

	private Double rechargeAmountFree;

	private int rechargeType;

	public int getBusinessId() {
		return businessId;
	}

	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}

	/**
	 * 操作人Id
	 */
	public int getOptId() {
		return optId;
	}

	/**
	 * 操作人Id
	 */
	public void setOptId(int optId) {
		this.optId = optId;
	}

	/**
	 * 操作人
	 */
	public String getOptName() {
		return optName;
	}

	/**
	 * 操作人
	 */
	public void setOptName(String optName) {
		this.optName = optName;
	}

	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	public int getPlatform() {
		return platform;
	}

	public void setPlatform(int platform) {
		this.platform = platform;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 充值金额
	 */
	public Double getRechargeAmount() {
		return rechargeAmount;
	}

	/**
	 * 充值金额
	 */
	public void setRechargeAmount(Double rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}

	/**
	 * 赠送金额
	 */
	public Double getRechargeAmountFree() {
		return rechargeAmountFree;
	}

	/**
	 * 赠送金额
	 */
	public void setRechargeAmountFree(Double rechargeAmountFree) {
		this.rechargeAmountFree = rechargeAmountFree;
	}

	/**
	 * 1 充值 2 赠送 3 充值加赠送
	 */
	public int getRechargeType() {
		return rechargeType;
	}

	/**
	 * 1 充值 2 赠送 3 充值加赠送
	 */
	public void setRechargeType(int rechargeType) {
		this.rechargeType = rechargeType;
	}


}
