package com.edaisong.api.dao.inter;

import com.edaisong.entity.ClienterLocation;

public interface IClienterLocationDao {
    int deleteByPrimaryKey(Long id);

    int insert(ClienterLocation record);

    int insertSelective(ClienterLocation record);

    ClienterLocation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClienterLocation record);

    int updateByPrimaryKey(ClienterLocation record);
}