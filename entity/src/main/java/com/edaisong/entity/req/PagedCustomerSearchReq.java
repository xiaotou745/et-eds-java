package com.edaisong.entity.req;

import com.edaisong.entity.common.PagedRequestBase;

public class PagedCustomerSearchReq extends PagedRequestBase {
private int businessID;
private String search;
public int getBusinessID() {
	return businessID;
}
public void setBusinessID(int businessID) {
	this.businessID = businessID;
}
public String getSearch() {
	return search;
}
public void setSearch(String search) {
	this.search = search;
}
}
