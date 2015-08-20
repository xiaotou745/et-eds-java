package com.edaisong.entity;

import java.util.Date;

public class OrderLog {
    private Long id;

    private Integer orderid;

    private Byte orderstatus;

    private String optiondescription;

    private String optionperson;

    private Date optiontime;

    private Byte isvalid;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Byte getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(Byte orderstatus) {
        this.orderstatus = orderstatus;
    }

    public String getOptiondescription() {
        return optiondescription;
    }

    public void setOptiondescription(String optiondescription) {
        this.optiondescription = optiondescription == null ? null : optiondescription.trim();
    }

    public String getOptionperson() {
        return optionperson;
    }

    public void setOptionperson(String optionperson) {
        this.optionperson = optionperson == null ? null : optionperson.trim();
    }

    public Date getOptiontime() {
        return optiontime;
    }

    public void setOptiontime(Date optiontime) {
        this.optiontime = optiontime;
    }

    public Byte getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Byte isvalid) {
        this.isvalid = isvalid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}