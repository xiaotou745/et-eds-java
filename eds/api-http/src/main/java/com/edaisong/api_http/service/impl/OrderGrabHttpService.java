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
import com.edaisong.entity.req.MyOrderGrabCReq;
import com.edaisong.entity.req.OrderGrabDetailCReq;
import com.edaisong.entity.resp.MyOrderGrabDetailResp;
import com.edaisong.entity.resp.MyOrderGrabCResp;
import com.edaisong.entity.resp.OrderRegionResp;

@Service
public class OrderGrabHttpService implements IOrderGrabHttpService {
	@Autowired
	private IOrderGrabService iOrderGrabService;
	/*
	 * 获取我的任务
	 * @see com.edaisong.api_http.service.inter.IOrderGrabHttpService#getMyGrabOrderC(com.edaisong.entity.req.MyOrderGrabCReq)
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
	public HttpResultModel<MyOrderGrabDetailResp> getMyOrderGrabDetailC(
			OrderGrabDetailCReq orderGrabDetailCReq) {  
		HttpResultModel<MyOrderGrabDetailResp> result =new HttpResultModel<MyOrderGrabDetailResp>();
		result.setStatus(HttpReturnRnums.Success.value());
		result.setMessage(HttpReturnRnums.Success.desc());
		MyOrderGrabDetailResp orderGrabDetailModel= iOrderGrabService.getMyOrderGrabDetailC(orderGrabDetailCReq); 
		result.setResult(orderGrabDetailModel);		
		return result;
	}

	 
	 
}
