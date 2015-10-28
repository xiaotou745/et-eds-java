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

	@Override
	public String testVal() {
		// TODO Auto-generated method stub
		return "123";
	}

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
			HttpResultModel<OrderStatisticsBResp> result=new HttpResultModel<OrderStatisticsBResp>();
			result.setStatus(HttpReturnRnums.ParaError.value());
			result.setMessage(HttpReturnRnums.ParaError.desc());
			return result;  
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
			HttpResultModel<QueryOrderBResp> result=new HttpResultModel<QueryOrderBResp>();
			result.setStatus(HttpReturnRnums.ParaError.value());
			result.setMessage(HttpReturnRnums.ParaError.desc());
			return result;   
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
		para.setPageSize(100);//TODO  该接口暂时不做分页 目前只取100条数据 茹化肖
		if ( para.getClienterId() == null
				|| para.getClienterId() == 0
				|| ( para.getStatus() != OrderStatus.Taking.value() && para.getStatus() != OrderStatus.Delivery
						.value())) {
			HttpResultModel<QueryOrderCResp> result=new HttpResultModel<QueryOrderCResp>();
			result.setStatus(HttpReturnRnums.ParaError.value());
			result.setMessage(HttpReturnRnums.ParaError.desc());
			return result; 
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
			HttpResultModel<List<QueryOrder>> result=new HttpResultModel<List<QueryOrder>>();
			result.setStatus(HttpReturnRnums.ParaError.value());
			result.setMessage(HttpReturnRnums.ParaError.desc());
			return result; 
		}
		return orderService.getCompliteOrder(para,0);
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
		para.setPageSize(1000);//TODO 因为WIKI接口没有写分页参数,暂时一次性将所有数据返回,
		if (para.getDateInfo() == null || para.getDateInfo().trim().isEmpty() || para.getClienterId() == null || para.getClienterId() == 0) {
			HttpResultModel<List<QueryOrder>> result=new HttpResultModel<List<QueryOrder>>();
			result.setStatus(HttpReturnRnums.ParaError.value());
			result.setMessage(HttpReturnRnums.ParaError.desc());
			return result; 
		}
		return orderService.getCompliteOrder(para,1);
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
		OrderStatisticsCResp orderStatisticsResp = orderService.getOrderStatisticsC(orderStatisticsCReq);
		HttpResultModel<OrderStatisticsCResp> httpResultModel = new HttpResultModel<OrderStatisticsCResp>();
		httpResultModel.setResult(orderStatisticsResp);
		return httpResultModel;
	}

}
