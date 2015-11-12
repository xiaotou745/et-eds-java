package com.edaisong.entity.domain;

import java.util.Date;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;

public class OrderRegionModel {
    private Integer overlayId;
	private String overlayName;
	@JsonIgnore
	private Date opttime;
    private List<LatAndLng> overlayPointList;
    private List<OrderRegionModel> subLists;
    public Integer getOverlayId() {
		return overlayId;
	}
	public void setOverlayId(Integer overlayId) {
		this.overlayId = overlayId;
	}
	public String getOverlayName() {
		return overlayName;
	}
	public void setOverlayName(String overlayName) {
		this.overlayName = overlayName;
	}
	public List<LatAndLng> getOverlayPointList() {
		return overlayPointList;
	}
	public void setOverlayPointList(List<LatAndLng> overlayPointList) {
		this.overlayPointList = overlayPointList;
	}
	public List<OrderRegionModel> getSubLists() {
		return subLists;
	}
	public void setSubLists(List<OrderRegionModel> subLists) {
		this.subLists = subLists;
	}
	public Date getOpttime() {
		return opttime;
	}
	public void setOpttime(Date opttime) {
		this.opttime = opttime;
	}
}
