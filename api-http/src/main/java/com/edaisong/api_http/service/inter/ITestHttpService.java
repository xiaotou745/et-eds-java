package com.edaisong.api_http.service.inter;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.edaisong.entity.req.TestServiceReq;
import com.edaisong.entity.resp.TestServiceResp;

public interface ITestHttpService {
	public TestServiceResp getList(TestServiceReq req);
}
