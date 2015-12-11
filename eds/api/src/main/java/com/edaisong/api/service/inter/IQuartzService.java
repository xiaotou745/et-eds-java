package com.edaisong.api.service.inter;

import java.util.List;

import com.edaisong.entity.QuartzServiceModel;

public interface IQuartzService {
	/**
	 * @author haichao
	 * @date 2015年12月10日 10:15:58 获取所有服务列表
	 * */
	List<QuartzServiceModel> query();

	/**
	 * @author haichao
	 * @date 2015年12月10日 10:18:44 修改服务状态
	 * */
	int updateStatus(int id ,int status);
	
	/**
	 * @author haichao
	 * @date 2015年12月10日 10:18:44 设置启动关闭服务
	 * */
	int mainJob(int id,int status);
}
