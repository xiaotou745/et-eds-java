package com.edaisong.entity.domain;

import java.math.BigDecimal;
import java.util.Date;

public class GroupBusinessModel {
	private Integer id;

    private String groupbusiname;

    private String loginname;

    private String password;

    private String createname;

    private Date createtime;

    private String modifyname;

    private Date modifytime;

    private BigDecimal amount;

    private Integer isvalid;

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

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname == null ? "" : loginname.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? "" : password.trim();
    }

    public String getCreatename() {
        return createname;
    }

    public void setCreatename(String createname) {
        this.createname = createname == null ? "" : createname.trim();
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
        this.modifyname = modifyname == null ? "" : modifyname.trim();
    }

    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }
}
