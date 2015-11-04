package com.edaisong.entity;

import java.util.Date;

public class OrderGrabChild {
    private Integer id;

    private Integer graborderid;

    private Integer orderid;

    private Integer orderchildid;

    private Integer childid;
    
    private Integer businessid;   
	private Byte status;

    private Date actualdonedate;

    private Double donelongitude;

    private Double donelatitude;
	private Double orderCommission;
    private Double settleMoney;
	private Double commissionRate;
    private Double baseCommission;
    private Double websiteSubsidy;
    private Double adjustment;
    private Double distribsubsidy;
    public Integer getBusinessid() {
		return businessid;
	}

	public void setBusinessid(Integer businessid) {
		this.businessid = businessid;
	}


    public Double getOrderCommission() {
		return orderCommission;
	}

	public void setOrderCommission(Double orderCommission) {
		this.orderCommission = orderCommission;
	}

	public Double getSettleMoney() {
		return settleMoney;
	}

	public void setSettleMoney(Double settleMoney) {
		this.settleMoney = settleMoney;
	}

	public Double getCommissionRate() {
		return commissionRate;
	}

	public void setCommissionRate(Double commissionRate) {
		this.commissionRate = commissionRate;
	}

	public Double getBaseCommission() {
		return baseCommission;
	}

	public void setBaseCommission(Double baseCommission) {
		this.baseCommission = baseCommission;
	}

	public Double getWebsiteSubsidy() {
		return websiteSubsidy;
	}

	public void setWebsiteSubsidy(Double websiteSubsidy) {
		this.websiteSubsidy = websiteSubsidy;
	}

	public Double getAdjustment() {
		return adjustment;
	}

	public void setAdjustment(Double adjustment) {
		this.adjustment = adjustment;
	}




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGraborderid() {
        return graborderid;
    }

    public void setGraborderid(Integer graborderid) {
        this.graborderid = graborderid;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getOrderchildid() {
        return orderchildid;
    }

    public void setOrderchildid(Integer orderchildid) {
        this.orderchildid = orderchildid;
    }

    public Integer getChildid() {
        return childid;
    }

    public void setChildid(Integer childid) {
        this.childid = childid;
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

    public Double getDonelongitude() {
        return donelongitude;
    }

    public void setDonelongitude(Double donelongitude) {
        this.donelongitude = donelongitude;
    }

    public Double getDonelatitude() {
        return donelatitude;
    }

    public void setDonelatitude(Double donelatitude) {
        this.donelatitude = donelatitude;
    }

	public Double getDistribsubsidy() {
		return distribsubsidy;
	}

	public void setDistribsubsidy(Double distribsubsidy) {
		this.distribsubsidy = distribsubsidy;
	}
}