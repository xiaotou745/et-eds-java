package com.edaisong.entity.req;

import com.edaisong.entity.common.PagedRequestBase;


public class PagedAccountReq extends PagedRequestBase{

	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
	   this.keyword = keyword;
	}
	private int Id;
	private String keyword;
}
