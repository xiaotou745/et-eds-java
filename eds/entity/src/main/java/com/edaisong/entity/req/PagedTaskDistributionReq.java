package com.edaisong.entity.req;

import com.edaisong.entity.common.PagedRequestBase;

public class PagedTaskDistributionReq extends PagedRequestBase {
	private String name;
    private String dataStart ;
    private String dataEnd ;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDataStart() {
		return dataStart;
	}
	public void setDataStart(String dataStart) {
		this.dataStart = dataStart;
	}
	public String getDataEnd() {
		return dataEnd;
	}
	public void setDataEnd(String dataEnd) {
		this.dataEnd = dataEnd;
	}

	

}
