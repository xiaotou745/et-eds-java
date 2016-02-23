package com.eds.service.inter;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.tencent.model.PayParamModel;
import com.tencent.model.PayResultModel;
import com.tencent.protocol.refund_protocol.RefundReqData;

@Path("/wxservice")
// 当前方法接收的参数类型
@Consumes("application/json")
// 当前类的所有方法都返回json格式的数据
@Produces("application/json; charset=utf-8")
public interface IWxService {

	/**
	 * APP支付
	 * */
	@POST
	@Path("/getappurl")
	public String GetAppUrl(PayParamModel model) throws Exception;

	/**
	 * 二维码支付
	 * */
	@POST
	@Path("/getqrurl")
	public PayResultModel GetQrUrl(PayParamModel model) throws Exception;

	/**
	 * 查询订单 out_trade_no=商户单号
	 * */
	@POST
	@Path("/query")
	public void Query(String out_trade_no,int platform) throws Exception;

	/**
	 * 退款
	 * */
	@POST
	@Path("refund")
	public void Refund(RefundReqData model,int platform) throws Exception;

	/**
	 * 关闭订单
	 * */
	@POST
	@Path("close")
	public String Close(String out_trade_no,int platform) throws Exception;
}
