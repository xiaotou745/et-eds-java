
package com.edaisong.entity.resp;

import java.util.List;

import com.edaisong.entity.common.ResponseBase;
import com.edaisong.entity.domain.ClienterModel;


public class ClienterResp extends ResponseBase{
	private	List<ClienterModel> clienterList;	
	//private int TotalRecord;
//	private int TotalPage;
//	private int CurrentPage;
//	private int PageSize;
	
	public List<ClienterModel> getClienterList() {
		return clienterList;
	}
	
	public void setClienterList(List<ClienterModel> clienterList) {
		this.clienterList = clienterList;
	}

//	public int getTotalRecord() {
//		return TotalRecord;
//	}
//
//	public void setTotalRecord(int totalRecord) {
//		TotalRecord = totalRecord;
//	}
//
//	public int getTotalPage() {
//		return TotalPage;
//	}
//
//	public void setTotalPage(int totalPage) {
//		TotalPage = totalPage;
//	}
//
//	public int getCurrentPage() {
//		return CurrentPage;
//	}
//
//	public void setCurrentPage(int currentPage) {
//		CurrentPage = currentPage;
//	}
//
//	public int getPageSize() {
//		return PageSize;
//	}
//
//	public void setPageSize(int pageSize) {
//		PageSize = pageSize;
//	}
	
}
