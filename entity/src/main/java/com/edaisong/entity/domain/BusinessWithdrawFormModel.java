package com.edaisong.entity.domain;

import com.edaisong.entity.BusinessWithdrawForm;

/**
 * 商家体现单Domain Model(包含商家信息)
 * @author pengyi
 * @date 20150831
 *
 */
public class BusinessWithdrawFormModel extends BusinessWithdrawForm {
	// 商户名称
	private String businessName;
	// 商户电话
	private String businessPhoneNo;
	// 商家累计提现金额
	private double hasWithdrawPrice;
	// 申请提款时间起
	private String withdrawDateStart;
	// 申请提款时间止
	private String withdrawDateEnd;
	// 提款单状态
	private int withdrawStatus;
	// 商户所在城市
	private String businessCity;
	// 超时时间（单位为：天）
	private int dateDiff;
	// 易宝RquestId
	private String requestId;
	// 易宝主账户
	private String customerNumber;
	// 易宝子账户
	private String ledgerno;
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getBusinessPhoneNo() {
		return businessPhoneNo;
	}
	public void setBusinessPhoneNo(String businessPhoneNo) {
		this.businessPhoneNo = businessPhoneNo;
	}
	public double getHasWithdrawPrice() {
		return hasWithdrawPrice;
	}
	public void setHasWithdrawPrice(double hasWithdrawPrice) {
		this.hasWithdrawPrice = hasWithdrawPrice;
	}
	public String getWithdrawDateStart() {
		return withdrawDateStart;
	}
	public void setWithdrawDateStart(String withdrawDateStart) {
		this.withdrawDateStart = withdrawDateStart;
	}
	public String getWithdrawDateEnd() {
		return withdrawDateEnd;
	}
	public void setWithdrawDateEnd(String withdrawDateEnd) {
		this.withdrawDateEnd = withdrawDateEnd;
	}
	public int getWithdrawStatus() {
		return withdrawStatus;
	}
	public void setWithdrawStatus(int withdrawStatus) {
		this.withdrawStatus = withdrawStatus;
	}
	public String getBusinessCity() {
		return businessCity;
	}
	public void setBusinessCity(String businessCity) {
		this.businessCity = businessCity;
	}
	public int getDateDiff() {
		return dateDiff;
	}
	public void setDateDiff(int dateDiff) {
		this.dateDiff = dateDiff;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getCustomerNumber() {
		return customerNumber;
	}
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}
	public String getLedgerno() {
		return ledgerno;
	}
	public void setLedgerno(String ledgerno) {
		this.ledgerno = ledgerno;
	}
}
