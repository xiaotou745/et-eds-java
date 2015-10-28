package com.edaisong.api.dao.inter;

import com.edaisong.entity.GroupApiConfig;

public interface IGroupApiConfigDao {
    int deleteByPrimaryKey(Integer appid);

    int insert(GroupApiConfig record);

    int insertSelective(GroupApiConfig record);

    GroupApiConfig selectByPrimaryKey(Integer appid);

    int updateByPrimaryKeySelective(GroupApiConfig record);

    int updateByPrimaryKey(GroupApiConfig record);
}