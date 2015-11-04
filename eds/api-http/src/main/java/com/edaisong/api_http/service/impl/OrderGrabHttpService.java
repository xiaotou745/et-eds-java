package com.edaisong.api_http.service.impl;

import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.service.inter.IOrderGrabService;
import com.edaisong.api_http.service.inter.IOrderGrabHttpService;
import com.edaisong.core.enums.returnenums.HttpReturnRnums;
import com.edaisong.entity.common.HttpResultModel; 
import com.edaisong.entity.domain.OrderGrabDetailModel;
import com.edaisong.entity.req.MyOrderGrabBReq;
import com.edaisong.entity.req.MyOrderGrabCReq;
import com.edaisong.entity.req.OrderGrabDetailBReq;
import com.edaisong.entity.req.OrderGrabDetailCReq;
 
import com.edaisong.entity.resp.MyOrderGrabDetailCResp;
import com.edaisong.entity.resp.MyOrderGrabCResp;
import com.edaisong.entity.resp.OrderGrabResp;
import com.edaisong.entity.resp.OrderRegionResp;

@Service
public class OrderGrabHttpService implements IOrderGrabHttpService {
	@Autowired
	private IOrderGrabService iOrderGrabService;
	
	@Autowired
	private IOrderGrabService orderGrabService;
	/**
	 * 用户抢单
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
	
	/*
	 * 获取我的任务
	 * wangchao
	 */
	@Override
	public HttpResultModel<List<MyOrderGrabCResp>> getMyOrderGrabC(
			MyOrderGrabCReq myOrderGrabCReq) { 
		HttpResultModel<List<MyOrderGrabCResp>> result=new HttpResultModel<List<MyOrderGrabCResp>>();
		result.setStatus(HttpReturnRnums.Success.value());
		result.setMessage(HttpReturnRnums.Success.desc());
		List<MyOrderGrabCResp>  myOrderGrabCList= iOrderGrabService.getMyOrderGrabC(myOrderGrabCReq);
		result.setResult(myOrderGrabCList); 
		return  result;
	}
	@Override
	public HttpResultModel<MyOrderGrabDetailCResp> getMyOrderGrabDetailC(
			OrderGrabDetailCReq orderGrabDetailCReq) {  
		HttpResultModel<MyOrderGrabDetailCResp> result =new HttpResultModel<MyOrderGrabDetailCResp>();
		result.setStatus(HttpReturnRnums.Success.value());
		result.setMessage(HttpReturnRnums.Success.desc());
		MyOrderGrabDetailCResp orderGrabDetailModel= iOrderGrabService.getMyOrderGrabDetailC(orderGrabDetailCReq); 
		result.setResult(orderGrabDetailModel);		
		return result;
	}
}
