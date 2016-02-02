package com.tencent.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tencent.model.PayResultModel;

//import com.tencent.qrcode.Data;

public class QrCodeService {
	private final static Logger logger = LoggerFactory
			.getLogger(QrCodeService.class);
	private final static int RIGHT_MOVE_STEP = 4;
	private final static int ARR_LEN_MUL = 2;
	private static final char hexDigits[] = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	private final static int CONNECT_TIMEOUT = 5000; // in milliseconds
	private final static String DEFAULT_ENCODING = "UTF-8";

	/**
	 * 作用：生成签名
	 */
	public static String getSign(Map<String, String> payApiMap, String key) {

		String string = "";

		string = formatBizQueryParaMap(payApiMap, false);

		// 签名步骤二：在string后加入KEY
		string = string + "&key=" + key;
		//
		// 签名步骤三：MD5加密
		logger.info("String for MD5: string: " + string);
		string = md5(string);

		// 签名步骤四：所有字符转为大写
		String result_ = string.toUpperCase();
		return result_;
	}

	/**
	 * 作用：格式化参数，签名过程需要使用
	 */
	public static String formatBizQueryParaMap(Map<String, String> paraMap,
			boolean urlencode) {
		String buff = "";
		List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(
				paraMap.entrySet());

		Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {
			public int compare(Map.Entry<String, String> o1,
					Map.Entry<String, String> o2) {
				return (o1.getKey()).toString().compareTo(o2.getKey());
			}
		});

		for (int i = 0; i < infoIds.size(); i++) {
			Map.Entry<String, String> item = infoIds.get(i);
			// System.out.println(item.getKey());
			if (item.getKey() != "") {

				String key = item.getKey();
				String val = item.getValue();
				if (urlencode) {
					try {
						val = URLEncoder.encode(val, "utf-8");
					} catch (UnsupportedEncodingException e) {
					}
				}
				buff = buff + key + "=" + val + "&";
			}
		}

		if (buff.isEmpty() == false) {
			buff = buff.substring(0, buff.length() - 1);
		}
		return buff;
	}

	/**
	 * 作用：array转xml
	 */
	public static String mapToXml(HashMap<String, String> arr) {
		String xml = "<xml>";

		Iterator<Entry<String, String>> iter = arr.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, String> entry = iter.next();
			String key = entry.getKey();
			String val = entry.getValue();
			if (IsNumeric(val)) {
				xml += "<" + key + ">" + val + "</" + key + ">";

			} else
				xml += "<" + key + "><![CDATA[" + val + "]]></" + key + ">";
		}
		xml += "</xml>";
		return xml;
	}

	public static String md5(String s) {
		return digest(s, "MD5");
	}

	private static String digest(String s, String algorithm) {
		if (s == null || s.length() <= 0)
			return s;

		try {
			byte[] strTemp = s.getBytes("UTF-8");
			MessageDigest mdTemp = MessageDigest.getInstance(algorithm);
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * ARR_LEN_MUL];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> RIGHT_MOVE_STEP & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 是否为数字
	 * */
	public static boolean IsNumeric(String str) {
		try {
			Integer.parseInt(str);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static String GetPrepayId(String xmlData) {
		String resXml = postData(
				"https://api.mch.weixin.qq.com/pay/unifiedorder", xmlData);
		Document dd = null;
		try {
			dd = DocumentHelper.parseText(resXml);
		} catch (DocumentException e) {
			return "";
		}
		if (dd == null || dd.getRootElement() == null) {
			return "";
		}

		Element root = dd.getRootElement();
		Element ElementPrepay_id = root.element("prepay_id");
		if (ElementPrepay_id == null) {
			return "";
		}
		String prepay_id = ElementPrepay_id.getText();
		System.out.println(prepay_id);
		return prepay_id;
	}

	public static PayResultModel getCodeUrl(String xmlData) {
		PayResultModel model = new PayResultModel();
		String resXml = postData(
				"https://api.mch.weixin.qq.com/pay/unifiedorder", xmlData);
		Document dd = null;
		try {
			dd = DocumentHelper.parseText(resXml);
		} catch (DocumentException e) {
			return model;
		}
		if (dd != null) {
			Element root = dd.getRootElement();
			if (root == null) {
				return model;
			}
			Element codeUrl = root.element("code_url");
			Element prepay_id = root.element("prepay_id");
			if (codeUrl == null || prepay_id == null) {
				return model;
			}
			model.setQr_code(codeUrl.getText());// 解析 xml 获得 code_url
			model.setPrepay_id(prepay_id.getText()); // 获取prepay_id
		}
		return model;
	}

	/**
	 * 取消订单
	 * */
	public static String CloseOrder(String xmlData) {
		String resXml = postData(
				"https://api.mch.weixin.qq.com/pay/closeorder", xmlData);
		Document dd = null;
		try {
			dd = DocumentHelper.parseText(resXml);
		} catch (DocumentException e) {
			return "FAIL";
		}
		if (dd == null || dd.getRootElement() == null) {
			return "FAIL";
		}

		Element root = dd.getRootElement();
		Element ElementReturn_code = root.element("return_code");
		if (ElementReturn_code == null) {
			return "FAIL";
		}
		String return_code = ElementReturn_code.getText();
		System.out.println(return_code);
		return return_code;
	}

	public static String postData(String urlStr, String data) {
		return postData(urlStr, data, null);
	}

	public static String postData(String urlStr, String data, String contentType) {
		BufferedReader reader = null;
		try {
			URL url = new URL(urlStr);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			conn.setConnectTimeout(CONNECT_TIMEOUT);
			conn.setReadTimeout(CONNECT_TIMEOUT);
			if (StringUtils.isNotBlank(contentType))
				conn.setRequestProperty("content-type", contentType);
			OutputStreamWriter writer = new OutputStreamWriter(
					conn.getOutputStream(), DEFAULT_ENCODING);
			if (data == null)
				data = "";
			writer.write(data);
			writer.flush();
			writer.close();

			reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), DEFAULT_ENCODING));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
				sb.append("\r\n");
			}
			return sb.toString();
		} catch (IOException e) {
			logger.error("Error connecting to " + urlStr + ": "
					+ e.getMessage());
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (IOException e) {
			}
		}
		return null;
	}
}
