package com.edaisong.api.dao.inter;

import com.edaisong.entity.ClienterForzenLog;

public interface IClienterForzenLogDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ClienterForzenLog record);

    int insertSelective(ClienterForzenLog record);

    ClienterForzenLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClienterForzenLog record);

    int updateByPrimaryKey(ClienterForzenLog record);
}