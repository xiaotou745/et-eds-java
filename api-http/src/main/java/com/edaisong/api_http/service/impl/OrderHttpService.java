package com.edaisong.api_http.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.service.inter.IOrderService;
import com.edaisong.api_http.entity.ResultModel;
import com.edaisong.api_http.entity.ReturnRnums;
import com.edaisong.api_http.service.inter.IOrderHttpService;
import com.edaisong.core.enums.OrderStatus;
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
	public ResultModel<OrderStatisticsBResp> orderStatisticsB(OrderStatisticsBReq para) {
		ResultModel<OrderStatisticsBResp> resultModel = new ResultModel<OrderStatisticsBResp>();
		if (para.getMonthInfo() == null || para.getMonthInfo().trim().isEmpty() || para.getBusinessId() == 0 ) {
			resultModel
			.setStatus(ReturnRnums.ParaError.value())
			.setMessage(ReturnRnums.ParaError.desc());
			return resultModel;
		}
		OrderStatisticsBResp orderStatisticsResp = orderService.getOrderStatisticsB(para);
		resultModel.setResult(orderStatisticsResp);
		return resultModel;
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
	public ResultModel<QueryOrderBResp> queryOrderB(QueryOrderReq para) {
		ResultModel<QueryOrderBResp> resultModel = new ResultModel<QueryOrderBResp>();
		if ( para.getBusinessId() == null
				|| para.getBusinessId() == 0
				|| (para.getStatus() != OrderStatus.New.value() && para.getStatus() != OrderStatus.Taking.value() && para.getStatus() != OrderStatus.Delivery
						.value())) {
			resultModel.setStatus(ReturnRnums.ParaError.value()).setMessage(ReturnRnums.ParaError.desc());
			return resultModel;
		}
		para.setDateInfo(null);
		para.setClienterId(null);
		resultModel.setResult(orderService.queryOrderB(para));
		return resultModel;
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
	public ResultModel<QueryOrderCResp> queryOrderC(QueryOrderReq para) {
		ResultModel<QueryOrderCResp> resultModel = new ResultModel<QueryOrderCResp>();
		if ( para.getClienterId() == null
				|| para.getClienterId() == 0
				|| ( para.getStatus() != OrderStatus.Taking.value() && para.getStatus() != OrderStatus.Delivery
						.value())) {
			resultModel.setStatus(ReturnRnums.ParaError.value()).setMessage(ReturnRnums.ParaError.desc());
			return resultModel;
		}
		para.setDateInfo(null);
		para.setClienterId(null);
		//resultModel.setResult(orderService.queryOrderB(para));
		return resultModel;
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
	public ResultModel<List<QueryOrder>> getCompliteOrderB(QueryOrderReq para) {
		ResultModel<List<QueryOrder>> resultModel = new ResultModel<List<QueryOrder>>();
		if (para.getDateInfo() == null || para.getDateInfo().trim().isEmpty() || para.getBusinessId() == null || para.getBusinessId() == 0) {
			resultModel.setStatus(ReturnRnums.ParaError.value()).setMessage(ReturnRnums.ParaError.desc());
			return resultModel;
		}
		resultModel.setResult(orderService.getCompliteOrder(para));
		return resultModel;
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
	public ResultModel<List<QueryOrder>> getCompliteOrderC(QueryOrderReq para) {
		ResultModel<List<QueryOrder>> resultModel = new ResultModel<List<QueryOrder>>();
		if (para.getDateInfo() == null || para.getDateInfo().trim().isEmpty() || para.getClienterId() == null || para.getClienterId() == 0) {
			resultModel
			.setStatus(ReturnRnums.ParaError.value())
			.setMessage(ReturnRnums.ParaError.desc());
			return resultModel;
		}
		resultModel.setResult(orderService.getCompliteOrder(para));
		return resultModel;
	}

	/**
	 * C端任务统计接口
	 * 
	 * @author WangXuDan
	 * @date 20150910
	 * @param data
	 */
	@Override
	public ResultModel<OrderStatisticsCResp> orderStatisticsC(OrderStatisticsCReq orderStatisticsCReq) {
		// OrderStatisticsCReq orderStatisticsCReq=new OrderStatisticsCReq();
		// orderStatisticsCReq.setClienterId(3233) ;
		// orderStatisticsCReq.setMonthInfo("2015-09");
		OrderStatisticsCResp orderStatisticsResp = orderService.getOrderStatisticsC(orderStatisticsCReq);
		ResultModel<OrderStatisticsCResp> resultModel = new ResultModel<OrderStatisticsCResp>();
		resultModel.setResult(orderStatisticsResp);
		return resultModel;
	}

}
