package com.edaisong.entity.resp;

import java.util.List;

import com.edaisong.entity.domain.OrderRespModel;


public class MyOrderBResp { 
 
	private List<OrderRespModel> orderRespModel;
	private Integer quHuoOrderCountTotal;
	private Integer peiSongOrderCountTotal;
	private Integer yiWanChenOrderCountTotal; 

	public List<OrderRespModel> getOrderRespModel() {
		return orderRespModel;
	}

	public void setOrderRespModel(List<OrderRespModel> orderRespModel) {
		this.orderRespModel = orderRespModel;
	}

	public Integer getQuHuoOrderCountTotal() {
		return quHuoOrderCountTotal;
	}

	public void setQuHuoOrderCountTotal(Integer quHuoOrderCountTotal) {
		this.quHuoOrderCountTotal = quHuoOrderCountTotal;
	}

	public Integer getPeiSongOrderCountTotal() {
		return peiSongOrderCountTotal;
	}

	public void setPeiSongOrderCountTotal(Integer peiSongOrderCountTotal) {
		this.peiSongOrderCountTotal = peiSongOrderCountTotal;
	}

	public Integer getYiWanChenOrderCountTotal() {
		return yiWanChenOrderCountTotal;
	}

	public void setYiWanChenOrderCountTotal(Integer yiWanChenOrderCountTotal) {
		this.yiWanChenOrderCountTotal = yiWanChenOrderCountTotal;
	} 
}
