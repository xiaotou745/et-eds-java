package com.edaisong.api.dao.inter;

import com.edaisong.entity.ClienterBindOptionLog;

public interface IClienterBindOptionLogDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ClienterBindOptionLog record);

    int insertSelective(ClienterBindOptionLog record);

    ClienterBindOptionLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClienterBindOptionLog record);

    int updateByPrimaryKey(ClienterBindOptionLog record);
}