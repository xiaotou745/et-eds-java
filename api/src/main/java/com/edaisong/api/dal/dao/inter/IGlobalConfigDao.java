package com.edaisong.api.dal.dao.inter;

import com.edaisong.entity.GlobalConfig;

public interface IGlobalConfigDao {
    int deleteByPrimaryKey(Integer id);

    int insert(GlobalConfig record);

    int insertSelective(GlobalConfig record);

    GlobalConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GlobalConfig record);

    int updateByPrimaryKey(GlobalConfig record);
}