package com.edaisong.entity.resp;

import java.util.List;
 
import com.edaisong.entity.domain.MyOrderHadFinishCModel;

public class HadFinishCResp { 
	
	private Integer hadFinishTotal;
	
	private String dateInfo;
	
	private List<MyOrderHadFinishCModel> myOrderHadFinishCModelList;
 

	public Integer getHadFinishTotal() {
		return hadFinishTotal;
	}

	public void setHadFinishTotal(Integer hadFinishTotal) {
		this.hadFinishTotal = hadFinishTotal;
	}

	public String getDateInfo() {
		return dateInfo;
	}

	public void setDateInfo(String dateInfo) {
		this.dateInfo = dateInfo;
	}

	public List<MyOrderHadFinishCModel> getMyOrderHadFinishCModelList() {
		return myOrderHadFinishCModelList;
	}

	public void setMyOrderHadFinishCModelList(
			List<MyOrderHadFinishCModel> myOrderHadFinishCModelList) {
		this.myOrderHadFinishCModelList = myOrderHadFinishCModelList;
	} 
}
