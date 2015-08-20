package com.edaisong.api.dao.inter;

import java.util.List;

import com.edaisong.entity.BusinessBalanceRecord;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.PagedTransDetailReq;

public interface IBusinessBalanceRecordDao {
    int insert(BusinessBalanceRecord record);
    /**
     *商家中心 -交易明细分页列表
     *2015年8月4日13:02:01
     *茹化肖
     * */
    PagedResponse<BusinessBalanceRecord> getTransDetailList(PagedTransDetailReq par);
}