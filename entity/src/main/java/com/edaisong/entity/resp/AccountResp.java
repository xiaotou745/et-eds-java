package com.edaisong.entity.resp;

import java.util.List;

import com.edaisong.entity.Account;
import com.edaisong.entity.common.ResponseBase;

public class AccountResp  extends ResponseBase{
	public List<Account> getResultList() {
		return resultList;
	}
	public void setResultList(List<Account> resultList) {
		this.resultList = resultList;
		}
	public int getTotalRecord() {
		return TotalRecord;	
	}
	public void setTotalRecord(int totalRecord) {
		TotalRecord = totalRecord;
	}
	public int getTotalPage() {
		return TotalPage;
	}
	public void setTotalPage(int totalPage) {
		TotalPage = totalPage;
	}
	public int getCurrentPage() {
		return CurrentPage;
	}
	public void setCurrentPage(int currentPage) {
		CurrentPage = currentPage;
	}
	public int getPageSize() {
		return PageSize;
	}
	public void setPageSize(int pageSize) {
		PageSize = pageSize;
	}
	private int TotalRecord;
	private int TotalPage;
	private int CurrentPage;
	private int PageSize;

	private List<Account>  resultList;
}
