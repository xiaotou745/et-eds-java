package com.eds.service.inter;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.xml.ws.soap.Addressing;

import com.alipay.api.AlipayApiException;
import com.eds.entity.ParamAliModel;

@Path("/aliservice")
@Consumes("application/json")
// 当前方法接收的参数类型
@Produces("application/json; charset=utf-8")
// 当前类的所有方法都返回json格式的数据
public interface IAliService {
	@POST
	@Path("/cancelorder")
	public void cancelOrder(ParamAliModel model) throws AlipayApiException;
}
