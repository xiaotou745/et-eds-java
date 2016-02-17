package com.edaisong.api_http.service.impl;

import org.springframework.stereotype.Service;

import com.edaisong.api_http.common.QuartzServiceHelper;
import com.edaisong.api_http.service.inter.IHttpQuartzService;
import com.edaisong.entity.common.HttpResultModel;

@Service
public class HttpQuartzService implements IHttpQuartzService {
	@Override
	public HttpResultModel<String> test() {
		return QuartzServiceHelper.doSend("testDouService");
	}
	
}
