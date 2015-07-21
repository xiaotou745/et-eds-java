package com.edaisong.api.dal.dao.inter;

import java.util.List;

import com.edaisong.entity.Message;

public interface IMessageDao {
    int deleteByPrimaryKey(Long id);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);
    
    List<Message> getMessageList();
}