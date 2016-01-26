package com.eds.service.impl;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.eds.common.Config;
import com.eds.service.inter.IWxService;
import com.google.common.collect.Maps;
import com.tencent.common.Configure;
import com.tencent.common.RandomStringGenerator;
import com.tencent.common.Util;
import com.tencent.model.PayParamModel;
import com.tencent.model.PayResultModel;
import com.tencent.service.QrCodeService;

@Service
public class WxService implements IWxService {
	String WxPayNotify = new Config().getConfig("WxPayNotify");

	/**
	 * 扫码支付
	 * */
	@Override
	public PayResultModel GetPayUrl(PayParamModel model) throws Exception {
		HashMap<String, String> paramMap = Maps.newHashMap();
		paramMap.put("trade_type", "NATIVE"); // 交易类型
		paramMap.put("spbill_create_ip", Util.localIp()); // 本机的Ip
		paramMap.put("product_id", model.getOrder_no()); // 商户根据自己业务传递的参数 必填
		paramMap.put("body", model.getBody()); // 描述
		paramMap.put("out_trade_no", model.getOrder_no()); // 商户 后台的贸易单号
		paramMap.put("total_fee", "" + model.getTotal_fee()); // 金额必须为整数 单位为分
		paramMap.put("notify_url", WxPayNotify); // 支付成功后，回调地址
		paramMap.put("appid", Configure.getAppid()); // appid
		paramMap.put("mch_id", Configure.getMchid()); // 商户号
		paramMap.put("nonce_str",
				RandomStringGenerator.getRandomStringByLength(32)); // 随机数
		paramMap.put("sign",
				QrCodeService.getSign(paramMap, Configure.getKey()));// 根据微信签名规则，生成签名
		String xmlData = QrCodeService.mapToXml(paramMap);// 把参数转换成XML数据格式
		return QrCodeService.getCodeUrl(xmlData);
	}

}
