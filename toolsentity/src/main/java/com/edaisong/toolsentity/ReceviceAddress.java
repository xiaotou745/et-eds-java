package com.edaisong.toolsentity;

import java.util.Date;

public class ReceviceAddress {
    private Long id;

    private String phoneno;

    private String address;

    private Date pubdate;

    private Date addtime;

    private Integer businessid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno == null ? null : phoneno.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Date getPubdate() {
        return pubdate;
    }

    public void setPubdate(Date pubdate) {
        this.pubdate = pubdate;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Integer getBusinessid() {
        return businessid;
    }

    public void setBusinessid(Integer businessid) {
        this.businessid = businessid;
    }
}