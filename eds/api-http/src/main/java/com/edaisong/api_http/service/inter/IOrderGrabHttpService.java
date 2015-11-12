package com.edaisong.api_http.service.inter;

import java.util.List;

import javax.ws.rs.Consumes; 
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.edaisong.entity.common.HttpResultModel;  
import com.edaisong.entity.domain.QueryOrder;
import com.edaisong.entity.req.HadFinishOrderReq;
import com.edaisong.entity.req.MyOrderGrabCReq; 
import com.edaisong.entity.req.OrderGrabCompleteReq;
import com.edaisong.entity.req.OrderGrabConfirmTakeReq;
import com.edaisong.entity.req.OrderGrabDetailCReq;
 
import com.edaisong.entity.req.OrderGrabReq;
import com.edaisong.entity.req.QueryOrderReq;
import com.edaisong.entity.resp.HadFinishOrderResp;
import com.edaisong.entity.resp.MyOrderGrabDetailCResp;
import com.edaisong.entity.resp.MyOrderGrabCResp;
import com.edaisong.entity.resp.OrderGrabResp;



@Path("/ordergrab")
@Consumes("application/json; charset=utf-8")//当前方法接收的参数类型
@Produces("application/json; charset=utf-8")//当前类的所有方法都返回json格式的数据
public interface IOrderGrabHttpService {
	/**
	 * 抢单
	 * @author 胡灵波
	 * @date 2015年11月2日 15:44:40
	 * @version 1.0
	 * @param req
	 * @return
	 */
	@POST
	@Path("/receive")
	HttpResultModel<OrderGrabResp> Receive(OrderGrabReq req);
	
	/**
	 * 取货
	 * @author 胡灵波
	 * @date 2015年11月4日 10:54:12
	 * @version 1.0
	 * @param req
	 * @return
	 */
	@POST
	@Path("/confirmtake")
	HttpResultModel<Integer> ConfirmTake(OrderGrabConfirmTakeReq req);
	
	/**
	 * 完成订单
	 * @author 胡灵波
	 * @date 2015年11月4日 11:10:23
	 * @version 1.0
	 * @param req
	 * @return
	 */
	@POST
	@Path("/complete")
	HttpResultModel<Integer> Complete(OrderGrabCompleteReq req);
	
	/*
	 * 获取我的任务C
	 * wangchao
	 */
	@POST
	@Path("/getmyordergrabc")
	HttpResultModel<MyOrderGrabCResp> getMyOrderGrabC(MyOrderGrabCReq myOrderGrabCReq);
	
	/*
	 * 获取我的任务详情C
	 * wangchao
	 */
	@POST
	@Path("/getmyordergrabdetailc")
	public HttpResultModel<MyOrderGrabDetailCResp> getMyOrderGrabDetailC(OrderGrabDetailCReq orderGrabDetailCReq);
	 
	/**
	 * C端已完成任务列表 
	 * wangchao
	 */
	@POST
	@Path("/gethadfinishorderc")
	public HttpResultModel<HadFinishOrderResp> getHadFinishOrderC(HadFinishOrderReq para);

}