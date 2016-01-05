package com.edaisong.api.dao.inter;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.edaisong.entity.Feedback;
import com.edaisong.entity.OrderTip;
import com.edaisong.entity.TaskDistributionConfig;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.PagedTaskDistributionConfigReq;

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
	
	/**
	 * @author 胡灵波
	 * @date  2016年01月4日  11:44:04
	 * 获取配送费配置
	 * */	
    PagedResponse<TaskDistributionConfig> query(PagedTaskDistributionConfigReq req);
    
    int insertSelective(TaskDistributionConfig record);   

    
	TaskDistributionConfig selectByKM(int id, double km);
	
	
	TaskDistributionConfig selectByKG(int id,double kg);
	
    int deleteByPrimaryKey(Integer id);
	
}
