package com.eds.service.inter;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import com.tencent.model.PayParamModel;
import com.tencent.model.PayResultModel;

@Path("/wxservice")
// 当前方法接收的参数类型
@Consumes("application/json")
// 当前类的所有方法都返回json格式的数据
@Produces("application/json; charset=utf-8")
public interface IWxService {

	@POST
	@Path("/getappurl")
	public String GetAppUrl(PayParamModel model) throws Exception;

	@POST
	@Path("/getqrurl")
	public PayResultModel GetQrUrl(PayParamModel model) throws Exception;

}
