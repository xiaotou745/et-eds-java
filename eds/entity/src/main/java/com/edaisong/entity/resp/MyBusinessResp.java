package com.edaisong.entity.resp;

import java.util.List;

import com.edaisong.entity.domain.MyBusinessModel;

public class MyBusinessResp{
	
	private Integer fuWuZhongTotal;
	
	private Integer shenQingZhongTotal;
	 
	private List<MyBusinessModel> myBusinessModelList;
	
	public Integer getFuWuZhongTotal() {
		return fuWuZhongTotal;
	}

	public void setFuWuZhongTotal(Integer fuWuZhongTotal) {
		this.fuWuZhongTotal = fuWuZhongTotal;
	}

	public Integer getShenQingZhongTotal() {
		return shenQingZhongTotal;
	}

	public void setShenQingZhongTotal(Integer shenQingZhongTotal) {
		this.shenQingZhongTotal = shenQingZhongTotal;
	}

	public List<MyBusinessModel> getMyBusinessModelList() {
		return myBusinessModelList;
	}

	public void setMyBusinessModelList(List<MyBusinessModel> myBusinessModelList) {
		this.myBusinessModelList = myBusinessModelList;
	}

	
}
