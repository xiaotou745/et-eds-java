package com.edaisong.api_http.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.service.inter.IOrderService;
import com.edaisong.api_http.entity.ResultModel;
import com.edaisong.api_http.service.inter.IOrderHttpService;
import com.edaisong.entity.resp.OrderStatisticsResp;


/**
 * 订单模块 
 * @author CaoHeYang
 * @date 20150910
 */
@Service
public class OrderHttpService implements IOrderHttpService {

	@Autowired
	private IOrderService orderService;
	/**
	 * B端任务统计接口
	 * @author CaoHeYang
	 * @date 20150910
	 * @param data 
	 * @return
	 */
	@Override
	public ResultModel<OrderStatisticsResp> orderStatisticsB(String data) {
		OrderStatisticsResp orderStatisticsResp=orderService.getOrderStatisticsB();
		ResultModel<OrderStatisticsResp> resultModel=new ResultModel<OrderStatisticsResp>();
		resultModel.setResult(orderStatisticsResp);
		return resultModel;
	}

}
