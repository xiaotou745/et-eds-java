package com.edaisong.entity;

public class AuthorityDepartment {
    private Integer id;

    private Integer parid;

    private String departname;

    private Boolean belock;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParid() {
        return parid;
    }

    public void setParid(Integer parid) {
        this.parid = parid;
    }

    public String getDepartname() {
        return departname;
    }

    public void setDepartname(String departname) {
        this.departname = departname == null ? null : departname.trim();
    }

    public Boolean getBelock() {
        return belock;
    }

    public void setBelock(Boolean belock) {
        this.belock = belock;
    }
}