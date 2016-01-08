/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.alipay.factory;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.config.EDSAlipayConfig;
import com.alipay.config.Platform;
import com.alipay.config.RRTAlipayConfig;
import com.alipay.constants.EDSAlipayServiceEnvConstants;
import com.alipay.constants.RRTAlipayServiceEnvConstants;
import com.alipay.parmodel.ParamPlatformModel;

/**
 * API调用客户端工厂
 * 
 * @author taixu.zqq
 * @version $Id: AlipayAPIClientFactory.java, v 0.1 2014年7月23日 下午5:07:45
 *          taixu.zqq Exp $
 */
public class AlipayAPIClientFactory {

	/** API调用客户端 */
	private static AlipayClient alipayEdsClient;

	private static AlipayClient alipayRRTClient;

	/**
	 * 获得API调用客户端
	 * 
	 * @return
	 */
	public static AlipayClient getAlipayClient(int platform) {

		if (platform == Platform.EDS.value()) {

			if (null == alipayEdsClient) {
				alipayEdsClient = new DefaultAlipayClient(
						EDSAlipayServiceEnvConstants.ALIPAY_GATEWAY,
						EDSAlipayServiceEnvConstants.APP_ID,
						EDSAlipayServiceEnvConstants.PRIVATE_KEY, "json",
						EDSAlipayServiceEnvConstants.CHARSET,
						EDSAlipayServiceEnvConstants.ALIPAY_PUBLIC_KEY);
			}
			return alipayEdsClient;
		}
		if (platform == Platform.RRT.value()) {
			if (null == alipayRRTClient) {
				alipayRRTClient = new DefaultAlipayClient(
						RRTAlipayServiceEnvConstants.ALIPAY_GATEWAY,
						RRTAlipayServiceEnvConstants.APP_ID,
						RRTAlipayServiceEnvConstants.PRIVATE_KEY, "json",
						RRTAlipayServiceEnvConstants.CHARSET,
						RRTAlipayServiceEnvConstants.ALIPAY_PUBLIC_KEY);
			}
			return alipayRRTClient;
		}
		return null;
	}
	
	/**
	 * 获取支付平台
	 * */
	public static ParamPlatformModel getPlatformModel(int platform){
		ParamPlatformModel paramPlatformModel=new ParamPlatformModel();
		if (platform==Platform.EDS.value()) {
			paramPlatformModel.setPartner(EDSAlipayConfig.partner);
			paramPlatformModel.setEmail(EDSAlipayConfig.email);
			paramPlatformModel.setKey(EDSAlipayConfig.key);
			paramPlatformModel.setAccount_name(EDSAlipayConfig.account_name);
			paramPlatformModel.setInput_charset(EDSAlipayConfig.input_charset);
			
			paramPlatformModel.setAlipay_public_key(EDSAlipayServiceEnvConstants.ALIPAY_PUBLIC_KEY);
			paramPlatformModel.setSign_charset(EDSAlipayServiceEnvConstants.SIGN_CHARSET);
			paramPlatformModel.setCharset(EDSAlipayServiceEnvConstants.CHARSET);
			paramPlatformModel.setSign_type(EDSAlipayServiceEnvConstants.SIGN_TYPE);
			paramPlatformModel.setApp_id(EDSAlipayServiceEnvConstants.APP_ID);
			paramPlatformModel.setPrivate_key(EDSAlipayServiceEnvConstants.PRIVATE_KEY);
			paramPlatformModel.setPublic_key(EDSAlipayServiceEnvConstants.PUBLIC_KEY);
			paramPlatformModel.setAlipay_gateway(EDSAlipayServiceEnvConstants.ALIPAY_GATEWAY);
			paramPlatformModel.setGrant_type(EDSAlipayServiceEnvConstants.GRANT_TYPE);
		}
		if (platform==Platform.RRT.value()) {
			paramPlatformModel.setPartner(RRTAlipayConfig.partner);
			paramPlatformModel.setEmail(RRTAlipayConfig.email);
			paramPlatformModel.setKey(RRTAlipayConfig.key);
			paramPlatformModel.setAccount_name(RRTAlipayConfig.account_name);
			paramPlatformModel.setInput_charset(RRTAlipayConfig.input_charset);
			
			paramPlatformModel.setAlipay_public_key(RRTAlipayServiceEnvConstants.ALIPAY_PUBLIC_KEY);
			paramPlatformModel.setSign_charset(RRTAlipayServiceEnvConstants.SIGN_CHARSET);
			paramPlatformModel.setCharset(RRTAlipayServiceEnvConstants.CHARSET);
			paramPlatformModel.setSign_type(RRTAlipayServiceEnvConstants.SIGN_TYPE);
			paramPlatformModel.setApp_id(RRTAlipayServiceEnvConstants.APP_ID);
			paramPlatformModel.setPrivate_key(RRTAlipayServiceEnvConstants.PRIVATE_KEY);
			paramPlatformModel.setPublic_key(RRTAlipayServiceEnvConstants.PUBLIC_KEY);
			paramPlatformModel.setAlipay_gateway(RRTAlipayServiceEnvConstants.ALIPAY_GATEWAY);
			paramPlatformModel.setGrant_type(RRTAlipayServiceEnvConstants.GRANT_TYPE);
		}
		return paramPlatformModel;
	} 
	
	
}
