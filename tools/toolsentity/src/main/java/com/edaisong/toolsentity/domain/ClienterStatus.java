package com.edaisong.toolsentity.domain;
/**
 * C端用户状态
 * @author CaoHeYang
 * @date 20150911
 */
public class ClienterStatus {
    private int userid ;
    private int status ;
    private String phoneno ;
    private Double amount ;
    private Double AllowWithdrawPrice ;
    private int IsBind ;
    private int IsOnlyShowBussinessTask ;
    private int DeliveryCompanyId ;
    private String DeliveryCompanyName ;
    private int IsDisplay ;
    private int WorkStatus ;
    private int IsReceivePush ;
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
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getAllowWithdrawPrice() {
		return AllowWithdrawPrice;
	}
	public void setAllowWithdrawPrice(Double allowWithdrawPrice) {
		AllowWithdrawPrice = allowWithdrawPrice;
	}
	/**
	 * 是否绑定了商户（0：否 1：是）
	 * @return
	 */
	public int getIsBind() {
		return IsBind;
	}
	/**
	 * 是否绑定了商户（0：否 1：是）
	 * @param isBind
	 */
	public void setIsBind(int isBind) {
		IsBind = isBind;
	}
	/**
	 * 是否只显示雇主任务
	 * @return
	 */
	public int getIsOnlyShowBussinessTask() {
		return IsOnlyShowBussinessTask;
	}
	/**
	 * 是否只显示雇主任务
	 * @param isOnlyShowBussinessTask
	 */
	public void setIsOnlyShowBussinessTask(int isOnlyShowBussinessTask) {
		IsOnlyShowBussinessTask = isOnlyShowBussinessTask;
	}
	/**
	 * 配送公司Id
	 * @return
	 */
	public int getDeliveryCompanyId() {
		return DeliveryCompanyId;
	}
	/**
	 * 配送公司Id
	 * @param deliveryCompanyId
	 */
	public void setDeliveryCompanyId(int deliveryCompanyId) {
		DeliveryCompanyId = deliveryCompanyId;
	}
	/**
	 * 配送公司名称
	 * @return
	 */
	public String getDeliveryCompanyName() {
		return DeliveryCompanyName;
	}
	/**
	 * 配送公司名称
	 * @param deliveryCompanyName
	 */
	public void setDeliveryCompanyName(String deliveryCompanyName) {
		DeliveryCompanyName = deliveryCompanyName;
	}
	/**
	 * 是否显示 金额 0隐藏 1 显示
	 * @return
	 */
	public int getIsDisplay() {
		return IsDisplay;
	}
	/**
	 * 是否显示 金额 0隐藏 1 显示
	 * @param isDisplay
	 */
	public void setIsDisplay(int isDisplay) {
		IsDisplay = isDisplay;
	}
	/**
	 *  超人状态 0上班  1下班 默认为0
	 * @return
	 */
	public int getWorkStatus() {
		return WorkStatus;
	}
	/**
	 *  超人状态 0上班  1下班 默认为0
	 * @param workStatus
	 */
	public void setWorkStatus(int workStatus) {
		WorkStatus = workStatus;
	}
	/**
	 * 骑士是否接受推送 1 接口 0 不接受 默认1
	 * @return
	 */
	public int getIsReceivePush() {
		return IsReceivePush;
	}
	/**
	 * 骑士是否接受推送 1 接口 0 不接受 默认1
	 * @param isReceivePush
	 */
	public void setIsReceivePush(int isReceivePush) {
		IsReceivePush = isReceivePush;
	}
    
}
