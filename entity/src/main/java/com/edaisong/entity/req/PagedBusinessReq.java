package com.edaisong.entity.req;

import java.math.BigDecimal;
import java.util.List;

import com.edaisong.entity.common.PagedRequestBase;
import com.edaisong.entity.common.RequestBase;

public class PagedBusinessReq extends PagedRequestBase {
	private String recommendPhone;
	private String businessName;
	private String businessPhone;
	private int status;
	private int mealsSettleMode;
	private BigDecimal businessSettlementRatio;
	private int groupId;
	private int businessGroupId;
	private int commissionType;
	private String businessCity;
	
	private int searchType;
	private String provinceCode;
	private String cityCode;
	private int businessId;


	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * 餐费结算方式（0：线下结算 1：线上结算）
	 * 
	 * @return the mealsSettleMode
	 */
	public int getMealsSettleMode() {
		return mealsSettleMode;
	}

	/**
	 * 餐费结算方式（0：线下结算 1：线上结算）
	 * 
	 * @param mealsSettleMode
	 *            the mealsSettleMode to set
	 */
	public void setMealsSettleMode(int mealsSettleMode) {
		this.mealsSettleMode = mealsSettleMode;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getBusinessPhone() {
		return businessPhone;
	}

	public void setBusinessPhone(String businessPhone) {
		this.businessPhone = businessPhone;
	}

	/**
	 * 商户结算比例
	 * 
	 * @return the businessSettlementRatio
	 */
	public BigDecimal getBusinessSettlementRatio() {
		return businessSettlementRatio;
	}

	/**
	 * 商户结算比例
	 * 
	 * @param businessSettlementRatio
	 *            the businessSettlementRatio to set
	 */
	public void setBusinessSettlementRatio(BigDecimal businessSettlementRatio) {
		this.businessSettlementRatio = businessSettlementRatio;
	}

	/**
	 * 查询类型,按当天,本周,还是本月
	 * 
	 * @return the searchType
	 */
	public int getSearchType() {
		return searchType;
	}

	/**
	 * 查询类型,按当天,本周,还是本月
	 * 
	 * @param searchType
	 *            the searchType to set
	 */
	public void setSearchType(int searchType) {
		this.searchType = searchType;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getBusinessCity() {
		return businessCity;
	}

	public void setBusinessCity(String businessCity) {
		this.businessCity = businessCity;
	}

	/**
	 * 结算类型：1：固定比例 2：固定金额
	 * 
	 * @return the commissionType
	 */
	public int getCommissionType() {
		return commissionType;
	}

	/**
	 * 结算类型：1：固定比例 2：固定金额
	 * 
	 * @param commissionType
	 *            the commissionType to set
	 */
	public void setCommissionType(int commissionType) {
		this.commissionType = commissionType;
	}

	/**
	 * 分组ID
	 * 
	 * @return the businessGroupId
	 */
	public int getBusinessGroupId() {
		return businessGroupId;
	}

	/**
	 * 分组ID
	 * 
	 * @param businessGroupId
	 *            the businessGroupId to set
	 */
	public void setBusinessGroupId(int businessGroupId) {
		this.businessGroupId = businessGroupId;
	}

	public int getBusinessId() {
		return businessId;
	}

	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}

	/**
	 * 推荐人电话
	 * 
	 * @return the recommendPhone
	 */
	public String getRecommendPhone() {
		return recommendPhone;
	}

	/**
	 * 推荐人电话
	 * 
	 * @param recommendPhone
	 *            the recommendPhone to set
	 */
	public void setRecommendPhone(String recommendPhone) {
		this.recommendPhone = recommendPhone;
	}
}
