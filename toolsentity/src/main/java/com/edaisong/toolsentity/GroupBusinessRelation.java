package com.edaisong.toolsentity;

import java.util.Date;

public class GroupBusinessRelation {
    private Integer id;

    private Integer groupid;

    private Integer businessid;

    private String createby;

    private Date createtime;

    private String updateby;

    private Date updatetime;

    private Short isbind;

    private Short isenable;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupid() {
		return groupid;
	}

	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}

	public Integer getBusinessid() {
        return businessid;
    }

    public void setBusinessid(Integer businessid) {
        this.businessid = businessid;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby == null ? null : createby.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getUpdateby() {
        return updateby;
    }

    public void setUpdateby(String updateby) {
        this.updateby = updateby == null ? null : updateby.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Short getIsbind() {
        return isbind;
    }

    public void setIsbind(Short isbind) {
        this.isbind = isbind;
    }

    public Short getIsenable() {
        return isenable;
    }

    public void setIsenable(Short isenable) {
        this.isenable = isenable;
    }
}