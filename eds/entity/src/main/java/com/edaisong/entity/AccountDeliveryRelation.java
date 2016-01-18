package com.edaisong.entity;

import java.util.Date;

public class AccountDeliveryRelation {
    private Integer id;

    private Integer accountid;

    private Integer deliverycompanyid;

    private Date createtime;

    private String createby;

    private Integer isenable;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccountid() {
        return accountid;
    }

    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    public Integer getDeliverycompanyid() {
        return deliverycompanyid;
    }

    public void setDeliverycompanyid(Integer deliverycompanyid) {
        this.deliverycompanyid = deliverycompanyid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby == null ? null : createby.trim();
    }

    public Integer getIsenable() {
        return isenable;
    }

    public void setIsenable(Integer isenable) {
        this.isenable = isenable;
    }
}