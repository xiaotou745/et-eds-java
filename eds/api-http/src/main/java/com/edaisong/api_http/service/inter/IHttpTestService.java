package com.edaisong.api_http.service.inter;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.edaisong.api_http.entity.TestServiceReq;
import com.edaisong.api_http.entity.TestServiceResp;
import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.domain.BusinessDetailModel;
import com.edaisong.entity.resp.OrderStatisticsBResp;

@Path("/testservice")
//@Consumes("application/json")//当前方法接收的参数类型
@Produces("application/json; charset=utf-8")//当前类的所有方法都返回json格式的数据
public interface IHttpTestService {
	
	@POST
	@Path("/postlist")
	//post和get不能并存
	 //@Consumes(MediaType.APPLICATION_JSON)//当前方法接收的参数类型
	 //@Produces("application/xml")//当前类的所有方法都返回json格式的数据
	//@Consumes({"application/xml","application/json","application/x-www-form-urlencoded;charset=UTF-8"})
	//加上@FormParam("")，只能接收application/x-www-form-urlencoded的数据
	//去掉@FormParam("")，支持json和xml
	//TestServiceReq和TestServiceResp中添加@XmlRootElement(name = "testServiceReq")，只是为了支持xml（json不需要加）
	 public HttpResultModel<BusinessDetailModel> selectByID(TestServiceReq req);
	
	@GET
	@Path("/getlist")
	public HttpResultModel<BusinessDetailModel> getList();
	
	@POST
	@Path("/signalrpush")
	 public HttpResultModel<BusinessDetailModel> signalrPush();
}
