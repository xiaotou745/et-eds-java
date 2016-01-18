package com.edaisong.entity.req;

import com.edaisong.entity.common.PagedRequestBase;

/**
 * 查询第三方集团  
 * @author CaoHeYang
 * @date 20160118
 */
public class PagedGroupReq extends PagedRequestBase{
	private Long id;
	private String groupName;
	private String appKey;
	private int isValid;


	public int getIsValid() {
		return isValid;
	}
	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
}
