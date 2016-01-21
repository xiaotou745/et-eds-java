package com.eds.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayMobilePublicMessagePushRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayMobilePublicMessagePushResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.alipay.f2fpay.ToAlipayQrTradePay;
import com.alipay.factory.AlipayAPIClientFactory;
import com.eds.common.Config;
import com.eds.service.inter.IAliService;
import com.alipay.parmodel.ParamAliModel;
import com.alipay.parmodel.ParamBatchTransModel;
import com.alipay.parmodel.ParamPlatformModel;
import com.alipay.util.*;
import java.util.*;


@Service
public class AliService implements IAliService {
	String AliPayNotifyKey = new Config().getConfig("AliPayNotifyKey");
	String AliPayNotifyRSA = new Config().getConfig("AliPayNotifyRSA");

	/**
	 * 批量付款
	 * */
	@Override
	public String batchTrans(ParamBatchTransModel model) {
		// 把请求参数打包成数组
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "batch_trans_notify");
		ParamPlatformModel paramPlatformModel = AlipayAPIClientFactory
				.getPlatformModel(model.getPlatform());
		sParaTemp.put("partner", paramPlatformModel.getPartner());
		sParaTemp.put("email", paramPlatformModel.getEmail());
		sParaTemp.put("key", paramPlatformModel.getKey());
		sParaTemp.put("account_name", paramPlatformModel.getAccount_name());
		sParaTemp.put("_input_charset", paramPlatformModel.getInput_charset());
		sParaTemp.put("notify_url", model.getNotify_url());
		sParaTemp.put("pay_date", sdf.format(now));
		sParaTemp.put("batch_no", model.getBatch_no());
		sParaTemp.put("batch_fee", model.getBatch_fee());
		sParaTemp.put("batch_num", model.getBatch_num());
		sParaTemp.put("detail_data", model.getDetail_info());
		// 建立请求
		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
		return sHtmlText;
	}

	/**
	 * 生成订单
	 * */
	@Override
	public String createOrder(ParamAliModel model)
			throws AlipayApiException {
		AlipayTradePrecreateResponse response = null;
		response = ToAlipayQrTradePay.qrPay(model.getOut_trade_no(),
				model.getTotal_fee(), model.getSubject(), model.getPlatform(),
				AliPayNotifyRSA, model.getNotify_url());
		String qrCode="";
		if (null != response && response.isSuccess()) {
			System.out.println(response.getBody());
			System.out.println(response.isSuccess());
			System.out.println(response.getMsg());
			if (response.getCode().equals("10000")) {
				qrCode=response.getQrCode();
			} 
		} 
		return qrCode;
	}

	/**
	 * 取消订单
	 * */
	@Override
	public AlipayMobilePublicMessagePushResponse cancelOrder(ParamAliModel model)
			throws AlipayApiException {
		AlipayClient alipayClient = AlipayAPIClientFactory
				.getAlipayClient(model.getPlatform());
		AlipayMobilePublicMessagePushRequest request = new AlipayMobilePublicMessagePushRequest();
		// 传入接口中规定的业务参数
		JSONObject json = new JSONObject();
		json.put("out_trade_no", model.getTrade_no());
		request.setBizContent(json.toString());
		return alipayClient.execute(request);
	}

	/**
	 * 退款
	 * */
	@Override
	public AlipayTradeRefundResponse refundOrder(ParamAliModel model)
			throws AlipayApiException {
		AlipayClient alipayClient = AlipayAPIClientFactory
				.getAlipayClient(model.getPlatform());
		AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();

		JSONObject json = new JSONObject();
		json.put("trade_no", model.getTrade_no());
		json.put("refund_amount", model.getRefund_amount());
		json.put("refund_reason", model.getRefund_amount());

		request.setBizContent(json.toString());
		AlipayTradeRefundResponse response = alipayClient.execute(request);
		return response;
	}

	/**
	 * 查询订单状态
	 * */
	@Override
	public AlipayTradeQueryResponse queryOrder(ParamAliModel model)
			throws AlipayApiException {
		AlipayClient alipayClient = AlipayAPIClientFactory
				.getAlipayClient(model.getPlatform());
		AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
		net.sf.json.JSONObject json = new net.sf.json.JSONObject();
		// StringBuilder sb=new StringBuilder();
		// sb.append("{\"out_trade_no\":\"" + model.getOut_trade_no() + "\"}");
		json.put("out_trade_no", model.getOut_trade_no());

		System.out.println(json.toString());
		request.setBizContent(json.toString());
		AlipayTradeQueryResponse response = alipayClient.execute(request);
		return response;
	}

}
