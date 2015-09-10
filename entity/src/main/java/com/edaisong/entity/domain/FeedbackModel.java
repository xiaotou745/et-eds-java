package com.edaisong.entity.domain;

import java.util.Date;

public class FeedbackModel {
    private Long id;

    private Integer feedbackid;

    private Short usertype;    

    private Short feedbacktype;

    private String content;

    private Date createtime;
    
    private String usertypeDisplay;
    
    private String feedbacktypeDisplay;
    
    private String name;
    
    private String phoneNo;
    
    private String city;

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
		this.content = content;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getUsertypeDisplay() {
		return usertypeDisplay;
	}

	public void setUsertypeDisplay(String usertypeDisplay) {
		this.usertypeDisplay = usertypeDisplay;
	}

	public String getFeedbacktypeDisplay() {
		return feedbacktypeDisplay;
	}

	public void setFeedbacktypeDisplay(String feedbacktypeDisplay) {
		this.feedbacktypeDisplay = feedbacktypeDisplay;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

  
}