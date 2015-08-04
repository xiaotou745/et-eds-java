package com.edaisong.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Subsidy {
    private Integer id;

    private BigDecimal ordercommission;

    private BigDecimal distribsubsidy;

    private BigDecimal websitesubsidy;

    private Date startdate;

    private Date enddate;

    private Byte status;

    private Integer groupid;

    private BigDecimal pkmcost;

    private Integer ordertype;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getOrdercommission() {
        return ordercommission;
    }

    public void setOrdercommission(BigDecimal ordercommission) {
        this.ordercommission = ordercommission;
    }

    public BigDecimal getDistribsubsidy() {
        return distribsubsidy;
    }

    public void setDistribsubsidy(BigDecimal distribsubsidy) {
        this.distribsubsidy = distribsubsidy;
    }

    public BigDecimal getWebsitesubsidy() {
        return websitesubsidy;
    }

    public void setWebsitesubsidy(BigDecimal websitesubsidy) {
        this.websitesubsidy = websitesubsidy;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public BigDecimal getPkmcost() {
        return pkmcost;
    }

    public void setPkmcost(BigDecimal pkmcost) {
        this.pkmcost = pkmcost;
    }

    public Integer getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(Integer ordertype) {
        this.ordertype = ordertype;
    }
}