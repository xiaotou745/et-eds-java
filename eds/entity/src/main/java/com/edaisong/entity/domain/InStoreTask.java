package com.edaisong.entity.domain;

import java.util.List;

/**
 * 门店订单信息基本情况
 * @author CaoHeYang
 * @date 20151102
 */
public class InStoreTask {
	private int businessId;
	private String businessName;
	private String businessAddress;
	private String distanceToBusiness;
	private List<InStoreOrderRegionInfo> list;
	private double businessLongitude;
	private double businessLatitude;
	private String businessCityName;
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
	 * 商户名称
	 * 
	 * @return
	 */
	public String getBusinessName() {
		return businessName;
	}

	/**
	 * 商户名称
	 * 
	 * @param businessName
	 */
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	/**
	 * 商户地址
	 * 
	 * @return
	 */
	public String getBusinessAddress() {
		return businessAddress;
	}

	/**
	 * 商户地址
	 * 
	 * @param businessAddress
	 */
	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
	}

	/**
	 * 骑士到门店距离
	 * 
	 * @return
	 */
	public String getDistanceToBusiness() {
		return distanceToBusiness;
	}

	/**
	 * 骑士到门店距离
	 * 
	 * @param distanceToBusiness
	 */
	public void setDistanceToBusiness(String distanceToBusiness) {
		this.distanceToBusiness = distanceToBusiness;
	}

	/**
	 * 门店下一级 区域 信息
	 * 
	 * @return
	 */
	public List<InStoreOrderRegionInfo> getList() {
		return list;
	}

	/**
	 * 门店下一级 区域 信息
	 * 
	 * @param list
	 */
	public void setList(List<InStoreOrderRegionInfo> list) {
		this.list = list;
	}  

	public double getBusinessLongitude() {
		return businessLongitude;
	}

	public void setBusinessLongitude(double businessLongitude) {
		this.businessLongitude = businessLongitude;
	}

	public double getBusinessLatitude() {
		return businessLatitude;
	}

	public void setBusinessLatitude(double businessLatitude) {
		this.businessLatitude = businessLatitude;
	}

	public String getBusinessCityName() {
		return businessCityName;
	}

	public void setBusinessCityName(String businessCityName) {
		this.businessCityName = businessCityName;
	}

}
