package com.edaisong.api.service.inter;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.springframework.web.bind.annotation.RequestBody;

import com.edaisong.entity.req.TestServiceReq;
import com.edaisong.entity.resp.TestServiceResp;

@Path("/urlService")
public interface ITestService {
	
	@POST
	@Path("/postlist")
	//post和get不能并存
	 //@Consumes(MediaType.APPLICATION_JSON)//当前方法接收的参数类型
	 //@Produces("application/xml")//当前类的所有方法都返回json格式的数据
	//@Consumes({"application/xml","application/json","application/x-www-form-urlencoded;charset=UTF-8"})
	//加上@FormParam("")，只能接收application/x-www-form-urlencoded的数据
	//去掉@FormParam("")，支持json和xml
	//TestServiceReq和TestServiceResp中添加@XmlRootElement(name = "testServiceReq")，只是为了支持xml（json不需要加）
	 public TestServiceResp selectBusinessBalanceByID(TestServiceReq req);
	
	@GET
	@Path("/getlist")
	public TestServiceResp getList();
}
