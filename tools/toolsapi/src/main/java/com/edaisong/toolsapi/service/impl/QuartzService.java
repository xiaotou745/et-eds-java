package com.edaisong.toolsapi.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.bson.NewBSONDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.toolsapi.common.QuartzManager;
import com.edaisong.toolsapi.dao.inter.IQuartzServiceDao;
import com.edaisong.toolsapi.redis.RedisService;
import com.edaisong.toolsapi.service.inter.IQuartzService;
import com.edaisong.toolscore.consts.RedissCacheKey;
import com.edaisong.toolsentity.QuartzServiceModel;
import com.edaisong.toolsentity.common.PagedResponse;
import com.edaisong.toolsentity.domain.QuartzHttpModel;
import com.edaisong.toolsentity.req.PagedQuartzServiceReq;
import com.edaisong.toolsentity.req.QuartzUpdateReq;

@Service
public class QuartzService implements IQuartzService {
	@Autowired
	IQuartzServiceDao quartzServiceDao;
	@Autowired
	RedisService redisService;

	/**
	 * @author hailongzhao
	 * @date 20151211
	 * */
	@Override
	public int updateStatus(QuartzUpdateReq req) {
		int result= quartzServiceDao.updateStatus(req);
		if (result>0) {
			//成功更新了服务的状态，则清除相关的redis缓存
			Set<String> mSet=redisService.keys(RedissCacheKey.QuartzConfig_Key);
			for (String string : mSet) {
				redisService.remove(string,false);
			}
		}
		return result;
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

	@Override
	public QuartzHttpModel queryStartList(int appSource) {
		String redisKey=RedissCacheKey.QuartzConfig_Key+appSource;
		QuartzHttpModel redisModel=redisService.get(redisKey, QuartzHttpModel.class);
		if (redisModel==null) {
			redisModel=new QuartzHttpModel();
			List<QuartzServiceModel> listData=quartzServiceDao.queryStartList(appSource);
			redisModel.setListData(listData);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String versionCode=sdf.format(new Date());
			redisModel.setVersionCode(versionCode);
			redisService.set(redisKey, redisModel, 360, TimeUnit.DAYS);
		}

		return redisModel;
	}

	@Override
	public int delete(long id) {
		return quartzServiceDao.delete(id);
	}
}
