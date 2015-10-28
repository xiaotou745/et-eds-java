package com.edaisong.entity;

import java.util.Date;

public class Region {
    private Integer id;

    private String code;

    private String name;

    private String chnname;

    private String briefname;

    private String enname;

    private String creator;

    private Date createdate;

    private String modifyuser;

    private Date modifydate;

    private Integer fid;

    private Integer provinceid;

    private Integer cityid;

    private Integer depth;

    private Integer isbroad;

    private String qping;

    private String jping;

    private String briefqping;

    private String briefjping;

    private Boolean isgov;

    private String host;

    private String baidulat;

    private String baidulong;

    private Integer nationalcode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getChnname() {
        return chnname;
    }

    public void setChnname(String chnname) {
        this.chnname = chnname == null ? null : chnname.trim();
    }

    public String getBriefname() {
        return briefname;
    }

    public void setBriefname(String briefname) {
        this.briefname = briefname == null ? null : briefname.trim();
    }

    public String getEnname() {
        return enname;
    }

    public void setEnname(String enname) {
        this.enname = enname == null ? null : enname.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getModifyuser() {
        return modifyuser;
    }

    public void setModifyuser(String modifyuser) {
        this.modifyuser = modifyuser == null ? null : modifyuser.trim();
    }

    public Date getModifydate() {
        return modifydate;
    }

    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Integer getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(Integer provinceid) {
        this.provinceid = provinceid;
    }

    public Integer getCityid() {
        return cityid;
    }

    public void setCityid(Integer cityid) {
        this.cityid = cityid;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    public Integer getIsbroad() {
        return isbroad;
    }

    public void setIsbroad(Integer isbroad) {
        this.isbroad = isbroad;
    }

    public String getQping() {
        return qping;
    }

    public void setQping(String qping) {
        this.qping = qping == null ? null : qping.trim();
    }

    public String getJping() {
        return jping;
    }

    public void setJping(String jping) {
        this.jping = jping == null ? null : jping.trim();
    }

    public String getBriefqping() {
        return briefqping;
    }

    public void setBriefqping(String briefqping) {
        this.briefqping = briefqping == null ? null : briefqping.trim();
    }

    public String getBriefjping() {
        return briefjping;
    }

    public void setBriefjping(String briefjping) {
        this.briefjping = briefjping == null ? null : briefjping.trim();
    }

    public Boolean getIsgov() {
        return isgov;
    }

    public void setIsgov(Boolean isgov) {
        this.isgov = isgov;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host == null ? null : host.trim();
    }

    public String getBaidulat() {
        return baidulat;
    }

    public void setBaidulat(String baidulat) {
        this.baidulat = baidulat == null ? null : baidulat.trim();
    }

    public String getBaidulong() {
        return baidulong;
    }

    public void setBaidulong(String baidulong) {
        this.baidulong = baidulong == null ? null : baidulong.trim();
    }

    public Integer getNationalcode() {
        return nationalcode;
    }

    public void setNationalcode(Integer nationalcode) {
        this.nationalcode = nationalcode;
    }
}