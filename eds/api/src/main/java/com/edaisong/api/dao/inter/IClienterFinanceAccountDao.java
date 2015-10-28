package com.edaisong.api.dao.inter;

import com.edaisong.entity.ClienterFinanceAccount;

public interface IClienterFinanceAccountDao {
    int deleteByPrimaryKey(Integer id);

    boolean insert(ClienterFinanceAccount record);

    int insertSelective(ClienterFinanceAccount record);

    ClienterFinanceAccount selectByPrimaryKey(Integer id);

    boolean updateByPrimaryKeySelective(ClienterFinanceAccount record);

    int updateByPrimaryKey(ClienterFinanceAccount record);
    
    int getCountByClientId(int clienterId,int accountType);
}