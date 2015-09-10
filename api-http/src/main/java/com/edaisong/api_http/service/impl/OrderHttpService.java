package com.edaisong.api_http.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.service.inter.IOrderService;
import com.edaisong.api_http.entity.ResultModel;
import com.edaisong.api_http.service.inter.IOrderHttpService;
import com.edaisong.entity.req.OrderStatisticsBReq;
import com.edaisong.entity.resp.OrderStatisticsBResp;


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
	public ResultModel<OrderStatisticsBResp> orderStatisticsB(String data) {
		OrderStatisticsBReq orderStatisticsBReq=new OrderStatisticsBReq();
		orderStatisticsBReq.setBusinessId(2008);
		orderStatisticsBReq.setMonthInfo("2015-09");
		OrderStatisticsBResp orderStatisticsResp=orderService.getOrderStatisticsB(orderStatisticsBReq);
		ResultModel<OrderStatisticsBResp> resultModel=new ResultModel<OrderStatisticsBResp>();
		resultModel.setResult(orderStatisticsResp);
		return resultModel;
	}
	/**
	 * B端任务统计接口
	 * @author CaoHeYang
	 * @date 20150910
	 * @param data 
	 * @return
	 */
	@Override
	public ResultModel<OrderStatisticsBResp> queryOrderB(String data) {
	   
		return null;
	}

}
