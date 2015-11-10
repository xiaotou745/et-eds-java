package com.edaisong.entity.resp;

import java.util.List;
 


import com.edaisong.entity.domain.MyOrderGrabCModel;
import com.edaisong.entity.domain.MyOrderHadFinishCModel;

public class HadFinishOrderResp {  
	
	private Integer yiWanChengTotal;
	
	private List<MyOrderGrabCModel> myOrderGrabCModelList; 

	public List<MyOrderGrabCModel> getMyOrderGrabCModelList() {
		return myOrderGrabCModelList;
	}

	public void setMyOrderGrabCModelList(List<MyOrderGrabCModel> myOrderGrabCModelList) {
		this.myOrderGrabCModelList = myOrderGrabCModelList;
	}

	public Integer getYiWanChengTotal() {
		return yiWanChengTotal;
	}

	public void setYiWanChengTotal(Integer yiWanChengTotal) {
		this.yiWanChengTotal = yiWanChengTotal;
	} 
}
