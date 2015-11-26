package com.edaisong.api.dao.inter;


import java.util.List;

import com.edaisong.entity.TaskDistributionConfig;

public interface ITaskDistributionConfigDao {
	/**
	 * @author haichao
	 * @date 2015年11月24日 17:18:47
	 * 普通任务配送费配置
	 * */
	List<TaskDistributionConfig> query();
	
	/**
	 * @author haichao
	 * @date 2015年11月25日 11:44:04
	 * 更新普通任务配送费配置
	 * */
	int update(TaskDistributionConfig req);
}
