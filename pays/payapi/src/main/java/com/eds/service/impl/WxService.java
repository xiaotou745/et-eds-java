package com.eds.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eds.common.Config;
import com.eds.service.inter.IWxService;
import com.google.common.collect.Maps;
import com.tencent.common.Configure_;
import com.tencent.common.RandomStringGenerator;
import com.tencent.common.Signature;
import com.tencent.common.Util;
import com.tencent.model.PayParamModel;
import com.tencent.model.PayResultModel;
import com.tencent.parmodel.ParamPlatformModel;
import com.tencent.parmodel.PlatformModel;
import com.tencent.protocol.pay_protocol.ScanPayReqData;
import com.tencent.protocol.pay_query_protocol.ScanPayQueryReqData;
import com.tencent.protocol.refund_protocol.RefundReqData;
import com.tencent.service.QrCodeService;
import com.tencent.service.RefundService;
import com.tencent.service.ScanPayQueryService;
import com.tencent.service.ScanPayService;

@Service
public class WxService implements IWxService {
	String WxPayNotify = new Config().getConfig("WxPayNotify");

	/**
	 * APP支付
	 * */
	@Override
	public String GetAppUrl(PayParamModel model) throws Exception {
		HashMap<String, String> paramMap = Maps.newHashMap();
		ParamPlatformModel paramPlatformModel = new PlatformModel()
				.getPlayformModel(model.getPlatform());
		paramMap.put("trade_type", "APP"); // 交易类型
		paramMap.put("spbill_create_ip", Util.localIp()); // 本机的Ip
		paramMap.put("product_id", model.getOrder_no()); // 商户根据自己业务传递的参数 必填
		paramMap.put("body", model.getBody()); // 描述
		paramMap.put("out_trade_no", model.getOrder_no()); // 商户 后台的贸易单号
		paramMap.put("total_fee", "" + model.getTotal_fee()); // 金额必须为整数 单位为分
		paramMap.put("notify_url", WxPayNotify); // 支付成功后，回调地址
		paramMap.put("appid", paramPlatformModel.getAppid()); // appid
		paramMap.put("mch_id", paramPlatformModel.getMchid()); // 商户号
		paramMap.put("nonce_str",
				RandomStringGenerator.getRandomStringByLength(32)); // 随机数
		String attach = model.getNotify() + "|" + model.getPlatform() + "^"
				+ model.getAttach();
		paramMap.put("attach", attach);

		paramMap.put("sign",
				QrCodeService.getSign(paramMap, paramPlatformModel.getKey()));// 根据微信签名规则，生成签名
		String xmlData = QrCodeService.mapToXml(paramMap);// 把参数转换成XML数据格式
		return QrCodeService.GetPrepayId(xmlData);
	}

	/**
	 * 扫码支付
	 * */
	@Override
	public PayResultModel GetQrUrl(PayParamModel model) throws Exception {
		HashMap<String, String> paramMap = Maps.newHashMap();
		ParamPlatformModel paramPlatformModel = new PlatformModel()
				.getPlayformModel(model.getPlatform());
		paramMap.put("trade_type", "NATIVE"); // 交易类型
		paramMap.put("spbill_create_ip", Util.localIp()); // 本机的Ip
		paramMap.put("product_id", model.getOrder_no()); // 商户根据自己业务传递的参数 必填
		paramMap.put("body", model.getBody()); // 描述
		paramMap.put("out_trade_no", model.getOrder_no()); // 商户 后台的贸易单号
		paramMap.put("total_fee", "" + model.getTotal_fee()); // 金额必须为整数 单位为分
		paramMap.put("notify_url", WxPayNotify); // 支付成功后，回调地址
		paramMap.put("appid", paramPlatformModel.getAppid()); // appid
		paramMap.put("mch_id", paramPlatformModel.getMchid()); // 商户号
		paramMap.put("nonce_str",
				RandomStringGenerator.getRandomStringByLength(32)); // 随机数
		String attach = model.getNotify() + "|" + model.getPlatform();
		paramMap.put("attach", attach);
		paramMap.put("sign",
				QrCodeService.getSign(paramMap, paramPlatformModel.getKey()));// 根据微信签名规则，生成签名
		String xmlData = QrCodeService.mapToXml(paramMap);// 把参数转换成XML数据格式
		return QrCodeService.getCodeUrl(xmlData);
	}

	/**
	 * 订单查询
	 * */
	@Override
	public void Query(String out_trade_no, int platform) throws Exception {
		// TODO Auto-generated method stub
		ScanPayQueryReqData scanPayQueryReqData = new ScanPayQueryReqData("",
				out_trade_no);
		String result = new ScanPayQueryService().request(scanPayQueryReqData);
		System.out.println(result);
	}

	/**
	 * 退款
	 * */
	@Override
	public void Refund(RefundReqData model, int platform) throws Exception {
		RefundReqData refundReqData = new RefundReqData(
				model.getTransaction_id(), model.getOut_trade_no(),
				model.getDevice_info(), model.getOut_refund_no(),
				model.getTotal_fee(), model.getRefund_fee(),
				model.getOp_user_id(), model.getRefund_fee_type());
		String result = new RefundService().request(refundReqData);
		System.out.println(result);
	}

	/**
	 * 关闭订单
	 * */
	@Override
	public String Close(String out_trade_no, int platform) throws Exception {

		HashMap<String, String> paramMap = Maps.newHashMap();
		ParamPlatformModel paramPlatformModel = new PlatformModel()
				.getPlayformModel(platform);
		paramMap.put("out_trade_no", out_trade_no); // 平台单号
		paramMap.put("appid", paramPlatformModel.getAppid()); // appid
		paramMap.put("mch_id", paramPlatformModel.getMchid()); // 商户号
		paramMap.put("nonce_str",
				RandomStringGenerator.getRandomStringByLength(32)); // 随机数
		paramMap.put("sign",
				QrCodeService.getSign(paramMap, paramPlatformModel.getKey()));// 根据微信签名规则，生成签名
		String xmlData = QrCodeService.mapToXml(paramMap);// 把参数转换成XML数据格式
		return QrCodeService.CloseOrder(xmlData);
	}

	/**
	 * 初始化SDK依赖的几个关键配置
	 * 
	 * @param key
	 *            签名算法需要用到的秘钥
	 * @param appID
	 *            公众账号ID
	 * @param mchID
	 *            商户ID
	 * @param sdbMchID
	 *            子商户ID，受理模式必填
	 * @param certLocalPath
	 *            HTTP证书在服务器中的路径，用来加载证书用
	 * @param certPassword
	 *            HTTP证书的密码，默认等于MCHID
	 */
	public static void initSDKConfiguration(String key, String appID,
			String mchID, String sdbMchID, String certLocalPath,
			String certPassword) {
		Configure_.setKey(key);
		Configure_.setAppID(appID);
		Configure_.setMchID(mchID);
		Configure_.setSubMchID(sdbMchID);
		Configure_.setCertLocalPath(certLocalPath);
		Configure_.setCertPassword(certPassword);
	}

}
