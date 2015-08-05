package com.edaisong.api_http.service;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

public class TestClient {
	public static void main(String[] args) throws HttpException, IOException {
		String url = "http://localhost:8080/api-http/services/urlService/getlist.xml";
		HttpClient client = new HttpClient();
		//HttpMethod method = getMethod(url, "");
		 HttpMethod method=postMethod(url);
		int statusCode = client.executeMethod(method);
		if (statusCode != HttpStatus.SC_OK) {
			System.err.println("Method failed: " + method.getStatusLine());
		}
		byte[] responseBody = method.getResponseBody();
		String msg = new String(responseBody);
		System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzz"+msg);
		System.exit(0);
	}

	private static HttpMethod getMethod(String url, String param)
			throws IOException {
		String d = url;
		if (!(param == null || param.equals(""))) {
			d = url + "?" + param;
		}
		GetMethod get = new GetMethod(d);
		get.releaseConnection();
		return get;
	}

	@SuppressWarnings("deprecation")
	private static HttpMethod postMethod(String url) {
		PostMethod post = new PostMethod(url);
//		post.setRequestHeader("Content-Type", "application/xml;charset=gbk");
//		String parm="<testServiceReq><recordType>9</recordType><operateTime>2015-7-21</operateTime></testServiceReq>";
		post.setRequestHeader("Content-Type", "application/json;charset=gbk");
		String parm="{\"recordType\":9,\"operateTime\":\"2015-1-1\"}";
//		NameValuePair[] param = { new NameValuePair(
//				"",
//				parm) };
//		post.setRequestBody(param);
		post.setRequestBody(parm);
		//post.setParameter("", parm);
		return post;
	}
}
