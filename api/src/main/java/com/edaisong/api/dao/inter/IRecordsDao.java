package com.edaisong.api.dao.inter;

import com.edaisong.entity.Records;

public interface IRecordsDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Records record);

    int insertSelective(Records record);

    Records selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Records record);

    int updateByPrimaryKey(Records record);
}