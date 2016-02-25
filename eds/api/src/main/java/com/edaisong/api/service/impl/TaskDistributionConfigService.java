package com.edaisong.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edaisong.api.common.TransactionalRuntimeException;
import com.edaisong.api.dao.inter.IBiDao;
import com.edaisong.api.dao.inter.IBusinessDao;
import com.edaisong.api.dao.inter.IOptLogDao;
import com.edaisong.api.dao.inter.ITaskDistributionConfigDao;
import com.edaisong.api.dao.inter.ITaskDistributionDao;
import com.edaisong.api.service.inter.ITaskDistributionConfigService;
import com.edaisong.core.enums.FlashPushOrderEnum;
import com.edaisong.core.enums.OptLogEnum;
import com.edaisong.core.enums.PublishOrderReturnEnum;
import com.edaisong.core.enums.TaskDistributionConfigEnum;
import com.edaisong.entity.OptLog;
import com.edaisong.entity.OrderTip;
import com.edaisong.entity.TaskDistribution;
import com.edaisong.entity.TaskDistributionConfig;
import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.BusinessModel;
import com.edaisong.entity.req.BusinessReq;
import com.edaisong.entity.req.PagedOrderTipReq;
import com.edaisong.entity.req.PagedTaskDistributionConfigReq;
import com.edaisong.entity.req.TaskDistributionConfigReq;
import com.edaisong.entity.resp.OrderResp;
import com.edaisong.entity.resp.TaskDistributionConfigResp;

