package com.edaisong.entity;

import java.util.Date;

public class OrderGrabChild {
    private Integer id;

    private Integer graborderid;

    private Integer orderid;

    private Integer orderchildid;

    private Integer childid;

    private Byte status;

    private Date actualdonedate;

    private Double donelongitude;

    private Double donelatitude;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGraborderid() {
        return graborderid;
    }

    public void setGraborderid(Integer graborderid) {
        this.graborderid = graborderid;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getOrderchildid() {
        return orderchildid;
    }

    public void setOrderchildid(Integer orderchildid) {
        this.orderchildid = orderchildid;
    }

    public Integer getChildid() {
        return childid;
    }

    public void setChildid(Integer childid) {
        this.childid = childid;
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
}