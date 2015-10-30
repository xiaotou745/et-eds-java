package com.edaisong.entity.resp;

import java.util.List; 

public class OrderRegionResp {
	
	/*
	 * 区域Id
	 */
	private Integer id;
	
	/*
	 * 区域名称
	 */
	private String regionName;
	
	/*
	 * 二级区域
	 */
	private List<TwoOrderRegion> twoOrderRegionList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
 
	public List<TwoOrderRegion> getTwoOrderRegionList() {
		return twoOrderRegionList;
	}

	public void setTwoOrderRegionList(List<TwoOrderRegion> twoOrderRegionList) {
		this.twoOrderRegionList = twoOrderRegionList;
	}
}
