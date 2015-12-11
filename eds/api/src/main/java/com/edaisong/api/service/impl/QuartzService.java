package com.edaisong.api.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IQuartzServiceDao;
import com.edaisong.api.service.inter.IQuartzService;
import com.edaisong.core.quartz.QuartzManager;
import com.edaisong.core.quartz.ChildJob;
import com.edaisong.core.util.SpringBeanHelper;
import com.edaisong.entity.QuartzServiceModel;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.PagedQuartzServiceReq;
import com.edaisong.entity.req.QuartzUpdateReq;

@Service
public class QuartzService implements IQuartzService {
	@Autowired
	IQuartzServiceDao quartzServiceDao;

	/**
	 * @author hailongzhao
	 * @date 20151211
	 * */
	@Override
	public int mainJob(QuartzUpdateReq req) {
		QuartzServiceModel item=quartzServiceDao.selectById(req.getId());
		String jobName = item.getBeanName();
		if(req.getStatus()==1){
			try {
				SpringBeanHelper.getCustomBean(item.getBeanName());
			} catch (Exception e) {
				return -1;
			}
			Date firstFireTime=QuartzManager.getFirstFireTime(item.getExecTime());
			req.setFirstFireTime(firstFireTime);
			QuartzManager.addJob(jobName, ChildJob.class, item.getExecTime());// 如果库里是开启，本地没开启就新增服务
		}
		else {
			//停止
			QuartzManager.removeJob(jobName);
		}
		return quartzServiceDao.updateStatus(req);
	}

	@Override
	public PagedResponse<QuartzServiceModel> pagedQuery(
			PagedQuartzServiceReq req) {
		return quartzServiceDao.pagedQuery(req);
	}

	@Override
	public int insert(QuartzServiceModel record) {
		return quartzServiceDao.insert(record);
	}

	@Override
	public int update(QuartzServiceModel record) {
		return quartzServiceDao.update(record);
	}

	@Override
	public boolean checkCron(String cron) {
		return QuartzManager.isValidExpression(cron);
	}
}
