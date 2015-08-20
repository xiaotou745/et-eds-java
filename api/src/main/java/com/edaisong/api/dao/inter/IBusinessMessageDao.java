package com.edaisong.api.dao.inter;

import com.edaisong.entity.BusinessMessage;

public interface IBusinessMessageDao {
    int deleteByPrimaryKey(Long id);

    int insert(BusinessMessage record);

    int insertSelective(BusinessMessage record);

    BusinessMessage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BusinessMessage record);

    int updateByPrimaryKey(BusinessMessage record);
    
    /**
     * 获得最新一条商户消息
     * @param businessId
     * @author pengyi
     * @date 20150818
     * @return
     */
    BusinessMessage getLatestMessage(int businessId);
}