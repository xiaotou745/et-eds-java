package com.edaisong.entity;

import java.util.Date;

/**
 * 实体类AlipayBatch. (属性说明自动提取数据库字段的描述信息)
 * 
 * @author edaisong.system
 * @date 2015-10-20 11:31:04
 *
 */
public class AlipayBatch {
	/**
	 * 自增ID(PK)
	 */
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 批次单号
	 */
	private String batchNo;

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	/**
	 * 总提现金额
	 */
	private Double totalWithdraw;

	public Double getTotalWithdraw() {
		return totalWithdraw;
	}

	public void setTotalWithdraw(Double totalWithdraw) {
		this.totalWithdraw = totalWithdraw;
	}

	/**
	 * 操作笔数
	 */
	private Integer optTimes;

	public Integer getOptTimes() {
		return optTimes;
	}

	public void setOptTimes(Integer optTimes) {
		this.optTimes = optTimes;
	}

	/**
	 * 成功笔数
	 */
	private Integer successTimes;

	public Integer getSuccessTimes() {
		return successTimes;
	}

	public void setSuccessTimes(Integer successTimes) {
		this.successTimes = successTimes;
	}

	/**
	 * 失败笔数
	 */
	private Integer failTimes;

	public Integer getFailTimes() {
		return failTimes;
	}

	public void setFailTimes(Integer failTimes) {
		this.failTimes = failTimes;
	}

	/**
	 * 批次单状态 0打款中 1 打款完成 默认0
	 */
	private Integer status;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 批次单下属提现单号集合 多个提现单号用 ',' 分割
	 */
	private String withdrawNos;

	public String getWithdrawNos() {
		return withdrawNos;
	}

	public void setWithdrawNos(String withdrawNos) {
		this.withdrawNos = withdrawNos;
	}

	/**
	 * 批次单下属提现单id集合 多个提现单id用 ',' 分割
	 */
	private String withdrawIds;

	public String getWithdrawIds() {
		return withdrawIds;
	}

	public void setWithdrawIds(String withdrawIds) {
		this.withdrawIds = withdrawIds;
	}

	/**
	 * 批次单创建人
	 */
	private String createBy;

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	/**
	 * 批次单创建时间 默认系统时间
	 */
	private Date createTime;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 支付宝回调时间
	 */
	private Date callbackTime;

	public Date getCallbackTime() {
		return callbackTime;
	}

	public void setCallbackTime(Date callbackTime) {
		this.callbackTime = callbackTime;
	}

	/**
	 * 最后操作人
	 */
	private String lastOptUser;

	public String getLastOptUser() {
		return lastOptUser;
	}

	public void setLastOptUser(String lastOptUser) {
		this.lastOptUser = lastOptUser;
	}

	/**
	 * 最后操作时间
	 */
	private Date lastOptTime;

	public Date getLastOptTime() {
		return lastOptTime;
	}

	public void setLastOptTime(Date lastOptTime) {
		this.lastOptTime = lastOptTime;
	}

	/**
	 * 备注
	 */
	private String remarks;

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}