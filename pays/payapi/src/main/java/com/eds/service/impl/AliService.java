package com.eds.service.impl;

import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayResponse;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayAcquireCancelRequest;
import com.eds.entity.ParamAliModel;
import com.eds.service.inter.IAliService;

@Service
public class AliService implements IAliService {

	private static String serverUrl = "https://openapi.alipay.com/gateway.do";
	private static String app_id = "2015081900222190";
	private static String privateKey = "G:\\project\\e代送众包版\\Eds.SuperMan\\SuperManWebApi\\Content\\pem\\rsa_private_key.pem";
	private static String format = "json";

	@Override
	public void cancelOrder(ParamAliModel model) throws AlipayApiException {

		AlipayClient alipayClient = new DefaultAlipayClient(serverUrl, app_id,
				privateKey, format);

		AlipayAcquireCancelRequest alipayAcquireCancelRequest = new AlipayAcquireCancelRequest();
		alipayAcquireCancelRequest.setTradeNo(model.getTrade_no());
		AlipayResponse response = alipayClient
				.execute(alipayAcquireCancelRequest);

	}

}
