package com.edaisong.entity;

import java.util.Date;

public class OrderGrab {
    private Integer id;

    private String graborderno;

    private Integer businessid;     

	private Integer clienterid;

    private Integer orderCount;

    private Byte status;

    private Date actualdonedate;

    private Date grabtime;

    private Date pickuptime;

    private Double pickuplongitude;

    private Double pickuplatitude;

    private Double grablongitude;

    private Double grablatitude;

    private Double donelongitude;

    private Double donelatitude;   

	private Integer orderRegionOneId;
    
    private String orderRegionOneName;
    
    private Integer  orderRegionTwoId;
    
    private String orderRegionTwoName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGraborderno() {
		return graborderno;
	}

	public void setGraborderno(String graborderno) {
		this.graborderno = graborderno;
	}

	public Integer getBusinessid() {
		return businessid;
	}

	public void setBusinessid(Integer businessid) {
		this.businessid = businessid;
	}

	public Integer getClienterid() {
		return clienterid;
	}

	public void setClienterid(Integer clienterid) {
		this.clienterid = clienterid;
	}

	public Integer getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Integer ordercount) {
		this.orderCount = ordercount;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Date getActualdonedate() {
		return actualdonedate;
	}

	public void setActualdonedate(Date actualdonedate) {
		this.actualdonedate = actualdonedate;
	}

	public Date getGrabtime() {
		return grabtime;
	}

	public void setGrabtime(Date grabtime) {
		this.grabtime = grabtime;
	}

	public Date getPickuptime() {
		return pickuptime;
	}

	public void setPickuptime(Date pickuptime) {
		this.pickuptime = pickuptime;
	}

	public Double getPickuplongitude() {
		return pickuplongitude;
	}

	public void setPickuplongitude(Double pickuplongitude) {
		this.pickuplongitude = pickuplongitude;
	}

	public Double getPickuplatitude() {
		return pickuplatitude;
	}

	public void setPickuplatitude(Double pickuplatitude) {
		this.pickuplatitude = pickuplatitude;
	}

	public Double getGrablongitude() {
		return grablongitude;
	}

	public void setGrablongitude(Double grablongitude) {
		this.grablongitude = grablongitude;
	}

	public Double getGrablatitude() {
		return grablatitude;
	}

	public void setGrablatitude(Double grablatitude) {
		this.grablatitude = grablatitude;
	}

	public Double getDonelongitude() {
		return donelongitude;
	}

	public void setDonelongitude(Double donelongitude) {
		this.donelongitude = donelongitude;
	}

	public Double getDonelatitude() {
		return donelatitude;
	}

	public void setDonelatitude(Double donelatitude) {
		this.donelatitude = donelatitude;
	}

	public Integer getOrderRegionOneId() {
		return orderRegionOneId;
	}

	public void setOrderRegionOneId(Integer orderRegionOneId) {
		this.orderRegionOneId = orderRegionOneId;
	}

	public String getOrderRegionOneName() {
		return orderRegionOneName;
	}

	public void setOrderRegionOneName(String orderRegionOneName) {
		this.orderRegionOneName = orderRegionOneName;
	}

	public Integer getOrderRegionTwoId() {
		return orderRegionTwoId;
	}

	public void setOrderRegionTwoId(Integer orderRegionTwoId) {
		this.orderRegionTwoId = orderRegionTwoId;
	}

	public String getOrderRegionTwoName() {
		return orderRegionTwoName;
	}

	public void setOrderRegionTwoName(String orderRegionTwoName) {
		this.orderRegionTwoName = orderRegionTwoName;
	}
    

   
}