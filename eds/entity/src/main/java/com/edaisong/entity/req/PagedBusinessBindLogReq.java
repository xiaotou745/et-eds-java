package com.edaisong.entity.req;

import com.edaisong.entity.common.PagedRequestBase;

/**
 *
 * @author  pengyi 
 * @date 2015年9月9日 下午5:40:09
 * @version 1.0
 * @parameter
 * @since
 */
public class PagedBusinessBindLogReq extends PagedRequestBase{
	private int groupBusinessId;
	private String bizName;
	public int getGroupBusinessId() {
		return groupBusinessId;
	}
	public void setGroupBusinessId(int groupBusinessId) {
		this.groupBusinessId = groupBusinessId;
	}
	public String getBizName() {
		return bizName;
	}
	public void setBizName(String bizName) {
		this.bizName = bizName;
	}
}
