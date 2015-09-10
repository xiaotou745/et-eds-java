package com.edaisong.api.dao.inter;

import com.edaisong.entity.Feedback;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.FeedbackModel;
import com.edaisong.entity.req.PagedFeedbackReq;

public interface IFeedbackDao {
    int deleteByPrimaryKey(Long id);

    int insert(Feedback record);

    int insertSelective(Feedback record);

    Feedback selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Feedback record);

    int updateByPrimaryKey(Feedback record);
    
    PagedResponse<FeedbackModel> query(PagedFeedbackReq req); 
}