package com.edaisong.entity.domain;

public class BusinessRechargeLog {
	/// <summary>
    /// 商户Id
    /// </summary>
    public int businessId ;

    /// <summary>
    /// 操作人
    /// </summary>
    public String optName ;

    /// <summary>
    /// 操作描述
    /// </summary>
    public String remark ;
    /// <summary>
    /// 充值金额
    /// </summary>
    public Double rechargeAmount ;
    /// <summary>
    /// 1 充值 2 赠送 3 充值加赠送
    /// </summary>
    public int rechargeType ;
    /// <summary>
    /// 支付方式：1：支付宝；2微信;3后台;4赠送
    /// </summary>
    public int payType ;
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
	public int getRechargeType() {
		return rechargeType;
	}
	public void setRechargeType(int rechargeType) {
		this.rechargeType = rechargeType;
	}
	public int getPayType() {
		return payType;
	}
	public void setPayType(int payType) {
		this.payType = payType;
	}
    

}
