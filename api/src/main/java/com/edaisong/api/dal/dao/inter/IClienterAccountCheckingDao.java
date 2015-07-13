package com.edaisong.api.dal.dao.inter;

import com.edaisong.entity.ClienterAccountChecking;

public interface IClienterAccountCheckingDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ClienterAccountChecking record);

    int insertSelective(ClienterAccountChecking record);

    ClienterAccountChecking selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClienterAccountChecking record);

    int updateByPrimaryKey(ClienterAccountChecking record);
}