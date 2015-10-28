package com.edaisong.toolsentity;

import java.util.Date;

public class RoleInfo {
    private Integer id;

    private String roleName;

    private Date createTime;

    private String remark;

    private String optName;

    private Boolean beLock;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOptName() {
		return optName;
	}

	public void setOptName(String optName) {
		this.optName = optName;
	}

	public Boolean getBeLock() {
		return beLock;
	}

	public void setBeLock(Boolean beLock) {
		this.beLock = beLock;
	}

    
}