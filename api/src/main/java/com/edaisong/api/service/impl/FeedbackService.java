package com.edaisong.api.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IFeedbackDao;
import com.edaisong.api.dao.inter.IGlobalConfigDao;
import com.edaisong.api.service.inter.IFeedbackService;
import com.edaisong.api.service.inter.IGlobalConfigService;
import com.edaisong.core.cache.redis.RedisService;
import com.edaisong.core.consts.RedissCacheKey;
import com.edaisong.core.enums.UserType;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.core.util.PropertyUtils;
import com.edaisong.core.util.StringUtils;
import com.edaisong.entity.Feedback;
import com.edaisong.entity.GlobalConfig;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.FeedbackModel;
import com.edaisong.entity.domain.GlobalConfigModel;
import com.edaisong.entity.req.ConfigSaveReq;
import com.edaisong.entity.req.PagedFeedbackReq;
import com.edaisong.entity.req.PagedGlobalConfigReq;
/*
 * 管理员工具 
 * */
@Service
public class FeedbackService implements IFeedbackService {
	@Autowired
	private IFeedbackDao feedbackDao ;

	@Override
	public int  addByData(Feedback record) 
	{
		return feedbackDao.insert(record);
	}
	
	@Override
	public PagedResponse<FeedbackModel> query(PagedFeedbackReq req)  throws ParseException{
		
		if (!StringUtils.isEmpty(req.getEndTime()))		
		{		
			Date finalDt = ParseHelper.ToDate(req.getEndTime(), "yyyy-MM-dd");
			finalDt = ParseHelper.plusDate(finalDt, 2, 1);
			req.setEndTime(ParseHelper.ToDateString(finalDt, "yyyy-MM-dd"));
		}
		
		return feedbackDao.query(req);
	}		


}