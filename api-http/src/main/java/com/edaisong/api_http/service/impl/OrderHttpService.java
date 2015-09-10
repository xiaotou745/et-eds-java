package com.edaisong.api_http.service.impl;

import com.edaisong.api_http.entity.ResultModel;
import com.edaisong.api_http.service.inter.IOrderHttpService;
import com.edaisong.entity.resp.OrderStatisticsResp;


/**
 * 订单模块 
 * @author CaoHeYang
 * @date 20150910
 */
public class OrderHttpService implements IOrderHttpService {

	/**
	 * B端任务统计接口
	 * @author CaoHeYang
	 * @date 20150910
	 * @param data 
	 * @return
	 */
	@Override
	public ResultModel<OrderStatisticsResp> orderStatisticsB(String data) {
		return null;
	}

}
