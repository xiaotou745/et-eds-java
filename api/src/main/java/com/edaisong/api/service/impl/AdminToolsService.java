package com.edaisong.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dal.dao.inter.IGlobalConfigDao;
import com.edaisong.api.service.inter.IAdminToolsService;
import com.edaisong.entity.domain.GlobalConfigModel;
import com.edaisong.entity.param.ConfigSavePram;
/*
 * 管理员工具 
 * */
@Service
public class AdminToolsService implements IAdminToolsService {
	@Autowired
	private IGlobalConfigDao iGlobalConfigDao ;
	/*
	 * 获取全局配置变量
	 * 茹化肖
	 * 2015年7月20日17:48:31
	 * */
	@Override
	public List<GlobalConfigModel> getGlobalConfigByGroupId(Integer id) {
		// TODO Auto-generated method stub
		return iGlobalConfigDao.getGlobalConfigByGroupId(id);
	}
	/*
	 * 修改全局变量参数
	 * */
	@Override
	public Boolean saveConfig(ConfigSavePram par) {
		// TODO Auto-generated method stub
		return iGlobalConfigDao.saveConfig(par);
	}

}