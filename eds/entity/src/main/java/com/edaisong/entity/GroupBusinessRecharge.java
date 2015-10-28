package com.edaisong.entity;

import java.math.BigDecimal;
import java.util.Date;

public class GroupBusinessRecharge {
    private Integer id;

    private Integer groupbusinessid;

    private String paytype;

    private String orderno;

    private Double payamount;

    private Integer paystatus;

    private String payby;

    private Date requesttime;

    private Date paytime;

    private String originalorderno;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupbusinessid() {
        return groupbusinessid;
    }

    public void setGroupbusinessid(Integer groupbusinessid) {
        this.groupbusinessid = groupbusinessid;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }

    public Double getPayamount() {
        return payamount;
    }

    public void setPayamount(Double payamount) {
        this.payamount = payamount;
    }

    public Integer getPaystatus() {
        return paystatus;
    }

    public void setPaystatus(Integer paystatus) {
        this.paystatus = paystatus;
    }

    public String getPayby() {
        return payby;
    }

    public void setPayby(String payby) {
        this.payby = payby == null ? null : payby.trim();
    }

    public Date getRequesttime() {
        return requesttime;
    }

    public void setRequesttime(Date requesttime) {
        this.requesttime = requesttime;
    }

    public Date getPaytime() {
        return paytime;
    }

    public void setPaytime(Date paytime) {
        this.paytime = paytime;
    }

    public String getOriginalorderno() {
        return originalorderno;
    }

    public void setOriginalorderno(String originalorderno) {
        this.originalorderno = originalorderno == null ? null : originalorderno.trim();
    }
}