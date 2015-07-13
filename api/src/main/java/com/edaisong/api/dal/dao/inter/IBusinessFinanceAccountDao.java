package com.edaisong.api.dal.dao.inter;

import com.edaisong.entity.BusinessFinanceAccount;

public interface IBusinessFinanceAccountDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BusinessFinanceAccount record);

    int insertSelective(BusinessFinanceAccount record);

    BusinessFinanceAccount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BusinessFinanceAccount record);

    int updateByPrimaryKey(BusinessFinanceAccount record);
}