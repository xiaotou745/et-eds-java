package com.edaisong.toolsentity;

import java.util.Date;

public class AppVersion {
    private long id;

    private String version;

    private int ismust;

    private String updateurl;

    private String message;

    private int platform;

    private int usertype;

    private Date createdate;
    
    private String createby;
    
    private Date updateDate;
    
    private String updateby;
    
    private int istiming;
    
    private Date timingdate;
    
    private int pubstatus;
    
    private int appsource;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public int getIsmust() {
		return ismust;
	}

	public void setIsmust(int ismust) {
		this.ismust = ismust;
	}

	public String getUpdateurl() {
		return updateurl;
	}

	public void setUpdateurl(String updateurl) {
		this.updateurl = updateurl;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getPlatform() {
		return platform;
	}

	public void setPlatform(int platform) {
		this.platform = platform;
	}

	public int getUsertype() {
		return usertype;
	}

	public void setUsertype(int usertype) {
		this.usertype = usertype;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getCreateby() {
		return createby;
	}

	public void setCreateby(String createby) {
		this.createby = createby;
	}

	public int getIstiming() {
		return istiming;
	}

	public void setIstiming(int istiming) {
		this.istiming = istiming;
	}

	public Date getTimingdate() {
		return timingdate;
	}

	public void setTimingdate(Date timingdate) {
		this.timingdate = timingdate;
	}

	public int getPubstatus() {
		return pubstatus;
	}

	public void setPubstatus(int pubstatus) {
		this.pubstatus = pubstatus;
	}

	public int getAppsource() {
		return appsource;
	}

	public void setAppsource(int appsource) {
		this.appsource = appsource;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateby() {
		return updateby;
	}

	public void setUpdateby(String updateby) {
		this.updateby = updateby;
	}
   
}