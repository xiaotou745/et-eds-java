package com.edaisong.entity;

import java.util.Date;

public class OrderRegion {
    private Integer id;

    private Integer businessid;

    private String name;

    private String coordinate;

    private Integer parentid;
    private Boolean haschild;

    private Byte status;

    private Integer waitingcount;
    
    private Integer grabcount;  

	private Integer distributioning;

    private Integer donecount;

    private Date createtime;

    private String createname;

    private Date opttime;

    private String optname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBusinessid() {
        return businessid;
    }

    public void setBusinessid(Integer businessid) {
        this.businessid = businessid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate == null ? null : coordinate.trim();
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getWaitingcount() {
        return waitingcount;
    }

    public void setWaitingcount(Integer waitingcount) {
        this.waitingcount = waitingcount;
    }
    public Integer getGrabcount() {
  		return grabcount;
  	}

  	public void setGrabcount(Integer grabcount) {
  		this.grabcount = grabcount;
  	}

    public Integer getDistributioning() {
        return distributioning;
    }

    public void setDistributioning(Integer distributioning) {
        this.distributioning = distributioning;
    }

    public Integer getDonecount() {
        return donecount;
    }

    public void setDonecount(Integer donecount) {
        this.donecount = donecount;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCreatename() {
        return createname;
    }

    public void setCreatename(String createname) {
        this.createname = createname == null ? null : createname.trim();
    }

    public Date getOpttime() {
        return opttime;
    }

    public void setOpttime(Date opttime) {
        this.opttime = opttime;
    }

    public String getOptname() {
        return optname;
    }

    public void setOptname(String optname) {
        this.optname = optname == null ? null : optname.trim();
    }

	public Boolean getHaschild() {
		return haschild;
	}

	public void setHaschild(Boolean haschild) {
		this.haschild = haschild;
	}
}