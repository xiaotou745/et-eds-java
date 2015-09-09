package com.edaisong.api.dao.inter;

import com.edaisong.entity.GroupBusinessLog;

public interface IGroupBusinessLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GroupBusinessLog record);

    int insertSelective(GroupBusinessLog record);

    GroupBusinessLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GroupBusinessLog record);

    int updateByPrimaryKey(GroupBusinessLog record);
}