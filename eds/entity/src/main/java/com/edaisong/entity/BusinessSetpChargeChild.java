package com.edaisong.entity;

import java.util.Date;

public class BusinessSetpChargeChild {
	/**
	 * 
	 */
	private Long id;

	/**
	 * 步长策略ID
	 */
	private Long setpChargeId;

	/**
	 * 该阶段最低值(不包含)
	 */
	private Double minValue;

	/**
	 * 该区间最高值(包含)
	 */
	private Double maxValue;

	/**
	 * 
	 */
	private Date createDate;

	/**
	 * 收费金额
	 */
	private Double chargeValue;


	/**
	 * 获取
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置
	 * @param id 
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取步长策略ID
	 */
	public Long getSetpChargeId() {
		return setpChargeId;
	}
	/**
	 * 设置步长策略ID
	 * @param setpChargeId 步长策略ID
	 */
	public void setSetpChargeId(Long setpChargeId) {
		this.setpChargeId = setpChargeId;
	}

	/**
	 * 获取该阶段最低值(不包含)
	 */
	public Double getMinValue() {
		return minValue;
	}
	/**
	 * 设置该阶段最低值(不包含)
	 * @param minValue 该阶段最低值(不包含)
	 */
	public void setMinValue(Double minValue) {
		this.minValue = minValue;
	}

	/**
	 * 获取该区间最高值(包含)
	 */
	public Double getMaxValue() {
		return maxValue;
	}
	/**
	 * 设置该区间最高值(包含)
	 * @param maxValue 该区间最高值(包含)
	 */
	public void setMaxValue(Double maxValue) {
		this.maxValue = maxValue;
	}

	/**
	 * 获取
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * 设置
	 * @param createDate 
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * 获取收费金额
	 */
	public Double getChargeValue() {
		return chargeValue;
	}
	/**
	 * 设置收费金额
	 * @param chargeValue 收费金额
	 */
	public void setChargeValue(Double chargeValue) {
		this.chargeValue = chargeValue;
	}


}
