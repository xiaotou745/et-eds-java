package com.edaisong.entity;

import java.math.BigDecimal;
import java.util.Date;

public class BusinessRecharge {
    private Integer id;

    private Integer businessid;

    private Integer paytype;

    private String orderno;

    private BigDecimal payamount;

    private Integer paystatus;

    private String payby;

    private Date paytime;

    private String originalorderno;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBusinessid() {
        return businessid;
    }

    public void setBusinessid(Integer businessid) {
        this.businessid = businessid;
    }

    public Integer getPaytype() {
        return paytype;
    }

    public void setPaytype(Integer paytype) {
        this.paytype = paytype;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }

    public BigDecimal getPayamount() {
        return payamount;
    }

    public void setPayamount(BigDecimal payamount) {
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