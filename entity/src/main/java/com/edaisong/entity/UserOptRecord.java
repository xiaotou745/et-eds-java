package com.edaisong.entity;

import java.util.Date;

public class UserOptRecord {
    private Integer id;

    private Integer usertype;

    private Integer userid;

    private Integer opttype;

    private Integer optuserid;

    private String optusername;

    private Integer optusertype;

    private Date optdatetime;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUsertype() {
        return usertype;
    }

    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getOpttype() {
        return opttype;
    }

    public void setOpttype(Integer opttype) {
        this.opttype = opttype;
    }

    public Integer getOptuserid() {
        return optuserid;
    }

    public void setOptuserid(Integer optuserid) {
        this.optuserid = optuserid;
    }

    public String getOptusername() {
        return optusername;
    }

    public void setOptusername(String optusername) {
        this.optusername = optusername == null ? null : optusername.trim();
    }

    public Integer getOptusertype() {
        return optusertype;
    }

    public void setOptusertype(Integer optusertype) {
        this.optusertype = optusertype;
    }

    public Date getOptdatetime() {
        return optdatetime;
    }

    public void setOptdatetime(Date optdatetime) {
        this.optdatetime = optdatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}