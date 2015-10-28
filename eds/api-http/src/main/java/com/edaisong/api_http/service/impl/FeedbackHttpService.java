package com.edaisong.api_http.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.service.inter.IFeedbackService;
import com.edaisong.api_http.service.inter.IFeedbackhttpService;
import com.edaisong.core.enums.FeedbackState;
import com.edaisong.core.enums.FeedbackType;
import com.edaisong.core.enums.SystemState;
import com.edaisong.core.util.EnumHelper;
import com.edaisong.entity.Feedback;
import com.edaisong.entity.common.EnumRecord;
import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.resp.FeedbackResp;

@Service
public class FeedbackHttpService implements IFeedbackhttpService {

	@Autowired
	private IFeedbackService  feedbackService;

	/**
	 * B端商户增加意见反馈
	 * 
	 * @author 胡灵波
	 * @date 2015年9月10日 16:35:01
	 * @param data
	 * @return
	 */
	@Override
	public HttpResultModel<FeedbackResp> feedbackB(Feedback record) {
		
		HttpResultModel<FeedbackResp> returnmodel = new HttpResultModel<FeedbackResp>();
		record.setUsertype((short)1);
		int row= feedbackService.addByData(record);
		if(row>0)
		{
			returnmodel.setStatus(FeedbackState.Success.value());
			returnmodel.setMessage(FeedbackState.getEnum(FeedbackState.Success.value()).desc() );			
     	}
		else
		{
			returnmodel.setStatus(FeedbackState.SystemError.value());
			returnmodel.setMessage(FeedbackState.getEnum(FeedbackState.SystemError.value()).desc() );
		}
		  
		return returnmodel;
	}

	/**
	 * B端门店增加意见反馈
	 * 
	 * @author 胡灵波
	 * @date 2015年9月10日 16:35:01
	 * @param data
	 * @return
	 */
	@Override
	public HttpResultModel<FeedbackResp> feedbackC(Feedback record) {
		
		HttpResultModel<FeedbackResp> returnmodel = new HttpResultModel<FeedbackResp>();
		record.setUsertype((short)2);
		int row= feedbackService.addByData(record);
		if(row>0)
		{
			returnmodel.setStatus(FeedbackState.Success.value());
			returnmodel.setMessage(FeedbackState.getEnum(FeedbackState.Success.value()).desc() );			
     	}
		else
		{
			returnmodel.setStatus(FeedbackState.SystemError.value());
			returnmodel.setMessage(FeedbackState.getEnum(FeedbackState.SystemError.value()).desc() );
		}
		  
		return returnmodel;
	}
	
	/**
	 * 意见反馈类型列表
	 * 
	 * @author 胡灵波
	 * @date 2015年9月10日 21:45:07
	 * @param data
	 * @return
	 */
	@Override
	public List<EnumRecord> getFeedbackType() {
		List<EnumRecord> list=new ArrayList<EnumRecord>();
		
		List<FeedbackType> listFeedbackType=EnumHelper.GetEnumItems(FeedbackType.class);
		for(FeedbackType t:listFeedbackType)
		{			
			EnumRecord eRecord=new EnumRecord();
			eRecord.setCode(t.value());
			eRecord.setDesc(t.desc());
			list.add(eRecord);		
		}
		
		return  list;

	}

}
