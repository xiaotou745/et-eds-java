package com.edaisong.api.dao.inter;

import com.edaisong.entity.ClienterMessage;

public interface IClienterMessageDao {
    int deleteByPrimaryKey(Long id);

    int insert(ClienterMessage record);

    int insertSelective(ClienterMessage record);

    ClienterMessage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClienterMessage record);

    int updateByPrimaryKey(ClienterMessage record);
	/**
	 * 获取最新消息
	 * @author CaoHeYang
	 * @param clienterId
	 * @date 20150909
	 * @return
	 */
	ClienterMessage getLatestMessage(int clienterId);
}