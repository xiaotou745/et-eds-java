package com.edaisong.api.service.impl;

import com.edaisong.api.service.inter.IAdminToolsService;
import com.edaisong.entity.domain.GlobalConfigModel;
/*
 * 管理员工具 
 * */
public class AdminToolsService implements IAdminToolsService {
	/*
	 * 获取全局配置变量
	 * 茹化肖
	 * 2015年7月20日17:48:31
	 * */
	@Override
	public GlobalConfigModel getGlobalConfigByGroupId(Integer id) {
		// TODO Auto-generated method stub
		return new GlobalConfigModel();
	}

}