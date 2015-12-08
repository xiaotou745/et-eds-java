package com.edaisong.api.dao.inter;

import java.util.List;

import com.edaisong.entity.QuartzServiceModel;

/**
 * @author haichao
 *
 */
public interface IQuartzServiceDao {
	
	/**
	 * 获取任务调度数据
	 * 窦海超
	 * 2015年12月4日 17:51:08
	 * */
	List<QuartzServiceModel> query();
}
