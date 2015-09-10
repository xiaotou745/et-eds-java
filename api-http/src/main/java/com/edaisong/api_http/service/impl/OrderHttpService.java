package com.edaisong.api_http.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.service.inter.IOrderService;
import com.edaisong.api_http.entity.ResultModel;
import com.edaisong.api_http.service.inter.IOrderHttpService;
import com.edaisong.core.enums.OrderStatus;
import com.edaisong.entity.req.OrderStatisticsBReq;
import com.edaisong.entity.req.QueryOrderBReq;
import com.edaisong.entity.resp.OrderStatisticsBResp;
import com.edaisong.entity.resp.QueryOrderBResp;


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
	 * B 端首页 订单列表
	 * @author CaoHeYang
	 * @date 20150910
	 * @param data 
	 * @return
	 */
	@Override
	public ResultModel<QueryOrderBResp> queryOrderB(String data) {
		QueryOrderBReq para=	new QueryOrderBReq();
		para.setBusinessId(2092);
		para.setStatus(OrderStatus.Delivery.value());
		ResultModel<QueryOrderBResp> resultModel=new ResultModel<QueryOrderBResp>();
		resultModel.setResult(orderService.queryOrderB(para));
		return resultModel;
	}

}
