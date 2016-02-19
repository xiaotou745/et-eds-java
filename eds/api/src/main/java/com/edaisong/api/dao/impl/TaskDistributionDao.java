package com.edaisong.api.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.ITaskDistributionDao;
import com.edaisong.entity.TaskDistribution;
import com.edaisong.entity.TaskDistributionConfig;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.PagedTaskDistributionConfigReq;
import com.edaisong.entity.req.PagedTaskDistributionReq;

@Repository
public class TaskDistributionDao extends DaoBase implements ITaskDistributionDao {

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(TaskDistribution record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(TaskDistribution record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public TaskDistribution selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(TaskDistribution record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(TaskDistribution record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
    public PagedResponse<TaskDistribution> query(PagedTaskDistributionReq req)
    {
		PagedResponse<TaskDistribution> model = getReadOnlySqlSessionUtil()
				.selectPageList(
						"ITaskDistributionDao.query_Sp",
						req);
		return model;
    }
	

}
