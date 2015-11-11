package com.edaisong.entity.domain;

import com.edaisong.entity.Business;

public class BusinessModifyModel extends Business {
	private String optId;
	private String optName;
	private String remark;
	private String oldbusinessgroupidname;
	private String businessgroupidname;
	public String getOldbusinessgroupidname() {
		return oldbusinessgroupidname;
	}

	public void setOldbusinessgroupidname(String oldbusinessgroupidname) {
		this.oldbusinessgroupidname = oldbusinessgroupidname;
	}

	public String getBusinessgroupidname() {
		return businessgroupidname;
	}

	public void setBusinessgroupidname(String businessgroupidname) {
		this.businessgroupidname = businessgroupidname;
	}

	public String getOptId() {
		return optId;
	}

	public void setOptId(String optId) {
		this.optId = optId;
	}

	public String getOptName() {
		return optName;
	}

	public void setOptName(String optName) {
		this.optName = optName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
