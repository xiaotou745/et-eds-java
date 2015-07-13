package com.edaisong.api.dal.dao.inter;

import com.edaisong.entity.ClienterMessage;

public interface IClienterMessageDao {
    int deleteByPrimaryKey(Long id);

    int insert(ClienterMessage record);

    int insertSelective(ClienterMessage record);

    ClienterMessage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClienterMessage record);

    int updateByPrimaryKey(ClienterMessage record);
}