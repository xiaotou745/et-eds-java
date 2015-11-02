package com.edaisong.api_http.service.impl;

import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.springframework.stereotype.Service;

import com.edaisong.api_http.service.inter.IGrabOrderHttpService;
import com.edaisong.entity.common.HttpResultModel; 
import com.edaisong.entity.req.MyGrabOrderCReq;
import com.edaisong.entity.resp.MyGrabOrderCResp;
@Service
public class GrabOrderHttpService implements IGrabOrderHttpService {
 
	@Override
	public HttpResultModel<List<MyGrabOrderCResp>> getMyGrabOrderC(
			MyGrabOrderCReq myGrabOrderCReq) { 
		return null;
	}
	 
}
