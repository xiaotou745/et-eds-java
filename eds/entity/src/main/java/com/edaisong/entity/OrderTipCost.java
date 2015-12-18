package com.edaisong.entity;

import java.math.BigDecimal;
import java.util.Date;

public class OrderTipCost {
    private Integer id;

    private Integer orderid;

    private double amount;
    
    private double tipamount;

    public double getTipamount() {
		return tipamount;
	}

	public void setTipamount(double tipamount) {
		this.tipamount = tipamount;
	}

	private String createname;

    private Date createtime;

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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCreatename() {
        return createname;
    }

    public void setCreatename(String createname) {
        this.createname = createname == null ? null : createname.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}