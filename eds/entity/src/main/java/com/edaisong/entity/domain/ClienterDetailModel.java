package com.edaisong.entity.domain;

import com.edaisong.entity.Clienter;

public class ClienterDetailModel extends Clienter{
	 /// <summary>
    /// 户名
    /// </summary>
    private String accountName ;
    /// <summary>
    /// 卡号(DES加密)
    /// </summary>
    private String accountNo ;
    /// <summary>
    /// 账号类型：(1网银 2支付宝 3微信 4财付通 5百度钱包）
    /// </summary>
    private int accountType ;
    /// <summary>
    /// 开户行
    /// </summary>
    private String openBank ;
    /// <summary>
    /// 开户支行
    /// </summary>
    private String openSubBank ;
    /// <summary>
    /// 操作人id
    /// </summary>
    private int optUserId ;

    /// <summary>
    /// 操作人名称
    /// </summary>
    private String optUserName ;
    /// <summary>
    /// 标签
    /// </summary>
    private String tags ;


    /// <summary>
    /// 是否店内骑士
    /// </summary>
    private int isCooperation ;

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public int getAccountType() {
		return accountType;
	}

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

	public int getOptUserId() {
		return optUserId;
	}

	public void setOptUserId(int optUserId) {
		this.optUserId = optUserId;
	}

	public String getOptUserName() {
		return optUserName;
	}

	public void setOptUserName(String optUserName) {
		this.optUserName = optUserName;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}


	public int getIsCooperation() {
		return isCooperation;
	}

	public void setIsCooperation(int isCooperation) {
		this.isCooperation = isCooperation;
	}
}
