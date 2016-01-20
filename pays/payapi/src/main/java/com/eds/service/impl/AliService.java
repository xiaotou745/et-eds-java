package com.eds.service.impl;

import java.text.SimpleDateFormat;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayMobilePublicMessagePushRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayMobilePublicMessagePushResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.alipay.f2fpay.ToAlipayQrTradePay;
import com.alipay.factory.AlipayAPIClientFactory;
import com.edaisong.core.util.PropertyUtils;
import com.eds.common.Config;
import com.eds.service.inter.IAliService;
import com.alipay.config.*;
import com.alipay.parmodel.ParamAliModel;
import com.alipay.parmodel.ParamBatchTransModel;
import com.alipay.parmodel.ParamPlatformModel;
import com.alipay.util.*;
import com.alipay.util.httpClient.HttpRequest;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;

@Service
public class AliService implements IAliService {
	String AliPayNotifyKey = new Config().getConfig("AliPayNotifyKey");
	String AliPayNotifyRSA = new Config().getConfig("AliPayNotifyRSA");

	@Override
	public String NotifyKey(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, String> params = new HashMap<String, String>();

		// Map requestParams = request.getParameterMap();
		Map requestParams = null;
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		// 批量付款数据中转账成功的详细信息
		// AlipayBatchCallBackModel alipayBatchCallBackModel = new
		// AlipayBatchCallBackModel();
		// params.put("platform", "1");
		params.put("verify", "1");// key
		if (AlipayNotify.verify(params)) {// 验证成功
			return "fail";
		} else {
			return "fail";
		}
	}

	@Override
	public String NotifyRSA(HttpServletRequest request) {
		// Map<String, String> params = new HashMap<String, String>();
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		// 批量付款数据中转账成功的详细信息
		// AlipayBatchCallBackModel alipayBatchCallBackModel = new
		// AlipayBatchCallBackModel();
		// params.put("platform", "1");
		params.put("verify", "0");// rsa
		if (AlipayNotify.verify(params)) {// 验证成功
			// 这里调用接口，
			return "fail";
		} else {
			return "fail";
		}
	}

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
	public AlipayTradePrecreateResponse createOrder(ParamAliModel model)
			throws AlipayApiException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// String time_expire = sdf.format(System.currentTimeMillis() + 24 * 60
		// * 60 * 1000);
		AlipayTradePrecreateResponse response = null;
		// 使用SDK，调用交易下单接口
		// response = alipayClient.execute(request);
		response = ToAlipayQrTradePay.qrPay(model.getOut_trade_no(),
				model.getTotal_fee(), model.getSubject(), model.getPlatform());

		// 这里只是简单的打印，请开发者根据实际情况自行进行处理
		if (null != response && response.isSuccess()) {
			System.out.println(response.getBody());
			System.out.println(response.isSuccess());
			System.out.println(response.getMsg());
			if (response.getCode().equals("10000")) {
				System.out.println("商户订单号：" + response.getOutTradeNo());
				System.out.println("二维码值：" + response.getQrCode());// 商户将此二维码值生成二维码，然后展示给用户，用户用支付宝手机钱包扫码完成支付
				// 二维码的生成，网上有许多开源方法，可以参看：http://blog.csdn.net/feiyu84/article/details/9089497
			} else {
				// 打印错误码
				System.out.println("错误码：" + response.getSubCode());
				System.out.println("错误描述：" + response.getSubMsg());
			}
		} else {
			System.out.println("response==null");
		}
		return response;
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
