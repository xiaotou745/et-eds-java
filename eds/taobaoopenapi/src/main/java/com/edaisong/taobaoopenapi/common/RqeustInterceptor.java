package com.edaisong.taobaoopenapi.common;

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
import com.edaisong.core.util.JsonUtil;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.core.util.StringUtils;
import com.edaisong.core.util.SystemUtils;
import com.edaisong.entity.domain.ActionLog;
import com.edaisong.entity.taobao.TaoBaoResponseBase;


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
		try {
			Exchange exchange = message.getExchange();
			Message inMessage = exchange.getInMessage();
			Message outMessage = exchange.getOutMessage();
			Method methodName = (Method) inMessage.get("org.apache.cxf.resource.method");
			if(methodName==null){
				return;
			}
	
			String exceptionMsg = "";
			String stackTrace = "";
			String resultJson = "";
			MessageContentsList messageContentsList = (MessageContentsList) outMessage.getContent(List.class);
			//系统异常时，返回值的类型为ResponseImpl
			if (messageContentsList.get(0) instanceof ResponseImpl) {
				Object responseEntity=((ResponseImpl)messageContentsList.get(0)).getEntity();
				if (responseEntity instanceof TaoBaoResponseBase) {
					TaoBaoResponseBase res = (TaoBaoResponseBase)responseEntity;
					if (res.getError_msg()!=null&&!res.getError_msg().isEmpty()) {
						exceptionMsg = res.getError_code();
						stackTrace = res.getError_msg();
					}
				}
			}else if (messageContentsList.get(0) instanceof TaoBaoResponseBase) {
				//正常返回时，返回值的类型为TaoBaoResponseBase
				//此时需要记录返回值的json
				TaoBaoResponseBase res = (TaoBaoResponseBase)messageContentsList.get(0);
				try {
					resultJson = JsonUtil.obj2string(res);
				} catch (Exception e) {
					exceptionMsg = e.getMessage();
					stackTrace = StringUtils.getStackTrace(e);
					
					TaoBaoResponseBase rep=new TaoBaoResponseBase();
					rep.setIs_success(false);
					rep.setError_code(exceptionMsg);
					rep.setError_msg(stackTrace);
					rep.setResult(false);
			        messageContentsList.remove(0);
			        messageContentsList.add(0, rep);
				}
			}
	
			TreeMap header = (TreeMap) inMessage.get(Message.PROTOCOL_HEADERS);
			String contentType = (String) inMessage.get(Message.CONTENT_TYPE);
			String httpRequestMethod = (String) inMessage.get(Message.HTTP_REQUEST_METHOD);
			String url = (String) inMessage.get(Message.REQUEST_URL);
		
	
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
			logEngity.setSourceSys("taobaoopenapi");
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
