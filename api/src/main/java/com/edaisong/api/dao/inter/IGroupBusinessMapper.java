package com.edaisong.api.dao.inter;

import com.edaisong.entity.GroupBusiness;

public interface IGroupBusinessMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GroupBusiness record);

    int insertSelective(GroupBusiness record);

    GroupBusiness selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GroupBusiness record);

    int updateByPrimaryKey(GroupBusiness record);
}