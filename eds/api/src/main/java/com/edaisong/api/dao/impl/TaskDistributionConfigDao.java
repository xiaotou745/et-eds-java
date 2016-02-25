package com.edaisong.api.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.ITaskDistributionConfigDao;
import com.edaisong.entity.OrderTip;
import com.edaisong.entity.TaskDistributionConfig;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.PagedTaskDistributionConfigReq;
import com.edaisong.entity.req.PagedOrderTipReq;

@Repository
public class TaskDistributionConfigDao extends DaoBase implements ITaskDistributionConfigDao {

	/**
	 * @author haichao
	 * @date 2015年11月24日 17:18:47
	 * 普通任务配送费配置
	 * */
	@Override
	public List<TaskDistributionConfig> query() {
		return getReadOnlySqlSessionUtil().selectList("ITaskDistributionConfigDao.query");
	}
	
	@Override
	public List<TaskDistributionConfig> queryByTaskDistributionId(int taskDistributionId) {
		return getReadOnlySqlSessionUtil().selectList("ITaskDistributionConfigDao.queryByTaskDistributionId",taskDistributionId);
	}
	/**
	 * @author haichao
	 * @date 2015年11月25日 11:44:04
	 * 更新普通任务配送费配置
	 * */
	@Override
	public int update(TaskDistributionConfig req) {
		return getMasterSqlSessionUtil().update("ITaskDistributionConfigDao.update",req);
	}
	
	@Override
    public PagedResponse<TaskDistributionConfig> query(PagedTaskDistributionConfigReq req)
    {
		PagedResponse<TaskDistributionConfig> model = getReadOnlySqlSessionUtil()
				.selectPageList(
						"ITaskDistributionConfigDao.query_Sp",
						req);
		return model;
    }
	
	public int insertSelective(TaskDistributionConfig record)
	{
		return getMasterSqlSessionUtil().insert(
				"ITaskDistributionConfigDao.insertSelective", record);
	}
	
	public TaskDistributionConfig selectByKM(int id, int taskDistributionId, double km)
	{
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id", id);
		paramMap.put("taskDistributionId", taskDistributionId);
		paramMap.put("kM", km);
		
		return getMasterSqlSessionUtil().selectOne(
				"ITaskDistributionConfigDao.selectByKM", paramMap);
	}
	
	public TaskDistributionConfig selectByKG(int id,int taskDistributionId,double kg)
	{
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id", id);
		paramMap.put("taskDistributionId", taskDistributionId);
		paramMap.put("kG", kg);
		
		return getMasterSqlSessionUtil().selectOne(
				"ITaskDistributionConfigDao.selectByKG", paramMap);
	}
	
	public int deleteByPrimaryKey(Integer id)
	{
		return getMasterSqlSessionUtil().delete(
				"ITaskDistributionConfigDao.deleteByPrimaryKey", id);
	}
	
	@Override
	public TaskDistributionConfig selectByPrimaryKey(int id)
	{
		return getReadOnlySqlSessionUtil().selectOne("ITaskDistributionConfigDao.selectByPrimaryKey",id);
	}
}
