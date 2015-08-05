package com.edaisong.entity;

public class CommissionType {
    private Integer id;

    private String commissionname;

    private Byte isvalid;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommissionname() {
        return commissionname;
    }

    public void setCommissionname(String commissionname) {
        this.commissionname = commissionname == null ? null : commissionname.trim();
    }

    public Byte getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Byte isvalid) {
        this.isvalid = isvalid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}