package com.edaisong.entity;

import java.lang.Double;
import java.util.Date;

public class Subsidy {
    private Integer id;

    private Double ordercommission;

    private Double distribsubsidy;

    private Double websitesubsidy;

    private Date startdate;

    private Date enddate;

    private Byte status;

    private Integer groupid;

    private Double pkmcost;

    private Integer ordertype;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getOrdercommission() {
        return ordercommission;
    }

    public void setOrdercommission(Double ordercommission) {
        this.ordercommission = ordercommission;
    }

    public Double getDistribsubsidy() {
        return distribsubsidy;
    }

    public void setDistribsubsidy(Double distribsubsidy) {
        this.distribsubsidy = distribsubsidy;
    }

    public Double getWebsitesubsidy() {
        return websitesubsidy;
    }

    public void setWebsitesubsidy(Double websitesubsidy) {
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

    public Double getPkmcost() {
        return pkmcost;
    }

    public void setPkmcost(Double pkmcost) {
        this.pkmcost = pkmcost;
    }

    public Integer getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(Integer ordertype) {
        this.ordertype = ordertype;
    }
}