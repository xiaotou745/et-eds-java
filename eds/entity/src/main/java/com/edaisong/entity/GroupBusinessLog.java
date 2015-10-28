package com.edaisong.entity;

import java.util.Date;

public class GroupBusinessLog {
    private Integer id;
    
    private Integer groupbusinessid; 
    
    private String optname;

    private Date opttime;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setGroupBusinessId(Integer groupbusinessid) {
        this.groupbusinessid = groupbusinessid;
    }
    
    public Integer getGroupBusinessId() {
        return groupbusinessid;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getOptname() {
        return optname;
    }

    public void setOptname(String optname) {
        this.optname = optname == null ? "" : optname.trim();
    }

    public Date getOpttime() {
        return opttime;
    }

    public void setOpttime(Date opttime) {
        this.opttime = opttime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? "" : remark.trim();
    }
}