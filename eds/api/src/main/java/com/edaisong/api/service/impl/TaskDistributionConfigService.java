package com.edaisong.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edaisong.api.common.TransactionalRuntimeException;
import com.edaisong.api.dao.inter.IBiDao;
import com.edaisong.api.dao.inter.IOptLogDao;
import com.edaisong.api.dao.inter.ITaskDistributionConfigDao;
import com.edaisong.api.service.inter.ITaskDistributionConfigService;
import com.edaisong.core.enums.OptLogEnum;
import com.edaisong.entity.OptLog;
import com.edaisong.entity.TaskDistributionConfig;
import com.edaisong.entity.req.TaskDistributionConfigReq;

@Service
public class TaskDistributionConfigService implements
		ITaskDistributionConfigService {

	@Autowired
	private ITaskDistributionConfigDao taskDistributionConfigDao;

	@Autowired
	private IOptLogDao optLogDao;

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
				conf.setkG(req.getMastetKG());
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

}
