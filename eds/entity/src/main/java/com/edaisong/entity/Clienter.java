package com.edaisong.entity;

import java.lang.Double;
import java.util.Date;

public class Clienter {
    private Integer id;

    private String phoneno;

    private String loginname;

    private String recommendphone;

    private String password;

    private String truename;

    private String idcard;

    private String picwithhandurl;

    private String picurl;

    private Byte status;

    private Double accountbalance;

    private Date inserttime;

    private String invitecode;

    private String city;

    private String cityid;

    private Integer groupid;

    private String healthcardid;

    private String internaldepart;

    private String provincecode;

    private String areacode;

    private String citycode;

    private String province;

    private Integer bussinessid;

    private Integer workstatus;

    private Double allowwithdrawprice;

    private Double haswithdrawprice;

    private Short isbind;

    private Integer deliverycompanyid;
    private Long timespan;
    public Long getTimespan() {
		return timespan;
	}

	public void setTimespan(Long timespan) {
		this.timespan = timespan;
	}

	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public boolean isReceivePush() {
		return isReceivePush;
	}

	public void setReceivePush(boolean isReceivePush) {
		this.isReceivePush = isReceivePush;
	}

	public String getHeadPhoto() {
		return headPhoto;
	}

	public void setHeadPhoto(String headPhoto) {
		this.headPhoto = headPhoto;
	}

	public Integer getGradeType() {
		return GradeType;
	}

	public void setGradeType(Integer gradeType) {
		GradeType = gradeType;
	}

	public Integer getIsCooperation() {
		return isCooperation;
	}

	public void setIsCooperation(Integer isCooperation) {
		this.isCooperation = isCooperation;
	}

	private String appkey;
    private boolean isReceivePush;
    private String headPhoto;
    private Integer GradeType;
    private Integer isCooperation; 

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno == null ? null : phoneno.trim();
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname == null ? null : loginname.trim();
    }

    public String getRecommendphone() {
        return recommendphone;
    }

    public void setRecommendphone(String recommendphone) {
        this.recommendphone = recommendphone == null ? null : recommendphone.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename == null ? null : truename.trim();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getPicwithhandurl() {
        return picwithhandurl;
    }

    public void setPicwithhandurl(String picwithhandurl) {
        this.picwithhandurl = picwithhandurl == null ? null : picwithhandurl.trim();
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl == null ? null : picurl.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Double getAccountbalance() {
        return accountbalance;
    }

    public void setAccountbalance(Double accountbalance) {
        this.accountbalance = accountbalance;
    }

    public Date getInserttime() {
        return inserttime;
    }

    public void setInserttime(Date inserttime) {
        this.inserttime = inserttime;
    }

    public String getInvitecode() {
        return invitecode;
    }

    public void setInvitecode(String invitecode) {
        this.invitecode = invitecode == null ? null : invitecode.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid == null ? null : cityid.trim();
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public String getHealthcardid() {
        return healthcardid;
    }

    public void setHealthcardid(String healthcardid) {
        this.healthcardid = healthcardid == null ? null : healthcardid.trim();
    }

    public String getInternaldepart() {
        return internaldepart;
    }

    public void setInternaldepart(String internaldepart) {
        this.internaldepart = internaldepart == null ? null : internaldepart.trim();
    }

    public String getProvincecode() {
        return provincecode;
    }

    public void setProvincecode(String provincecode) {
        this.provincecode = provincecode == null ? null : provincecode.trim();
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode == null ? null : areacode.trim();
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode == null ? null : citycode.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public Integer getBussinessid() {
        return bussinessid;
    }

    public void setBussinessid(Integer bussinessid) {
        this.bussinessid = bussinessid;
    }

    public Integer getWorkstatus() {
        return workstatus;
    }

    public void setWorkstatus(Integer workstatus) {
        this.workstatus = workstatus;
    }

    public Double getAllowwithdrawprice() {
        return allowwithdrawprice;
    }

    public void setAllowwithdrawprice(Double allowwithdrawprice) {
        this.allowwithdrawprice = allowwithdrawprice;
    }

    public Double getHaswithdrawprice() {
        return haswithdrawprice;
    }

    public void setHaswithdrawprice(Double haswithdrawprice) {
        this.haswithdrawprice = haswithdrawprice;
    }

    public Short getIsbind() {
        return isbind;
    }

    public void setIsbind(Short isbind) {
        this.isbind = isbind;
    }

    public Integer getDeliverycompanyid() {
        return deliverycompanyid;
    }

    public void setDeliverycompanyid(Integer deliverycompanyid) {
        this.deliverycompanyid = deliverycompanyid;
    }
}