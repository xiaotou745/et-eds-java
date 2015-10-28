package com.edaisong.api.service.impl;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IFeedbackDao;
import com.edaisong.api.service.inter.IFeedbackService;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.core.util.StringUtils;
import com.edaisong.entity.Feedback;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.FeedbackModel;
import com.edaisong.entity.req.PagedFeedbackReq;
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