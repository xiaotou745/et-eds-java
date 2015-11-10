package com.edaisong.entity;

import java.util.Date;

public class OrderRegionLog {
    private Integer id;

    private Integer orderregionid;

    private Integer businessid;

    private String remark;

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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}