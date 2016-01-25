package com.eds.service.impl;

import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alipay.util.AlipayNotify;
import com.tencent.common.Util;
import com.tencent.service.NotifyService;

@Controller
@RequestMapping("wxnotifyservice")
public class WxNotifyService {

	@RequestMapping("ttest")
	public void test(HttpServletRequest request, HttpServletResponse response) {
		// Util.GenerateNonceStr();
	}

	/**
	 * 微信回调
	 * */
	@RequestMapping("wxpaynotify")
	public void NotifyKey(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String result = null;
		NotifyService notify = new NotifyService();
		notify.xwPayNotify(request, response);

		OutputStream outputStream = response.getOutputStream();
		byte[] dataByteArr = result.getBytes("UTF-8");
		outputStream.write(dataByteArr);
	}
}
