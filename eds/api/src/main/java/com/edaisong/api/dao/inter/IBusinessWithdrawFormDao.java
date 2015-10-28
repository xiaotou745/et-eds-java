package com.edaisong.api.dao.inter;

import com.edaisong.entity.BusinessWithdrawForm;
import com.edaisong.entity.domain.BusinessWithdrawFormModel;

public interface IBusinessWithdrawFormDao {
    int deleteByPrimaryKey(Long id);

    int insert(BusinessWithdrawForm record);

    int insertSelective(BusinessWithdrawForm record);

    BusinessWithdrawForm selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BusinessWithdrawForm record);

    int updateByPrimaryKey(BusinessWithdrawForm record);
    
    /**
     * 根据申请单Id获取商家提现申请单
     * @param withwardId
     * @author pengyi
     * @return
     */
    BusinessWithdrawFormModel getBusinessWithdrawListById(String withwardId);
}