package com.edaisong.toolsentity.domain;


import java.lang.Double;
import java.text.DecimalFormat;
import java.util.Date;

public class ClienterModel {
    private Integer id;

    private String trueName;    

    private String phoneNo;
    
    private String idCard;   

    private  Double accountBalance;//帐户余额
    
    private String picWithHandUrl;
    
    private String picUrl;    

    private  Date  insertTime;
    
    private Integer workStatus;      
    
    private  Double allowWithdrawPrice;//可提现余额
    
    private Byte status;//审核状态       
    
    private String groupName;
    
    private Integer deliveryCompanyId;
    
    private String deliveryCompanyName;
    
    private String recommendPhone;
    
    private String recommendName;
    
    // 是否绑定（0：否 1：是）
    private int isBind;

	public int getIsBind() {
		return isBind;
	}

	public void setIsBind(int isBind) {
		this.isBind = isBind;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Double getAccountBalance() {
		return accountBalance;
	}
	public String getAccountBalanceString() {
		return new DecimalFormat("0.00").format(accountBalance);
	}
	public void setAccountBalance(Double accountBalance) {
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

	public Integer getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(Integer workStatus) {
		this.workStatus = workStatus;
	}

	public Double getAllowWithdrawPrice() {
		return allowWithdrawPrice;
	}
	
	public String getAllowWithdrawPriceString() {
	    return new DecimalFormat("0.00").format(allowWithdrawPrice);
	}

	public void setAllowWithdrawPrice(Double allowWithdrawPrice) {
		this.allowWithdrawPrice = allowWithdrawPrice;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Integer getDeliveryCompanyId() {
		return deliveryCompanyId;
	}

	public void setDeliveryCompanyId(Integer deliveryCompanyId) {
		this.deliveryCompanyId = deliveryCompanyId;
	}

	public String getDeliveryCompanyName() {
		return deliveryCompanyName;
	}

	public void setDeliveryCompanyName(String deliveryCompanyName) {
		this.deliveryCompanyName = deliveryCompanyName;
	}

	public String getRecommendPhone() {
		return recommendPhone;
	}

	public void setRecommendPhone(String recommendPhone) {
		this.recommendPhone = recommendPhone;
	}

	public String getRecommendName() {
		return recommendName;
	}

	public void setRecommendName(String recommendName) {
		this.recommendName = recommendName;
	}    
    
    
}
