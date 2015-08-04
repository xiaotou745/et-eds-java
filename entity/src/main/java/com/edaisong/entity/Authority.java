package com.edaisong.entity;

public class Authority {
    private Integer id;

    private Integer authoritytype;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAuthoritytype() {
        return authoritytype;
    }

    public void setAuthoritytype(Integer authoritytype) {
        this.authoritytype = authoritytype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}