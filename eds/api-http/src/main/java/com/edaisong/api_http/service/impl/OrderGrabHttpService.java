package com.edaisong.api_http.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.service.inter.IOrderGrabService;
import com.edaisong.api_http.service.inter.IOrderGrabHttpService;
import com.edaisong.core.enums.ClienterBindBusinessEnum;
import com.edaisong.core.enums.OrderGrabEnum;
import com.edaisong.core.enums.returnenums.HttpReturnRnums;
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

@Service
public class OrderGrabHttpService implements IOrderGrabHttpService {
	
	@Autowired
	private IOrderGrabService orderGrabService;
	/**
	 * 骑士抢单
	 * @author 胡灵波
	 * @date 2015年11月2日 14:56:05
	 * @version 1.0
	 * @param req
	 * @return
	 */
	@Override
	public HttpResultModel<OrderGrabResp> Receive(OrderGrabReq req)
	{
		HttpResultModel<OrderGrabResp> resp=orderGrabService.GrabOrder(req);			
		return resp;
	}
	
	/**
	 * 骑士取货
	 * @author 胡灵波
	 * @date 2015年11月2日 14:56:05
	 * @version 1.0
	 * @param req
	 * @return
	 */
	@Override
	public HttpResultModel<Integer> ConfirmTake(OrderGrabConfirmTakeReq req)
	{
		HttpResultModel<Integer> resp=orderGrabService.ConfirmTake(req);
		return resp;
	}
	
	/**
	 * 骑士完成订单
	 * @author 胡灵波
	 * @date 2015年11月2日 14:56:05
	 * @version 1.0
	 * @param req
	 * @return
	 */
	@Override	       
	public HttpResultModel<Integer> Complete(OrderGrabCompleteReq req)
	{
		HttpResultModel<Integer> resp=orderGrabService.Complete(req);
		return resp;
	}	

	
	/*
	 * 获取我的任务
	 * wangchao
	 */
	@Override
	public HttpResultModel<MyOrderGrabCResp> getMyOrderGrabC(
			MyOrderGrabCReq myOrderGrabCReq) { 
		HttpResultModel<MyOrderGrabCResp> result=new HttpResultModel<MyOrderGrabCResp>();
		if(myOrderGrabCReq.getClienterId()<=0){
			result.setStatus(OrderGrabEnum.ClienterIdEmpty.value());
			result.setMessage(OrderGrabEnum.ClienterIdEmpty.desc());
			return result;
		}	 
		
		result.setStatus(HttpReturnRnums.Success.value());
		result.setMessage(HttpReturnRnums.Success.desc());
		MyOrderGrabCResp  myOrderGrabCResp= orderGrabService.getMyOrderGrabC(myOrderGrabCReq);
		result.setResult(myOrderGrabCResp); 
		return  result;
	}
	/*
	 * 获取任务详情
	 * wangchao
	 */
	@Override
	public HttpResultModel<MyOrderGrabDetailCResp> getMyOrderGrabDetailC(
			OrderGrabDetailCReq orderGrabDetailCReq) {  
		HttpResultModel<MyOrderGrabDetailCResp> result =new HttpResultModel<MyOrderGrabDetailCResp>();
		if(orderGrabDetailCReq.getGrabOrderId()<=0){
			result.setStatus(OrderGrabEnum.GrabOrderIdEmpty.value());
			result.setMessage(OrderGrabEnum.GrabOrderIdEmpty.desc());
			return result;
		}	
		result.setStatus(HttpReturnRnums.Success.value());
		result.setMessage(HttpReturnRnums.Success.desc());
		MyOrderGrabDetailCResp orderGrabDetailModel= orderGrabService.getMyOrderGrabDetailC(orderGrabDetailCReq); 
		result.setResult(orderGrabDetailModel);		
		return result;
	}
 
	@Override
	public HttpResultModel<HadFinishOrderResp> getHadFinishOrderC(
			HadFinishOrderReq para) {
		para.setPageSize(1000); 
		HttpResultModel<HadFinishOrderResp> result=new HttpResultModel<HadFinishOrderResp>();
		if (para.getDateInfo() == null || para.getDateInfo().trim().isEmpty() || para.getClienterId() == null || para.getClienterId() == 0) {
			result.setStatus(HttpReturnRnums.ParaError.value());
			result.setMessage(HttpReturnRnums.ParaError.desc());
			return result; 
		}
		result.setStatus(HttpReturnRnums.Success.value());
		result.setMessage(HttpReturnRnums.Success.desc());
		HadFinishOrderResp hadFinishOrderResp  =orderGrabService.getHadFinishOrderC(para);
		result.setResult(hadFinishOrderResp);
		return result;
	}
}
