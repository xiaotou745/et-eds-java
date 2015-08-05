package com.edaisong.api.dal.dao.inter;

import com.edaisong.entity.GlobalConfigLog;

public interface IGlobalConfigLogDao {
    int deleteByPrimaryKey(Integer id);

    int insert(GlobalConfigLog record);

    int insertSelective(GlobalConfigLog record);

    GlobalConfigLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GlobalConfigLog record);

    int updateByPrimaryKey(GlobalConfigLog record);
}