package com.edaisong.api_http.common;

import java.io.InputStream;
import java.util.Date;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Exchange;
import org.apache.cxf.message.Message; 
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

import com.edaisong.core.security.AES;
import com.edaisong.core.util.JsonUtil;
import com.edaisong.core.util.PropertyUtils;
import com.edaisong.core.util.StreamUtils;
import com.edaisong.core.util.StringUtils;
import com.edaisong.entity.req.AesParameterReq;
public class AESInterceptor  extends AbstractPhaseInterceptor<Message> {

	public AESInterceptor() {
		//接受参数时候调用
		super(Phase.RECEIVE);
	}
	//解密数据
	@Override
	public void handleMessage(Message message) throws Fault {
		String encryptMsg="";
		String decryptMsg="";
		logCustomerInfo(message,encryptMsg,decryptMsg);
		InputStream mContentString = message.getContent(InputStream.class);
		String inputMsg = StreamUtils.copyToStringNoclose(mContentString);
		logCustomerInfo(message,inputMsg,decryptMsg);
		
		AesParameterReq req = JsonUtil.str2obj(inputMsg,AesParameterReq.class);
		encryptMsg=req.getData();
		decryptMsg=req.getData();
		logCustomerInfo(message,encryptMsg,decryptMsg);
		String interceptSwith =PropertyUtils.getProperty("InterceptSwith");//"1" 开启加密
		if(interceptSwith.equals("1"))
		{
			System.out.println("已开启AES解密拦截器");
			try {
				decryptMsg = AES.aesDecrypt(StringUtils.trimRight(req.getData(),"\n"));// AES解密
				logCustomerInfo(message,encryptMsg,decryptMsg);
				InputStream stream=StreamUtils.StringToInputStream(decryptMsg);
				message.setContent(InputStream.class, stream);//回填流
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		else
		{
			System.out.println("暂未开启AES解密拦截器");
		}
		System.out.println("未解密的入参:"+encryptMsg);
		System.out.println("解密后的入参:"+decryptMsg);
		logCustomerInfo(message,encryptMsg,decryptMsg);
	}
	/**
	 * 记录额外的信息，用于统计log（先删除，后添加）
	 * @author hailongzhao
	 * @date 20151019
	 * @param message
	 * @param encryptMsg
	 * @param decryptMsg
	 */
	private void logCustomerInfo(Message message,String encryptMsg,String decryptMsg){
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
