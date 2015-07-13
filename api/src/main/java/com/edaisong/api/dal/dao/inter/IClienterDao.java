package com.edaisong.api.dal.dao.inter;

import com.edaisong.entity.Clienter;

public interface IClienterDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Clienter record);

    int insertSelective(Clienter record);

    Clienter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Clienter record);

    int updateByPrimaryKey(Clienter record);
}