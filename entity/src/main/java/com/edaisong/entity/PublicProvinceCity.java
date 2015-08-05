package com.edaisong.entity;

public class PublicProvinceCity {
    private Integer code;

    private String name;

    private Integer parentid;

    private Byte ispublic;

    private Integer jibie;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public Byte getIspublic() {
        return ispublic;
    }

    public void setIspublic(Byte ispublic) {
        this.ispublic = ispublic;
    }

    public Integer getJibie() {
        return jibie;
    }

    public void setJibie(Integer jibie) {
        this.jibie = jibie;
    }
}