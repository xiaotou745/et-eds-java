package com.edaisong.api.dal.dao.inter;

import java.util.List;

import com.edaisong.entity.Group;

public interface IGroupDao {
    int deleteByPrimaryKey(Long id);

    int insert(Group record);

    int insertSelective(Group record);

    Group selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Group record);

    int updateByPrimaryKey(Group record);
    
    List<Group> getGroupListByID(Long id);
}