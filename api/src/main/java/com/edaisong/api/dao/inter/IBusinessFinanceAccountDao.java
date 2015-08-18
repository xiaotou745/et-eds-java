package com.edaisong.api.dao.inter;

import com.edaisong.entity.BusinessFinanceAccount;

public interface IBusinessFinanceAccountDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BusinessFinanceAccount record);

    BusinessFinanceAccount getDetailByBusinesID(Integer businessID);
}

