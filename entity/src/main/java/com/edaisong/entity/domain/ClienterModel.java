package com.edaisong.entity.domain;


import java.math.BigDecimal;
import java.util.Date;

public class ClienterModel {
    private Long id;

    private String trueName;    

    private String phoneNo;
    
    private String idCard;   

    private  BigDecimal accountBalance;//帐户余额
    
    private String picWithHandUrl;
    
    private String picUrl;    

    private  Date  insertTime;
    
    private int workStatus;      
    
    private  BigDecimal allowWithdrawPrice;//可提现余额
    
    private int status;//审核状态     

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public BigDecimal getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getPicWithHandUrl() {
		return picWithHandUrl;
	}

	public void setPicWithHandUrl(String picWithHandUrl) {
		this.picWithHandUrl = picWithHandUrl;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	public int getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(int workStatus) {
		this.workStatus = workStatus;
	}

	public BigDecimal getAllowWithdrawPrice() {
		return allowWithdrawPrice;
	}

	public void setAllowWithdrawPrice(BigDecimal allowWithdrawPrice) {
		this.allowWithdrawPrice = allowWithdrawPrice;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
    
}
