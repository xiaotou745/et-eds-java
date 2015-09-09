package com.edaisong.api.dao.inter;

import com.edaisong.entity.GroupBusinessRelation;

public interface IGroupBusinessRelationDao {
    int deleteByPrimaryKey(Integer id);

    int insert(GroupBusinessRelation record);

    int insertSelective(GroupBusinessRelation record);

    GroupBusinessRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GroupBusinessRelation record);

    int updateByPrimaryKey(GroupBusinessRelation record);
}