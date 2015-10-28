package com.edaisong.entity.req;

import java.util.Date;

/**
 * 骑士 余额 可提现余额
 * 
 * @author CaoHeYang
 * @date 20150831
 */
public class ClienterMoney {
	private int clienterId;
	private Double amount;
	private int status;
	private int recordType;
	private String operator;
	private Date operateTime;
	private long withwardId;
	private String relationNo;
	private String remark;

	/**
	 * 骑士id
	 * 
	 * @return
	 */
	public int getClienterId() {
		return clienterId;
	}

	/**
	 * 骑士id
	 * 
	 * @param businessId
	 */
	public void setClienterId(int clienterId) {
		this.clienterId = clienterId;
	}

	/**
	 * 流水金额
	 * 
	 * @return
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * 流水金额
	 * 
	 * @param amount
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/**
	 * 流水状态(1、交易成功 2、交易中）
	 * 
	 * @return
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * 流水状态(1、交易成功 2、交易中）
	 * 
	 * @param status
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * 交易类型
	 * 
	 * @return
	 */
	public int getRecordType() {
		return recordType;
	}

	/**
	 * 交易类型
	 * 
	 * @param recordType
	 */
	public void setRecordType(int recordType) {
		this.recordType = recordType;
	}

	/**
	 * 操作人
	 * 
	 * @return
	 */
	public String getOperator() {
		return operator;
	}

	/**
	 * 操作人
	 * 
	 * @param operator
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}

	/**
	 * 操作时间
	 * 
	 * @return
	 */
	public Date getOperateTime() {
		return operateTime;
	}

	/**
	 * 操作时间
	 * 
	 * @param operateTime
	 */
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	/**
	 * 提现单ID
	 * 
	 * @return
	 */
	public long getWithwardId() {
		return withwardId;
	}

	/**
	 * 提现单ID
	 * 
	 * @param withwardId
	 */
	public void setWithwardId(long withwardId) {
		this.withwardId = withwardId;
	}

	/**
	 * 关联单号
	 * 
	 * @return
	 */
	public String getRelationNo() {
		return relationNo;
	}

	/**
	 * 关联单号
	 * 
	 * @param relationNo
	 */
	public void setRelationNo(String relationNo) {
		this.relationNo = relationNo;
	}

	/**
	 * 描述
	 * 
	 * @return
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 描述
	 * 
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
