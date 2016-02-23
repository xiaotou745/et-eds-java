package com.edaisong.entity;

import java.util.Date;

public class Group {
    private Long id;

    private String groupname;

    private String createname;

    private Date createtime;

    private String modifyname;

    private Date modifytime;

    private Byte isvalid;

    private Integer ismodifybind;

    /**
	 * 登录帐号
	 */
	private String phoneNo;

	/**
	 * 登录密码
	 */
	private String password;

	/**
	 * 业务描述
	 */
	private String description;

	/**
	 * 日均单量
	 */
	private Integer averageCount;

	/**
	 * 日均单价
	 */
	private Double averagePrice;

	/**
	 * 审核状态 0待审核 1审核通过 2 审核拒绝
	 */
	private Integer auditStatu;

	/**
	 * 拒绝原因 审核拒绝时必填有效
	 */
	private String refuseReason;

	
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname == null ? null : groupname.trim();
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

    public Byte getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Byte isvalid) {
        this.isvalid = isvalid;
    }

    public Integer getIsmodifybind() {
        return ismodifybind;
    }

    public void setIsmodifybind(Integer ismodifybind) {
        this.ismodifybind = ismodifybind;
    }
    /**
	 * 获取登录帐号
	 */
	public String getPhoneNo() {
		return phoneNo;
	}
	/**
	 * 设置登录帐号
	 * @param phoneNo 登录帐号
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	/**
	 * 获取登录密码
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置登录密码
	 * @param password 登录密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 获取业务描述
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置业务描述
	 * @param description 业务描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 获取日均单量
	 */
	public Integer getAverageCount() {
		return averageCount;
	}
	/**
	 * 设置日均单量
	 * @param averageCount 日均单量
	 */
	public void setAverageCount(Integer averageCount) {
		this.averageCount = averageCount;
	}

	/**
	 * 获取日均单价
	 */
	public Double getAveragePrice() {
		return averagePrice;
	}
	/**
	 * 设置日均单价
	 * @param averagePrice 日均单价
	 */
	public void setAveragePrice(Double averagePrice) {
		this.averagePrice = averagePrice;
	}

	/**
	 * 获取审核状态 0待审核 1审核通过 2 审核拒绝
	 */
	public Integer getAuditStatu() {
		return auditStatu;
	}
	/**
	 * 设置审核状态 0待审核 1审核通过 2 审核拒绝
	 * @param auditStatu 审核状态 0待审核 1审核通过 2 审核拒绝
	 */
	public void setAuditStatu(Integer auditStatu) {
		this.auditStatu = auditStatu;
	}

	/**
	 * 获取拒绝原因 审核拒绝时必填有效
	 */
	public String getRefuseReason() {
		return refuseReason;
	}
	/**
	 * 设置拒绝原因 审核拒绝时必填有效
	 * @param refuseReason 拒绝原因 审核拒绝时必填有效
	 */
	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}


}