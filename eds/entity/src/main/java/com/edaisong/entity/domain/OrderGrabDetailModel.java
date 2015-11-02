package com.edaisong.entity.domain;

import java.util.Date;

public class OrderGrabDetailModel {
	private Integer id;

    private Integer orderid;

    private String graborderno;

    private Integer businessid;
    
    private String businessName;
    
    private String businessPhone;
    
    private String businessAddress;
    
    private Integer clienterid;

    private String ordercount;

    private Byte status;

    private Date actualdonedate;

    private Date grabtime;

    private Date pickuptime;
  
    private Double ordercommission;

    private Double settlemoney;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public String getGraborderno() {
        return graborderno;
    }

    public void setGraborderno(String graborderno) {
        this.graborderno = graborderno == null ? null : graborderno.trim();
    }

    public Integer getBusinessid() {
        return businessid;
    }

    public void setBusinessid(Integer businessid) {
        this.businessid = businessid;
    }

    public Integer getClienterid() {
        return clienterid;
    }

    public void setClienterid(Integer clienterid) {
        this.clienterid = clienterid;
    }

    public String getOrdercount() {
        return ordercount;
    }

    public void setOrdercount(String ordercount) {
        this.ordercount = ordercount == null ? null : ordercount.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getActualdonedate() {
        return actualdonedate;
    }

    public void setActualdonedate(Date actualdonedate) {
        this.actualdonedate = actualdonedate;
    }

    public Date getGrabtime() {
        return grabtime;
    }

    public void setGrabtime(Date grabtime) {
        this.grabtime = grabtime;
    }

    public Date getPickuptime() {
        return pickuptime;
    }

    public void setPickuptime(Date pickuptime) {
        this.pickuptime = pickuptime;
    } 

    public Double getOrdercommission() {
        return ordercommission;
    }

    public void setOrdercommission(Double ordercommission) {
        this.ordercommission = ordercommission;
    }

    public Double getSettlemoney() {
        return settlemoney;
    }

    public void setSettlemoney(Double settlemoney) {
        this.settlemoney = settlemoney;
    }

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getBusinessPhone() {
		return businessPhone;
	}

	public void setBusinessPhone(String businessPhone) {
		this.businessPhone = businessPhone;
	}

	public String getBusinessAddress() {
		return businessAddress;
	}

	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
	} 
}
