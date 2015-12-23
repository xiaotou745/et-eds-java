package com.edaisong.api_http.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.common.LogServiceBLL;
import com.edaisong.api_http.service.inter.IActiveMqService;
import com.edaisong.core.util.JsonUtil;
import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.domain.ActionLog;
@Service
public class ActiveMqService implements IActiveMqService{
	@Autowired
	LogServiceBLL logServiceBLL;
	@Override
	public HttpResultModel<String> sendMsg(String msg) {
		ActionLog logEngity =JsonUtil.str2obj(msg, ActionLog.class);
		logServiceBLL.SystemActionLog(logEngity);
		HttpResultModel<String> resultModel=new HttpResultModel<String>();
	    return resultModel;
	}
}
