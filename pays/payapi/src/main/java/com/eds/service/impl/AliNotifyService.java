package com.eds.service.impl;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alipay.util.AlipayNotify;
import java.util.*;

@Controller
@RequestMapping("alinotifyservice")
public class AliNotifyService {

	/**
	 * key的方式回去调验证
	 * */
	@RequestMapping("notifykey")
	public void NotifyKey(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		Enumeration<String> b = request.getParameterNames();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
		}
		params.put("platform", "1");
		params.put("verify", "1");// key
		String result = "fail";
		if (AlipayNotify.verify(params)) {// 验证成功
			// return "fail";
			result = "fail";// 上线的时候返回success
		}
		OutputStream outputStream = response.getOutputStream();
		byte[] dataByteArr = result.getBytes("UTF-8");
		outputStream.write(dataByteArr);
	}

	/**
	 * rsa的方式回调验证
	 * */
	@RequestMapping("notifyrsa")
	public void NotifyRSA(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		Enumeration<String> b = request.getParameterNames();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
		}
		String body = params.get("body");
		JSONObject json = JSONObject.fromObject(body);

		params.put("verify", "0");// rsa
		params.put("platform", json.getString("platform"));
		String result = "fail";
		if (AlipayNotify.verify(params)) {// 验证成功
			// return "fail";
			// 这里去回调业务层数据
			String notifyUrl = json.getString("notifyUrl");

			result = "fail";// 上线的时候返回success
		}
		OutputStream outputStream = response.getOutputStream();
		byte[] dataByteArr = result.getBytes("UTF-8");
		outputStream.write(dataByteArr);
	}
}
