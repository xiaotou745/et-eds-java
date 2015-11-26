package com.edaisong.api.service.inter;

import java.util.List;

import com.edaisong.entity.TaskDistributionConfig;
import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.req.TaskDistributionConfigReq;
import com.edaisong.entity.resp.TaskDistributionConfigResp;

public interface ITaskDistributionConfigService {

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
	int update(TaskDistributionConfigReq req);
	
	
	/**
	* @author haichao
	* @date 2015年11月26日 09:54:08
	* 获取普通任务配送费配置
	* */
	HttpResultModel<TaskDistributionConfigResp> getTaskDistributionConfig();
}
