package com.edaisong.api.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.ITaskDistributionConfigDao;
import com.edaisong.entity.TaskDistributionConfig;

@Repository
public class TaskDistributionConfigDao extends DaoBase implements ITaskDistributionConfigDao {

	/**
	 * @author haichao
	 * @date 2015年11月24日 17:18:47
	 * 普通任务配送费配置
	 * */
	@Override
	public List<TaskDistributionConfig> query() {
		return getReadOnlySqlSessionUtil().selectList("com.edaisong.api.dao.inter.ITaskDistributionConfigDao.query");
	}

	/**
	 * @author haichao
	 * @date 2015年11月25日 11:44:04
	 * 更新普通任务配送费配置
	 * */
	@Override
	public int update(TaskDistributionConfig req) {
		return getMasterSqlSessionUtil().update("com.edaisong.api.dao.inter.ITaskDistributionConfigDao.update",req);
	}

}
