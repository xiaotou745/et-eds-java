package com.eds.common;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Exchange;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.AbstractHTTPDestination;

import com.edaisong.core.security.AES;
import com.edaisong.core.util.JsonUtil;
import com.edaisong.core.util.PropertyUtils;
import com.edaisong.core.util.StreamUtils;
import com.edaisong.core.util.StringUtils;
import com.edaisong.entity.req.ParameterReq;

public class MsgInterceptor extends AbstractPhaseInterceptor<Message> {

	public MsgInterceptor() {
		// 接受参数时候调用
		super(Phase.RECEIVE);
	}

	// 解密数据
	@Override
	public void handleMessage(Message message) throws Fault {
		String encryptMsg = "";
		String decryptMsg = "";
		boolean needCheck=checkNeedEncrypt(message);
		try {
			InputStream inputStream = message.getContent(InputStream.class);
			String inputMsg = StreamUtils.copyToString(inputStream,Charset.forName("utf-8"));
			logCustomerInfo(message, encryptMsg, decryptMsg);
			if (inputMsg == null || inputMsg.isEmpty()) {
				return;
			}
			String interceptSwith = PropertyUtils.getProperty("InterceptSwith");// "1"//	
			if (interceptSwith.equals("1")&&needCheck) {
				System.out.println("已开启解密拦截器");
				if (inputMsg.indexOf("data") < 0) {
					throw new RuntimeException("应该传入加密后的入参！");
				}
				ParameterReq req = JsonUtil.str2obj(inputMsg,ParameterReq.class);
				encryptMsg = req.getData();
				decryptMsg = AES.aesDecrypt(StringUtils.trimRight(req.getData(), "\n"));// AES解密
			} else {
				encryptMsg = inputMsg;
				decryptMsg = inputMsg;
				System.out.println("暂未开启解密拦截器");
				if (inputMsg.indexOf("data") > 0&&needCheck) {
					throw new RuntimeException("应该传入未加密的入参");
				}
			}
			InputStream stream = StreamUtils.StringToInputStream(decryptMsg);
			message.setContent(InputStream.class, stream);// 回填流
		} catch (Exception e) {
			logCustomerInfo(message, encryptMsg, decryptMsg);
			throw new RuntimeException("处理入参时出错:" + e.getMessage());
		}

		System.out.println("未解密的入参:" + encryptMsg);
		System.out.println("解密后的入参:" + decryptMsg);
		logCustomerInfo(message, encryptMsg, decryptMsg);

		if (decryptMsg.indexOf("{") < 0 && decryptMsg.indexOf("}") < 0&&needCheck) {
			throw new RuntimeException("解密后的参数必须是json格式的数据");
		}
	}
	private boolean checkNeedEncrypt(Message message){
		//设置请求参数白名单
		HttpServletRequest request = (HttpServletRequest) message
				.get(AbstractHTTPDestination.HTTP_REQUEST);// 这句可以
		if (request.getRequestURI().contains("/aliservice/notifykey")) {
			return false;
		}
		return true;
	}
	/**
	 * 记录额外的信息，用于统计log（先删除，后添加）
	 * 
	 * @author hailongzhao
	 * @date 20151019
	 * @param message
	 * @param encryptMsg
	 * @param decryptMsg
	 */
	private void logCustomerInfo(Message message, String encryptMsg,
			String decryptMsg) {
		Exchange exchange = message.getExchange();
		if (exchange.containsKey("requestTime")) {
			exchange.remove("requestTime");
		}
		if (exchange.containsKey("encryptMsg")) {
			exchange.remove("encryptMsg");
		}
		if (exchange.containsKey("decryptMsg")) {
			exchange.remove("decryptMsg");
		}
		exchange.put("requestTime", new Date());
		exchange.put("encryptMsg", encryptMsg);
		exchange.put("decryptMsg", decryptMsg);
	}
}
