package com.edaisong.api.dao.inter;

import com.edaisong.entity.BusinessFinanceAccount;
import com.edaisong.entity.domain.BusinessRechargeLog;

public interface IBusinessFinanceAccountDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BusinessFinanceAccount record);

    BusinessFinanceAccount getDetailByBusinesID(Integer businessID);
    
    boolean businessRecharge(BusinessRechargeLog log);
}

