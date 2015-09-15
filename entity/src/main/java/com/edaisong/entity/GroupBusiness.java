package com.edaisong.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class GroupBusiness  implements Serializable{
    private Integer id;

    private String groupbusiname;

    private String loginname;

    private String password;

    private String createname;

    private Date createtime;

    private String modifyname;

    private Date modifytime;

    private Double amount;

    private Integer isvalid;
    private Integer isallowoverdraft;

    private Integer isallowoverdraft;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupbusiname() {
        return groupbusiname;
    }

    public void setGroupbusiname(String groupbusiname) {
        this.groupbusiname = groupbusiname == null ? null : groupbusiname.trim();
    }
 
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
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

    public String getModifyname() {
        return modifyname;
    }

    public void setModifyname(String modifyname) {
        this.modifyname = modifyname == null ? null : modifyname.trim();
    }

    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public Integer getIsAllowOverdraft() {
        return isallowoverdraft;
    }

    public void setIsAllowOverdraft(Integer isallowoverdraft) {
        this.isallowoverdraft = isallowoverdraft;
    }
}