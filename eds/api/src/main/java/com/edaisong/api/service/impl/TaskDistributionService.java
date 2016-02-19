package com.edaisong.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edaisong.api.common.TransactionalRuntimeException;
import com.edaisong.api.dao.inter.IBiDao;
import com.edaisong.api.dao.inter.IOptLogDao;
import com.edaisong.api.dao.inter.ITaskDistributionConfigDao;
import com.edaisong.api.dao.inter.ITaskDistributionDao;
import com.edaisong.api.service.inter.ITaskDistributionConfigService;
import com.edaisong.api.service.inter.ITaskDistributionService;
import com.edaisong.core.enums.FlashPushOrderEnum;
import com.edaisong.core.enums.OptLogEnum;
import com.edaisong.core.enums.PublishOrderReturnEnum;
import com.edaisong.entity.OptLog;
import com.edaisong.entity.OrderTip;
import com.edaisong.entity.TaskDistribution;
import com.edaisong.entity.TaskDistributionConfig;
import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.PagedOrderTipReq;
import com.edaisong.entity.req.PagedTaskDistributionConfigReq;
import com.edaisong.entity.req.PagedTaskDistributionReq;
import com.edaisong.entity.req.TaskDistributionConfigReq;
import com.edaisong.entity.resp.OrderResp;
import com.edaisong.entity.resp.TaskDistributionConfigResp;

@Service
public class TaskDistributionService implements
       ITaskDistributionService {

	@Autowired
	private ITaskDistributionDao taskDistributionDao;
	
	/**
	 * @author 胡灵波
	 * @date  2016年02月18日  11:44:04
	 * 获取配送费配置
	 * */	
	@Override
	public PagedResponse<TaskDistribution> query(
			PagedTaskDistributionReq req) {
		return taskDistributionDao.query(req);		
	}


  

	
}
