package com.edaisong.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IQuartzServiceDao;
import com.edaisong.api.service.inter.IQuartzService;
import com.edaisong.core.util.QuartzManager;
import com.edaisong.entity.QuartzServiceModel;

@Service
public class QuartzService implements IQuartzService {
	@Autowired
	IQuartzServiceDao quartzServiceDao;

	/**
	 * @author haichao
	 * @date 2015年12月10日 10:15:58 获取所有服务列表
	 * */
	@Override
	public List<QuartzServiceModel> query() {
		return quartzServiceDao.query();
	}

	/**
	 * @author haichao
	 * @date 2015年12月10日 10:18:44 修改服务状态
	 * */
	@Override
	public int updateStatus(int id, int status) {
		return quartzServiceDao.updateStatus(id, status);
	}
	/**
	 * @author haichao
	 * @date 2015年12月10日 10:18:44 设置启动关闭服务
	 * */
	@Override
	public int mainJob(int id, int status) {
		String jobName="testJobName";
		if(status==1){
			//启动
			QuartzManager.addJob(jobName, com.edaisong.api.quartzservice.TestJob.class, "0/1 * * * * ?");// 如果库里是开启，本地没开启就新增服务
		}
		else {
			//停止
			QuartzManager.removeJob(jobName);
		}
		return updateStatus(id,status);
	}

}
