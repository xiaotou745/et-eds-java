package com.edaisong.entity;

public class PushMessageStatus {
    private Integer id;

    private String messageid;

    private Integer androiddeliveredcount;

    private Integer pushmessageid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessageid() {
        return messageid;
    }

    public void setMessageid(String messageid) {
        this.messageid = messageid == null ? null : messageid.trim();
    }

    public Integer getAndroiddeliveredcount() {
        return androiddeliveredcount;
    }

    public void setAndroiddeliveredcount(Integer androiddeliveredcount) {
        this.androiddeliveredcount = androiddeliveredcount;
    }

    public Integer getPushmessageid() {
        return pushmessageid;
    }

    public void setPushmessageid(Integer pushmessageid) {
        this.pushmessageid = pushmessageid;
    }
}