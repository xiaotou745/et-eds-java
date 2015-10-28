package com.edaisong.toolsentity;

public class BusinessThirdRelation {
    private Integer id;

    private Integer businessid;

    private Integer originalbusiid;

    private Integer groupid;

    private String groupname;

    private Integer auditstatus;

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

    public Integer getOriginalbusiid() {
        return originalbusiid;
    }

    public void setOriginalbusiid(Integer originalbusiid) {
        this.originalbusiid = originalbusiid;
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname == null ? null : groupname.trim();
    }

    public Integer getAuditstatus() {
        return auditstatus;
    }

    public void setAuditstatus(Integer auditstatus) {
        this.auditstatus = auditstatus;
    }
}