package com.edaisong.api_http.common;

import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.jaxrs.impl.ResponseImpl;
import org.apache.cxf.message.Exchange;
import org.apache.cxf.message.Message;
import org.apache.cxf.message.MessageContentsList;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

import javax.servlet.http.HttpServletRequest;

import java.lang.reflect.Method;

import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.springframework.beans.factory.annotation.Autowired;

import com.edaisong.api.common.LogServiceBLL;
import com.edaisong.core.enums.returnenums.HttpReturnRnums;
import com.edaisong.core.util.JsonUtil;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.core.util.SystemUtils;
import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.domain.ActionLog;

public class RqeustInterceptor extends AbstractPhaseInterceptor<Message> {
	@Autowired
	LogServiceBLL logServiceBLL;

	public RqeustInterceptor(String phase) {
		super(phase);
	}

	public RqeustInterceptor() {
		super(Phase.PRE_STREAM);
	}

	/**
	 * <请求拦截器> 创 建 人: zhaohailong 创建时间: 20151020
	 * 
	 * @param arg0
	 * @throws Fault
	 */
	public void handleMessage(Message message) throws Fault {
		// for (String iterable_element : message.keySet()) {
		// System.out.println("message------" + iterable_element + ":"+
		// message.get(iterable_element));
		// }
		// Exchange exchange = message.getExchange();
		// for (String iterable_element : exchange.keySet()) {
		// System.out.println("exchange------" + iterable_element + ":"+
		// exchange.get(iterable_element));
		// }
		// Message inputmsgMessage = message.getExchange().getInMessage();
		// for (String iterable_element : inputmsgMessage.keySet()) {
		// System.out.println("InMessage------" + iterable_element + ":"+
		// inputmsgMessage.get(iterable_element));
		// }
		// HttpServletResponse reponse = (HttpServletResponse)
		// message.get(AbstractHTTPDestination.HTTP_RESPONSE);//这句可以获取到request
		// final ByteArrayOutputStream os =
		// message.getContent(ByteArrayOutputStream.class);
		// String result = "";
		//
		// HttpResultModel<String> error =
		// JsonUtil.str2obj(result,HttpResultModel.class);
		try {
			Exchange exchange = message.getExchange();
			Message inMessage = exchange.getInMessage();
			Message outMessage = exchange.getOutMessage();
	
			String exceptionMsg = "";
			String stackTrace = "";
			String resultJson = "";
			MessageContentsList messageContentsList = (MessageContentsList) outMessage.getContent(List.class);
			//系统异常时，返回值的类型为ResponseImpl
			if (messageContentsList.get(0) instanceof ResponseImpl) {
				Object responseEntity=((ResponseImpl)messageContentsList.get(0)).getEntity();
				if (responseEntity instanceof HttpResultModel) {
					HttpResultModel<Object> res = (HttpResultModel<Object>)responseEntity;
					if (res.getStatus() == HttpReturnRnums.SystemError.value()) {
						exceptionMsg = res.getMessage();
						stackTrace = (String) res.getResult();
					}
				}
			}else if (messageContentsList.get(0) instanceof HttpResultModel) {
				//正常返回时，返回值的类型为HttpResultModel<Object>)
				//此时需要记录返回值的json
				HttpResultModel<Object> res = (HttpResultModel<Object>)messageContentsList.get(0);
				resultJson = JsonUtil.obj2string(res);
			}
	
			TreeMap header = (TreeMap) inMessage.get(Message.PROTOCOL_HEADERS);
			String contentType = (String) inMessage.get(Message.CONTENT_TYPE);
			String httpRequestMethod = (String) inMessage.get(Message.HTTP_REQUEST_METHOD);
			String url = (String) inMessage.get(Message.REQUEST_URL);
			Method methodName = (Method) inMessage.get("org.apache.cxf.resource.method");
	
			HttpServletRequest request = (HttpServletRequest) inMessage.get(AbstractHTTPDestination.HTTP_REQUEST);// 这句可以获取到request
			String clientIp = SystemUtils.getClientIp(request);
			
			List<String> ipinfoList = SystemUtils.getLocalIpInfo();
			String appServerIP = JsonUtil.obj2string(ipinfoList);
	
			Date endDate = new Date();
			Date requestTime = (Date) exchange.get("requestTime");
			String param = (String) exchange.get("encryptMsg");
			String decryptMsg = (String) exchange.get("decryptMsg");
			
			ActionLog logEngity = new ActionLog();
			logEngity.setUserID(-1);
			logEngity.setUserName("");
			logEngity.setRequestType(0);
			logEngity.setClientIp(clientIp);
			logEngity.setSourceSys("apihttp");
			logEngity.setRequestUrl(url);
			logEngity.setParam(param);
			logEngity.setDecryptMsg(decryptMsg);
			logEngity.setContentType(contentType);
			logEngity.setHeader(header.toString());
			logEngity.setRequestMethod(httpRequestMethod);
			logEngity.setMethodName(methodName.toString());
			logEngity.setResultJson(resultJson);
			logEngity.setAppServer(appServerIP);
			logEngity.setException(exceptionMsg);
			logEngity.setStackTrace(stackTrace);
			logEngity.setExecuteTime(endDate.getTime() - requestTime.getTime());
			logEngity.setRequestTime(ParseHelper.ToDateString(requestTime, ""));
			logEngity.setRequestEndTime(ParseHelper.ToDateString(endDate, ""));
			logServiceBLL.SystemActionLog(logEngity);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
