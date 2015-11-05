package com.edaisong.entity.resp;

import java.util.List;

import com.edaisong.entity.domain.MyOrderGrabCModel;

public class MyOrderGrabCResp {
	
	private Integer quHuoZhongTotal;
	
	private Integer peiSongZhongTotal;
	
	private List<MyOrderGrabCModel> myOrderGrabCModelList;
	

	public Integer getPeiSongZhongTotal() {
		return peiSongZhongTotal;
	}

	public void setPeiSongZhongTotal(Integer peiSongZhongTotal) {
		this.peiSongZhongTotal = peiSongZhongTotal;
	}

	public Integer getQuHuoZhongTotal() {
		return quHuoZhongTotal;
	}

	public void setQuHuoZhongTotal(Integer quHuoZhongTotal) {
		this.quHuoZhongTotal = quHuoZhongTotal;
	}

	public List<MyOrderGrabCModel> getMyOrderGrabCModelList() {
		return myOrderGrabCModelList;
	}

	public void setMyOrderGrabCModelList(List<MyOrderGrabCModel> myOrderGrabCModelList) {
		this.myOrderGrabCModelList = myOrderGrabCModelList;
	} 
	
	
}
