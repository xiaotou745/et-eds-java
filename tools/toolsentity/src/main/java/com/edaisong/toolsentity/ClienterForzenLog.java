package com.edaisong.toolsentity;

import java.util.Date;

public class ClienterForzenLog {
    private Integer id;

    private Integer clienterforzenid;

    private String operator;

    private Integer operatype;

    private Date createdate;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClienterforzenid() {
        return clienterforzenid;
    }

    public void setClienterforzenid(Integer clienterforzenid) {
        this.clienterforzenid = clienterforzenid;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Integer getOperatype() {
        return operatype;
    }

    public void setOperatype(Integer operatype) {
        this.operatype = operatype;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}