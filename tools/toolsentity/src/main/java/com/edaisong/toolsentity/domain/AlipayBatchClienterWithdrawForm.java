package com.edaisong.toolsentity.domain;

/**
 * 支付宝批次详情 提现单详情
 * 
 * @author CaoHeYang
 * @date 20151020
 */
public class AlipayBatchClienterWithdrawForm {
	private int id;
	private String withwardNo;
	private String accountNo;
	private String trueName;
	private Double amount;
	private int status;
	private String lastOptUser;
	private String payFailedReason;

	/**
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 提现单号
	 * 
	 * @return
	 */
	public String getWithwardNo() {
		return withwardNo;
	}

	/**
	 * 提现单号
	 * 
	 * @param withwardNo
	 */
	public void setWithwardNo(String withwardNo) {
		this.withwardNo = withwardNo;
	}

	/**
	 * 支付宝账号
	 * 
	 * @return
	 */
	public String getAccountNo() {
		return accountNo;
	}

	/**
	 * 支付宝账号
	 * 
	 * @param accountNo
	 */
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	/**
	 * 支付宝用户名
	 * 
	 * @return
	 */
	public String getTrueName() {
		return trueName;
	}

	/**
	 * 支付宝用户名
	 * 
	 * @param trueName
	 */
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	/**
	 * 金额
	 * 
	 * @return
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * 金额
	 * 
	 * @param amount
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/**
	 * 提现单 状态
	 * 
	 * @return
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * 提现单 状态
	 * 
	 * @param status
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * 操作人
	 * 
	 * @return
	 */
	public String getLastOptUser() {
		return lastOptUser;
	}

	/**
	 * 操作人
	 * 
	 * @param lastOptUser
	 */
	public void setLastOptUser(String lastOptUser) {
		this.lastOptUser = lastOptUser;
	}

	/**
	 * 打款失败原因
	 * 
	 * @return
	 */
	public String getPayFailedReason() {
		return payFailedReason;
	}

	/**
	 * 打款失败原因
	 * 
	 * @param payFailedReason
	 */
	public void setPayFailedReason(String payFailedReason) {
		this.payFailedReason = payFailedReason;
	}

}
