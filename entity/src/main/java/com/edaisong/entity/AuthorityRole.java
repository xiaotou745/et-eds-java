package com.edaisong.entity;

public class AuthorityRole {
    private Integer id;

    private String rolename;

    private Boolean belock;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

    public Boolean getBelock() {
        return belock;
    }

    public void setBelock(Boolean belock) {
        this.belock = belock;
    }
}