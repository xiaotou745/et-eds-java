package com.tencent.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import net.sf.json.JSONObject;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.xml.sax.SAXException;

import com.tencent.common.XMLParser;

import java.util.*;

public class NotifyService {
	public Map<String, Object> xwPayNotify(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		BufferedReader reader;
		reader = new BufferedReader(new InputStreamReader(
				request.getInputStream(), "utf-8"));
		StringBuffer sb = new StringBuffer();
		String line = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line).append("\r\n");
		}
		// String notifyXml = ;
		// notifyXml.
		Map<String, Object> map = XMLParser.getMapFromXML(sb.toString());

		System.out.println(map.get("return_code"));
		String return_cdoe = (String) map.get("return_code");
		if (!return_cdoe.toUpperCase().equals("SUCCESS")) {
			return null;
		}
		// 这里还要验证一下sign

		// 如果没问题再返回map
		return map;
	}

}
