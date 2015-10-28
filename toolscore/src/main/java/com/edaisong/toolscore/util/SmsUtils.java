package com.edaisong.toolscore.util;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class SmsUtils {
	/**
	 * 发送短信 2015年9月28日 13:12:08 窦海超 Mobile 手机号码，多个号码‘,’号隔开 Content 发送内容
	 * **/
	public long testSendSms() {
		try {
			return sendSMS("13426401627", "您的验证码为：1234");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 发送短信 2015年9月28日 13:12:08
		@author haichao
		@param Mobile 手机号码，多个号码‘,’号隔开 
		@param Content 发送内容,如果是验证码可以用{num}直接传参，会产生6位随机数
	 * **/
	public static long sendSMS(String Mobile, String Content)
			throws MalformedURLException, UnsupportedEncodingException {
		URL url = null;
		String CorpID = "BJZX01876";// 账户名
		String Pwd = "123456";// 密码
		String send_content = URLEncoder.encode(
				Content.replaceAll("<br/>", ""), "utf-8");// 发送内容
		String surl="http://api.bjszrk.com/sdk/BatchSend.aspx?CorpID="
				+ CorpID + "&Pwd=" + Pwd + "&Mobile=" + Mobile + "&Content="
				+ send_content + "%e3%80%90%e9%a5%bf%e5%8a%bf%e5%8a%9b%e3%80%91&encode=utf-8";
		System.out.print(surl);
		url = new URL(surl);
		BufferedReader in = null;
		long inputLine = 0;
		try {
			System.out.println("开始发送短信手机号码为 ：" + Mobile);
			in = new BufferedReader(new InputStreamReader(url.openStream()));
			inputLine = new Long(in.readLine()).longValue();
		} catch (Exception e) {
			System.out.println("网络异常,发送短信失败！");
			inputLine = -2;
		}
		System.out.println("结束发送短信返回值：  " + inputLine);
		return inputLine;
	}
	/*
	 * public long httpPost(){ HttpClient client = new HttpClient(); PostMethod
	 * post = new PostMethod("http://api.bjszrk.com/sdk/BatchSend.aspx");
	 * post.addRequestHeader
	 * ("Content-Type","application/x-www-form-urlencoded;charset=utf-8"
	 * );//在头文件中设置转码 NameValuePair[] data ={ new NameValuePair("CorpID",
	 * "访问接口账户id"), new NameValuePair("Pwd", "访问接口账户密码"), new
	 * NameValuePair("Mobile","下发目的手机号码"), new
	 * NameValuePair("Content","下发短信内容"), new NameValuePair("Cell","扩展号"), new
	 * NameValuePair("SendTime ","下发时间") }; post.setRequestBody(data);
	 * client.executeMethod(post); Header[] headers = post.getResponseHeaders();
	 * int statusCode = post.getStatusCode();
	 * System.out.println("statusCode:"+statusCode); for(Header h : headers){
	 * System.out.println(h.toString()); } String result = new
	 * String(post.getResponseBodyAsString().getBytes("utf-8"));
	 * System.out.println(result); post.releaseConnection();
	 * 
	 * }
	 */
}
