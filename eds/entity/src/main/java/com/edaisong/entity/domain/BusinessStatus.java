package com.edaisong.entity.domain;

/**
 * B端用户状态
 * @author CaoHeYang
 * @date 20150911
 */
public class BusinessStatus {
    private int userid;
    private int status;
    private int OneKeyPubOrder;
    private int IsAllowOverdraft;
    private Double BalancePrice;
    private int IsAllowCashPay;
    private int isEnable;
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getOneKeyPubOrder() {
		return OneKeyPubOrder;
	}
	public void setOneKeyPubOrder(int oneKeyPubOrder) {
		OneKeyPubOrder = oneKeyPubOrder;
	}
	/**
	 * 是否允许透支，1允许，0不允许
	 * @return
	 */
	public int getIsAllowOverdraft() {
		return IsAllowOverdraft;
	}
	/**
	 * 是否允许透支，1允许，0不允许
	 * @param isAllowOverdraft
	 */
	public void setIsAllowOverdraft(int isAllowOverdraft) {
		IsAllowOverdraft = isAllowOverdraft;
	}
	/**
	 * 商户余额
	 * @return
	 */
	public Double getBalancePrice() {
		return BalancePrice;
	}
	/**
	 * 商户余额
	 * @param balancePrice
	 */
	public void setBalancePrice(Double balancePrice) {
		BalancePrice = balancePrice;
	}
	/**
	 * 是否允许现金支付 1允许 0不允许
	 * @return
	 */
	public int getIsAllowCashPay() {
		return IsAllowCashPay;
	}
	/**
	 * 是否允许现金支付 1允许 0不允许
	 * @param isAllowCashPay
	 */
	public void setIsAllowCashPay(int isAllowCashPay) {
		IsAllowCashPay = isAllowCashPay;
	}
	public int getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(int isEnable) {
		this.isEnable = isEnable;
	}
    
}
