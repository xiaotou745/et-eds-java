package com.edaisong.entity;

import java.util.Date;

public class GroupBusinessBindOptionLog {
    private Integer id;

    private Integer groupid;

    private Integer businessid;

    private Integer optid;

    private String optname;

    private Date inserttime;

    private String remark;
    
    private Short opttype;//1绑定   2解绑

    public Integer getGroupid() {
		return groupid;
	}

	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}

	public Short getOpttype() {
		return opttype;
	}

	public void setOpttype(Short opttype) {
		this.opttype = opttype;
	}

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

    public Integer getOptid() {
        return optid;
    }

    public void setOptid(Integer optid) {
        this.optid = optid;
    }

    public String getOptname() {
        return optname;
    }

    public void setOptname(String optname) {
        this.optname = optname == null ? null : optname.trim();
    }

    public Date getInserttime() {
        return inserttime;
    }

    public void setInserttime(Date inserttime) {
        this.inserttime = inserttime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}