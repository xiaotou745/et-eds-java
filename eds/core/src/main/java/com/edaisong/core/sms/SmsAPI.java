package com.edaisong.core.sms;

import java.net.URL;

import javax.xml.namespace.QName;

public class SmsAPI {
	private static final QName SERVICE_NAME = new QName("http://tempuri.org/",
			"Sms");
	static URL wsdlURL = Sms.WSDL_LOCATION;
	static Sms ss = new Sms(wsdlURL, SERVICE_NAME);
	static SmsSoap port = ss.getSmsSoap();

	private SmsAPI() {
	}

	//易淘短信
	public static String SendSms(String mobile, String content) {
		return port.speedSend(mobile, content, "易代送", 0, "RRDT");
	}
	

	//易淘短信
	public static String sendSmsSaveLogB2B(String mobile, String content) {
		return port.sendSmsSaveLogB2B(mobile/*手机号码*/, content/*信息内容*/, "易代送"
				/*短信来源*/, null/*餐厅ID（可以为null）*/, -2/*餐厅所属集团ID*/, 
				"YX"/*短信平台*/);
	}
	
	/**
	 * 易淘语音短信
	 * @param mobile
	 * @param content
	 * @return
	 */
	public static String sendSmsSaveLogNew(String mobile, String content) {
		return port.sendSmsSaveLogNew(mobile/*手机号码*/, content/*信息内容*/, "易代送"/*短信来源*/, null/*餐厅ID（可以为null）*/, true, -2/*餐厅所属集团ID*/);
	}
}
