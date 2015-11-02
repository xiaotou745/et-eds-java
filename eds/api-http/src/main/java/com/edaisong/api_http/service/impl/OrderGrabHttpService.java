package com.edaisong.api_http.service.impl;

import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.springframework.stereotype.Service;

import com.edaisong.api_http.service.inter.IOrderGrabHttpService;
import com.edaisong.entity.common.HttpResultModel; 
import com.edaisong.entity.req.MyOrderGrabCReq;
import com.edaisong.entity.resp.MyOrderGrabCResp;
@Service
public class OrderGrabHttpService implements IOrderGrabHttpService {
 
	@Override
	public HttpResultModel<List<MyOrderGrabCResp>> getMyGrabOrderC(
			MyOrderGrabCReq myGrabOrderCReq) { 
		return null;
	}
	 
}
