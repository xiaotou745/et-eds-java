package com.edaisong.entity.domain;

import com.edaisong.entity.Business;

public class BusinessDetailModel extends Business {

	private String trueName;
	private String accountNo;
	private int accountType;
	private String openBank;
	private String openSubBank;
	private String groupName;
	private int isModifyBind;

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	/**
	 * 账号类型：(1网银 2支付宝 3微信 4财付通 5百度钱包）
	 */
	public int getAccountType() {
		return accountType;
	}

	/**
	 * 账号类型：(1网银 2支付宝 3微信 4财付通 5百度钱包）
	 */
	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

	public String getOpenBank() {
		return openBank;
	}

	public void setOpenBank(String openBank) {
		this.openBank = openBank;
	}

	public String getOpenSubBank() {
		return openSubBank;
	}

	public void setOpenSubBank(String openSubBank) {
		this.openSubBank = openSubBank;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * 是否可修改绑定第三方绑定
	 */
	public int getIsModifyBind() {
		return isModifyBind;
	}

	/**
	 * 是否可修改绑定第三方绑定
	 */
	public void setIsModifyBind(int isModifyBind) {
		this.isModifyBind = isModifyBind;
	}

}
