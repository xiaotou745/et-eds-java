package com.edaisong.entity;

import java.text.DecimalFormat;
import java.util.Date;

public class ClienterForzen {
    private Integer id;

    private Integer clienterid;

    private Double forzenamount;

    private String forzenreason;

    private Date forzendate;

    private String thawreason;

    private Date thawdate;

    private Integer status;

    private String operator;

    private Date createdate;
    
    private String clienterName;
    
    private String clienterPhone;
    
    

    public String getClienterName() {
		return clienterName;
	}

	public void setClienterName(String clienterName) {
		this.clienterName = clienterName;
	}

	public String getClienterPhone() {
		return clienterPhone;
	}

	public void setClienterPhone(String clienterPhone) {
		this.clienterPhone = clienterPhone;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClienterid() {
        return clienterid;
    }

    public void setClienterid(Integer clienterid) {
        this.clienterid = clienterid;
    }

    public Double getForzenamount() {
        return forzenamount;
    }
    
    public String getForzenamountString(){
    	return new DecimalFormat("0.00").format(forzenamount);
    }

    public void setForzenamount(Double forzenamount) {
        this.forzenamount = forzenamount;
    }

    public String getForzenreason() {
        return forzenreason;
    }

    public void setForzenreason(String forzenreason) {
        this.forzenreason = forzenreason == null ? null : forzenreason.trim();
    }

    public Date getForzendate() {
        return forzendate;
    }

    public void setForzendate(Date forzendate) {
        this.forzendate = forzendate;
    }

    public String getThawreason() {
        return thawreason;
    }

    public void setThawreason(String thawreason) {
        this.thawreason = thawreason == null ? null : thawreason.trim();
    }

    public Date getThawdate() {
        return thawdate;
    }

    public void setThawdate(Date thawdate) {
        this.thawdate = thawdate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
}