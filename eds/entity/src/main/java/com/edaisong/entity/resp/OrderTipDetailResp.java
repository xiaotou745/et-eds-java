package com.edaisong.entity.resp;

import java.util.Date;
import java.util.List;

import com.edaisong.entity.common.ResponseBase;
import com.edaisong.entity.domain.OrderRespModel;
import com.edaisong.entity.OrderChild;
import com.edaisong.entity.OrderTip;

/**
 * 获取订单详情
 * @author 胡灵波
 * @Date 2015年11月27日 11:44:38
 */
public class OrderTipDetailResp extends ResponseBase{
	
	private List<OrderTip> list;
	 
	  public List<OrderTip> getList() {
		return list;
	}

	public void setList(List<OrderTip> list) {
		this.list = list;
	}	
}
