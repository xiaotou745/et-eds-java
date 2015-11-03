package com.edaisong.entity.domain;

import java.util.Date;

public class FastOrderMapDetail {
	private Date pubDate ;
	private Double latitude ;
	private Double longitude ;
	private Date grabTime ;
	private Double grabLatitude ;
	private Double grabLongitude ;
	private Date pickUpTime ;
	private Double pickUpLatitude ;
	private Double pickUpLongitude ;
	private Date actualDoneDate ;
	private Double doneLatitude ;
	private Double doneLongitude;
	private Integer status;
	public Date getPubDate() {
		return pubDate;
	}
	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Date getGrabTime() {
		return grabTime;
	}
	public void setGrabTime(Date grabTime) {
		this.grabTime = grabTime;
	}
	public Double getGrabLatitude() {
		return grabLatitude;
	}
	public void setGrabLatitude(Double grabLatitude) {
		this.grabLatitude = grabLatitude;
	}
	public Double getGrabLongitude() {
		return grabLongitude;
	}
	public void setGrabLongitude(Double grabLongitude) {
		this.grabLongitude = grabLongitude;
	}
	public Date getPickUpTime() {
		return pickUpTime;
	}
	public void setPickUpTime(Date pickUpTime) {
		this.pickUpTime = pickUpTime;
	}
	public Double getPickUpLatitude() {
		return pickUpLatitude;
	}
	public void setPickUpLatitude(Double pickUpLatitude) {
		this.pickUpLatitude = pickUpLatitude;
	}
	public Double getPickUpLongitude() {
		return pickUpLongitude;
	}
	public void setPickUpLongitude(Double pickUpLongitude) {
		this.pickUpLongitude = pickUpLongitude;
	}
	public Date getActualDoneDate() {
		return actualDoneDate;
	}
	public void setActualDoneDate(Date actualDoneDate) {
		this.actualDoneDate = actualDoneDate;
	}
	public Double getDoneLatitude() {
		return doneLatitude;
	}
	public void setDoneLatitude(Double doneLatitude) {
		this.doneLatitude = doneLatitude;
	}
	public Double getDoneLongitude() {
		return doneLongitude;
	}
	public void setDoneLongitude(Double doneLongitude) {
		this.doneLongitude = doneLongitude;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
