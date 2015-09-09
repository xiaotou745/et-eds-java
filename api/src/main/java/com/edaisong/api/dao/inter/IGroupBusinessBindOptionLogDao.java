package com.edaisong.api.dao.inter;

import com.edaisong.entity.GroupBusinessBindOptionLog;

public interface IGroupBusinessBindOptionLogDao {
    int deleteByPrimaryKey(Integer id);

    int insert(GroupBusinessBindOptionLog record);

    int insertSelective(GroupBusinessBindOptionLog record);

    GroupBusinessBindOptionLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GroupBusinessBindOptionLog record);

    int updateByPrimaryKey(GroupBusinessBindOptionLog record);
}