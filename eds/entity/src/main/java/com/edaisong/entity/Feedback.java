package com.edaisong.entity;

import java.util.Date;

public class Feedback {
    private Long id;

    private Integer feedbackid;

    private Short usertype;

    private Short feedbacktype;

    private String content;

    private Date createtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFeedbackid() {
        return feedbackid;
    }

    public void setFeedbackid(Integer feedbackid) {
        this.feedbackid = feedbackid;
    }

    public Short getUsertype() {
        return usertype;
    }

    public void setUsertype(Short usertype) {
        this.usertype = usertype;
    }

    public Short getFeedbacktype() {
        return feedbacktype;
    }

    public void setFeedbacktype(Short feedbacktype) {
        this.feedbacktype = feedbacktype;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}