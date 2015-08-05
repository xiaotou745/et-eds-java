package com.edaisong.entity;

import java.util.Date;

public class Message {
    private Long id;

    private Short pushway;

    private Short messagetype;

    private String content;

    private Short sentstatus;

    private Short pushtype;

    private Short pushtarget;

    private String pushcity;

    private String pushphone;

    private Short sendtype;

    private Date sendtime;

    private Date overtime;

    private String createby;

    private Date createtime;

    private String updateby;

    private Date updatetime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getPushway() {
        return pushway;
    }

    public void setPushway(Short pushway) {
        this.pushway = pushway;
    }

    public Short getMessagetype() {
        return messagetype;
    }

    public void setMessagetype(Short messagetype) {
        this.messagetype = messagetype;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Short getSentstatus() {
        return sentstatus;
    }

    public void setSentstatus(Short sentstatus) {
        this.sentstatus = sentstatus;
    }

    public Short getPushtype() {
        return pushtype;
    }

    public void setPushtype(Short pushtype) {
        this.pushtype = pushtype;
    }

    public Short getPushtarget() {
        return pushtarget;
    }

    public void setPushtarget(Short pushtarget) {
        this.pushtarget = pushtarget;
    }

    public String getPushcity() {
        return pushcity;
    }

    public void setPushcity(String pushcity) {
        this.pushcity = pushcity == null ? null : pushcity.trim();
    }

    public String getPushphone() {
        return pushphone;
    }

    public void setPushphone(String pushphone) {
        this.pushphone = pushphone == null ? null : pushphone.trim();
    }

    public Short getSendtype() {
        return sendtype;
    }

    public void setSendtype(Short sendtype) {
        this.sendtype = sendtype;
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    public Date getOvertime() {
        return overtime;
    }

    public void setOvertime(Date overtime) {
        this.overtime = overtime;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby == null ? null : createby.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getUpdateby() {
        return updateby;
    }

    public void setUpdateby(String updateby) {
        this.updateby = updateby == null ? null : updateby.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}