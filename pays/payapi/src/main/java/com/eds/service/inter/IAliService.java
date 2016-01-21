package com.eds.service.inter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.alipay.api.AlipayApiException;
import com.alipay.api.response.AlipayMobilePublicMessagePushResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.alipay.parmodel.ParamAliModel;
import com.alipay.parmodel.ParamBatchTransModel;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.apache.cxf.message.Message;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/aliservice")
@Consumes("application/json")
// 当前方法接收的参数类型
@Produces("application/json; charset=utf-8")
// 当前类的所有方法都返回json格式的数据
public interface IAliService {

	@POST
	@Path("/createorder")
	public String createOrder(ParamAliModel model)
			throws AlipayApiException;

	@POST
	@Path("/cancelorder")
	public AlipayMobilePublicMessagePushResponse cancelOrder(ParamAliModel model)
			throws AlipayApiException;

	@POST
	@Path("/refundorder")
	public AlipayTradeRefundResponse refundOrder(ParamAliModel model)
			throws AlipayApiException;

	@POST
	@Path("/queryorder")
	public AlipayTradeQueryResponse queryOrder(ParamAliModel model)
			throws AlipayApiException;

	/**
	 * 批量付款
	 * */
	@POST
	@Path("batchtrans")
	@Produces(MediaType.TEXT_PLAIN)
	public String batchTrans(ParamBatchTransModel model);
}
