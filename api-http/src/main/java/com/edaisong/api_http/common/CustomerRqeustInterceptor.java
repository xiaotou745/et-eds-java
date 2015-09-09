package com.edaisong.api_http.common;

import java.util.Collection;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Exchange;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.phase.PhaseInterceptor;
import org.apache.cxf.service.invoker.MethodDispatcher;
import org.apache.cxf.service.model.BindingOperationInfo;

import java.lang.reflect.Method;

import org.apache.cxf.service.Service;



import com.edaisong.core.util.StreamUtils;
import com.edaisong.core.util.StringUtils;

import java.io.InputStream;

public class CustomerRqeustInterceptor extends
		AbstractPhaseInterceptor<Message> {

	public CustomerRqeustInterceptor(String phase) {
		super(phase);
	}

	public CustomerRqeustInterceptor() {
		super(Phase.RECEIVE);
	}

	/**
	 * <请求拦截器> 创 建 人: zhaohailong 创建时间: 2012-9-28 下午02:34:07
	 * 
	 * @param arg0
	 * @throws Fault
	 * @see [类、类#方法、类#成员]
	 */
	public void handleMessage(Message message) throws Fault {
		InputStream is = message.getContent(InputStream.class);
		 if (is==null) {
			return;
		}
		String msg = is.toString();
		Exchange exchange = message.getExchange();
		BindingOperationInfo bop = exchange.get(BindingOperationInfo.class);
		MethodDispatcher md = (MethodDispatcher) exchange.get(Service.class)
				.get(MethodDispatcher.class.getName());
		if (md != null) {
			Method method = md.getMethod(bop);
			System.out.println("********method name:" + method.getName());
		}
		System.out.println("*********In****Helloworld******" + msg);
	}
	public void handleFault(Message message) {  

        Exception exeption=message.getContent(Exception.class);
        if (exeption!=null) {
            System.out.println("出错了啊"+exeption.getMessage());
            //logger.error(exeption.getMessage(),exeption);  
		}
    }  
}
