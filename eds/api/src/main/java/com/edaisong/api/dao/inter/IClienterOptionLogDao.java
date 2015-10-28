package com.edaisong.api.dao.inter;

import com.edaisong.entity.ClienterOptionLog;

public interface IClienterOptionLogDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ClienterOptionLog record);

    int insertSelective(ClienterOptionLog record);

    ClienterOptionLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClienterOptionLog record);

    int updateByPrimaryKey(ClienterOptionLog record);
}