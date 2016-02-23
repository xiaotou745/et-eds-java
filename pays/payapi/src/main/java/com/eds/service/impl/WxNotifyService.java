package com.eds.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edaisong.core.security.AES;
import com.eds.common.HttpUtil;
import com.tencent.service.NotifyService;

@Controller
@RequestMapping("wxnotifyservice")
public class WxNotifyService {

	@RequestMapping("te")
	public void test(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// String result = HttpUtil.sendPost(
		// "http://172.18.10.73:7178/pay/BusinessRechargeNotify",
		// "data=0RhDwHp+oHXvDl+wMNBs+5L1quLA5qkfACHj3g3J0R/4C7yy4g7pGAcu2IHq09gCuWGEjnKA7+/Bb0j/k/RZPQdfaz58sa0hDz3MedyC5pwkr+eVog/zgnzm76y6vii+ltyzSSW2lRb8+bLoGU0TJRtvi71inMwN2A7u/HPER6wtjflIVab2567fw5YHlljIyhjFHgNk+XsTg5XLtonQp0QzjOwY3GUkNYOFZZKZ0x2vSPfOkSjFmKeV8YKfJ0eQ63kCdZUrxfdU7lOuaXmWuqJT8MzpDyzauzhSdLUqkUvcMTf9LDQRa+FIlfyxjjjyl1QbWO//MD2U0IjPeiTF0GRiXO6CeMrheSNvepaR1jkPnHMh9hfhxiPdNt0IOyKxAT+am2Dw8Yk+SIIqbDnTcOrEI2O6YvbirLKcIzYWn3hJiI7ADLBf1LvZ1U325mRF6rOWIxR9AcC4GvsE/PsHT6/+WkQ2GJHvM7qB0TLGseS8A7cu4yjI/jjsepEKEXAK9o2jd7oWex2kOS5JvHM1NFp4oQ1e5Aw3SazyHshc0kHpHQ94/WRm51l4cZENbYdcibymhZfAl2uM9Wy4RqDe1L6kgt5w3ibBQglL+TDu13ZwfN1A8X4frgVeNV2x17rpD1IXu7atThkNqjpwPg/1CYvcExKabUemYrt57zWnAqUUPYzoQzJHOvbAYLVzvWfP");//
		// .sendPost(notifyUrl,"data="+jsonStr)
		// System.out.println(result);
		String outString = "success";
		OutputStream outputStream;
		try {
			outputStream = response.getOutputStream();
			byte[] dataByteArr = outString.getBytes("UTF-8");
			outputStream.write(dataByteArr);
		} catch (IOException e1) { 
			e1.printStackTrace();
		} 
	}

	/**
	 * 微信回调
	 * */
	@RequestMapping("wxpaynotify")
	public void WxpayNotify(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String result = "fail";
		NotifyService notify = new NotifyService();
		Map<String, Object> map = notify.xwPayNotify(request);

		if (map != null) {
			// 这里去调用
			// result = "success";
			String attach = map.get("attach").toString();
			String notifyUrl = attach.split("\\|")[0].toString();// 业务上的回调
			
			String attachBase=attach.split("\\^")[1].toString();//业务上的扩展数据
			map.remove("attach");
			map.put("attach", attachBase);
			JSONArray json = JSONArray.fromObject(map);
			System.out.println(json.toString());
			String jsonStr = AES.aesEncrypt(json.toString());
			// String jsonStr = json.toString();
			result = HttpUtil.sendPost(notifyUrl, "data=" + jsonStr);// .sendPost(notifyUrl,"data="+jsonStr)
			System.out.println(result);
		}
		OutputStream outputStream = response.getOutputStream();
		byte[] dataByteArr = result.getBytes("UTF-8");
		outputStream.write(dataByteArr);
	}
}
