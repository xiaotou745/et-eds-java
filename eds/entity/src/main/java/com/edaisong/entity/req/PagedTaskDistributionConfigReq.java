package com.edaisong.entity.req;

import com.edaisong.entity.common.PagedRequestBase;

public class PagedTaskDistributionConfigReq extends PagedRequestBase {

	public Integer taskDistributionId;
	private String KM;
	private String KG;
	
	public Integer getTaskDistributionId() {
		return taskDistributionId;
	}
	public void setTaskDistributionId(Integer taskDistributionId) {
		this.taskDistributionId = taskDistributionId;
	}
	
	public String getKM() {
		return KM;
	}
	public void setKM(String kM) {
		KM = kM;
	}
	public String getKG() {
		return KG;
	}
	public void setKG(String kG) {
		KG = kG;
	}

	

}
