package com.tencent.parmodel;

import com.tencent.common.Platform;

public class PlatformModel {
	public ParamPlatformModel getPlayformModel(int platform) {
		ParamPlatformModel model = new ParamPlatformModel();

		if (platform == Platform.EDSBApp.value()) {
			// 易代送商家端APP支付
			model.setKey("10852AFB8F4044D88F5A24E978BBC053");
			// 公众号里的私钥
			model.setAppsecret("c0cb93c7fb3f7322326b0c853f0cba46");
			// 微信分配的公众号ID（开通公众号之后可以获取到）
			// 易代送商家端扫码支付
			model.setAppID("wxbb8fb40942327ec6");
			// 微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）
			model.setMchID("1264102901");
			// HTTPS证书密码，默认密码等于商户号MCHID
			model.setCertPassword("1264102901");
			// HTTPS证书的本地路径
			// model.setCertLocalPath("G:\\project\\e代送众包版\\Eds.SuperMan\\SuperManWebApi\\cert\\apiclient_cert.p12");

		} else if (platform == Platform.EDSSSBApp.value()) {
			model.setKey("51c56b886b5be869567dd389b3e5d1d6");
			// 公众号里的私钥
			model.setAppsecret("d4624c36b6795d1d99dcf0547af5443d");
			// 微信分配的公众号ID（开通公众号之后可以获取到）
			// 易代送商家端扫码支付
			model.setAppID("wx5505d0e3b58607a6");
			// 微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）
			model.setMchID("1291102901");
			// HTTPS证书密码，默认密码等于商户号MCHID
			model.setCertPassword("1291102901");
		} else if (platform == Platform.EDSBQr.value()) {
			// 易代送商家端扫码支付
			// model.setAppID("wxb89ebba3cec98a8c");
			// // 微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）
			// model.setMchID("1243442302");
			// // HTTPS证书密码，默认密码等于商户号MCHID
			// model.setCertPassword("1233410002");
		} else if (platform == Platform.EDSCApp.value()) {
			// 易代送骑士端APP支付

		} else if (platform == Platform.EDSCQr.value()) {
			// 易代送骑士端扫码支付
		}
		return model;

	}
}
