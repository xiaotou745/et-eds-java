package com.edaisong.api.dao.inter;

import com.edaisong.entity.ClienterForzen;

public interface IClienterForzenDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ClienterForzen record);

    int insertSelective(ClienterForzen record);

    ClienterForzen selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClienterForzen record);

    int updateByPrimaryKey(ClienterForzen record);
}