@Service
public class TaskDistributionConfigService implements
		ITaskDistributionConfigService {

	@Autowired
	private ITaskDistributionDao taskDistributionDao;
	
	@Autowired
	private ITaskDistributionConfigDao taskDistributionConfigDao;

	@Autowired
	private IBusinessDao businessDao;
	
	@Autowired
	private IOptLogDao optLogDao;

	/**
	 * @author haichao
	 * @date 2015年11月26日 09:54:08
	 * 获取普通任务配送费配置
	 * */
	@Override
	public HttpResultModel<List<TaskDistributionConfig>> getTaskDistributionConfig(BusinessReq req) {
		HttpResultModel<List<TaskDistributionConfig>> resultModel=new HttpResultModel<List<TaskDistributionConfig>>();
		
		int taskDistributionId=1;
		if(req!=null && req.getBusinessId()!=null && req.getBusinessId()>0 )
		{
			BusinessModel businessModel = businessDao.getBusiness((long) req.getBusinessId());
			if(businessModel!=null)
			{
				taskDistributionId=businessModel.getTaskDistributionId();
				if(taskDistributionId==0)
					taskDistributionId=1;
			}
		}			
				
		TaskDistribution taskDistributionModel= taskDistributionDao.selectByPrimaryKey(taskDistributionId);

		List<TaskDistributionConfig> list= taskDistributionConfigDao.queryByTaskDistributionId(taskDistributionId);//获取所有配送费配置
		resultModel.setMessage(taskDistributionModel.getRemark());
		resultModel.setResult(list);
		return resultModel;
	}

	/**
	 * @author haichao
	 * @date 2015年11月24日 17:18:47 普通任务配送费配置
	 * */
	@Override
	public List<TaskDistributionConfig> query() {
		return taskDistributionConfigDao.query();
	}

	/**
	 * @author haichao
	 * @date 2015年11月25日 11:44:04 更新普通任务配送费配置
	 * */
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	@Override
	public int update(TaskDistributionConfigReq req) {
		try {
			TaskDistributionConfig conf = null;
			if (!req.getIsUpdate().contains("1")) {
				// 更新主
				conf = new TaskDistributionConfig();
				conf.setkM(req.getMasterKM());
				conf.setkG(req.getMasterKG());
				conf.setDistributionPrice(req.getMasterPrice());
				conf.setId(1);
				taskDistributionConfigDao.update(conf);
			} 
			if (!req.getIsUpdate().contains("2")) {
				//更新ID=2
				conf = new TaskDistributionConfig();
				conf.setId(2);
				conf.setkM(req.getOneKM());
				conf.setDistributionPrice(req.getOneDistributionPrice());
				taskDistributionConfigDao.update(conf);
			}
			if (!req.getIsUpdate().contains("3")) {
				//更新ID=3
				conf = new TaskDistributionConfig();
				conf.setId(3);
				conf.setkG(req.getTwoKG());
				conf.setDistributionPrice(req.getTwoDistributionPrice());
				taskDistributionConfigDao.update(conf);
			}
			// 写操作日志
			OptLog log = new OptLog();
			log.setOptId(req.getOptId());
			log.setOptName(req.getOptName());
			log.setUpdateValue(req.getLogMsg());
			log.setOptType(OptLogEnum.TaskDistributionConfig.value());
			optLogDao.insert(log);
			return 1;
		} catch (Exception e) {
			// e.printStackTrace();
//			throw new TransactionalRuntimeException("错误啦");
			return 0;
		}	
		
	

	}

	@Override
	public PagedResponse<TaskDistributionConfig> query(
			PagedTaskDistributionConfigReq req) {
		return taskDistributionConfigDao.query(req);		
	}

	@Override
	public HttpResultModel<TaskDistributionConfigResp> add(TaskDistributionConfig record) {
		
		HttpResultModel<TaskDistributionConfigResp> resp = new HttpResultModel<TaskDistributionConfigResp>();

		if(record.getIsMaster()==1)
		{
		
		}
		else
		{
			if(record.getkM()>0)
			{
				TaskDistributionConfig selectModel= taskDistributionConfigDao.selectByKM(0,record.getTaskDistributionId(), record.getkM());
				if(selectModel!=null)
				{
					resp.setStatus(TaskDistributionConfigEnum.KMErr.value());
					resp.setMessage(TaskDistributionConfigEnum.KMErr.desc());
					return resp;
				}
			}
			else
			{
				TaskDistributionConfig selectModel= taskDistributionConfigDao.selectByKG(0,record.getTaskDistributionId(), record.getkG());
				if(selectModel!=null)
				{
					resp.setStatus(TaskDistributionConfigEnum.KGErr.value());
					resp.setMessage(TaskDistributionConfigEnum.KGErr.desc());
					return resp;
				}
			}
		}
		taskDistributionConfigDao.insertSelective(record);
		resp.setStatus(TaskDistributionConfigEnum.Success.value());
		resp.setMessage(TaskDistributionConfigEnum.Success.desc());		
		return resp;
	}
	
	@Override
	public  HttpResultModel<TaskDistributionConfigResp> modify(TaskDistributionConfig record)
	{
		HttpResultModel<TaskDistributionConfigResp> resp = new HttpResultModel<TaskDistributionConfigResp>();

		
		if(record.getIsMaster()==0 &&record.getkM()>0)
		{
			TaskDistributionConfig selectModel= taskDistributionConfigDao.selectByKM(record.getId(),record.getTaskDistributionId(), record.getkM());
			if(selectModel!=null)
			{
				resp.setStatus(TaskDistributionConfigEnum.KMErr.value());
				resp.setMessage(TaskDistributionConfigEnum.KMErr.desc());
				return resp;
			}
		}
		else if(record.getIsMaster()==0 &&record.getkM()==0)
		{
			TaskDistributionConfig selectModel= taskDistributionConfigDao.selectByKG(record.getId(),record.getTaskDistributionId(), record.getkG());
			if(selectModel!=null)
			{
				resp.setStatus(TaskDistributionConfigEnum.KGErr.value());
				resp.setMessage(TaskDistributionConfigEnum.KGErr.desc());
				return resp;
			}
		}
		taskDistributionConfigDao.update(record);
		resp.setStatus(TaskDistributionConfigEnum.Success.value());
		resp.setMessage(TaskDistributionConfigEnum.Success.desc());		
		return resp;
	}
	
	public TaskDistributionConfig selectByKM(int id, int taskDistributionId, double km)
	{
		return taskDistributionConfigDao.selectByKM(id,taskDistributionId,km);	
	}
	
	public TaskDistributionConfig selectByKG(int id, int taskDistributionId,double kg)
	{
		return taskDistributionConfigDao.selectByKG(id,taskDistributionId,kg);	
	}
    
	public int deleteByPrimaryKey(Integer id)
	{
		return taskDistributionConfigDao.deleteByPrimaryKey(id);	
	}
	
	public double calculator(TaskDistributionConfig record)
	{
		double cost=0;
		List<TaskDistributionConfig> list= taskDistributionConfigDao.queryByTaskDistributionId(record.getTaskDistributionId());
		if (list==null ||list.size()==0)
			return cost;
		
		double baseKM=list.get(0).getkM();
		double baseKG=list.get(0).getkG();
		double baseDistributionPrice=list.get(0).getDistributionPrice();		
		
		double kmDistributionPrice=0.0;
		for (int i = 1; i < list.size(); i++)
		{
			double currKM=list.get(i).getkM();
			double currDistributionPrice=list.get(i).getDistributionPrice();
			int currSteps=list.get(i).getSteps();
			
			if(currKM>=baseKM && record.getkM()>currKM)//获取第一个符合的值
			{		
				int currFlg=0;
				if((record.getkM()-currKM)%currSteps==0)
				{
					currFlg= (int)(record.getkM()-currKM)/currSteps;
				}
				else
				{
					currFlg= (int)(record.getkM()-currKM)/currSteps+1;
				}
				
				kmDistributionPrice=currDistributionPrice*currFlg;
				break;
			}
		}
		
		double kgDistributionPrice=0.0;
		for (int i = 1; i < list.size(); i++)
		{
			double currKG=list.get(i).getkG();
			double currDistributionPrice=list.get(i).getDistributionPrice();
			int currSteps=list.get(i).getSteps();
			
			if(currKG>=baseKG && record.getkG()>currKG)//获取第一个符合的值
			{			
				int currFlg=0;
				if((record.getkG()-currKG)%currSteps==0)
				{
					 currFlg= (int)(record.getkG()-currKG)/currSteps;
				}
				else
				{					
					currFlg= (int)(record.getkG()-currKG)/currSteps+1;
				}
				kgDistributionPrice=currDistributionPrice*currFlg;
				break;
			}
		}
		cost=baseDistributionPrice+kmDistributionPrice+kgDistributionPrice;
		return cost;
	}

	@Override
	public TaskDistributionConfig selectByPrimaryKey(int id) {
		return taskDistributionConfigDao.selectByPrimaryKey(id);	
	}
	
}
