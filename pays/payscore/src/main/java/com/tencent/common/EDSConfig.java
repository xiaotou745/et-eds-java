package com.tencent.common;

public class EDSConfig {
	private static String key = "";

	// 微信分配的公众号ID（开通公众号之后可以获取到）
	private static String appID = "";

	// 微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）
	private static String mchID = "";

	// 受理模式下给子商户分配的子商户号
	private static String subMchID = "";

	// HTTPS证书的本地路径
	private static String certLocalPath = "";

	// HTTPS证书密码，默认密码等于商户号MCHID
	private static String certPassword = "";
}
