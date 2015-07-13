package com.edaisong.api.dal.dao.inter;

import com.edaisong.entity.ClienterFinanceAccount;

public interface IClienterFinanceAccountDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ClienterFinanceAccount record);

    int insertSelective(ClienterFinanceAccount record);

    ClienterFinanceAccount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClienterFinanceAccount record);

    int updateByPrimaryKey(ClienterFinanceAccount record);
}