package com.edaisong.entity.domain;

public class BaiduApiResult {
	private BaiduApiLocation  location;
	
	private String formatted_address;
	
	private String business;
	
	private BaiduApiAddressComponent addressComponent;
	
	private String poiRegions[];
	
	private String sematic_description;
	
	private int cityCode;
	
	public BaiduApiLocation getLocation() {
		return location;
	}

	public void setLocation(BaiduApiLocation location) {
		this.location = location;
	}

	public String getFormatted_address() {
		return formatted_address;
	}

	public void setFormatted_address(String formatted_address) {
		this.formatted_address = formatted_address;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public BaiduApiAddressComponent getAddressComponent() {
		return addressComponent;
	}

	public void setAddressComponent(BaiduApiAddressComponent addressComponent) {
		this.addressComponent = addressComponent;
	}

	public String[] getPoiRegions() {
		return poiRegions;
	}

	public void setPoiRegions(String[] poiRegions) {
		this.poiRegions = poiRegions;
	}

	public String getSematic_description() {
		return sematic_description;
	}

	public void setSematic_description(String sematic_description) {
		this.sematic_description = sematic_description;
	}

	public int getCityCode() {
		return cityCode;
	}

	public void setCityCode(int cityCode) {
		this.cityCode = cityCode;
	}
}
