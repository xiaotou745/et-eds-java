package com.edaisong.entity;

import java.util.Date;

public class OrderRegionLog {
    private Integer id;

    private Integer orderregionid;

    private Integer businessid;

    private String name;

    private String coordinate;

    private Integer parentid;

    private Byte status;

    private Integer opttype;

    private Date opttime;

    private String optname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderregionid() {
        return orderregionid;
    }

    public void setOrderregionid(Integer orderregionid) {
        this.orderregionid = orderregionid;
    }

    public Integer getBusinessid() {
        return businessid;
    }

    public void setBusinessid(Integer businessid) {
        this.businessid = businessid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate == null ? null : coordinate.trim();
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getOpttype() {
        return opttype;
    }

    public void setOpttype(Integer opttype) {
        this.opttype = opttype;
    }

    public Date getOpttime() {
        return opttime;
    }

    public void setOpttime(Date opttime) {
        this.opttime = opttime;
    }

    public String getOptname() {
        return optname;
    }

    public void setOptname(String optname) {
        this.optname = optname == null ? null : optname.trim();
    }
}