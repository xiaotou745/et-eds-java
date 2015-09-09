package com.edaisong.api_http.common;

import java.util.List;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Element;

public class AuthInterceptor extends AbstractPhaseInterceptor<SoapMessage> {
	public AuthInterceptor(String phase) {
		super(phase);
	}

	public AuthInterceptor() {
		super(Phase.PRE_INVOKE);
	}

	public void handleMessage(SoapMessage message) throws Fault {
		List<Header> headers = message.getHeaders();
		if (headers == null || headers.size() == 0) {
			throw new Fault(new IllegalAccessException("没有认证信息"));
		}
		Header header = headers.get(0);
		System.out.println(header.getObject().getClass()+ "******************************");
		Element element = (Element) header.getObject();
		String username = element.getElementsByTagName("username").item(0).getNodeValue();
		String password = element.getElementsByTagName("password").item(0).getNodeValue();
		System.out.println("***************************************************");
		if ("admin".equals(username) && "123456".equals(password)) {
			System.out.println("===========处理其他的业务逻辑 ： 扣费===========");
		} else {
			throw new Fault(new RuntimeException("访问服务的用户名 或密码错误...."));
		}
	}
}
