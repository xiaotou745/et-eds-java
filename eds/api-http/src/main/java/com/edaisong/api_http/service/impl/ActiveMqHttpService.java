package com.edaisong.api_http.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.common.LogServiceBLL;
import com.edaisong.api_http.service.inter.IActiveMqHttpService;
import com.edaisong.core.util.JsonUtil;
import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.domain.ActionLog;
@Service
public class ActiveMqHttpService implements IActiveMqHttpService{
	@Autowired
	LogServiceBLL logServiceBLL;
	@Override
	public HttpResultModel<String> sendMsg(ActionLog logEngity) {
		logServiceBLL.SystemActionLog(logEngity);
		HttpResultModel<String> resultModel=new HttpResultModel<String>();
	    return resultModel;
	}
}
