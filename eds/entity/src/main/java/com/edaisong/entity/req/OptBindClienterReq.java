package com.edaisong.entity.req;

/**
 * 商戶端 我的骑士 申请中 同意/拒绝功能
 * 
 * @author CaoHeYang
 * @date 20151103
 */
public class OptBindClienterReq {
	private int relationId;
	private int auditStatus;
	private String optName;
	private int businessId;
	private String remark;
	private int isEnable;
	private int isBind;

	/**
	 * 关系id
	 * 
	 * @return
	 */
	public int getRelationId() {
		return relationId;
	}

	/**
	 * 关系id
	 * 
	 * @param relationId
	 */
	public void setRelationId(int relationId) {
		this.relationId = relationId;
	}

	/**
	 * 1审核通过，2审核拒绝
	 * 
	 * @return
	 */
	public int getAuditStatus() {
		return auditStatus;
	}

	/**
	 * 1审核通过，2审核拒绝
	 * 
	 * @param auditStatus
	 */
	public void setAuditStatus(int auditStatus) {
		this.auditStatus = auditStatus;
	}

	/**
	 * 操作人 业务逻辑层赋值
	 */
	public String getOptName() {
		return optName;
	}

	/**
	 * 操作人 业务逻辑层赋值
	 */
	public void setOptName(String optName) {
		this.optName = optName;
	}

	/**
	 * 商户id
	 * 
	 * @return
	 */
	public int getBusinessId() {
		return businessId;
	}

	/**
	 * 商户id
	 * 
	 * @param businessId
	 */
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}

	/**
	 * 备注 业务逻辑层赋值
	 * 
	 * @return
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 备注  业务逻辑层赋值
	 * 
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
  /**
   *  是否有效(0:否 1:是) 业务逻辑层赋值 
   * @return
   */
	public int getIsEnable() {
		return isEnable;
	}
  /**
   * 是否有效(0:否 1:是)  业务逻辑层赋值
   * @param isEnable
   */
	public void setIsEnable(int isEnable) {
		this.isEnable = isEnable;
	}
/**
 * 是否绑定(0:否 1:是) 业务逻辑层赋值
 * @return
 */
	public int getIsBind() {
		return isBind;
	}
/**
 * 是否绑定(0:否 1:是) 业务逻辑层赋值
 * @param isBind 
 */
	public void setIsBind(int isBind) {
		this.isBind = isBind;
	}

}
