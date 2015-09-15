package com.edaisong.api_http.common;

import java.io.InputStream;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message; 
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

import com.edaisong.core.security.AES;
import com.edaisong.core.util.JsonUtil;
import com.edaisong.core.util.StreamUtils;
import com.edaisong.entity.req.AesParameterReq;
public class AESInterceptor  extends AbstractPhaseInterceptor<Message> {

	public AESInterceptor() {
		//接受参数时候调用
		super(Phase.RECEIVE);
	}
	//解密数据
	@Override
	public void handleMessage(Message message) throws Fault {
		if(false)//TODO 建议设置开关暂时不开启 
		{
			try {
				InputStream mContentString=message.getContent(InputStream.class);
				String string=StreamUtils.copyToStringNoclose(mContentString);
				AesParameterReq req=JsonUtil.str2obj(string, AesParameterReq.class);
				System.out.println(string);
				string=AES.aesDecrypt(req.getData());//AES解密
				InputStream stream=StreamUtils.StringToInputStream(string);
				message.setContent(InputStream.class, stream);//回填流
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
