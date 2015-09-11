package com.edaisong.api_http.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.service.inter.IOrderService;
import com.edaisong.api_http.service.inter.IOrderHttpService;
import com.edaisong.core.enums.OrderStatus;
import com.edaisong.core.enums.returnenums.HttpReturnRnums;
import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.domain.QueryOrder;
import com.edaisong.entity.req.OrderStatisticsBReq;
import com.edaisong.entity.req.QueryOrderReq;
import com.edaisong.entity.resp.OrderStatisticsBResp;
import com.edaisong.entity.resp.QueryOrderBResp;
import com.edaisong.entity.resp.QueryOrderCResp;
import com.edaisong.entity.req.OrderStatisticsCReq;
import com.edaisong.entity.resp.OrderStatisticsCResp;

/**
 * 订单模块
 * 
 * @author CaoHeYang
 * @date 20150910
 */
@Service
public class OrderHttpService implements IOrderHttpService {

	@Autowired
	private IOrderService orderService;

	/**
	 * B端任务统计接口
	 * 
	 * @author CaoHeYang
	 * @date 20150910
	 * @param data
	 * @return
	 */
	@Override
	public HttpResultModel<OrderStatisticsBResp> orderStatisticsB(OrderStatisticsBReq para) {
		if (para.getMonthInfo() == null || para.getMonthInfo().trim().isEmpty() || para.getBusinessId() == 0 ) {
			return new HttpResultModel<OrderStatisticsBResp>()
			.setStatus(HttpReturnRnums.ParaError.value())
			.setMessage(HttpReturnRnums.ParaError.desc());
		}
		return orderService.getOrderStatisticsB(para);
	}

	/**
	 * B 端首页 订单列表
	 * 
	 * @author CaoHeYang
	 * @date 20150910
	 * @param data
	 * @return
	 */
	@Override
	public HttpResultModel<QueryOrderBResp> queryOrderB(QueryOrderReq para) {
		if ( para.getBusinessId() == null
				|| para.getBusinessId() == 0
				|| (para.getStatus() != OrderStatus.New.value() && para.getStatus() != OrderStatus.Taking.value() && para.getStatus() != OrderStatus.Delivery
						.value())) {
			return    new HttpResultModel<QueryOrderBResp>().setStatus(HttpReturnRnums.ParaError.value()).setMessage(HttpReturnRnums.ParaError.desc());
		}
		para.setDateInfo(null);
		para.setClienterId(null);
		return orderService.queryOrderB(para);
	}

	/**
	 *  C 端我的任务
	 * 
	 * @author CaoHeYang
	 * @date 20150911
	 * @param para
	 * @return
	 */
	@Override
	public HttpResultModel<QueryOrderCResp> queryOrderC(QueryOrderReq para) {
		if ( para.getClienterId() == null
				|| para.getClienterId() == 0
				|| ( para.getStatus() != OrderStatus.Taking.value() && para.getStatus() != OrderStatus.Delivery
						.value())) {
			return  new HttpResultModel<QueryOrderCResp>().setStatus(HttpReturnRnums.ParaError.value()).setMessage(HttpReturnRnums.ParaError.desc());
		}
		para.setDateInfo(null);
		para.setBusinessId(null);
		return orderService.queryOrderC(para);
	}
	/**
	 * B端已完成任务列表或者配送员配送列表
	 * 
	 * @author CaoHeYang
	 * @date 20150910
	 * @param data
	 * @return
	 */
	@Override
	public HttpResultModel<List<QueryOrder>> getCompliteOrderB(QueryOrderReq para) {
		if (para.getDateInfo() == null || para.getDateInfo().trim().isEmpty() || para.getBusinessId() == null || para.getBusinessId() == 0) {
			new HttpResultModel<List<QueryOrder>>().setStatus(HttpReturnRnums.ParaError.value()).setMessage(HttpReturnRnums.ParaError.desc());
		}
		return orderService.getCompliteOrder(para);
	}

	/**
	 * C端已完成任务列表
	 * 
	 * @author CaoHeYang
	 * @date 20150910
	 * @param data
	 * @return
	 */
	@Override
	public HttpResultModel<List<QueryOrder>> getCompliteOrderC(QueryOrderReq para) {
		if (para.getDateInfo() == null || para.getDateInfo().trim().isEmpty() || para.getClienterId() == null || para.getClienterId() == 0) {
			return new HttpResultModel<List<QueryOrder>>()
			.setStatus(HttpReturnRnums.ParaError.value())
			.setMessage(HttpReturnRnums.ParaError.desc());
		}
		return orderService.getCompliteOrder(para);
	}

	/**
	 * C端任务统计接口
	 * 
	 * @author WangXuDan
	 * @date 20150910
	 * @param data
	 */
	@Override
	public HttpResultModel<OrderStatisticsCResp> orderStatisticsC(OrderStatisticsCReq orderStatisticsCReq) {
		// OrderStatisticsCReq orderStatisticsCReq=new OrderStatisticsCReq();
		// orderStatisticsCReq.setClienterId(3233) ;
		// orderStatisticsCReq.setMonthInfo("2015-09");
		OrderStatisticsCResp orderStatisticsResp = orderService.getOrderStatisticsC(orderStatisticsCReq);
		HttpResultModel<OrderStatisticsCResp> httpResultModel = new HttpResultModel<OrderStatisticsCResp>();
		httpResultModel.setResult(orderStatisticsResp);
		return httpResultModel;
	}

}
