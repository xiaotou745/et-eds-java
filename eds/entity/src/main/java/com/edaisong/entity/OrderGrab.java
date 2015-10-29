package com.edaisong.entity;

import java.util.Date;

public class OrderGrab {
    private Integer id;

    private Integer orderid;

    private String graborderno;

    private Integer businessid;

    private Integer clienterid;

    private String ordercount;

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

    private Double ordercommission;

    private Double settlemoney;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public String getGraborderno() {
        return graborderno;
    }

    public void setGraborderno(String graborderno) {
        this.graborderno = graborderno == null ? null : graborderno.trim();
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

    public String getOrdercount() {
        return ordercount;
    }

    public void setOrdercount(String ordercount) {
        this.ordercount = ordercount == null ? null : ordercount.trim();
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

    public Double getOrdercommission() {
        return ordercommission;
    }

    public void setOrdercommission(Double ordercommission) {
        this.ordercommission = ordercommission;
    }

    public Double getSettlemoney() {
        return settlemoney;
    }

    public void setSettlemoney(Double settlemoney) {
        this.settlemoney = settlemoney;
    }
}