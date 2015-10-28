package com.edaisong.api.dao.inter;

import com.edaisong.entity.Channel_del;

public interface IChannel_delDao {
    int deleteByPrimaryKey(Long id);

    int insert(Channel_del record);

    int insertSelective(Channel_del record);

    Channel_del selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Channel_del record);

    int updateByPrimaryKey(Channel_del record);
}