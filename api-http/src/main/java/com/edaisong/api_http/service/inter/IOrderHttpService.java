package com.edaisong.api_http.service.inter;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.edaisong.api_http.entity.ResultModel;
import com.edaisong.entity.domain.QueryOrder;
import com.edaisong.entity.req.OrderStatisticsBReq;
import com.edaisong.entity.req.OrderStatisticsCReq;
import com.edaisong.entity.req.QueryOrderReq;
import com.edaisong.entity.resp.OrderStatisticsBResp;
import com.edaisong.entity.resp.QueryOrderBResp;
import com.edaisong.entity.resp.OrderStatisticsCResp;
import com.edaisong.entity.resp.QueryOrderCResp;

/**
 * 订单模块
 * 
 * @author CaoHeYang
 * @date 20150910
 */
@Path("/order")
@Consumes("application/json")
// 当前方法接收的参数类型
@Produces("application/json")
// 当前类的所有方法都返回json格式的数据
public interface IOrderHttpService {
	/**
	 * B端任务统计接口
	 * 
	 * @author CaoHeYang
	 * @date 20150910
	 * @param orderStatisticsBReq
	 * @return
	 */
	@POST
	@Path("/orderstatisticsb")
	public ResultModel<OrderStatisticsBResp> orderStatisticsB(OrderStatisticsBReq orderStatisticsBReq);

	/**
	 * B端任务统计接口
	 * 
	 * @author CaoHeYang
	 * @date 20150910
	 * @param  para
	 * @return
	 */
	@POST
	@Path("/queryorderb")
	public ResultModel<QueryOrderBResp> queryOrderB(QueryOrderReq para);
	
	/**
	 * C 端我的任务
	 * 
	 * @author CaoHeYang
	 * @date 20150911
	 * @param para
	 */
	@POST
	@Path("/queryorderc")
	ResultModel<QueryOrderCResp> queryOrderC(QueryOrderReq para);

	/**
	 * B端已完成任务列表或者配送员配送列表
	 * 
	 * @author CaoHeYang
	 * @date 20150910
	 * @param para
	 * @return
	 */
	@POST
	@Path("/getcompliteorderb")
	public ResultModel<List<QueryOrder>> getCompliteOrderB(QueryOrderReq para);

	/**
	 * C端已完成任务列表
	 * 
	 * @author CaoHeYang
	 * @date 20150910
	 * @param para
	 * @return
	 */
	@POST
	@Path("/getcompliteorderc")
	public ResultModel<List<QueryOrder>> getCompliteOrderC(QueryOrderReq para);

	/**
	 * C端任务统计接口
	 * 
	 * @author WangXuDan
	 * @date 20150910
	 * @param data
	 */
	@POST
	@Path("/orderstatisticsc")
	public ResultModel<OrderStatisticsCResp> orderStatisticsC(OrderStatisticsCReq orderStatisticsCReq);


}
