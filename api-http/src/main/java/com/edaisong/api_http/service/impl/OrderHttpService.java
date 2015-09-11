package com.edaisong.api_http.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.service.inter.IOrderService;
import com.edaisong.api_http.entity.ReturnRnums;
import com.edaisong.api_http.service.inter.IOrderHttpService;
import com.edaisong.core.enums.OrderStatus;
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
		HttpResultModel<OrderStatisticsBResp> HttpResultModel = new HttpResultModel<OrderStatisticsBResp>();
		if (para.getMonthInfo() == null || para.getMonthInfo().trim().isEmpty() || para.getBusinessId() == 0 ) {
			HttpResultModel
			.setStatus(ReturnRnums.ParaError.value())
			.setMessage(ReturnRnums.ParaError.desc());
			return HttpResultModel;
		}
		OrderStatisticsBResp orderStatisticsResp = orderService.getOrderStatisticsB(para);
		HttpResultModel.setResult(orderStatisticsResp);
		return HttpResultModel;
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
		HttpResultModel<QueryOrderBResp> HttpResultModel = new HttpResultModel<QueryOrderBResp>();
		if ( para.getBusinessId() == null
				|| para.getBusinessId() == 0
				|| (para.getStatus() != OrderStatus.New.value() && para.getStatus() != OrderStatus.Taking.value() && para.getStatus() != OrderStatus.Delivery
						.value())) {
			HttpResultModel.setStatus(ReturnRnums.ParaError.value()).setMessage(ReturnRnums.ParaError.desc());
			return HttpResultModel;
		}
		para.setDateInfo(null);
		para.setClienterId(null);
		HttpResultModel.setResult(orderService.queryOrderB(para));
		return HttpResultModel;
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
		HttpResultModel<QueryOrderCResp> HttpResultModel = new HttpResultModel<QueryOrderCResp>();
		if ( para.getClienterId() == null
				|| para.getClienterId() == 0
				|| ( para.getStatus() != OrderStatus.Taking.value() && para.getStatus() != OrderStatus.Delivery
						.value())) {
			HttpResultModel.setStatus(ReturnRnums.ParaError.value()).setMessage(ReturnRnums.ParaError.desc());
			return HttpResultModel;
		}
		para.setDateInfo(null);
		para.setBusinessId(null);
		HttpResultModel.setResult(orderService.queryOrderC(para));
		return HttpResultModel;
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
		HttpResultModel<List<QueryOrder>> HttpResultModel = new HttpResultModel<List<QueryOrder>>();
		if (para.getDateInfo() == null || para.getDateInfo().trim().isEmpty() || para.getBusinessId() == null || para.getBusinessId() == 0) {
			HttpResultModel.setStatus(ReturnRnums.ParaError.value()).setMessage(ReturnRnums.ParaError.desc());
			return HttpResultModel;
		}
		HttpResultModel.setResult(orderService.getCompliteOrder(para));
		return HttpResultModel;
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
		HttpResultModel<List<QueryOrder>> HttpResultModel = new HttpResultModel<List<QueryOrder>>();
		if (para.getDateInfo() == null || para.getDateInfo().trim().isEmpty() || para.getClienterId() == null || para.getClienterId() == 0) {
			HttpResultModel
			.setStatus(ReturnRnums.ParaError.value())
			.setMessage(ReturnRnums.ParaError.desc());
			return HttpResultModel;
		}
		HttpResultModel.setResult(orderService.getCompliteOrder(para));
		return HttpResultModel;
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
		HttpResultModel<OrderStatisticsCResp> HttpResultModel = new HttpResultModel<OrderStatisticsCResp>();
		HttpResultModel.setResult(orderStatisticsResp);
		return HttpResultModel;
	}

}
