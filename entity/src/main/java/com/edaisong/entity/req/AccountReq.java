package com.edaisong.entity.req;

import com.edaisong.entity.common.RequestBase;

public class AccountReq extends RequestBase{

	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getKeyword() {
		return Keyword;
	}
	public void setKeyword(String keyword) {
		Keyword = keyword;
	}
	public int getCurrentPage() {
		return CurrentPage;
	}
	public void setCurrentPage(int currentPage) {
		CurrentPage = currentPage;
	}
	private int Id;
	private String Keyword;
	private int CurrentPage;
	
	
}
