package com.edaisong.entity;

import java.util.Date;

public class BusinessFinanceAccount {
    private Integer id;

    private Integer businessid;

    private String truename;

    private String accountno;

    private Boolean isenable;

    private Short accounttype;

    private Short belongtype;

    private String openbank;

    private String opensubbank;

    private String createby;

    private Date createtime;

    private String updateby;

    private Date updatetime;
    private String openProvince;
    private String openCity;
    private int openProvinceCode;
    private int openCityCode;


    public String getOpenProvince() {
		return openProvince;
	}

	public void setOpenProvince(String openProvince) {
		this.openProvince = openProvince;
	}

	public String getOpenCity() {
		return openCity;
	}

	public void setOpenCity(String openCity) {
		this.openCity = openCity;
	}

	public int getOpenProvinceCode() {
		return openProvinceCode;
	}

	public void setOpenProvinceCode(int openProvinceCode) {
		this.openProvinceCode = openProvinceCode;
	}

	public int getOpenCityCode() {
		return openCityCode;
	}

	public void setOpenCityCode(int openCityCode) {
		this.openCityCode = openCityCode;
	}

	public String getIDCard() {
		return iDCard;
	}
    /**
     * 身份证号、营业执照 ，对公营业执照，对私身份照
     */
	public void setIDCard(String iDCard) {
		this.iDCard = iDCard;
	}

	public String getYeepayKey() {
		return yeepayKey;
	}

	public void setYeepayKey(String yeepayKey) {
		this.yeepayKey = yeepayKey;
	}

	private String iDCard;

    private String yeepayKey;

    private int yeepayStatus;
    

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

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename == null ? null : truename.trim();
    }

    public String getAccountno() {
        return accountno;
    }

    public void setAccountno(String accountno) {
        this.accountno = accountno == null ? null : accountno.trim();
    }

    public Boolean getIsenable() {
        return isenable;
    }

    public void setIsenable(Boolean isenable) {
        this.isenable = isenable;
    }

    public Short getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(Short accounttype) {
        this.accounttype = accounttype;
    }

    public Short getBelongtype() {
        return belongtype;
    }

    public void setBelongtype(Short belongtype) {
        this.belongtype = belongtype;
    }

    public String getOpenbank() {
        return openbank;
    }

    public void setOpenbank(String openbank) {
        this.openbank = openbank == null ? null : openbank.trim();
    }

    public String getOpensubbank() {
        return opensubbank;
    }

    public void setOpensubbank(String opensubbank) {
        this.opensubbank = opensubbank == null ? null : opensubbank.trim();
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
    /**
    * 易宝账户状态 0正常 1失败
    */
	public int getYeepayStatus() {
		return yeepayStatus;
	}
    /**
    * 易宝账户状态 0正常 1失败
    */
	public void setYeepayStatus(int yeepayStatus) {
		this.yeepayStatus = yeepayStatus;
	}
}