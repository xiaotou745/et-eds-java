package com.edaisong.api.dal.dao.inter;

import java.util.List;

import com.edaisong.entity.BusinessBalanceRecord;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.TransDetailReq;

public interface IBusinessBalanceRecordDao {
    int deleteByPrimaryKey(Long id);

    int insert(BusinessBalanceRecord record);

    int insertSelective(BusinessBalanceRecord record);

    BusinessBalanceRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BusinessBalanceRecord record);

    int updateByPrimaryKey(BusinessBalanceRecord record);
    /**
     *商家中心 -交易明细分页列表
     *2015年8月4日13:02:01
     *茹化肖
     * */
    PagedResponse<BusinessBalanceRecord> getTransDetailList(TransDetailReq par);
    
    
    
}