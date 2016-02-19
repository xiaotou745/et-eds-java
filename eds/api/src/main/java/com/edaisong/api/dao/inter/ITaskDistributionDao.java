package com.edaisong.api.dao.inter;

import java.util.List;

import com.edaisong.entity.OrderTip;
import com.edaisong.entity.TaskDistribution;
import com.edaisong.entity.TaskDistributionConfig;
import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.PagedOrderTipReq;
import com.edaisong.entity.req.PagedTaskDistributionConfigReq;
import com.edaisong.entity.req.PagedTaskDistributionReq;
import com.edaisong.entity.req.TaskDistributionConfigReq;
import com.edaisong.entity.resp.TaskDistributionConfigResp;

public interface ITaskDistributionDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TaskDistribution record);

    int insertSelective(TaskDistribution record);

    TaskDistribution selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TaskDistribution record);

    int updateByPrimaryKey(TaskDistribution record);    

    PagedResponse<TaskDistribution> query(PagedTaskDistributionReq req);

}
